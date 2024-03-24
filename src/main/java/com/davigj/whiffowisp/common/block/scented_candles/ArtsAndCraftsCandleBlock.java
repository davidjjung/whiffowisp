package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class ArtsAndCraftsCandleBlock extends ScentedCandleBlock {
    public ArtsAndCraftsCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.affect(level, pos, state, entity);
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            if (living instanceof Sheep sheep) {
                if (sheep.tickCount % 300 == 0) {
                    DyeColor color = this.getRandomColor();
                    while (color == sheep.getColor()) {
                        if (color != sheep.getColor()) {
                            break;
                        } else {
                            color = this.getRandomColor();
                        }
                    }
                    sheep.setColor(color);
                    RandomSource random = sheep.getRandom();
                    for (int i = 0; i < 4; i++) {
                        ((ServerLevel)(sheep.level)).sendParticles(ParticleTypes.CLOUD, sheep.getX() + random.nextDouble() - 0.5,
                                sheep.getEyeY(), sheep.getZ() + random.nextDouble() - 0.5,
                                1,0.0D, 0.0D, 0.0D,0.01D);
                    }
                }
                return;
            }
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.DIG_SPEED, 20 * state.getValue(CANDLES))));
        }
    }

    public DyeColor getRandomColor() {
        Random random = new Random();
        return switch (random.nextInt(12)) {
            case 1 -> DyeColor.BLUE;
            case 2 -> DyeColor.CYAN;
            case 3 -> DyeColor.GREEN;
            case 4 -> DyeColor.LIME;
            case 5 -> DyeColor.LIGHT_BLUE;
            case 6 -> DyeColor.MAGENTA;
            case 7 -> DyeColor.ORANGE;
            case 8 -> DyeColor.PINK;
            case 9 -> DyeColor.PURPLE;
            case 10 -> DyeColor.RED;
            default -> DyeColor.YELLOW;
        };
    }
}
