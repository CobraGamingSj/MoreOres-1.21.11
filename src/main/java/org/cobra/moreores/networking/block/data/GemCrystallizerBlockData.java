package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.GemCrystallizeBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.registry.ModItemTags;
import org.lwjgl.glfw.GLFW;

public record GemCrystallizerBlockData(int keyCode, BlockPos pos) implements CustomPayload {
    public static final Id<GemCrystallizerBlockData> ID = new Id<>(MoreOresModInitializer.id("c_block_data"));

    public static final PacketCodec<RegistryByteBuf, GemCrystallizerBlockData> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, GemCrystallizerBlockData::keyCode,
            BlockPos.PACKET_CODEC, GemCrystallizerBlockData::pos,
            GemCrystallizerBlockData::new
    );

    public void handle(ServerPlayNetworking.Context context) {
        context.server().execute(() -> {
            ServerWorld world = context.player().getEntityWorld();
            ServerPlayerEntity player = context.player();
            boolean alt = keyCode == GLFW.GLFW_KEY_LEFT_ALT || keyCode == GLFW.GLFW_KEY_RIGHT_ALT;

            if(alt) {
                ItemStack heldStack = player.getStackInHand(Hand.MAIN_HAND);

                if(!heldStack.isEmpty() && world.getBlockEntity(pos) instanceof GemCrystallizeBlockEntity be) {
                    ItemStack energyStack = be.energyStack();
                    ItemStack inputStack = be.ingredientStack();
                    ItemStack inputBeforeStack = be.ingredientAfterStack();
                    ItemStack radiantDustStack = be.radiantDustStack();

                    if(heldStack.isOf(ModItems.RADIANT_DUST)) {
                        if(radiantDustStack.isEmpty()) {
                            be.setStack(GemCrystallizeBlockEntity.RADIANT_DUST_SLOT, heldStack.copyWithCount(heldStack.getCount()));
                            heldStack.decrement(heldStack.getCount());
                        } else if (ItemStack.areItemsEqual(radiantDustStack, heldStack) && radiantDustStack.getCount() < radiantDustStack.getMaxCount()) {
                            radiantDustStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }

                    if(heldStack.getItem() == ModItems.ENERGY_INGOT) {
                        if(energyStack.isEmpty()) {
                            be.setStack(GemCrystallizeBlockEntity.ENERGY_SOURCE_SLOT, heldStack.copyWithCount(heldStack.getCount()));
                            heldStack.decrement(heldStack.getCount());
                        } else if (ItemStack.areItemsEqual(energyStack, heldStack) && energyStack.getCount() < energyStack.getMaxCount()) {
                            energyStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }

                    if(heldStack.isIn(ModItemTags.RAW_GEMSTONE)) {
                        if(inputStack.isEmpty()) {
                            be.setStack(GemCrystallizeBlockEntity.INGREDIENT_BEFORE_SLOT, heldStack.copyWithCount(heldStack.getCount()));
                            heldStack.decrement(heldStack.getCount());
                        } else if (ItemStack.areItemsEqual(inputStack, heldStack)) {
                            inputStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }
                }
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
