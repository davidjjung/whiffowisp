package com.davigj.whiffowisp.core.other.compat;

import com.github.alexmodguy.alexscaves.AlexsCaves;
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
}
