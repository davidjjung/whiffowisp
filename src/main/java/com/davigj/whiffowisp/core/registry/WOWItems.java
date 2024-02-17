package com.davigj.whiffowisp.core.registry;

import com.davigj.whiffowisp.core.WhiffOWisp;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WOWItems {
    public static final ItemSubRegistryHelper HELPER = WhiffOWisp.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> NETHERWAX = HELPER.createItem("netherwax", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}