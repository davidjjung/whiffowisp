package com.davigj.whiffowisp.core.registry;

import com.davigj.whiffowisp.common.block.entity.ScentedCandleBlockEntity;
import com.davigj.whiffowisp.core.WhiffOWisp;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WOWBlockEntityTypes {
    public static final BlockEntitySubRegistryHelper HELPER = WhiffOWisp.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<ScentedCandleBlockEntity>> SCENTED_CANDLE =
            HELPER.createBlockEntity("scented_candle", ScentedCandleBlockEntity::new, () -> Set.of(
                    WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get(),
                    WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get(),
                    WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get(),
                    WOWBlocks.HOMESICK_SCENTED_CANDLE.get(),
                    WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get(),
                    WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get(),
                    WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get(),

                    WOWBlocks.SOFT_BLANKET_SCENTED_CANDLE.get(),
                    WOWBlocks.VANILLA_BUNNY_SCENTED_CANDLE.get(),
                    WOWBlocks.FOREST_HAZE_SCENTED_CANDLE.get(),
                    WOWBlocks.MIDSUMMER_NIGHT_SCENTED_CANDLE.get(),
                    WOWBlocks.AUTUMN_WREATH_SCENTED_CANDLE.get(),
                    WOWBlocks.PINK_SANDS_SCENTED_CANDLE.get(),
                    WOWBlocks.TARNATION_SCENTED_CANDLE.get(),
                    WOWBlocks.BROKEN_TRUST_SCENTED_CANDLE.get(),
                    WOWBlocks.BLACK_CHERRY_SCENTED_CANDLE.get(),
                    WOWBlocks.DESERT_SUNSET_SCENTED_CANDLE.get(),
                    WOWBlocks.DAILY_SPECIAL_SCENTED_CANDLE.get()
            ));
}