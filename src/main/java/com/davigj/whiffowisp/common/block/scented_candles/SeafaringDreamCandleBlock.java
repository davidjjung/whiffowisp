package com.davigj.whiffowisp.common.block.scented_candles;

import com.davigj.whiffowisp.core.WhiffOWisp;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID)
public class SeafaringDreamCandleBlock extends ScentedCandleBlock {
    public SeafaringDreamCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.affect(level, pos, state, entity);
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.WATER_BREATHING, 20 * state.getValue(CANDLES))));

        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            this.getParticleOffsets(state).forEach((p_220695_) -> {
                addParticlesAndSound(level, p_220695_.add((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), random, state);
            });
        }
    }

    private static void addParticlesAndSound(Level p_220688_, Vec3 p_220689_, RandomSource p_220690_, BlockState state) {
        float f = p_220690_.nextFloat();
        ParticleOptions particle;
        if (state.getValue(WATERLOGGED)) {
            particle = ParticleTypes.BUBBLE;
        } else {
            particle = ParticleTypes.SMALL_FLAME;
        }
        if (f < 0.3F) {
            if (!state.getValue(TRIMMED)) {
                p_220688_.addParticle(ParticleTypes.SMOKE, p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
            }
            if (f < 0.17F) {
                p_220688_.playLocalSound(p_220689_.x + 0.5D, p_220689_.y + 0.5D, p_220689_.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + p_220690_.nextFloat(), p_220690_.nextFloat() * 0.7F + 0.3F, false);
            }
        }

        p_220688_.addParticle(particle, p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
    }

    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!state.getValue(WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
            BlockState blockstate = state.setValue(WATERLOGGED, Boolean.valueOf(true));
            level.setBlock(pos, blockstate, 3);
            level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
            return true;
        } else {
            return false;
        }
    }

    @SubscribeEvent
    public static void setAlight(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = player.level;
        BlockPos pos = event.getHitVec().getBlockPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();
        if (state.getBlock() instanceof SeafaringDreamCandleBlock && stack.getItem() instanceof FlintAndSteelItem
                && !state.getValue(LIT) && state.getValue(WATERLOGGED)) {
            level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
            player.swing(event.getHand());
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            player.getItemInHand(event.getHand()).hurtAndBreak(1, player, (p_41303_) -> {
                p_41303_.broadcastBreakEvent(event.getHand());
            });
        }
    }

}
