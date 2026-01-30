package com.davigj.whiffowisp.core.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WOWSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "whiffowisp");

    public static final RegistryObject<SoundEvent> CANDLE_TP =
            SOUND_EVENTS.register(
                    "candle_tp",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation("whiffowisp", "candle_tp")
                    )
            );

    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
