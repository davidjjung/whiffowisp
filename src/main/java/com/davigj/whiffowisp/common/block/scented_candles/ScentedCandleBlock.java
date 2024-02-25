package com.davigj.whiffowisp.common.block.scented_candles;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.mehvahdjukaar.supplementaries.common.block.blocks.BubbleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import static com.davigj.whiffowisp.core.other.WOWConstants.BOTTLED;
import static com.davigj.whiffowisp.core.other.WOWConstants.TRIMMED;

public class ScentedCandleBlock extends CandleBlock {

    public ScentedCandleBlock(Properties p_152801_) {
        super(p_152801_);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TRIMMED, Boolean.valueOf(false))
                .setValue(CANDLES, Integer.valueOf(1))
                .setValue(LIT, Boolean.valueOf(false))
                .setValue(WATERLOGGED, Boolean.valueOf(false))
                .setValue(BOTTLED, Boolean.valueOf(false)));
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (state.getValue(LIT)) {
            affect(level, pos, state, entity);
        }
        super.stepOn(level, pos, state, entity);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{TRIMMED, LIT, CANDLES, WATERLOGGED, BOTTLED});
    }

}
