package com.davigj.whiffowisp.core.other;

import com.davigj.whiffowisp.core.WhiffOWisp;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WOWConstants {
    public static final BooleanProperty TRIMMED = BooleanProperty.create("trimmed");
    public static final BooleanProperty BOTTLED = BooleanProperty.create("bottled");

    public static final String MINECRAFT = "minecraft";
    public static final ResourceLocation ABSORPTION = new ResourceLocation(MINECRAFT, "absorption");
    public static final ResourceLocation WEAKNESS = new ResourceLocation(MINECRAFT, "weakness");
    public static final ResourceLocation SPEED = new ResourceLocation(MINECRAFT, "speed");
    public static final ResourceLocation BLINDNESS = new ResourceLocation(MINECRAFT, "blindness");
    public static final ResourceLocation SLOWNESS = new ResourceLocation(MINECRAFT, "slowness");
    public static final ResourceLocation SLOW_FALLING = new ResourceLocation(MINECRAFT, "slow_falling");
    public static final ResourceLocation INVISIBILITY = new ResourceLocation(MINECRAFT, "invisibility");
    public static final ResourceLocation JUMP_BOOST = new ResourceLocation(MINECRAFT, "jump_boost");

    public static final String SUPPLEMENTARIES = "supplementaries";
    public static final String BUZZIER_BEES = "buzzier_bees";
    public static final String ARCHITECTS_PALETTE = "architects_palette";

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
        return mobEffectTags.getTag(WOWMobEffectTags.DAILY_SPECIAL_BLACKLIST).contains(effect);
    }
}
