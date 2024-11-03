package com.davigj.whiffowisp.core.registry;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.CreativeModeTabs.TOOLS_AND_UTILITIES;
import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WOWItems {
    public static final ItemSubRegistryHelper HELPER = WhiffOWisp.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> NETHERWAX = HELPER.createItem("netherwax", () -> new Item(new Item.Properties()));

    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(WhiffOWisp.MOD_ID)
                .tab(CreativeModeTabs.INGREDIENTS)
                .addItemsAfter(of(Items.NETHER_WART), NETHERWAX);

    }
}