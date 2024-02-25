package com.davigj.whiffowisp.core.mixin;

import com.davigj.whiffowisp.core.other.WOWConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.mehvahdjukaar.supplementaries.common.block.blocks.AbstractCandleSkullBlock")
public class AbstractCandleSkullBlockMixin {

    @Inject(method = "addParticlesAndSound", at = @At("HEAD"), cancellable = true, remap = false)
    private void noSmoke(ParticleType<?> particle, Level level, Vec3 vec3, RandomSource randomSource, CallbackInfo ci) {
        if (ModList.get().isLoaded(WOWConstants.SUPPLEMENTARIES)) {
            BlockState state = level.getBlockState(new BlockPos(vec3));
            if (state.getValue(WOWConstants.TRIMMED)) {
                float f = randomSource.nextFloat();
                if (f < 0.17F) {
                    level.playLocalSound(vec3.x + 0.5, vec3.y + 0.5, vec3.z + 0.5, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + randomSource.nextFloat(), randomSource.nextFloat() * 0.7F + 0.3F, false);
                }
                level.addParticle((ParticleOptions) particle, vec3.x, vec3.y, vec3.z, 0.0, 0.0, 0.0);
                ci.cancel();
            }
        }
    }

}
