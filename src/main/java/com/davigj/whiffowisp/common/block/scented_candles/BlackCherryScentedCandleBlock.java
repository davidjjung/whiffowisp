package com.davigj.whiffowisp.common.block.scented_candles;

import com.davigj.whiffowisp.core.other.compat.ACCompat;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;
import static com.davigj.whiffowisp.core.other.WOWConstants.ALEXSCAVES;

public class BlackCherryScentedCandleBlock extends ScentedCandleBlock {
    public BlackCherryScentedCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.affect(level, pos, state, entity);
        if (ModList.get().isLoaded(ALEXSCAVES)) {
            if (entity instanceof Player player) {
                ACCompat.blackCherryBoost(player, state);
            }

        }
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.DARKNESS, 20 * state.getValue(CANDLES))));
        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            this.getParticleOffsets(state).forEach((p_220695_) -> {
                addParticlesAndSound(level, p_220695_.add((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), random, state);
            });
        }
    }


    private static void addParticlesAndSound(Level level, Vec3 vec3, RandomSource random, BlockState state) {
        float f = random.nextFloat();
        if (!state.getValue(TRIMMED) && ModList.get().isLoaded(ALEXSCAVES)) {
            ACCompat.voidCloudParticle(level, vec3, f);
        }
        if (f < 0.3F) {
            if (!state.getValue(TRIMMED)) {
                level.addParticle(ParticleTypes.SMOKE, vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
            }
            if (f < 0.17F) {
                level.playLocalSound(vec3.x + 0.5D, vec3.y + 0.5D, vec3.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
            }
        }
        if (f < 0.85) {
            level.addParticle(ParticleTypes.SMALL_FLAME, vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
        } else {
            double d = random.nextGaussian() * 0.15;
            level.addParticle(WOWParticleTypes.VOID_EYE_FLAME.get(), vec3.x + d, vec3.y + (d * 2.5),
                    vec3.z + d, 0.0D, 0.0D, 0.0D);
        }
    }
}
