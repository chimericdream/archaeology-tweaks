package com.chimericdream.archtweaks.mixin;

import com.chimericdream.archtweaks.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import org.spongepowered.asm.mixin.Unique;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Unique
    protected boolean canHideItems(Block target) {
        return
            target.equals(Blocks.CLAY)
            || target.equals(Blocks.DIRT)
            || target.equals(Blocks.GRAVEL)
            || target.equals(Blocks.MUD)
            || target.equals(Blocks.PACKED_MUD)
            || target.equals(Blocks.RED_SAND)
            || target.equals(Blocks.ROOTED_DIRT)
            || target.equals(Blocks.SAND)
            || target.equals(Blocks.SOUL_SAND)
            || target.equals(Blocks.SOUL_SOIL);
    }

    @Unique
    protected BlockState getHiddenState(Block target) {
        if (target.equals(Blocks.CLAY)) {
            return ModBlocks.SUSPICIOUS_CLAY.getDefaultState();
        }

        if (target.equals(Blocks.DIRT)) {
            return ModBlocks.SUSPICIOUS_DIRT.getDefaultState();
        }

        if (target.equals(Blocks.GRAVEL)) {
            return Blocks.SUSPICIOUS_GRAVEL.getDefaultState();
        }

        if (target.equals(Blocks.MUD)) {
            return ModBlocks.SUSPICIOUS_MUD.getDefaultState();
        }

        if (target.equals(Blocks.PACKED_MUD)) {
            return ModBlocks.SUSPICIOUS_PACKED_MUD.getDefaultState();
        }

        if (target.equals(Blocks.RED_SAND)) {
            return ModBlocks.SUSPICIOUS_RED_SAND.getDefaultState();
        }

        if (target.equals(Blocks.ROOTED_DIRT)) {
            return ModBlocks.SUSPICIOUS_ROOTED_DIRT.getDefaultState();
        }

        if (target.equals(Blocks.SAND)) {
            return Blocks.SUSPICIOUS_SAND.getDefaultState();
        }

        if (target.equals(Blocks.SOUL_SAND)) {
            return ModBlocks.SUSPICIOUS_SOUL_SAND.getDefaultState();
        }

        return ModBlocks.SUSPICIOUS_SOUL_SOIL.getDefaultState();
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        Block target = state.getBlock();
        if (!canHideItems(target)) {
            return ActionResult.PASS;
        }

        BlockState newState = getHiddenState(target);

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
            if (!player.isCreative()) {
                player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
