package consular.extramagic.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MolotovEffect extends StatusEffect {
    public MolotovEffect() {
      super(
        StatusEffectCategory.HARMFUL,
        0xfaa200);
    }
   
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
      return true;
    }
   
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
      if (entity instanceof LivingEntity) {
        ((LivingEntity) entity).setOnFireFor(10 * (amplifier + 1));
      }
    }
  }