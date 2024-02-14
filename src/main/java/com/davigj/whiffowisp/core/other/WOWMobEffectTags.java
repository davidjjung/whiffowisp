package com.davigj.whiffowisp.core.other;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

public class WOWMobEffectTags {
    public static final TagKey<MobEffect> DAILY_SPECIAL_BLACKLIST = mobEffectTag("daily_special_blacklist");
    public WOWMobEffectTags() {
    }
    private static TagKey<MobEffect> mobEffectTag(String name) {
        return TagUtil.mobEffectTag(WhiffOWisp.MOD_ID, name);
    }
}
