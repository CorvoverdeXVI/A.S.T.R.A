package com.jss.astra;

import com.jss.astra.common.registry.ModCreativeTabs;
import com.jss.astra.common.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Astra.MODID)
public class Astra {

    public static final String MODID = "astra";

    public Astra() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModCreativeTabs.register(bus);
    }
}