package com.davigj.whiffowisp.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WOWConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> naturalRemedy;
        public final ForgeConfigSpec.ConfigValue<Integer> remedyRadius;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("changes");
            naturalRemedy = builder.comment("Do air purifiers nullify nearby candle smoke").define("Natural remedy", true);
            remedyRadius = builder.comment("The radius in which air purifiers nullify candle smoke").define("Purifier radius", 3);
            builder.pop();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final WOWConfig.Common COMMON;


    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WOWConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
