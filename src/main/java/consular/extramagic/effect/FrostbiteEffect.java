package consular.extramagic.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FrostbiteEffect extends StatusEffect {
    public FrostbiteEffect() {
      super(
        StatusEffectCategory.HARMFUL,
        0x00fff7);
    }
   
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
      return true;
    }
   
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
      if (entity instanceof LivingEntity) {
        ((LivingEntity) entity).setFrozenTicks((1 * (amplifier + 4)) + entity.getFrozenTicks());
      }
    }
  }