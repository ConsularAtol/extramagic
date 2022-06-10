package consular.extramagic.registry;

import consular.extramagic.ExtraMagic;
import consular.extramagic.effect.FrostbiteEffect;
import consular.extramagic.effect.MolotovEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {
    public static final StatusEffect FROSTBITE = new FrostbiteEffect();
    public static final StatusEffect MOLOTOV = new MolotovEffect();

    public static void registerEffects(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(ExtraMagic.MODID, "frostbite"), FROSTBITE);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(ExtraMagic.MODID, "molotov"), MOLOTOV);
    }
}
