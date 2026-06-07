package com.davigj.whiffowisp.common.block.scented_candles;

import com.davigj.whiffowisp.common.block.entity.ScentedCandleBlockEntity;
import com.davigj.whiffowisp.core.WOWConfig;
import com.davigj.whiffowisp.core.registry.WOWBlockEntityTypes;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.davigj.whiffowisp.core.other.WOWConstants.TRIMMED;

public class ScentedCandleBlock extends CandleBlock implements EntityBlock {
    private static final Int2ObjectMap<List<Vec3>> PARTICLE_OFFSETS = Util.make(() -> {
        Int2ObjectMap<List<Vec3>> int2objectmap = new Int2ObjectOpenHashMap<>();
        int2objectmap.defaultReturnValue(ImmutableList.of());
        int2objectmap.put(1, ImmutableList.of(new Vec3(0.5D, 0.5D, 0.5D)));
        int2objectmap.put(2, ImmutableList.of(new Vec3(0.313D, 0.5D, 0.56D), new Vec3(0.6825D, 0.6825D, 0.44D)));
        int2objectmap.put(3, ImmutableList.of(new Vec3(0.313D, 0.5D, 0.44D), new Vec3(0.625D, 0.6825D, 0.3125D), new Vec3(0.56D, 0.375D, 0.6875D)));
        int2objectmap.put(4, ImmutableList.of(new Vec3(0.313D, 0.5D, 0.38D), new Vec3(0.625D, 0.6825D, 0.3125D), new Vec3(0.38D, 0.375D, 0.6875D), new Vec3(0.6875D, 0.5D, 0.63D)));
        return Int2ObjectMaps.unmodifiable(int2objectmap);
    });

    private static final VoxelShape ONE_AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
    private static final VoxelShape TWO_AABB = Block.box(3.0D, 0.0D, 5.0D, 13.0D, 6.0D, 11.0D);
    private static final VoxelShape THREE_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 6.0D, 13.0D);
    private static final VoxelShape FOUR_AABB = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);

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

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!WOWConfig.COMMON.scentFX.get()) {
            return;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{TRIMMED, LIT, CANDLES, WATERLOGGED});
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ScentedCandleBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, (BlockEntityType) WOWBlockEntityTypes.SCENTED_CANDLE.get(), ScentedCandleBlockEntity::tick);
    }

    @javax.annotation.Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> entityType, BlockEntityType<E> otherEntity, BlockEntityTicker<? super E> ticker) {
        return otherEntity == entityType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(this);
        if (id != null) {
            tooltip.add(Component.translatable("tooltip." + id.getNamespace() + "." + id.getPath())
                            .withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public String getDescriptionId() {
        return "block.whiffowisp.scented_candle";
    }
}

