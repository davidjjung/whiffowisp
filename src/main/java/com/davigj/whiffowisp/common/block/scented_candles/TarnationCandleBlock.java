package com.davigj.whiffowisp.common.block.scented_candles;

import com.davigj.whiffowisp.core.other.WOWConstants;
import com.davigj.whiffowisp.core.registry.WOWParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;

public class TarnationCandleBlock extends ScentedCandleBlock{
    public TarnationCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.FIRE_RESISTANCE, 20 * state.getValue(CANDLES))));

        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            this.getParticleOffsets(state).forEach((p_220695_) -> {
                addParticlesAndSound(level, p_220695_.add((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), random);
            });
        }
    }

    private static void addParticlesAndSound(Level p_220688_, Vec3 p_220689_, RandomSource p_220690_) {
        float f = p_220690_.nextFloat();
        if (f < 0.3F) {
            p_220688_.addParticle(ParticleTypes.SMOKE, p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
            if (f < 0.17F) {
                p_220688_.playLocalSound(p_220689_.x + 0.5D, p_220689_.y + 0.5D, p_220689_.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + p_220690_.nextFloat(), p_220690_.nextFloat() * 0.7F + 0.3F, false);
            }
        }
        if (ModList.get().isLoaded(WOWConstants.ARCHITECTS_PALETTE)) {
            p_220688_.addParticle(WOWParticleTypes.SMALL_NETHER_BRASS_FLAME.get(), p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
        }
    }
}
