package com.davigj.whiffowisp.common.block.scented_candles;

import com.davigj.whiffowisp.core.other.compat.ACCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;

import static com.davigj.whiffowisp.core.other.WOWConstants.ALEXSCAVES;

public class BlackCherryScentedCandleBlock extends ScentedCandleBlock {
    // TODO: Using LIGHT_EMISSION from candle class, define lightlevel() for blockstate using config value
    // TODO: Find relevant eye particles. Make them animate around candle for funsies
    public BlackCherryScentedCandleBlock(Properties p_152801_) {
        super(p_152801_);
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.affect(level, pos, state, entity);
        if (ModList.get().isLoaded(ALEXSCAVES)) {
            if (entity instanceof Player player) {
                ACCompat.blackCherryBoost(player, state);
            }

        }
        if (!level.isClientSide && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(new MobEffectInstance(
                    MobEffects.DARKNESS, 20 * state.getValue(CANDLES))));
        }
    }
}
