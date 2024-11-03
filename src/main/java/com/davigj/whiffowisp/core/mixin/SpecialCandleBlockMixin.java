package com.davigj.whiffowisp.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;

@Mixin(targets = {"com.teamabnormals.buzzier_bees.common.block.SpecialCandleBlock"})
public class SpecialCandleBlockMixin {
    @Inject(method = "addParticlesAndSound", at = @At("HEAD"), cancellable = true, remap = false)
    private static void animateTrim(Level level, ResourceLocation particleName, Vec3 vec3, RandomSource random, CallbackInfo ci) {
        if (ModList.get().isLoaded("buzzier_bees")) {
            BlockState state = level.getBlockState(new BlockPos((int) vec3.x, (int) vec3.y, (int) vec3.z));
            if (state.getValue(TRIMMED)) {
                float f = random.nextFloat();
                if (f < 0.17F) {
                    level.playLocalSound(vec3.x + 0.5, vec3.y + 0.5, vec3.z + 0.5, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
                }
                ParticleType<?> options = (ParticleType) ForgeRegistries.PARTICLE_TYPES.getValue(particleName);
                if (options != null) {
                    level.addParticle((ParticleOptions)options, vec3.x, vec3.y, vec3.z, 0.0, 0.0, 0.0);
                }
                ci.cancel();
            }
        }
    }
}
