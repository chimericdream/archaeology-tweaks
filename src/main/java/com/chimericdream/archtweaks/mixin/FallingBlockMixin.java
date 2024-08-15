package com.chimericdream.archtweaks.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FallingBlock.class)
public abstract class FallingBlockMixin extends Block {
    public FallingBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        Block target = state.getBlock();
        if (!target.equals(Blocks.SAND) && !target.equals(Blocks.GRAVEL)) {
            return ActionResult.PASS;
        }

        if (!player.isSneaking()) {
            return ActionResult.PASS;
        }

        BlockState newState;
        if (target.equals(Blocks.SAND)) {
            newState = Blocks.SUSPICIOUS_SAND.getDefaultState();
        } else {
            newState = Blocks.SUSPICIOUS_GRAVEL.getDefaultState();
        }

        Item offhandItem = player.getOffHandStack().getItem();

        if (offhandItem.equals(Items.BRUSH) && player.getMainHandStack() != ItemStack.EMPTY) {
            world.setBlockState(pos, newState);
            NbtElement itemData = player.getMainHandStack().encode(world.getRegistryManager());

            BrushableBlockEntity be = (BrushableBlockEntity) world.getBlockEntity(pos);
            NbtCompound nbt = new NbtCompound();
            nbt.put("item", itemData);

            assert be != null;

            be.readNbt(nbt, world.getRegistryManager());
            world.addBlockEntity(be);
            player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
