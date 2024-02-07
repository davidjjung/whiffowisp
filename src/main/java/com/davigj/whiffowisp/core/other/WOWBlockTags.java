package com.davigj.whiffowisp.core.other;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class WOWBlockTags {
    public static final TagKey<Block> AIR_PURIFIERS = blockTag();

    private static TagKey<Block> blockTag() {
        return TagUtil.blockTag(WhiffOWisp.MOD_ID, "air_purifiers");
    }
}
