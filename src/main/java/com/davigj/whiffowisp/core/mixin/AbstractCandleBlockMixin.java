package com.davigj.whiffowisp.core.mixin;

import com.davigj.whiffowisp.core.WOWConfig;
import com.davigj.whiffowisp.core.other.WOWBlockStatements;
import com.davigj.whiffowisp.core.other.WOWBlockTags;
import com.google.common.collect.ImmutableList;
import com.teamabnormals.buzzier_bees.core.registry.BBParticleTypes;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.minecraft.world.level.block.AbstractCandleBlock.LIT;
import static net.minecraft.world.level.block.CandleBlock.CANDLES;

@Mixin(AbstractCandleBlock.class)
public class AbstractCandleBlockMixin {

    @Inject(method = "addParticlesAndSound", at = @At("HEAD"), cancellable = true)
    private static void trimmedParticles(Level level, Vec3 vec3, RandomSource random, CallbackInfo ci) {
        BlockState state = level.getBlockState(new BlockPos(vec3));
        if (state.getValue(WOWBlockStatements.TRIMMED)) {
            float f = random.nextFloat();
            if (f < 0.17F) {
                level.playLocalSound(vec3.x + 0.5, vec3.y + 0.5, vec3.z + 0.5, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
            }
            level.addParticle((ParticleOptions) ParticleTypes.SMALL_FLAME, vec3.x, vec3.y, vec3.z, 0.0, 0.0, 0.0);
            ci.cancel();
        }
    }
}
