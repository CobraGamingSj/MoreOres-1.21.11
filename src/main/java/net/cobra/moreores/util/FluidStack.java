package net.cobra.moreores.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public class FluidStack {
    private FluidVariant variant;
    private long amount;

    public FluidStack(FluidVariant variant, long amount) {
        this.variant = variant;
        this.amount = amount;
    }

    public FluidVariant getVariant() {
        return variant;
    }

    public long getAmount() {
        return amount;
    }

    public long setAmount(long amount) {
        return this.amount = amount;
    }

    public static long convertDropletsToMb(long drops) {
        return (drops / 81);
    }

    public static long convertMbToDroplets(long milliBuckets) {
        return milliBuckets * 81;
    }
}
