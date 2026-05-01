package com.jss.astra.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;

public class HeatCapabilityProvider implements ICapabilitySerializable<CompoundTag> {

    private final HeatCapability backend = new HeatCapability();
    private final LazyOptional<HeatCapability> optional = LazyOptional.of(() -> backend);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == HeatCapabilityStorage.HEAT_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("heat", backend.getHeat());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        backend.addHeat(nbt.getInt("heat"));
    }
}