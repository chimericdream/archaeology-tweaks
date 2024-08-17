package com.chimericdream.archtweaks.block;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks {
    public static final SuspiciousRedSandBlock SUSPICIOUS_RED_SAND;

    static {
        SUSPICIOUS_RED_SAND = new SuspiciousRedSandBlock();
    }

    public void register() {
        Registry.register(Registries.BLOCK, SuspiciousRedSandBlock.BLOCK_ID, SUSPICIOUS_RED_SAND);
        Registry.register(Registries.ITEM, SuspiciousRedSandBlock.BLOCK_ID, new BlockItem(SUSPICIOUS_RED_SAND, new Item.Settings()));
    }
}
