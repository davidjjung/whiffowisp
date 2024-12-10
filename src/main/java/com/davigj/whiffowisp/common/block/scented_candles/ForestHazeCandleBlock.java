package com.davigj.whiffowisp.common.block.scented_candles;

import com.davigj.whiffowisp.core.WOWConfig;
import galena.doom_and_gloom.index.OEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;

public class ForestHazeCandleBlock extends ScentedCandleBlock {

    public ForestHazeCandleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!WOWConfig.COMMON.scentFX.get() || !(entity instanceof LivingEntity)) {
            return;
        }

        if (!level.isClientSide && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN, 20 * state.getValue(CANDLES))));
        }
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (state.getValue(LIT)) {
            affect(level, pos, state, entity);
        }

        if (ModList.get().isLoaded("doom_and_gloom") && entity instanceof LivingEntity living)
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    OEffects.FOG.get(), 20 * state.getValue(CANDLES))));
        super.stepOn(level, pos, state, entity);
    }
}
