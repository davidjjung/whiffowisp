package com.davigj.whiffowisp.core.data.server;

import com.davigj.whiffowisp.core.registry.WOWBlocks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.FunctionUserBuilder;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WOWLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables;

    public WOWLootTableProvider(DataGenerator p_124437_) {
        super(p_124437_);
        this.tables = ImmutableList.of(Pair.of(WOWLootTableProvider.WOWBlockLoot::new, LootContextParamSets.BLOCK));
    }

    @Override
    public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return tables;
    }

    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
    }

    private static class WOWBlockLoot extends BlockLoot {

        @Override
        public void addTables() {
            this.add(WOWBlocks.RED_REDEMPTION_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.CARAVAN_SPICE_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.FIRESIDE_SPAT_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.SEAFARING_DREAM_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.ARTS_AND_CRAFTS_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.HOMESICK_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.DISTANT_SONG_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);

            this.add(WOWBlocks.SOFT_BLANKET_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.VANILLA_BUNNY_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.FOREST_HAZE_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.MIDSUMMER_NIGHT_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.AUTUMN_WREATH_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.PINK_SANDS_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.TARNATION_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
            this.add(WOWBlocks.DAILY_SPECIAL_SCENTED_CANDLE.get(), WOWBlockLoot::createCandleDrops);
        }


        public Iterable<Block> getKnownBlocks() {
            return (Iterable) ForgeRegistries.BLOCKS.getValues().stream().filter((block) -> {
                return ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals("whiffowisp");
            }).collect(Collectors.toSet());
        }
    }


}
