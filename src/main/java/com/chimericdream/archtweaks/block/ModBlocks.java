package com.chimericdream.archtweaks.block;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks {
    public static final SuspiciousClayBlock SUSPICIOUS_CLAY;
    public static final SuspiciousDirtBlock SUSPICIOUS_DIRT;
    public static final SuspiciousMudBlock SUSPICIOUS_MUD;
    public static final SuspiciousPackedMudBlock SUSPICIOUS_PACKED_MUD;
    public static final SuspiciousRedSandBlock SUSPICIOUS_RED_SAND;
    public static final SuspiciousRootedDirtBlock SUSPICIOUS_ROOTED_DIRT;
    public static final SuspiciousSoulSandBlock SUSPICIOUS_SOUL_SAND;
    public static final SuspiciousSoulSoilBlock SUSPICIOUS_SOUL_SOIL;

    static {
        SUSPICIOUS_CLAY = new SuspiciousClayBlock();
        SUSPICIOUS_DIRT = new SuspiciousDirtBlock();
        SUSPICIOUS_MUD = new SuspiciousMudBlock();
        SUSPICIOUS_PACKED_MUD = new SuspiciousPackedMudBlock();
        SUSPICIOUS_RED_SAND = new SuspiciousRedSandBlock();
        SUSPICIOUS_ROOTED_DIRT = new SuspiciousRootedDirtBlock();
        SUSPICIOUS_SOUL_SAND = new SuspiciousSoulSandBlock();
        SUSPICIOUS_SOUL_SOIL = new SuspiciousSoulSoilBlock();
    }

    public void register() {
        Registry.register(Registries.BLOCK, SuspiciousClayBlock.BLOCK_ID, SUSPICIOUS_CLAY);
        Registry.register(Registries.ITEM, SuspiciousClayBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_CLAY, new Item.Settings()));

        Registry.register(Registries.BLOCK, SuspiciousDirtBlock.BLOCK_ID, SUSPICIOUS_DIRT);
        Registry.register(Registries.ITEM, SuspiciousDirtBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_DIRT, new Item.Settings()));

        Registry.register(Registries.BLOCK, SuspiciousMudBlock.BLOCK_ID, SUSPICIOUS_MUD);
        Registry.register(Registries.ITEM, SuspiciousMudBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_MUD, new Item.Settings()));

        Registry.register(Registries.BLOCK, SuspiciousPackedMudBlock.BLOCK_ID, SUSPICIOUS_PACKED_MUD);
        Registry.register(Registries.ITEM, SuspiciousPackedMudBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_PACKED_MUD, new Item.Settings()));

        Registry.register(Registries.BLOCK, SuspiciousRedSandBlock.BLOCK_ID, SUSPICIOUS_RED_SAND);
        Registry.register(Registries.ITEM, SuspiciousRedSandBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_RED_SAND, new Item.Settings()));

        Registry.register(Registries.BLOCK, SuspiciousRootedDirtBlock.BLOCK_ID, SUSPICIOUS_ROOTED_DIRT);
        Registry.register(Registries.ITEM, SuspiciousRootedDirtBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_ROOTED_DIRT, new Item.Settings()));

        Registry.register(Registries.BLOCK, SuspiciousSoulSandBlock.BLOCK_ID, SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.ITEM, SuspiciousSoulSandBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_SOUL_SAND, new Item.Settings()));

        Registry.register(Registries.BLOCK, SuspiciousSoulSoilBlock.BLOCK_ID, SUSPICIOUS_SOUL_SOIL);
        Registry.register(Registries.ITEM, SuspiciousSoulSoilBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_SOUL_SOIL, new Item.Settings()));
    }
}
