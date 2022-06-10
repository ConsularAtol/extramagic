package consular.extramagic.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Consume extends Enchantment {

    public Consume() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    
    @Override
    public int getMinPower(int level){
        return 7;
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(user.getRandom().nextInt(100) <= (10 * level)) {
            if(target instanceof LivingEntity) {
                ((LivingEntity) user).addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 10, 0, true, false));
            }
 
            super.onTargetDamaged(user, target, level);
        }
    }
}
