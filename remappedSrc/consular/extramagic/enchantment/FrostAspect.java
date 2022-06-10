package consular.extramagic.enchantment;

import consular.extramagic.ExtraMagic;
import consular.extramagic.registry.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class FrostAspect extends Enchantment {

    public FrostAspect() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    
    @Override
    public int getMinPower(int level){
        return 7;
    }

    @Override
    public int getMaxLevel(){
        return 2;
    }

    @Override
    public boolean canAccept(Enchantment other){
        if(other == Enchantments.FIRE_ASPECT || other == ExtraMagic.POISON_ASPECT){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.FROSTBITE, 20 * (10 * level), level - 1));
            ((LivingEntity) target).extinguish();
        }
 
        super.onTargetDamaged(user, target, level);
    }
}
