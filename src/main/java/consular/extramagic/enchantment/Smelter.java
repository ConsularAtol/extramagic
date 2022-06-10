package consular.extramagic.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

public class Smelter extends Enchantment {

    public Smelter() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    
    @Override
    public int getMinPower(int level){
        return 12;
    }

    @Override
    public int getMaxLevel(){
        return 1;
    }

    @Override
    public boolean canAccept(Enchantment other){
        if(other == Enchantments.FORTUNE || other == Enchantments.SILK_TOUCH){
            return false;
        }
        else{
            return true;
        }
    }
}
