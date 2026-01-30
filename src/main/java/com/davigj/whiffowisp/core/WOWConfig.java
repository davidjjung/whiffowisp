package com.davigj.whiffowisp.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WOWConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> scentFX;
        public final ForgeConfigSpec.ConfigValue<Boolean> redRedemptionTrade;
        public final ForgeConfigSpec.ConfigValue<Boolean> softBlanketTrade;
        public final ForgeConfigSpec.ConfigValue<Boolean> caravanSpiceTrade;
        public final ForgeConfigSpec.ConfigValue<Integer> dailySpecialDuration;
        public final ForgeConfigSpec.ConfigValue<Boolean> distantSongTeleportsItems;
        public final ForgeConfigSpec.ConfigValue<Integer> distantSongTeleportDistance;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("changes");
            builder.push("candles");
            scentFX = builder.comment("Do scented candles affect living entities").define("Scent FX", true);
            dailySpecialDuration = builder.comment("Ticks per day for daily scented candles").define("Daily special duration", 24000);
            distantSongTeleportsItems = builder.comment("Can Distant Song Scented Candles teleport items").define("Distant Song teleports items", false);
            distantSongTeleportDistance = builder.comment("Horizontal teleport distance for distant song candles (in blocks)").defineInRange("Distant song teleport distance", 6, 1, 64);
            builder.pop();
            builder.push("trades");
            redRedemptionTrade = builder.comment("Do expert butchers trade red redemption candles").define("Red redemption trade", true);
            softBlanketTrade = builder.comment("Do expert butchers trade soft blanket candles").define("Soft blanket trade", true);
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
