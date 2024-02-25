package com.davigj.whiffowisp.core.data.client;

import com.davigj.whiffowisp.common.block.scented_candles.ScentedCandleBlock;
import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.other.WOWConstants;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class WOWBlockStateProvider extends BlockStateProvider {
    public WOWBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, WhiffOWisp.MOD_ID, exFileHelper);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    @Override
    protected void registerStatesAndModels() {
        this.scentedCandle((ScentedCandleBlock) WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get(), "whiffowisp:block/red_redemption_scented_candle");
        this.scentedCandle((ScentedCandleBlock) WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get(), "whiffowisp:block/caravan_spice_scented_candle");
        this.scentedCandle((ScentedCandleBlock) WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get(), "whiffowisp:block/fireside_spat_scented_candle");
        this.scentedCandle((ScentedCandleBlock) WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get(), "whiffowisp:block/seafaring_dream_scented_candle");
        this.scentedCandle((ScentedCandleBlock) WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get(), "whiffowisp:block/arts_and_crafts_scented_candle");
        this.scentedCandle((ScentedCandleBlock) WOWBlocks.HOMESICK_SCENTED_CANDLE.get(), "whiffowisp:block/homesick_scented_candle");
        this.scentedCandle((ScentedCandleBlock) WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get(), "whiffowisp:block/distant_song_scented_candle");

//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.SOFT_BLANKET_SCENTED_CANDLE.get(), "whiffowisp:block/soft_blanket_scented_candle");
//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.VANILLA_BUNNY_SCENTED_CANDLE.get(), "whiffowisp:block/vanilla_bunny_scented_candle");
//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.FOREST_HAZE_SCENTED_CANDLE.get(), "whiffowisp:block/forest_haze_scented_candle");
//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.MIDSUMMER_NIGHT_SCENTED_CANDLE.get(), "whiffowisp:block/midsummer_night_scented_candle");
//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.AUTUMN_WREATH_SCENTED_CANDLE.get(), "whiffowisp:block/autumn_wreath_scented_candle");
//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.PINK_SANDS_SCENTED_CANDLE.get(), "whiffowisp:block/pink_sands_scented_candle");
//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.TARNATION_SCENTED_CANDLE.get(), "whiffowisp:block/tarnation_scented_candle");
//        this.scentedCandle((ScentedCandleBlock) WOWBlocks.DAILY_SPECIAL_SCENTED_CANDLE.get(), "whiffowisp:block/daily_special_scented_candle");
    }
    private void scentedCandle(ScentedCandleBlock block, String candleName) {
        ModelFile smallCandle = models().withExistingParent(name(block) + "_small", mcLoc("whiffowisp:block/small_scented_candle"))
                .texture("all", new ResourceLocation(candleName + "_small")).texture("particle", new ResourceLocation(candleName + "_small"));
        ModelFile mediumCandle = models().withExistingParent(name(block) + "_medium", mcLoc("whiffowisp:block/medium_scented_candle"))
                .texture("all", new ResourceLocation(candleName + "_medium")).texture("particle", new ResourceLocation(candleName + "_medium"));
        ModelFile bigCandle = models().withExistingParent(name(block) + "_big", mcLoc("whiffowisp:block/big_scented_candle"))
                .texture("all", new ResourceLocation(candleName + "_big")).texture("particle", new ResourceLocation(candleName + "_big"));
        ModelFile largeCandle = models().withExistingParent(name(block) + "_large", mcLoc("whiffowisp:block/large_scented_candle"))
                .texture("all", new ResourceLocation(candleName + "_large")).texture("particle", new ResourceLocation(candleName + "_large"));
        scentedCandle(block, smallCandle, mediumCandle, bigCandle, largeCandle);
    }

    public void scentedCandle(CandleBlock block, ModelFile one, ModelFile two, ModelFile three, ModelFile four) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            ModelFile model = one;
            switch (state.getValue(CandleBlock.CANDLES)) {
                case 2 -> model = two;
                case 3 -> model = three;
                case 4 -> model = four;
            }
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        }, CandleBlock.WATERLOGGED, WOWConstants.BOTTLED, AbstractCandleBlock.LIT, WOWConstants.TRIMMED);
    }

    private void trimmedCandle(ScentedCandleBlock block, ResourceLocation candleTexture, ResourceLocation litCandleTexture) {
        ModelFile trimmedCandle = models().withExistingParent(name(block), mcLoc("minecraft:block/template_candle"))
                .texture("all", candleTexture).texture("particle", candleTexture);
        ModelFile trimmedTwoCandle = models().withExistingParent(name(block) + "_two_candles", mcLoc("minecraft:block/template_two_candles"))
                .texture("all", candleTexture).texture("particle", candleTexture);
        ModelFile trimmedThreeCandle = models().withExistingParent(name(block) + "_three_candles", mcLoc("minecraft:block/template_three_candles"))
                .texture("all", candleTexture).texture("particle", candleTexture);
        ModelFile trimmedFourCandle = models().withExistingParent(name(block) + "_four_candles", mcLoc("minecraft:block/template_four_candles"))
                .texture("all", candleTexture).texture("particle", candleTexture);
        ModelFile trimmedCandleLit = models().withExistingParent(name(block) + "_lit", mcLoc("minecraft:block/template_candle"))
                .texture("all", litCandleTexture).texture("particle", litCandleTexture);
        ModelFile trimmedTwoCandleLit = models().withExistingParent(name(block) + "_two_candles_lit", mcLoc("minecraft:block/template_two_candles"))
                .texture("all", litCandleTexture).texture("particle", litCandleTexture);
        ModelFile trimmedThreeCandleLit = models().withExistingParent(name(block) + "_three_candles_lit", mcLoc("minecraft:block/template_three_candles"))
                .texture("all", litCandleTexture).texture("particle", litCandleTexture);
        ModelFile trimmedFourCandleLit = models().withExistingParent(name(block) + "_four_candles_lit", mcLoc("minecraft:block/template_four_candles"))
                .texture("all", litCandleTexture).texture("particle", litCandleTexture);
        trimmedCandleBlock(block, trimmedCandle, trimmedCandleLit, trimmedTwoCandle, trimmedTwoCandleLit,
                trimmedThreeCandle, trimmedThreeCandleLit, trimmedFourCandle, trimmedFourCandleLit);
    }



    public void trimmedCandleBlock(CandleBlock block, ModelFile one, ModelFile oneLit, ModelFile two, ModelFile twoLit,
                                   ModelFile three, ModelFile threeLit, ModelFile four, ModelFile fourLit) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            ModelFile model = one;
            boolean lit = state.getValue(CandleBlock.LIT);
            model = lit ? oneLit : model;
            switch (state.getValue(CandleBlock.CANDLES)) {
                case 2 -> model = lit ? twoLit : two;
                case 3 -> model = lit ? threeLit : three;
                case 4 -> model = lit ? fourLit : four;
            }
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        }, CandleBlock.WATERLOGGED);
    }
}
