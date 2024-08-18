package com.chimericdream.archtweaks.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class BrushableFloatingBlock extends BlockWithEntity {
    public static final MapCodec<BrushableBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(Registries.BLOCK.getCodec().fieldOf("turns_into").forGetter(BrushableBlock::getBaseBlock), Registries.SOUND_EVENT.getCodec().fieldOf("brush_sound").forGetter(BrushableBlock::getBrushingSound), Registries.SOUND_EVENT.getCodec().fieldOf("brush_comleted_sound").forGetter(BrushableBlock::getBrushingCompleteSound), createSettingsCodec()).apply(instance, BrushableBlock::new);
    });
    private static final IntProperty DUSTED;
    public static final int field_42773 = 2;
    private final Block baseBlock;
    private final SoundEvent brushingSound;
    private final SoundEvent brushingCompleteSound;

    public MapCodec<BrushableBlock> getCodec() {
        return CODEC;
    }

    public BrushableFloatingBlock(Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, AbstractBlock.Settings settings) {
        super(settings);
        this.baseBlock = baseBlock;
        this.brushingSound = brushingSound;
        this.brushingCompleteSound = brushingCompleteSound;
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(DUSTED, 0));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{DUSTED});
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 2);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        world.scheduleBlockTick(pos, this, 2);
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity var6 = world.getBlockEntity(pos);
        if (var6 instanceof BrushableBlockEntity brushableBlockEntity) {
            brushableBlockEntity.scheduledTick();
        }
    }

    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BrushableBlockEntity(pos, state);
    }

    public Block getBaseBlock() {
        return this.baseBlock;
    }

    public SoundEvent getBrushingSound() {
        return this.brushingSound;
    }

    public SoundEvent getBrushingCompleteSound() {
        return this.brushingCompleteSound;
    }

    static {
        DUSTED = Properties.DUSTED;
    }
}
