package consular.extramagic.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import consular.extramagic.ExtraMagic;
import consular.extramagic.mixin.BrewingRecipeRegistryAccessor;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModPotions {
    
    private ModPotions() {}
	
	private static final Collection<RecipeToInit> RECIPES = new ArrayList<RecipeToInit>();
	
	public static final Potion GLOWING = register("glowing", new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600)), Items.GLOW_INK_SAC, Potions.AWKWARD);

	public static final Potion ABSORPTION = register("absorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 2 * 60 * 20, 1)), Items.GOLDEN_APPLE, Potions.AWKWARD);
	public static final Potion LONG_ABSORPTION = register("long_absorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 4 * 60 * 20, 1)), null, null);
	public static final Potion STRONG_ABSORPTION = register("strong_absorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 1 * 60 * 20, 3)), null, null);

	public static final Potion HEALTH_BOOST = register("health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2 * 60 * 20)), Items.GLISTERING_MELON_SLICE, ABSORPTION);
	public static final Potion LONG_HEALTH_BOOST = register("long_health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 4 * 60 * 20)), null, null);
	public static final Potion STRONG_HEALTH_BOOST = register("strong_health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 60 * 20, 1)), null, null);

	public static final Potion LEVITATION = register("levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 60 * 20)), Items.SHULKER_SHELL, Potions.AWKWARD);

	public static final Potion FROSTBITE = register("frostbite", new Potion(new StatusEffectInstance(ModEffects.FROSTBITE, 2 * 60 * 20)), Items.BLUE_ICE, Potions.AWKWARD);
	public static final Potion LONG_FROSTBITE = register("long_frostbite", new Potion(new StatusEffectInstance(ModEffects.FROSTBITE, 4 * 60 * 20)), null, null);
	public static final Potion STRONG_FROSTBITE = register("strong_frostbite", new Potion(new StatusEffectInstance(ModEffects.FROSTBITE, 1 * 60 * 20, 1)), null, null);

	public static final Potion MOLOTOV = register("molotov", new Potion(new StatusEffectInstance(ModEffects.MOLOTOV, 2 * 60 * 20)), Items.FLINT_AND_STEEL, Potions.AWKWARD);
	public static final Potion LONG_MOLOTOV = register("long_molotov", new Potion(new StatusEffectInstance(ModEffects.MOLOTOV, 4 * 60 * 20)), null, null);
	public static final Potion STRONG_MOLOTOV = register("strong_molotov", new Potion(new StatusEffectInstance(ModEffects.MOLOTOV, 1 * 60 * 20, 1)), null, null);
	
	public static void init() {
		RECIPES.forEach(RecipeToInit::init);
	}
	
	private static Potion register(String id, Potion potion, Item ingredient, Potion from) {
		RECIPES.add(new RecipeToInit(from, ingredient, potion));
		return Registry.register(Registry.POTION, new Identifier(ExtraMagic.MODID, id), potion);
	}
	
	private static void mapPotions(Potion in, Item ingredient, Potion result) {
		Identifier potionInId = Registry.POTION.getId(in);
		Identifier potionOutId = Registry.POTION.getId(result);
		Optional<Potion> inLong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "long_" + potionInId.getPath()));
		Optional<Potion> inStrong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "strong_" + potionInId.getPath()));
		Optional<Potion> outLong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "long_" + potionOutId.getPath()));
		Optional<Potion> outStrong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "strong_" + potionOutId.getPath()));
		if(outLong.isPresent() && inLong.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inLong.get(), ingredient, outLong.get());
		}
		if(outStrong.isPresent() && inStrong.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inStrong.get(), ingredient, outStrong.get());
		}
		BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(in, ingredient, result);
	}
	
	private static void variantRecipes(Potion potion) {
		Identifier id = Registry.POTION.getId(potion);
		Optional<Potion> lengthy = Registry.POTION.getOrEmpty(new Identifier(id.getNamespace(), "long_" + id.getPath()));
		Optional<Potion> strong = Registry.POTION.getOrEmpty(new Identifier(id.getNamespace(), "strong_" + id.getPath()));
		if(lengthy.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.REDSTONE, lengthy.get());
		}
		if(strong.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.GLOWSTONE_DUST, strong.get());
		}
	}
	
	private static class RecipeToInit {
		
		private final Potion in;
		private final Item ingredient;
		private final Potion result;
		
		private RecipeToInit(Potion in, Item ingredient, Potion result) {
			this.in = in;
			this.ingredient = ingredient;
			this.result = result;
		}
		
		public void init() {
			mapPotions(in, ingredient, result);
			variantRecipes(result);
		}
	}
}