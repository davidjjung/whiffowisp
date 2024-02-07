package com.davigj.whiffowisp.core.other;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import com.teamabnormals.buzzier_bees.common.block.SoulCandleBlock;
import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;
import static net.minecraft.world.level.block.CandleBlock.*;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID)
public class WOWEvents {

    private static final Map<Block, Block> CANDLE_MAP = new HashMap<>();

    @SubscribeEvent
    public static void trimWick(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = player.level;
        BlockPos pos = event.getHitVec().getBlockPos();
        BlockState state = level.getBlockState(pos);
        if (player.getItemInHand(event.getHand()).is(Tags.Items.SHEARS) && state.hasProperty(TRIMMED) && !state.getValue(TRIMMED)) {
            player.swing(event.getHand());
            event.setCancellationResult(InteractionResult.CONSUME);
            event.setCanceled(true);
            level.playSound((Player) null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 0.9F, 1.2F);
            level.setBlock(pos, state.setValue(TRIMMED, Boolean.valueOf(true)), 11);
            // TODO: add the other FlintAndSteel game event stuff and whatnot
            if (level instanceof ServerLevel) {
                BlockParticleOption soot = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DRIED_KELP_BLOCK.defaultBlockState());
                ((ServerLevel)player.level).sendParticles(soot.setPos(pos),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 2, 0, 0 + 0.05D, 0, 0.15D);
            }
        }
    }

    public static void initializeCandleMap() {
//        CANDLE_MAP.put(Blocks.CANDLE, WOWBlocks.TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.BLACK_CANDLE, WOWBlocks.BLACK_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.BLUE_CANDLE, WOWBlocks.BLUE_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.BROWN_CANDLE, WOWBlocks.BROWN_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.CYAN_CANDLE, WOWBlocks.CYAN_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.GRAY_CANDLE, WOWBlocks.GRAY_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.GREEN_CANDLE, WOWBlocks.GREEN_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.LIGHT_BLUE_CANDLE, WOWBlocks.LIGHT_BLUE_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.LIGHT_GRAY_CANDLE, WOWBlocks.LIGHT_GRAY_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.LIME_CANDLE, WOWBlocks.LIME_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.MAGENTA_CANDLE, WOWBlocks.MAGENTA_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.ORANGE_CANDLE, WOWBlocks.ORANGE_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.PINK_CANDLE, WOWBlocks.PINK_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.PURPLE_CANDLE, WOWBlocks.PURPLE_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.RED_CANDLE, WOWBlocks.RED_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.WHITE_CANDLE, WOWBlocks.WHITE_TRIMMED_CANDLE.get());
//        CANDLE_MAP.put(Blocks.YELLOW_CANDLE, WOWBlocks.YELLOW_TRIMMED_CANDLE.get());
//        if (ModList.get().isLoaded("buzzier_bees")) {
//            CANDLE_MAP.put(BBBlocks.SOUL_CANDLE.get(), WOWBlocks.TRIMMED_SOUL_CANDLE.get());
//        }
    }
}