package com.jss.astra.common.registry;

import com.jss.astra.Astra;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCreativeTabs {

    // 👇 QUESTO è il modo giusto in 1.20.1
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, Astra.MODID);

    public static final RegistryObject<CreativeModeTab> ASTRA_TAB = CREATIVE_TABS.register("astra_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.astra_tab"))
                    .icon(() -> new ItemStack(ModItems.GUIDE_BOOK.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.GUIDE_BOOK.get());
                    })
                    .build()
    );

    public static void register(IEventBus bus) {
        CREATIVE_TABS.register(bus);
    }
}