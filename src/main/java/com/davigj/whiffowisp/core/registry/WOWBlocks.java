package com.davigj.whiffowisp.core.registry;

import com.davigj.whiffowisp.common.block.scented_candles.*;
import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.other.WOWConstants;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WOWBlocks {
    public static final BlockSubRegistryHelper HELPER = WhiffOWisp.REGISTRY_HELPER.getBlockSubHelper();
    public static final RegistryObject<Block> NETHERWAX_BLOCK = HELPER.createBlock("netherwax_block", () -> new Block(Block.Properties.copy(Blocks.HONEYCOMB_BLOCK).mapColor(DyeColor.RED)));

    public static final RegistryObject<Block> RED_REDEMPTION_SCENTED_CANDLE = HELPER.createBlock("red_redemption_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.RED_CANDLE), WOWConstants.MINECRAFT, WOWConstants.RESISTANCE));
    public static final RegistryObject<Block> CARAVAN_SPICE_SCENTED_CANDLE = HELPER.createBlock("caravan_spice_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.BLUE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.SPEED, 1));
    public static final RegistryObject<Block> FIRESIDE_SPAT_SCENTED_CANDLE = HELPER.createBlock("fireside_spat_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.BLACK_CANDLE), WOWConstants.MINECRAFT, WOWConstants.BLINDNESS));

    public static final RegistryObject<Block> SEAFARING_DREAM_SCENTED_CANDLE = HELPER.createBlock("seafaring_dream_scented_candle", () -> new SeafaringDreamCandleBlock(Block.Properties.copy(Blocks.LIME_CANDLE)));
    public static final RegistryObject<Block> ARTS_AND_CRAFTS_SCENTED_CANDLE = HELPER.createBlock("arts_and_crafts_scented_candle", () -> new ArtsAndCraftsCandleBlock(Block.Properties.copy(Blocks.MAGENTA_CANDLE)));
    public static final RegistryObject<Block> HOMESICK_SCENTED_CANDLE = HELPER.createBlock("homesick_scented_candle", () -> new HomesickCandleBlock(Block.Properties.copy(Blocks.LIGHT_GRAY_CANDLE)));
    public static final RegistryObject<Block> DISTANT_SONG_SCENTED_CANDLE = HELPER.createBlock("distant_song_scented_candle", () -> new DistantSongCandleBlock(Block.Properties.copy(Blocks.MAGENTA_CANDLE)));

    public static final RegistryObject<Block> SOFT_BLANKET_SCENTED_CANDLE = HELPER.createBlock("soft_blanket_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.WHITE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.WEAKNESS));
    public static final RegistryObject<Block> VANILLA_BUNNY_SCENTED_CANDLE = HELPER.createBlock("vanilla_bunny_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.LIGHT_BLUE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.JUMP_BOOST, 1));
    public static final RegistryObject<Block> MIDSUMMER_NIGHT_SCENTED_CANDLE = HELPER.createBlock("midsummer_night_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.BLACK_CANDLE), WOWConstants.MINECRAFT, WOWConstants.INVISIBILITY));
    public static final RegistryObject<Block> AUTUMN_WREATH_SCENTED_CANDLE = HELPER.createBlock("autumn_wreath_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.ORANGE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.SLOW_FALLING));

    public static final RegistryObject<Block> FOREST_HAZE_SCENTED_CANDLE = HELPER.createBlock("forest_haze_scented_candle", () -> new ForestHazeCandleBlock(Block.Properties.copy(Blocks.GREEN_CANDLE)));
    public static final RegistryObject<Block> PINK_SANDS_SCENTED_CANDLE = HELPER.createBlock("pink_sands_scented_candle", () -> new PinkSandsCandleBlock(Block.Properties.copy(Blocks.PINK_CANDLE)));
    public static final RegistryObject<Block> TARNATION_SCENTED_CANDLE = HELPER.createBlock("tarnation_scented_candle", () -> new TarnationCandleBlock(Block.Properties.copy(Blocks.BROWN_CANDLE)));

    public static final RegistryObject<Block> BROKEN_TRUST_SCENTED_CANDLE = HELPER.createBlock("broken_trust_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.RED_CANDLE), WOWConstants.JNE, WOWConstants.BETRAYED));
    public static final RegistryObject<Block> BLACK_CHERRY_SCENTED_CANDLE = HELPER.createBlock("black_cherry_scented_candle", () -> new BlackCherryScentedCandleBlock(Block.Properties.copy(Blocks.PINK_CANDLE)));
    public static final RegistryObject<Block> DESERT_SUNSET_SCENTED_CANDLE = HELPER.createBlock("desert_sunset_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.ORANGE_CANDLE), WOWConstants.ATMOSPHERIC, WOWConstants.WORSENING));

    public static final RegistryObject<Block> DAILY_SPECIAL_SCENTED_CANDLE = HELPER.createBlock("daily_special_scented_candle", () -> new DailySpecialCandleBlock(Block.Properties.copy(Blocks.PURPLE_CANDLE)));


    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(WhiffOWisp.MOD_ID)
                .tab(CreativeModeTabs.COLORED_BLOCKS)
                .addItemsAfter(of(Items.PINK_CANDLE), RED_REDEMPTION_SCENTED_CANDLE, CARAVAN_SPICE_SCENTED_CANDLE,
                        FIRESIDE_SPAT_SCENTED_CANDLE, SEAFARING_DREAM_SCENTED_CANDLE, ARTS_AND_CRAFTS_SCENTED_CANDLE,
                        HOMESICK_SCENTED_CANDLE, DISTANT_SONG_SCENTED_CANDLE,
                        SOFT_BLANKET_SCENTED_CANDLE, VANILLA_BUNNY_SCENTED_CANDLE, FOREST_HAZE_SCENTED_CANDLE,
                        MIDSUMMER_NIGHT_SCENTED_CANDLE, AUTUMN_WREATH_SCENTED_CANDLE, PINK_SANDS_SCENTED_CANDLE,
                        TARNATION_SCENTED_CANDLE, DAILY_SPECIAL_SCENTED_CANDLE, BROKEN_TRUST_SCENTED_CANDLE,
                        BLACK_CHERRY_SCENTED_CANDLE, DESERT_SUNSET_SCENTED_CANDLE
                );
        CreativeModeTabContentsPopulator.mod(WhiffOWisp.MOD_ID)
                .tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .addItemsAfter(of(Items.PINK_CANDLE), RED_REDEMPTION_SCENTED_CANDLE, CARAVAN_SPICE_SCENTED_CANDLE,
                        FIRESIDE_SPAT_SCENTED_CANDLE, SEAFARING_DREAM_SCENTED_CANDLE, ARTS_AND_CRAFTS_SCENTED_CANDLE,
                        HOMESICK_SCENTED_CANDLE, DISTANT_SONG_SCENTED_CANDLE,
                        SOFT_BLANKET_SCENTED_CANDLE, VANILLA_BUNNY_SCENTED_CANDLE, FOREST_HAZE_SCENTED_CANDLE,
                        MIDSUMMER_NIGHT_SCENTED_CANDLE, AUTUMN_WREATH_SCENTED_CANDLE, PINK_SANDS_SCENTED_CANDLE,
                        TARNATION_SCENTED_CANDLE, DAILY_SPECIAL_SCENTED_CANDLE, BROKEN_TRUST_SCENTED_CANDLE,
                        BLACK_CHERRY_SCENTED_CANDLE, DESERT_SUNSET_SCENTED_CANDLE);
        CreativeModeTabContentsPopulator.mod(WhiffOWisp.MOD_ID)
                .tab(CreativeModeTabs.NATURAL_BLOCKS)
                .addItemsAfter(of(Items.HONEYCOMB_BLOCK), NETHERWAX_BLOCK);

    }
}