package consular.extramagic;

import consular.extramagic.enchantment.Consume;
import consular.extramagic.enchantment.FrostAspect;
import consular.extramagic.enchantment.Harvest;
import consular.extramagic.enchantment.PoisonAspect;
import consular.extramagic.enchantment.Smelter;
import consular.extramagic.registry.ModEffects;
import consular.extramagic.registry.ModPotions;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExtraMagic implements ModInitializer {

	public static final String MODID = "extramagic";
	
	public static Enchantment FROST_ASPECT = Registry.register(
        Registry.ENCHANTMENT,
        new Identifier(ExtraMagic.MODID, "frost_aspect"),
        new FrostAspect()
    );

	public static Enchantment CONSUME = Registry.register(
        Registry.ENCHANTMENT,
        new Identifier(ExtraMagic.MODID, "consume"),
        new Consume()
    );

	public static Enchantment HARVEST = Registry.register(
        Registry.ENCHANTMENT,
        new Identifier(ExtraMagic.MODID, "harvest"),
        new Harvest()
    );

	public static Enchantment SMELTER = Registry.register(
        Registry.ENCHANTMENT,
        new Identifier(ExtraMagic.MODID, "smelter"),
        new Smelter()
    );

	public static Enchantment POISON_ASPECT = Registry.register(
        Registry.ENCHANTMENT,
        new Identifier(ExtraMagic.MODID, "poison_aspect"),
        new PoisonAspect()
    );

	@Override
	public void onInitialize() {
		ModPotions.init();
		ModEffects.registerEffects();
	}
}
