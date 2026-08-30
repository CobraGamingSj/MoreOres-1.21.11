package org.cobra.moreores.client.gui.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.machine.GemPurifierBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.networking.block.data.GemPurifierDataSynchronizer;
import org.cobra.moreores.registry.ModItemTags;

public class GemPurifierScreenHandler extends AbstractGemMachineScreenHandler<GemPurifierBlockEntity> {
    private final Inventory inventory;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;

    // Client Side Constructor
    public GemPurifierScreenHandler(int syncId, PlayerInventory playerInventory, GemPurifierDataSynchronizer data) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(data.blockPos()),
                new ArrayPropertyDelegate(3));
    }

    // Main Constructor
    public GemPurifierScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlerType.GEM_PURIFIER, syncId, blockEntity.getPos(), (GemPurifierBlockEntity) blockEntity);
        checkSize((Inventory) blockEntity, 17);

        this.inventory = ((Inventory) blockEntity);
        this.context = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());
        this.propertyDelegate = propertyDelegate;

        this.addSlot(new Slot(inventory, 0, 79, 11) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.RAW_GEMSTONE) || stack.isIn(ModItemTags.RAW_GEMSTONE_BLOCKS);
            }
        }); // Input
        this.addSlot(new Slot(inventory, 1, 75, 61) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.GEMSTONE_BLOCKS);
            }
        }); // Result
        this.addSlot(new Slot(inventory, 2, 40, 20) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.HAS_ENERGY);
            }
        }); // Energy Input
        this.addSlot(new Slot(inventory, 3, 12, 20)); // Water Source

        this.addSlot(new Slot(inventory, 4, 109, 33)); // Redstone Source
        
        addFirstAdditionalInventory(inventory);

        addPlayerGenericInventory(playerInventory);
        addPlayerHotbarInventory(playerInventory);

        addProperties(propertyDelegate);
    }

    public boolean isPolishing() {
        return propertyDelegate.get(0) > 0;
    }

    public int getRedstoneDust() {
        return propertyDelegate.get(2);
    }
    
    public int progressGetter() {
        int progress = this.propertyDelegate.get(0); //Progress
        int maxProgress = this.propertyDelegate.get(1); //Max Progress
        int progressArrowSize = 27; //Height of progress arrow

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
                if(!this.insertItem(originalStack, 15, 51, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, stack);
            } else if(invSlot >= 15 && invSlot < 51) {
                if(isValidInput(originalStack)) {
                    if(!this.insertItem(originalStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isValidEnergyItem(originalStack)) {
                    if(!this.insertItem(originalStack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if(!this.insertItem(originalStack, 3, 15, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                if(!this.insertItem(originalStack, 15, 51, false)) {
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
        return stack.isIn(ModItemTags.RAW_GEMSTONE);
    }

    private boolean isValidEnergyItem(ItemStack stack) {
        return stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
    }

    private boolean isWaterBucket(ItemStack stack) {
        return stack.isOf(Items.WATER_BUCKET);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, ModBlocks.GEM_PURIFIER_BLOCK);
    }

    @Override
    public void addPlayerGenericInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            for (int l = 0; l < 3; ++l) {
                this.addSlot(new Slot(playerInventory, i * 3 + l + 9, 142 + l * 18, 11 + i * 18));
            }
        }
    }

    @Override
    public void addPlayerHotbarInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 201, 11 + i * 18));
        }
    }

    @Override
    public void addFirstAdditionalInventory(Inventory playerInventory) {
        for (int i = 0; i < 12; ++i) {
            this.addSlot(new Slot(playerInventory, 5 + i, 6 + i * 18, 178));
        }
    }
}