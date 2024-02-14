package com.davigj.whiffowisp.core.data.server;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class WOWBlockTagsProvider extends BlockTagsProvider {
    public WOWBlockTagsProvider(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, WhiffOWisp.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        this.tag(BlockTags.CANDLES).add(
                WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get(),
                WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get(),
                WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get(),
                WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get(),
                WOWBlocks.SOFT_BLANKET_SCENTED_CANDLE.get(),
                WOWBlocks.FOREST_HAZE_SCENTED_CANDLE.get(),
                WOWBlocks.VANILLA_BUNNY_SCENTED_CANDLE.get(),
                WOWBlocks.PINK_SANDS_SCENTED_CANDLE.get(),
                WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get(),
                WOWBlocks.HOMESICK_SCENTED_CANDLE.get(),
                WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get(),
                WOWBlocks.MIDSUMMER_NIGHT_SCENTED_CANDLE.get(),
                WOWBlocks.TARNATION_SCENTED_CANDLE.get(),
                WOWBlocks.AUTUMN_WREATH_SCENTED_CANDLE.get(),
                WOWBlocks.DAILY_SPECIAL_SCENTED_CANDLE.get()
        );
    }

}
