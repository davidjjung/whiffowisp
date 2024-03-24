package com.davigj.whiffowisp.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WOWConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> scentFX;
        public final ForgeConfigSpec.ConfigValue<Boolean> redRedemptionTrade;
        public final ForgeConfigSpec.ConfigValue<Boolean> caravanSpiceTrade;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("changes");
            builder.push("candles");
            scentFX = builder.comment("Do scented candles affect living entities").define("Scent FX", true);
            builder.pop();
            builder.push("trades");
            redRedemptionTrade = builder.comment("Do expert butchers trade red redemption candles").define("Red redemption trade", true);
            caravanSpiceTrade = builder.comment("Do wandering traders trade caravan spice candles").define("Caravan spice trade", true);
            builder.pop();
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
