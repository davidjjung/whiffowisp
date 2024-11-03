package com.davigj.whiffowisp.core.data.server;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWItems;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class WOWRecipeProvider extends BlueprintRecipeProvider {
    public WOWRecipeProvider(PackOutput output) {
        super(WhiffOWisp.MOD_ID, output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        cook(consumer, (ItemLike) Items.NETHER_WART, (ItemLike)WOWItems.NETHERWAX.get());
    }

    public static void cook(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input),
                RecipeCategory.MISC, output, 0.35F, 200)
                .unlockedBy(getHasName(input), has(input)).save(consumer);
    }
}
