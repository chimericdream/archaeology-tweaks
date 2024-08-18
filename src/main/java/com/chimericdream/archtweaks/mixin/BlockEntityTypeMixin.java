package com.chimericdream.archtweaks.mixin;

import com.chimericdream.archtweaks.block.ModBlocks;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BlockEntityType.Builder;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin<T extends BlockEntity> {
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static <T extends BlockEntity> void create(String id, Builder<T> builder, CallbackInfoReturnable<BlockEntityType<T>> cir) {
        if (id.equals("brushable_block")) {
            Builder<BrushableBlockEntity> actualBuilder = BlockEntityType.Builder.create(
                BrushableBlockEntity::new,
                Blocks.SUSPICIOUS_SAND,
                Blocks.SUSPICIOUS_GRAVEL,
                ModBlocks.SUSPICIOUS_CLAY,
                ModBlocks.SUSPICIOUS_DIRT,
                ModBlocks.SUSPICIOUS_MUD,
                ModBlocks.SUSPICIOUS_PACKED_MUD,
                ModBlocks.SUSPICIOUS_RED_SAND,
                ModBlocks.SUSPICIOUS_ROOTED_DIRT,
                ModBlocks.SUSPICIOUS_SOUL_SAND,
                ModBlocks.SUSPICIOUS_SOUL_SOIL
            );
            Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
            cir.setReturnValue((BlockEntityType)Registry.register(Registries.BLOCK_ENTITY_TYPE, id, actualBuilder.build(type)));
        }
    }
}
