package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.registry.ModItemTags;
import org.lwjgl.glfw.GLFW;

public record GemPurifierBlockData(int keyCode, BlockPos pos) implements CustomPayload {
    public static final Id<GemPurifierBlockData> ID = new Id<>(MoreOresModInitializer.id("block_key"));

    public static final PacketCodec<RegistryByteBuf, GemPurifierBlockData> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, GemPurifierBlockData::keyCode,
            BlockPos.PACKET_CODEC, GemPurifierBlockData::pos,
            GemPurifierBlockData::new
    );

    public void handle(ServerPlayNetworking.Context context) {
        context.server().execute(() -> {
            ServerWorld world = context.player().getEntityWorld();
            ServerPlayerEntity player = context.player();
            boolean alt = keyCode == GLFW.GLFW_KEY_LEFT_ALT || keyCode == GLFW.GLFW_KEY_RIGHT_ALT;

            if(world.getBlockEntity(pos) instanceof GemPurifierBlockEntity be) {
                if(alt) {

                    ItemStack heldStack = player.getMainHandStack();

                    ItemStack energyStack = be.energyStack();
                    ItemStack fluidStack = be.fluidStack();
                    ItemStack inputStack = be.ingredientStack();

                    if((heldStack.getItem() == ModItems.ENERGY_INGOT || heldStack.getItem() == ModBlocks.ENERGY_BLOCK.asItem())) {
                        if(energyStack.isEmpty()) {
                            be.setStack(GemPurifierBlockEntity.ENERGY_SOURCE_SLOT, heldStack.copyWithCount(heldStack.getCount()));
                            heldStack.decrement(heldStack.getCount());} else if (ItemStack.areItemsEqual(energyStack, heldStack) && energyStack.getCount() < energyStack.getMaxCount()) {
                            energyStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }

                    if(heldStack.isOf(Items.WATER_BUCKET)) {
                        if(fluidStack.isEmpty()) {
                            be.setStack(GemPurifierBlockEntity.WATER_SOURCE_SLOT, heldStack.copy());
                            heldStack.decrement(1);
                        } else if (ItemStack.areItemsEqual(fluidStack, heldStack)) {
                            fluidStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }

                    if(heldStack.isIn(ModItemTags.RAW_GEMSTONE) || heldStack.isIn(ModItemTags.RAW_GEMSTONE_BLOCKS)) {
                        if(inputStack.isEmpty()) {
                            be.setStack(GemPurifierBlockEntity.INGREDIENT_SLOT, heldStack.copyWithCount(heldStack.getCount()));
                            heldStack.decrement(heldStack.getCount());
                        } else if (ItemStack.areItemsEqual(inputStack, heldStack)) {
                            inputStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }
                } else {
                    player.openHandledScreen(be);
                }
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
