package com.jss.astra.common.capability;

public class HeatCapability {

    private int heat = 0;
    private final int maxHeat = 100;

    public int getHeat() {
        return heat;
    }

    public void addHeat(int amount) {
        heat = Math.min(maxHeat, heat + amount);
    }

    public void reset() {
        heat = 0;
    }

    public float getPercent() {
        return (float) heat / maxHeat;
    }
}