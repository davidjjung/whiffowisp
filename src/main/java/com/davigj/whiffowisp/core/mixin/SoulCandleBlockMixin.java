package com.davigj.whiffowisp.core.mixin;

import com.davigj.whiffowisp.core.other.WOWBlockStatements;
import com.teamabnormals.buzzier_bees.common.block.SoulCandleBlock;
import com.teamabnormals.buzzier_bees.core.registry.BBParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = {"com.teamabnormals.buzzier_bees.common.block.SoulCandleBlock"})
public class SoulCandleBlockMixin {

    @Inject(method = "addSoulParticlesAndSound", at = @At("HEAD"), cancellable = true, remap = false)
    private static void animateTrim(Level level, Vec3 vec3, RandomSource random, CallbackInfo ci) {
        if (ModList.get().isLoaded("buzzier_bees")) {
            BlockState state = level.getBlockState(new BlockPos(vec3));
            if (state.getValue(WOWBlockStatements.TRIMMED)) {
                float f = random.nextFloat();
                if (f < 0.17F) {
                    level.playLocalSound(vec3.x + 0.5, vec3.y + 0.5, vec3.z + 0.5, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
                }
                level.addParticle((ParticleOptions) BBParticleTypes.SMALL_SOUL_FIRE_FLAME.get(), vec3.x, vec3.y, vec3.z, 0.0, 0.0, 0.0);
                ci.cancel();
            }
        }
    }
}
