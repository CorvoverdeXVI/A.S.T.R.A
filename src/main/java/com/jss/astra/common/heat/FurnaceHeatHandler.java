package com.jss.astra.common.heat;

import com.jss.astra.Astra;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Astra.MODID)
public class FurnaceHeatHandler {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {

        // solo server
        if (event.level.isClientSide()) return;

        if (event.phase != TickEvent.Phase.END) return;

        Level level = event.level;

        for (Player player : level.players()) {

            BlockPos playerPos = player.blockPosition();

            // controlliamo SOLO blocchi immediatamente vicini (ottimizzato)
            for (BlockPos pos : BlockPos.betweenClosed(
                    playerPos.offset(-1, -1, -1),
                    playerPos.offset(1, 1, 1))) {

                BlockEntity be = level.getBlockEntity(pos);

                if (!(be instanceof FurnaceBlockEntity furnace)) continue;

                // furnace accesa?
                boolean lit = furnace.getBlockState()
                        .getValue(BlockStateProperties.LIT);

                if (!lit) continue;

                // hitbox blocco
                AABB blockBox = new AABB(pos);

                // hitbox player
                AABB playerBox = player.getBoundingBox();

                // collisione reale (tocca il blocco)
                if (playerBox.intersects(blockBox)) {
                    player.hurt(level.damageSources().hotFloor(), 0.5F);
                }
            }
        }
    }
}