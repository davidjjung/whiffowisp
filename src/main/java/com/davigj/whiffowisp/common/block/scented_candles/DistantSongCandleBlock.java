package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DistantSongCandleBlock extends ScentedCandleBlock {

    public DistantSongCandleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.affect(level, pos, state, entity);
        if (!(entity instanceof LivingEntity living)) return;
        if (living instanceof Player player && player.isCrouching()) return;

        switch (state.getValue(CANDLES)) {
            case 1 -> teleport(living, level, pos, 0, -6);
            case 2 -> teleport(living, level, pos, -6, 0);
            case 3 -> teleport(living, level, pos, 6, 0);
            case 4 -> teleport(living, level, pos, 0, 6);
        }
    }

    public static void teleport(LivingEntity living, Level level, BlockPos candlePos, int xOffset, int zOffset) {
        BlockPos startPos = living.blockPosition();
        BlockPos targetPos = findPos(startPos, level, xOffset, zOffset);
        if (targetPos == null) return;

        double x = targetPos.getX() + 0.5;
        double y = targetPos.getY();
        double z = targetPos.getZ() + 0.5;

        int tries = 0;
        while (!level.getBlockState(BlockPos.containing(x, y, z)).isAir() && tries < 5) {
            y++;
            tries++;
        }

        if (tries == 4 || living.isPassenger() || living.isVehicle()) {
            failTeleport(living, level);
            return;
        }

        if (!level.isClientSide) {
            living.teleportTo(x, y + 0.75D, z);
            living.resetFallDistance();

            float pitch = 1.3F + living.getRandom().nextFloat();

            if (living instanceof Player player) {
                player.playNotifySound(
                        SoundEvents.ENDERMAN_TELEPORT,
                        SoundSource.PLAYERS,
                        0.12F,
                        pitch
                );

                level.playSound(
                        player,
                        candlePos,
                        SoundEvents.ENDERMAN_TELEPORT,
                        SoundSource.BLOCKS,
                        0.12F,
                        pitch
                );
            } else {
                level.playSound(
                        null,
                        candlePos,
                        SoundEvents.ENDERMAN_TELEPORT,
                        SoundSource.BLOCKS,
                        0.12F,
                        pitch
                );
            }
        }

        if (level.isClientSide) {
            RandomSource random = level.getRandom();
            for (int i = 0; i < 4; i++) {
                level.addParticle(
                        ParticleTypes.END_ROD,
                        living.getX() + random.nextDouble() - 0.5,
                        living.getY(),
                        living.getZ() + random.nextDouble() - 0.5,
                        0.0D, 0.0D, 0.0D
                );
            }
        }
    }

    private static void failTeleport(LivingEntity living, Level level) {
        if (living.tickCount % 20 != 0) return;

        if (level.isClientSide) {
            RandomSource random = level.getRandom();
            for (int i = 0; i < 5; i++) {
                level.addParticle(
                        ParticleTypes.SMOKE,
                        living.getX() + random.nextDouble() - 0.5,
                        living.getY(),
                        living.getZ() + random.nextDouble() - 0.5,
                        0.0D, 0.0D, 0.0D
                );
            }
            living.playSound(SoundEvents.FIRECHARGE_USE, 0.3F, 4.0F);
        } else {
            living.hurt(living.damageSources().magic(), 1.0F);
        }
    }

    private static BlockPos findPos(BlockPos startPos, Level level, int xOffset, int zOffset) {
        BlockPos pos = startPos.offset(xOffset, 0, zOffset);
        return pos.getY() >= level.getMinBuildHeight() && pos.getY() <= level.getMaxBuildHeight()
                ? pos
                : null;
    }
}