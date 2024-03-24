package com.davigj.whiffowisp.core.registry;

import com.davigj.whiffowisp.common.block.scented_candles.*;
import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.other.WOWConstants;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WOWBlocks {
    public static final BlockSubRegistryHelper HELPER = WhiffOWisp.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> RED_REDEMPTION_SCENTED_CANDLE = HELPER.createBlock("red_redemption_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.RED_CANDLE), WOWConstants.MINECRAFT, WOWConstants.RESISTANCE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CARAVAN_SPICE_SCENTED_CANDLE = HELPER.createBlock("caravan_spice_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.BLUE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.SPEED, 1), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FIRESIDE_SPAT_SCENTED_CANDLE = HELPER.createBlock("fireside_spat_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.BLACK_CANDLE), WOWConstants.MINECRAFT, WOWConstants.BLINDNESS), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> SEAFARING_DREAM_SCENTED_CANDLE = HELPER.createBlock("seafaring_dream_scented_candle", () -> new SeafaringDreamCandleBlock(Block.Properties.copy(Blocks.LIME_CANDLE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ARTS_AND_CRAFTS_SCENTED_CANDLE = HELPER.createBlock("arts_and_crafts_scented_candle", () -> new ArtsAndCraftsCandleBlock(Block.Properties.copy(Blocks.MAGENTA_CANDLE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOMESICK_SCENTED_CANDLE = HELPER.createBlock("homesick_scented_candle", () -> new HomesickCandleBlock(Block.Properties.copy(Blocks.LIGHT_GRAY_CANDLE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DISTANT_SONG_SCENTED_CANDLE = HELPER.createBlock("distant_song_scented_candle", () -> new DistantSongCandleBlock(Block.Properties.copy(Blocks.MAGENTA_CANDLE)), CreativeModeTab.TAB_DECORATIONS);

    /*
    public static final RegistryObject<Block> SOFT_BLANKET_SCENTED_CANDLE = HELPER.createBlock("soft_blanket_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.WHITE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.WEAKNESS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> VANILLA_BUNNY_SCENTED_CANDLE = HELPER.createBlock("vanilla_bunny_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.LIGHT_BLUE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.JUMP_BOOST, 1), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FOREST_HAZE_SCENTED_CANDLE = HELPER.createBlock("forest_haze_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.GREEN_CANDLE), WOWConstants.MINECRAFT, WOWConstants.SLOWNESS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> MIDSUMMER_NIGHT_SCENTED_CANDLE = HELPER.createBlock("midsummer_night_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.BLACK_CANDLE), WOWConstants.MINECRAFT, WOWConstants.INVISIBILITY), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> AUTUMN_WREATH_SCENTED_CANDLE = HELPER.createBlock("autumn_wreath_scented_candle", () -> new ScentedEffectCandleBlock(Block.Properties.copy(Blocks.ORANGE_CANDLE), WOWConstants.MINECRAFT, WOWConstants.SLOW_FALLING), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> PINK_SANDS_SCENTED_CANDLE = HELPER.createBlock("pink_sands_scented_candle", () -> new PinkSandsCandleBlock(Block.Properties.copy(Blocks.PINK_CANDLE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> TARNATION_SCENTED_CANDLE = HELPER.createBlock("tarnation_scented_candle", () -> new TarnationCandleBlock(Block.Properties.copy(Blocks.BROWN_CANDLE)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> DAILY_SPECIAL_SCENTED_CANDLE = HELPER.createBlock("daily_special_scented_candle", () -> new DailySpecialCandleBlock(Block.Properties.copy(Blocks.PURPLE_CANDLE)), CreativeModeTab.TAB_DECORATIONS);
     */
}