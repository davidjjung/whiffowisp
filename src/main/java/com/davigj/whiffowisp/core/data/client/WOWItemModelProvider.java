package com.davigj.whiffowisp.core.data.client;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WOWItemModelProvider extends ItemModelProvider {
    public WOWItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WhiffOWisp.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.generatedItem((ItemLike) WOWItems.NETHERWAX.get());
    }

    private void generatedItem(ItemLike item) {
        ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
        ((ItemModelBuilder)this.withExistingParent(itemName.getPath(), "item/" + "generated")).texture("layer0", new ResourceLocation(this.modid, "item/" + itemName.getPath()));
    }
}
