package com.davigj.whiffowisp.common.block.scented_candles;

import com.davigj.whiffowisp.core.WOWConfig;
import com.davigj.whiffowisp.core.registry.WOWSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
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
        if (entity instanceof Player player && player.isCrouching()) return;
        if (entity instanceof ItemEntity && !WOWConfig.COMMON.distantSongTeleportsItems.get()) return;

        int d = WOWConfig.COMMON.distantSongTeleportDistance.get();

        switch (state.getValue(CANDLES)) {
            case 1 -> teleport(entity, level, pos, 0, -d);
            case 2 -> teleport(entity, level, pos, -d, 0);
            case 3 -> teleport(entity, level, pos, 0, d);
            case 4 -> teleport(entity, level, pos, d, 0);
        }
    }

    public static void teleport(Entity entity, Level level, BlockPos candlePos, int xOffset, int zOffset) {
        BlockPos startPos = entity.blockPosition();
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

        if (tries == 4 || entity.isPassenger() || entity.isVehicle()) {
            if (entity instanceof LivingEntity living) {
                failTeleport(living, level, candlePos);
            }
            return;
        }

        if (!level.isClientSide) {
            entity.teleportTo(x, y + 0.75D, z);

            if (entity instanceof ItemEntity item) {
                item.setDeltaMovement(0.0D, item.getDeltaMovement().y, 0.0D);
                item.hurtMarked = true;
            }

            float pitch = 1.0F + level.getRandom().nextFloat();
            float volume = 0.3F;

            if (entity instanceof Player player) {
                player.resetFallDistance();
                player.playNotifySound(
                        WOWSounds.CANDLE_TP.get(),
                        SoundSource.PLAYERS,
                        volume,
                        pitch
                );

                level.playSound(
                        player,
                        candlePos,
                        WOWSounds.CANDLE_TP.get(),
                        SoundSource.BLOCKS,
                        volume,
                        pitch
                );
            } else {
                level.playSound(
                        null,
                        candlePos,
                        WOWSounds.CANDLE_TP.get(),
                        SoundSource.BLOCKS,
                        volume,
                        pitch
                );
            }
        }

        if (level instanceof ServerLevel serverLevel) {
            RandomSource random = serverLevel.getRandom();

            for (int i = 0; i < 6; i++) {
                serverLevel.sendParticles(
                        ParticleTypes.END_ROD,
                        candlePos.getX() + 0.5 + random.nextGaussian() * 0.25,
                        candlePos.getY() + 0.67,
                        candlePos.getZ() + 0.5 + random.nextGaussian() * 0.25,
                        1,
                        0.0D,
                        0.02D,
                        0.0D,
                        0.0D
                );
            }
        }
    }

    private static void failTeleport(LivingEntity living, Level level, BlockPos candlePos) {
        if (living.tickCount % 20 != 0) return;

        if (level instanceof ServerLevel serverLevel) {
            RandomSource random = serverLevel.getRandom();

            for (int i = 0; i < 6; i++) {
                serverLevel.sendParticles(
                        ParticleTypes.SMOKE,
                        candlePos.getX() + 0.5 + random.nextGaussian() * 0.3,
                        candlePos.getY() + 1.0,
                        candlePos.getZ() + 0.5 + random.nextGaussian() * 0.3,
                        1,
                        0.0D,
                        0.02D,
                        0.0D,
                        0.0D
                );
            }

            serverLevel.playSound(
                    null,
                    candlePos,
                    SoundEvents.FIRECHARGE_USE,
                    SoundSource.BLOCKS,
                    0.3F,
                    4.0F
            );
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