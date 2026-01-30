package com.davigj.whiffowisp.core.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WOWConstants {
    public static final String MINECRAFT = "minecraft";
    public static final String ATMOSPHERIC = "atmospheric";
    public static final String DOOM_GLOOM = "doom_and_gloom";
    public static final String SUPPLEMENTARIES = "supplementaries";
    public static final String AMENDMENTS = "amendments";
    public static final String BUZZIER_BEES = "buzzier_bees";
    public static final String ARCHITECTS_PALETTE = "architects_palette";
    public static final String JNE = "netherexp";
    public static final String TF = "twilightforest";

    public static final ResourceLocation WEAKNESS = new ResourceLocation(MINECRAFT, "weakness");
    public static final ResourceLocation SPEED = new ResourceLocation(MINECRAFT, "speed");
    public static final ResourceLocation BLINDNESS = new ResourceLocation(MINECRAFT, "blindness");
    public static final ResourceLocation RESISTANCE = new ResourceLocation(MINECRAFT, "resistance");
    public static final ResourceLocation SLOW_FALLING = new ResourceLocation(MINECRAFT, "slow_falling");
    public static final ResourceLocation INVISIBILITY = new ResourceLocation(MINECRAFT, "invisibility");
    public static final ResourceLocation JUMP_BOOST = new ResourceLocation(MINECRAFT, "jump_boost");
    public static final ResourceLocation DARKNESS = new ResourceLocation(MINECRAFT, "darkness");
    public static final ResourceLocation BETRAYED = new ResourceLocation(JNE, "betrayed");
    public static final ResourceLocation WORSENING = new ResourceLocation(ATMOSPHERIC, "worsening");


    public static final String[] colors = new String[]{"white", "orange", "magenta", "light_blue", "light_gray", "gray", "black", "blue",
    "red", "green", "lime", "pink", "yellow", "purple", "cyan", "brown"};

    public static List<MobEffect> availableEffects = Collections.emptyList();

    public static void initializeDailySpecials() {
        List<MobEffect> allEffects = ForgeRegistries.MOB_EFFECTS.getValues().stream().toList();
        availableEffects = new ArrayList<>();
        for (MobEffect effect : allEffects) {
            if (!isBlacklisted(effect)) {
                availableEffects.add(effect);
            }
        }
    }

    private static boolean isBlacklisted(MobEffect effect) {
        ITagManager<MobEffect> mobEffectTags = ForgeRegistries.MOB_EFFECTS.tags();
        if (mobEffectTags == null) {
            return false;
        }
        return mobEffectTags.getTag(WOWMobEffectTags.DAILY_SPECIAL_BLACKLIST).contains(effect) || effect.isInstantenous();
    }
}
