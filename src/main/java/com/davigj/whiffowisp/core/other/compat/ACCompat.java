package com.davigj.whiffowisp.core.other.compat;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.server.entity.living.UnderzealotEntity;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.github.alexmodguy.alexscaves.server.message.UpdateItemTagMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.world.level.block.CandleBlock.CANDLES;

public class ACCompat {
    public static void blackCherryBoost(Player player, BlockState state) {
        Level level = player.level();
        ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.is((Item) ACItemRegistry.CLOAK_OF_DARKNESS.get()) && !level.isClientSide) {
            CompoundTag tag = stack.getOrCreateTag();
            int charge = tag.getInt("CloakCharge");
            boolean flag = false;
            if (charge < (Integer) AlexsCaves.COMMON_CONFIG.darknessCloakChargeTime.get()) {
                charge += state.getValue(CANDLES) / 2;
                tag.putInt("CloakCharge", charge);
                flag = true;
            }
            if (flag) {
                AlexsCaves.sendNonLocal(new UpdateItemTagMessage(player.getId(), stack), (ServerPlayer)player);
            }
        }
    }

    public static void voidCloudParticle(Level level, Vec3 vec3, float f) {
        if (f < 0.015) {
            if (level.getRandom().nextBoolean()) {
                level.addParticle(ACParticleRegistry.VOID_BEING_CLOUD.get(), vec3.x + f, vec3.y + f, vec3.z + f, 0.0D, 0.0D, 0.0D);
            } else {
                level.addParticle(ACParticleRegistry.VOID_BEING_EYE.get(), vec3.x + (2 * f), vec3.y + (2 * f), vec3.z + (2 * f), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
