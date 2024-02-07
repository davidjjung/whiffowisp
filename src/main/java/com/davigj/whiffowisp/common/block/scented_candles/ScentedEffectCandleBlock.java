package com.davigj.whiffowisp.common.block.scented_candles;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ScentedEffectCandleBlock extends ScentedCandleBlock{
    String modid;
    ResourceLocation effect;
    int amplifier;

    public ScentedEffectCandleBlock(Properties properties, String modid, ResourceLocation effect, int amplifier) {
        super(properties);
        this.modid = modid;
        this.effect = effect;
        this.amplifier = amplifier;
    }

    public void affect(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide) {
            if (ModList.get().isLoaded(modid) && entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(new MobEffectInstance(
                        getCompatEffect(modid, effect).get(), 20 * state.getValue(CANDLES), amplifier)));
            }
        }
    }

    public static Supplier<MobEffect> getCompatEffect(String modid, ResourceLocation effect) {
        return (ModList.get().isLoaded(modid) ? () -> ForgeRegistries.MOB_EFFECTS.getValue(effect) : () -> null);
    }
}
