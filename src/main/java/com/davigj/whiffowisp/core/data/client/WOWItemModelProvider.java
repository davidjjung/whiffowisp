package com.davigj.whiffowisp.core.data.client;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import com.davigj.whiffowisp.core.registry.WOWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WOWItemModelProvider extends ItemModelProvider {
    public WOWItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, WhiffOWisp.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        this.generatedItem((ItemLike) WOWItems.NETHERWAX.get());
        this.generatedItem((ItemLike) WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.HOMESICK_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.SOFT_BLANKET_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.VANILLA_BUNNY_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.FOREST_HAZE_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.MIDSUMMER_NIGHT_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.AUTUMN_WREATH_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.PINK_SANDS_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.TARNATION_SCENTED_CANDLE.get());
        this.generatedItem((ItemLike) WOWBlocks.DAILY_SPECIAL_SCENTED_CANDLE.get());
    }

    private void generatedItem(ItemLike item) {
        ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
        ((ItemModelBuilder)this.withExistingParent(itemName.getPath(), "item/" + "generated")).texture("layer0", new ResourceLocation(this.modid, "item/" + itemName.getPath()));
    }
}
