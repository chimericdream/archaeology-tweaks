package com.chimericdream.archtweaks;

import com.chimericdream.archtweaks.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchTweaksMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModInfo.MOD_ID);

    public static final ModBlocks BLOCKS;

    static {
        BLOCKS = new ModBlocks();
    }

    @Override
    public void onInitialize() {
        LOGGER.info("[archtweaks] Registering blocks");
        BLOCKS.register();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> content.addAfter(
            Items.SUSPICIOUS_SAND,
            ModBlocks.SUSPICIOUS_RED_SAND
        ));
    }
}
