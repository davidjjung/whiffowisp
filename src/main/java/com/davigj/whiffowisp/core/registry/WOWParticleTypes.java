package com.davigj.whiffowisp.core.registry;

import com.davigj.whiffowisp.core.WhiffOWisp;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WhiffOWisp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WOWParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WhiffOWisp.MOD_ID);

    public static final RegistryObject<SimpleParticleType> SMALL_NETHER_BRASS_FLAME = PARTICLE_TYPES.register("small_nether_brass_flame", () -> new SimpleParticleType(false));
    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.register(SMALL_NETHER_BRASS_FLAME.get(), FlameParticle.SmallFlameProvider::new);
    }
}
