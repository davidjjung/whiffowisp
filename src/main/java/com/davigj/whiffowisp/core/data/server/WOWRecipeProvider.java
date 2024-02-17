package com.davigj.whiffowisp.core.data.server;

import com.davigj.whiffowisp.core.registry.WOWItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class WOWRecipeProvider extends RecipeProvider {
    public WOWRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        cook(consumer, (ItemLike) Items.NETHER_WART, (ItemLike)WOWItems.NETHERWAX.get());
    }

    public static void cook(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemLike[]{input}), output, 0.35F, 200).unlockedBy(getHasName(input), has(input)).save(consumer);
    }
}
