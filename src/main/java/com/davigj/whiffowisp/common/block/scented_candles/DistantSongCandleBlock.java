package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CryingObsidianBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DistantSongCandleBlock extends ScentedCandleBlock{
    public DistantSongCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            teleportToNorthCardinal(living, level);
        }
    }

    public static boolean teleportToNorthCardinal(LivingEntity living, Level level) {
        BlockPos playerPos = living.blockPosition();
        BlockPos targetPos = findNorthCardinalPos(playerPos, level);

        if (targetPos != null) {
            living.teleportTo(targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5);
            return true;
        } else {
            return false;
        }
    }

    private static BlockPos findNorthCardinalPos(BlockPos startPos, Level level) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(startPos.getX(), startPos.getY(), startPos.getZ() - 0);
        while (mutablePos.getY() < level.getMaxBuildHeight() && level.isEmptyBlock(mutablePos)) {
            mutablePos.move(0, 0, -1);
        }
        if (!level.isEmptyBlock(mutablePos)) {
            return mutablePos;
        } else {
            return null;
        }
    }
}
