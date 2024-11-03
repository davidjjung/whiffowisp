package com.davigj.whiffowisp.core;

import com.davigj.whiffowisp.core.data.client.WOWBlockStateProvider;
import com.davigj.whiffowisp.core.data.client.WOWItemModelProvider;
import com.davigj.whiffowisp.core.data.server.WOWBlockTagsProvider;
import com.davigj.whiffowisp.core.data.server.WOWLootTableProvider;
import com.davigj.whiffowisp.core.data.server.WOWRecipeProvider;
import com.davigj.whiffowisp.core.registry.WOWBlocks;
import com.davigj.whiffowisp.core.registry.WOWItems;
import com.davigj.whiffowisp.core.registry.WOWParticleTypes;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.CompletableFuture;

import static com.davigj.whiffowisp.core.other.WOWBlockStatements.addTrimStates;

@Mod(WhiffOWisp.MOD_ID)
public class WhiffOWisp {
    public static final String MOD_ID = "whiffowisp";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public WhiffOWisp() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> WOWItems::buildCreativeTabContents);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> WOWBlocks::buildCreativeTabContents);

		REGISTRY_HELPER.register(bus);
        WOWParticleTypes.PARTICLE_TYPES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);
        context.registerConfig(ModConfig.Type.COMMON, WOWConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            addTrimStates();
        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean server = event.includeServer();

        WOWBlockTagsProvider blockTags = new WOWBlockTagsProvider(output, provider, helper);
        generator.addProvider(server, blockTags);

        generator.addProvider(server, new WOWLootTableProvider(output));
        generator.addProvider(server, new WOWRecipeProvider(output));

        boolean includeClient = event.includeClient();
        generator.addProvider(includeClient, new WOWItemModelProvider(output, helper));
        generator.addProvider(includeClient, new WOWBlockStateProvider(output, helper));
    }
}