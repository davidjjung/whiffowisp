package com.davigj.whiffowisp.core.other.compat;

import com.davigj.whiffowisp.core.other.WOWConstants;
import galena.doom_and_gloom.index.OEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.ModList;

public class DoomGloomCompat {
    public final static MobEffect FOG;

    static {
        FOG = ModList.get().isLoaded(WOWConstants.DOOM_GLOOM) ? OEffects.FOG.get() : null;
    }
}
