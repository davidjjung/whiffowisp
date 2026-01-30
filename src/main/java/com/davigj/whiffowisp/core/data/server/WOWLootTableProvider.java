package com.davigj.whiffowisp.core.data.server;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WOWLootTableProvider extends LootTableProvider {
    public WOWLootTableProvider(PackOutput output) {
        super(output, BuiltInLootTables.all(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(WOWBlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }

    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
    }


    public static class WOWBlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

        protected WOWBlockLoot() {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        public void generate() {
            this.add(WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.HOMESICK_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.HOMESICK_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get()));

            this.add(WOWBlocks.SOFT_BLANKET_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.SOFT_BLANKET_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.VANILLA_BUNNY_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.VANILLA_BUNNY_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.FOREST_HAZE_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.FOREST_HAZE_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.MIDSUMMER_NIGHT_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.MIDSUMMER_NIGHT_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.AUTUMN_WREATH_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.AUTUMN_WREATH_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.PINK_SANDS_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.PINK_SANDS_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.TARNATION_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.TARNATION_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.BROKEN_TRUST_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.BROKEN_TRUST_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.BLACK_CHERRY_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.BLACK_CHERRY_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.DESERT_SUNSET_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.DESERT_SUNSET_SCENTED_CANDLE.get()));
            this.add(WOWBlocks.DAILY_SPECIAL_SCENTED_CANDLE.get(), (block) -> createCandleDrops(WOWBlocks.DAILY_SPECIAL_SCENTED_CANDLE.get()));
        }


        @Override
        public Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(WhiffOWisp.MOD_ID)).collect(Collectors.toSet());
        }
    }


}
