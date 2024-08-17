package com.chimericdream.archtweaks.block;

import com.chimericdream.archtweaks.ModInfo;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.RED_SAND;

public class SuspiciousRedSandBlock extends BrushableBlock {
    public static final Identifier BLOCK_ID = Identifier.of(ModInfo.MOD_ID, "suspicious_red_sand");

    public SuspiciousRedSandBlock() {
        super(
            RED_SAND,
            SoundEvents.ITEM_BRUSH_BRUSHING_SAND,
            SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE,
            AbstractBlock.Settings
                .create()
                .mapColor(MapColor.ORANGE)
                .instrument(NoteBlockInstrument.SNARE)
                .strength(0.25F)
                .sounds(BlockSoundGroup.SUSPICIOUS_SAND)
                .pistonBehavior(PistonBehavior.DESTROY)
        );
    }
}
