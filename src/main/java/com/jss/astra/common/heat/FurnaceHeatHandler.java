package com.jss.astra.common.heat;

import com.jss.astra.Astra;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Astra.MODID)
public class FurnaceHeatHandler {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {

        if (event.level.isClientSide()) return;
        if (event.phase != TickEvent.Phase.END) return;

        Level level = event.level;

        for (Player player : level.players()) {

            BlockPos playerPos = player.blockPosition();

            for (BlockPos pos : BlockPos.betweenClosed(
                    playerPos.offset(-1, -1, -1),
                    playerPos.offset(1, 1, 1))) {

                BlockEntity be = level.getBlockEntity(pos);
                if (!(be instanceof AbstractFurnaceBlockEntity)) continue;

                BlockState state = level.getBlockState(pos);
                if (!state.getValue(BlockStateProperties.LIT)) continue;

                AABB blockBox = new AABB(pos).inflate(0.01);
                AABB playerBox = player.getBoundingBox();

                if (!playerBox.intersects(blockBox)) continue;

                float damage = (be instanceof BlastFurnaceBlockEntity) ? 2.0F : 0.5F;
                player.hurt(level.damageSources().hotFloor(), damage);
            }
        }
    }
}