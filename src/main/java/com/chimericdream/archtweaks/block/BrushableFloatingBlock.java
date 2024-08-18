package com.chimericdream.archtweaks.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BrushableFloatingBlock extends BrushableBlock {
    public BrushableFloatingBlock(Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, AbstractBlock.Settings settings) {
        super(baseBlock, brushingSound, brushingCompleteSound, settings);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity var6 = world.getBlockEntity(pos);
        if (var6 instanceof BrushableBlockEntity brushableBlockEntity) {
            brushableBlockEntity.scheduledTick();
        }
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    }
}
