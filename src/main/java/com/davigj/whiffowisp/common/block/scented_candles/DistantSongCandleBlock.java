package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityTeleportEvent;

public class DistantSongCandleBlock extends ScentedCandleBlock {
    public DistantSongCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (entity instanceof Player player && player.isCrouching()) { return; }
            switch (state.getValue(CANDLES)) {
                case 1 -> teleport(living, level, 0, -6);
                case 2 -> teleport(living, level, -6, 0);
                case 3 -> teleport(living, level, 6, 0);
                case 4 -> teleport(living, level, 0, 6);
            }
        }
    }

    public static void teleport(LivingEntity living, Level level, int xOffset, int zOffset) {
        BlockPos playerPos = living.blockPosition();
        BlockPos targetPos = findPos(playerPos, level, xOffset, zOffset);
        if (targetPos != null) {
            double x = targetPos.getX() + 0.5;
            double y = targetPos.getY();
            double z = targetPos.getZ() + 0.5;
            int i = 0;
            while (!level.getBlockState(new BlockPos(x, y, z)).isAir() && i < 5) {
                i++;
                y++;
            }
            if (i != 4) {
                if (living.isPassenger() || living.isVehicle()) {
                    failTeleport(living, level);
                    return;
                }
                if (!level.isClientSide) {
                    living.teleportTo(x, y, z);
                    living.resetFallDistance();
                    if (!(living instanceof Player)) living.playSound(SoundEvents.ENDERMAN_TELEPORT, 0.12F, 1.3F + living.getRandom().nextFloat());
                } else {
                    for (int j = 0; j < 4; ++j) {
                        RandomSource random = level.getRandom();
                        level.addParticle(ParticleTypes.END_ROD, living.getX() + random.nextDouble() - 0.5,
                                living.getY(), living.getZ() + random.nextDouble() - 0.5,
                                0.0D, 0.0D, 0.0D);
                    }
                    living.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.12F, 1.3F + living.getRandom().nextFloat());
                }
            } else {
                failTeleport(living, level);
            }
        }
    }

    private static void failTeleport(LivingEntity living, Level level) {
        if (living.tickCount % 20 == 0) {
            if (level.isClientSide) {
                for (int i = 0; i < 5; ++i) {
                    RandomSource random = level.getRandom();
                    level.addParticle(ParticleTypes.SMOKE, living.getX() + random.nextDouble() - 0.5,
                            living.getY(), living.getZ() + random.nextDouble() - 0.5,
                            0.0D, 0.0D, 0.0D);
                }
                living.playSound(SoundEvents.FIRECHARGE_USE, 0.3F, 4.0F);
            } else {
                living.hurt(DamageSource.MAGIC, 1.0F);
            }
        }
    }

    private static BlockPos findPos(BlockPos startPos, Level level, int xOffset, int zOffset) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(startPos.getX() + xOffset, startPos.getY(), startPos.getZ() + zOffset);
        if (mutablePos.getY() >= level.getMinBuildHeight() && mutablePos.getY() <= level.getMaxBuildHeight()) {
            return mutablePos;
        } else {
            return null;
        }
    }
}
