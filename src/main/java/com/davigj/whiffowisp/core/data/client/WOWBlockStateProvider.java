package com.davigj.whiffowisp.core.data.client;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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
//        this.trimmedCandleBlock((CandleBlock) WOWBlocks.TRIMMED_CANDLE.get(), new ResourceLocation("minecraft:block/candle"), new ResourceLocation("minecraft:block/candle_lit"));
    }

    private void trimmedCandleBlock(CandleBlock block, ResourceLocation candle, ResourceLocation litCandle) {
        ModelFile trimmedCandle = models().withExistingParent(name(block), mcLoc("minecraft:block/template_candle"))
                .texture("all", candle).texture("particle", candle);
        ModelFile trimmedTwoCandle = models().withExistingParent(name(block) + "_two_candles", mcLoc("minecraft:block/template_two_candles"))
                .texture("all", candle).texture("particle", candle);
        ModelFile trimmedThreeCandle = models().withExistingParent(name(block) + "_three_candles", mcLoc("minecraft:block/template_three_candles"))
                .texture("all", candle).texture("particle", candle);
        ModelFile trimmedFourCandle = models().withExistingParent(name(block) + "_four_candles", mcLoc("minecraft:block/template_four_candles"))
                .texture("all", candle).texture("particle", candle);
        ModelFile trimmedCandleLit = models().withExistingParent(name(block) + "_lit", mcLoc("minecraft:block/template_candle"))
                .texture("all", litCandle).texture("particle", litCandle);
        ModelFile trimmedTwoCandleLit = models().withExistingParent(name(block) + "_two_candles_lit", mcLoc("minecraft:block/template_two_candles"))
                .texture("all", litCandle).texture("particle", litCandle);
        ModelFile trimmedThreeCandleLit = models().withExistingParent(name(block) + "_three_candles_lit", mcLoc("minecraft:block/template_three_candles"))
                .texture("all", litCandle).texture("particle", litCandle);
        ModelFile trimmedFourCandleLit = models().withExistingParent(name(block) + "_four_candles_lit", mcLoc("minecraft:block/template_four_candles"))
                .texture("all", litCandle).texture("particle", litCandle);
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
                case 2 -> {
                    model = lit ? twoLit : two;
                }
                case 3 -> {
                    model = lit ? threeLit : three;
                }
                case 4 -> {
                    model = lit ? fourLit : four;
                }
            }
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        }, CandleBlock.WATERLOGGED);
    }

}
