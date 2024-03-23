package com.davigj.whiffowisp.core.registry;

import com.davigj.whiffowisp.common.block.entity.ScentedCandleBlockEntity;
import com.davigj.whiffowisp.core.WhiffOWisp;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = WhiffOWisp.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class WOWBlockEntityTypes {
    public static final BlockEntitySubRegistryHelper HELPER = WhiffOWisp.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<ScentedCandleBlockEntity>> SCENTED_CANDLE = HELPER.createBlockEntity("scented_candle", ScentedCandleBlockEntity::new, () -> new Block[]{
            WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get(),
            WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get(),
            WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get(),
            WOWBlocks.HOMESICK_SCENTED_CANDLE.get(),
            WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get(),
            WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get(),
            WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get(),
    });
}