package consular.extramagic.mixin;

import org.spongepowered.asm.mixin.Mixin;

import consular.extramagic.ExtraMagic;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(HoeItem.class)
public class HoeItemMixin extends MiningToolItem{
    protected HoeItemMixin(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super((float)attackDamage, attackSpeed, material, BlockTags.HOE_MINEABLE, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        int damage = 1;
        if (!world.isClient) {
            if (EnchantmentHelper.getLevel(ExtraMagic.HARVEST, stack) >= 1) {
                if (state.getBlock() instanceof CropBlock crop) {
                    boolean planted = false;
                    for (ItemStack dropStack : CropBlock.getDroppedStacks(state, (ServerWorld) world, pos, null)) {
                        if (!planted) {
                            dropStack.decrement(dropStack.getCount());
                            world.setBlockState(pos, crop.withAge(0));
                            planted = true;
                        }
                    }
                    damage = 2;
                }
            }
            if (state.getHardness(world, pos) != 0.0F) {
                stack.damage(damage, miner, (e) -> {
                    e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                });
            }
        }
        return true;
    }
}
