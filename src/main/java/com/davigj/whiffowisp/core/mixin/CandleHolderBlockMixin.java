package com.davigj.whiffowisp.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;

@Pseudo
@Mixin(targets = "net.mehvahdjukaar.supplementaries.common.block.blocks.CandleHolderBlock")
public class CandleHolderBlockMixin {
    @WrapOperation(method = "addParticlesAndSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 0), remap = true)
    private void noSmoke(Level instance, ParticleOptions p_46631_, double p_46632_, double p_46633_, double p_46634_,
                         double p_46635_, double p_46636_, double p_46637_, Operation<Void> original, @Local(argsOnly = true) Vec3 vec3) {
        BlockState state = instance.getBlockState(new BlockPos(Mth.floor(Mth.floor(vec3.x)), Mth.floor(vec3.y - 0.05), Mth.floor(vec3.z)));

        if (!state.getValue(TRIMMED)) {
            System.out.println(state);
            original.call(instance, p_46631_, p_46632_, p_46633_, p_46634_, p_46635_, p_46636_, p_46637_);
        }
    }
}
