package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class OtherDistantSongCandleBlock extends ScentedCandleBlock {
    public OtherDistantSongCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            switch (state.getValue(CANDLES)) {
                case 1: teleportToCardinal(living, level, 0, -6);
                case 2: teleportToCardinal(living, level, -6, 0);
                case 3: teleportToCardinal(living, level, 6, 0);
                case 4: teleportToCardinal(living, level, 0, 6);
            }

        }
    }

    public static void teleportToCardinal(LivingEntity living, Level level, int xOffset, int zOffset) {
        BlockPos playerPos = living.blockPosition();
        BlockPos targetPos = findValidPos(playerPos, level, xOffset, zOffset);

        if (targetPos != null) {
            double x = targetPos.getX() + 0.5;
            double y = targetPos.getY();
            double z = targetPos.getZ() + 0.5;

            // Adjusting the y position to avoid teleporting into the ground
            while (!level.getBlockState(new BlockPos(x, y, z)).isAir()) {
                y++;
            }

            living.teleportTo(x, y, z);
            living.resetFallDistance();
        }
    }

    private static BlockPos findValidPos(BlockPos startPos, Level level, int xOffset, int zOffset) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(startPos.getX() + xOffset, startPos.getY(), startPos.getZ() + zOffset);

        // Ensure the target position is within the world bounds
        if (mutablePos.getY() >= level.getMinBuildHeight() && mutablePos.getY() <= level.getMaxBuildHeight()) {
            System.out.println(mutablePos);
            return mutablePos;
        } else {
            return null;
        }
    }
}
