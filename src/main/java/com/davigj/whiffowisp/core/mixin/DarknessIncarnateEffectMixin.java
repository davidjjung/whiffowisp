package com.davigj.whiffowisp.core.mixin;

import com.github.alexmodguy.alexscaves.server.potion.DarknessIncarnateEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(DarknessIncarnateEffect.class)
public class DarknessIncarnateEffectMixin {
    @Inject(method = "isInLight", at = @At("HEAD"), cancellable = true, remap = false)
    private static void ackshuallyNotLight(LivingEntity living, int threshold, CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(MobEffects.DARKNESS)) cir.setReturnValue(false);
    }
}
