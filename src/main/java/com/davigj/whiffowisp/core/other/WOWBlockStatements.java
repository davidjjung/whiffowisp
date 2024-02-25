package com.davigj.whiffowisp.core.other;

import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import virtuoel.statement.api.StateRefresher;

import java.util.function.Supplier;

import static com.davigj.whiffowisp.core.other.WOWConstants.TRIMMED;
import static com.davigj.whiffowisp.core.other.WOWConstants.colors;

public class WOWBlockStatements {
    // This utility class adds all the "trimmed" blockstate properties to existing blocks. Thanks, Statement!

    public static void addTrimStates() {
        // This is one way to do it; write it all out
        StateRefresher.INSTANCE.addBlockProperty(Blocks.CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.WHITE_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.ORANGE_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.MAGENTA_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.LIGHT_BLUE_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.YELLOW_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.LIME_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.PINK_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.GRAY_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.LIGHT_GRAY_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.CYAN_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.PURPLE_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.BLUE_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.BROWN_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.GREEN_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.RED_CANDLE, TRIMMED, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.BLACK_CANDLE, TRIMMED, false);

        if (ModList.get().isLoaded(WOWConstants.BUZZIER_BEES)) {
            // The reason this class is called during the common setup method is because these are run *after* forge has finished registering blocks
            StateRefresher.INSTANCE.addBlockProperty(BBBlocks.SOUL_CANDLE.get(), TRIMMED, false);
        }
        if (ModList.get().isLoaded(WOWConstants.SUPPLEMENTARIES)) {
            // I think this works? Not without risks, but I'm lazy at the moment
            StateRefresher.INSTANCE.addBlockProperty(getCompatBlock(WOWConstants.SUPPLEMENTARIES, "candle_holder").get(), TRIMMED, false);
            for (String color : colors) {
                StateRefresher.INSTANCE.addBlockProperty(getCompatBlock(WOWConstants.SUPPLEMENTARIES, "candle_holder_" + color).get(), TRIMMED, false);
            }
            StateRefresher.INSTANCE.addBlockProperty(getCompatBlock(WOWConstants.SUPPLEMENTARIES, "skull_candle").get(), TRIMMED, false);
            StateRefresher.INSTANCE.addBlockProperty(getCompatBlock(WOWConstants.SUPPLEMENTARIES, "skull_candle_wall").get(), TRIMMED, false);
            if (ModList.get().isLoaded(WOWConstants.BUZZIER_BEES)) {
                StateRefresher.INSTANCE.addBlockProperty(getCompatBlock(WOWConstants.SUPPLEMENTARIES, "candle_holder_soul").get(), TRIMMED, false);
                StateRefresher.INSTANCE.addBlockProperty(getCompatBlock(WOWConstants.SUPPLEMENTARIES, "skull_candle_soul").get(), TRIMMED, false);
                StateRefresher.INSTANCE.addBlockProperty(getCompatBlock(WOWConstants.SUPPLEMENTARIES, "skull_candle_soul_wall").get(), TRIMMED, false);
            }
        }
    }

    private static Supplier<Block> getCompatBlock(String modid, String blockID) {
        ResourceLocation block = new ResourceLocation(modid, blockID);
        return (ModList.get().isLoaded(modid) ? () -> ForgeRegistries.BLOCKS.getValue(block) : () -> null);
    }
}
