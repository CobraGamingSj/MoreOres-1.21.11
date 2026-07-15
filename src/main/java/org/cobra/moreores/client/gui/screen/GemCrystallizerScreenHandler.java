package org.cobra.moreores.client.gui.screen;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.GemCrystallizerBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.networking.block.data.GemCrystallizerDataSynchronizer;
import org.cobra.moreores.registry.ModItemTags;

public class GemCrystallizerScreenHandler extends AbstractGemMachineScreenHandler<GemCrystallizerBlockEntity> {
    private final Inventory inventory;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;

    public GemCrystallizerScreenHandler(int syncId, PlayerInventory playerInventory, GemCrystallizerDataSynchronizer data) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(data.blockPos()),
                new ArrayPropertyDelegate(4));
    }

    public GemCrystallizerScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate delegate) {
        super(ModScreenHandlerType.GEM_CRYSTALLIZER_SCREEN_HANDLER, syncId, blockEntity.getPos(), (GemCrystallizerBlockEntity) blockEntity);
        checkSize((Inventory) blockEntity, 11);

        this.inventory = (Inventory) blockEntity;
        this.context = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());
        this.propertyDelegate = delegate;

        this.addSlot(new Slot(inventory, 0, 47, 22) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.GEMSTONE_BLOCKS) || stack.isIn(ModItemTags.GEMSTONE);
            }
        }); // Input Before

        this.addSlot(new Slot(inventory, 1, 87, 22) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.GEMSTONE_BLOCKS) 
                        || stack.isIn(ModItemTags.GEMSTONE) || stack.isOf(Blocks.OBSIDIAN.asItem());
            }
        }); // Input After

        this.addSlot(new Slot(inventory, 2, 67, 72) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.CRYSTALLIZED);
            }
        }); // Result
        
        this.addSlot(new Slot(inventory, 3, 13, 21) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
            }
        }); // Energy Input

        this.addSlot(new Slot(inventory, 4, 39, 59) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.RADIANT_DUST);
            }
        }); // Radiant Slot

        this.addSlot(new Slot(inventory, 5, 92, 59)); // Redstone Slot
        
        addSecondAdditionalInventory(inventory);

        addPlayerGenericInventory(playerInventory);
        addPlayerHotbarInventory(playerInventory);

        addProperties(delegate);
    }

    @Override
    public void addSecondAdditionalInventory(Inventory playerInventory) {
        for (int i = 0; i < 5; ++i) {
            this.addSlot(new Slot(playerInventory, 6 + i, 179, 97 + i * 18));
        }
    }

    public boolean isPolishing() {
        return propertyDelegate.get(0) > 0;
    }

    public int getRedstoneDust() {
        return this.propertyDelegate.get(3);
    }
    
    public int getDustCount() {
        return propertyDelegate.get(2);
    }

    public int progressGetter() {
        int progress = this.propertyDelegate.get(0); //Progress
        int maxProgress = this.propertyDelegate.get(1); //Max Progress
        int progressArrowSize = 28; //Height of progress arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize/ maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if(slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            stack = originalStack.copy();

            if(invSlot == 2) {
                if(!this.insertItem(originalStack, 18, 54, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, stack);
            } else if(invSlot >= 18 && invSlot < 54) {
                if(isValidInput(originalStack)) {
                    if(!this.insertItem(originalStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isValidEnergyItem(originalStack)) {
                    if(!this.insertItem(originalStack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isRadiantDust(originalStack)) {
                    if(!this.insertItem(originalStack, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else {
                    if(!this.insertItem(originalStack, 6, 18, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                if(!this.insertItem(originalStack, 18, 54, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if(originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return stack;
    }

    private boolean isValidInput(ItemStack stack) {
        return stack.isIn(ModItemTags.GEMSTONE_BLOCKS) || stack.isIn(ModItemTags.RAW_GEMSTONE_BLOCKS) ||
                stack.isIn(ModItemTags.RAW_GEMSTONE) || stack.isIn(ModItemTags.GEMSTONE);
    }

    private boolean isValidEnergyItem(ItemStack stack) {
        return stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
    }

    private boolean isRadiantDust(ItemStack stack) {
        return stack.isOf(ModItems.RADIANT_DUST);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, ModBlocks.GEM_CRYSTALLIZER_BLOCK);
    }
}