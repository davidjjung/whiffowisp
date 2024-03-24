package com.davigj.whiffowisp.core.other;

import com.davigj.whiffowisp.core.WOWConfig;
import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;
import static com.davigj.whiffowisp.core.other.WOWConstants.initializeDailySpecials;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID)
public class WOWEvents {
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
                ((ServerLevel) player.level).sendParticles(soot.setPos(pos),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 2, 0, 0 + 0.05D, 0, 0.15D);
            }
        }
    }

    @SubscribeEvent
    public static void afterTagsLoaded(TagsUpdatedEvent event) {
        initializeDailySpecials();
    }

    @SubscribeEvent
    public static void villagerTrades(VillagerTradesEvent event) {
        if (WOWConfig.COMMON.redRedemptionTrade.get()) {
            TradeUtil.addVillagerTrades(event, VillagerProfession.BUTCHER, TradeUtil.EXPERT, new TradeUtil.BlueprintTrade(
                    7, WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get().asItem(), 1, 4, 5, 3
            ));
        }
    }
    @SubscribeEvent
    public static void wanderingTrades(WandererTradesEvent event) {
        if (WOWConfig.COMMON.caravanSpiceTrade.get()) {
            TradeUtil.addWandererTrades(event, new TradeUtil.BlueprintTrade(
                    4, WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get().asItem(), 2, 12, 1
            ));
        }
    }
}