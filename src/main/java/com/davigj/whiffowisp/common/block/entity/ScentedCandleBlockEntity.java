package com.davigj.whiffowisp.common.block.entity;

import com.davigj.whiffowisp.common.block.scented_candles.DistantSongCandleBlock;
import com.davigj.whiffowisp.common.block.scented_candles.ScentedCandleBlock;
import com.davigj.whiffowisp.core.WOWConfig;
import com.davigj.whiffowisp.core.registry.WOWBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;

public class ScentedCandleBlockEntity extends BlockEntity {
    public ScentedCandleBlockEntity(BlockPos pos, BlockState state) {
        super(WOWBlockEntityTypes.SCENTED_CANDLE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ScentedCandleBlockEntity candle) {
        if (!WOWConfig.COMMON.scentFX.get()) {return;}
        if (state.getBlock() instanceof DistantSongCandleBlock) {
            return;
        }
        if (state.getValue(ScentedCandleBlock.LIT) && !state.getValue(TRIMMED)) {
            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(0.5 * state.getValue(ScentedCandleBlock.CANDLES)))) {
                ((ScentedCandleBlock)(state.getBlock())).affect(level, pos, state, entity);
            }
        }
    }
}
