package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static com.davigj.whiffowisp.core.other.WOWConstants.availableEffects;

public class DailySpecialCandleBlock extends ScentedCandleBlock {
    public DailySpecialCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.affect(level, pos, state, entity);
        if (!(entity instanceof LivingEntity) || entity.level.isClientSide) {
            return;
        }
        long daysPassed = level.getDayTime() / 24000;
        for (int i = 0; i < 4; i++) {
            int index = (int) ((daysPassed + (i * Math.min(daysPassed, 12))) % (availableEffects.size()));
            MobEffect effect = availableEffects.get(index);
            ((LivingEntity) entity).addEffect(new MobEffectInstance(effect, 10 + (20 * state.getValue(CANDLES))));
        }
    }
}
