package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.TRIMMED;

public class ScentedCandleBlock extends CandleBlock {

    public ScentedCandleBlock(Properties p_152801_) {
        super(p_152801_);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TRIMMED, Boolean.valueOf(false))
                .setValue(CANDLES, Integer.valueOf(1))
                .setValue(LIT, Boolean.valueOf(false))
                .setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (state.getValue(LIT)) {
            affect(level, pos, state, entity);
        }
        super.stepOn(level, pos, state, entity);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{TRIMMED, LIT, CANDLES, WATERLOGGED});
    }

}
