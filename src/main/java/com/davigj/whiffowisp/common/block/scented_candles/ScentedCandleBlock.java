package com.davigj.whiffowisp.common.block.scented_candles;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.davigj.whiffowisp.core.other.WOWConstants.BOTTLED;
import static com.davigj.whiffowisp.core.other.WOWConstants.TRIMMED;

public class ScentedCandleBlock extends CandleBlock {
    private static final Int2ObjectMap<List<Vec3>> PARTICLE_OFFSETS = Util.make(() -> {
        Int2ObjectMap<List<Vec3>> int2objectmap = new Int2ObjectOpenHashMap<>();
        int2objectmap.defaultReturnValue(ImmutableList.of());
        int2objectmap.put(1, ImmutableList.of(new Vec3(0.5D, 0.500D, 0.5D)));
        int2objectmap.put(2, ImmutableList.of(new Vec3(0.5D, 0.688D, 0.5D)));
        int2objectmap.put(3, ImmutableList.of(new Vec3(0.5D, 0.875D, 0.5D)));
        int2objectmap.put(4, ImmutableList.of(new Vec3(0.5D, 1.050D, 0.5D)));
        return Int2ObjectMaps.unmodifiable(int2objectmap);
    });

    private static final VoxelShape ONE_AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
    private static final VoxelShape TWO_AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 9.0D, 10.0D);
    private static final VoxelShape THREE_AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 12.0D, 11.0D);
    private static final VoxelShape FOUR_AABB = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);

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

    public VoxelShape getShape(BlockState state, @NotNull BlockGetter block, @NotNull BlockPos pos, @NotNull CollisionContext collision) {
        return switch (state.getValue(CANDLES)) {
            default -> ONE_AABB;
            case 2 -> TWO_AABB;
            case 3 -> THREE_AABB;
            case 4 -> FOUR_AABB;
        };
    }

    protected Iterable<Vec3> getParticleOffsets(BlockState state) {
        return PARTICLE_OFFSETS.get(state.getValue(CANDLES).intValue());
    }
}
