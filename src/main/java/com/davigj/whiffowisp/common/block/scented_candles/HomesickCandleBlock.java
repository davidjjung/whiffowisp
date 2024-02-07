package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HomesickCandleBlock extends ScentedCandleBlock{
    public HomesickCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.REGENERATION, 15 * state.getValue(CANDLES))));
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.CONFUSION, 40 * state.getValue(CANDLES))));
        }
    }
}
