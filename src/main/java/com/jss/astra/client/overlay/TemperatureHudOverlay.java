package com.jss.astra.client.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class TemperatureHudOverlay implements IGuiOverlay {

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.level == null) return;
        if (mc.hitResult == null || mc.hitResult.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult hit = (BlockHitResult) mc.hitResult;
        BlockPos pos = hit.getBlockPos();

        // placeholder finché HeatField non esiste → 20°C = 293.15K
        double kelvin = getTemperatureAt(pos);

        String testo = String.format("%.1f K", kelvin);
        int colore = getColore(kelvin);

        // posizione: leggermente a destra della croce
        int x = screenWidth / 2 + 12;
        int y = screenHeight / 2 - 4;

        guiGraphics.drawString(mc.font, testo, x, y, colore, false);
    }

    private double getTemperatureAt(BlockPos pos) {
        //da sostituire con il sistema ricorda!
        return 293.15;
    }

    private int getColore(double kelvin) {
        double c = kelvin - 273.15;

        if (c >= 201)  return 0xFF8B0000; // rosso vino scuro
        if (c >= 101)  return 0xFFFF0000; // rosso
        if (c >= 51)   return 0xFFFFA500; // arancione
        if (c >= 38)   return 0xFFFFFF00; // giallo
        if (c >= 0)    return 0xFFFFFFFF; // bianco

        if (c >= -20)  return 0xFFE8F4FF; // bianco tendente al celestino
        if (c >= -30)  return 0xFFADD8E6; // celestino chiaro
        if (c >= -80)  return 0xFF87CEEB; // celeste
        if (c >= -100) return 0xFF0000FF; // blu
        if (c >= -200) return 0xFF00008B; // blu notte
        return 0xFF4B0082;                // viola scuro → zero assoluto
    }
}
