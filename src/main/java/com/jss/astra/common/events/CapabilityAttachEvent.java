package com.jss.astra.common.events;

import com.jss.astra.common.capability.HeatCapability;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityAttachEvent {

    @SubscribeEvent
    public void attachCaps(AttachCapabilitiesEvent<BlockEntity> event) {

        if (event.getObject() instanceof FurnaceBlockEntity) {
            event.addCapability(
                    new net.minecraft.resources.ResourceLocation("astra", "heat"),
                    new HeatCapabilityProvider()
            );
        }
    }
}