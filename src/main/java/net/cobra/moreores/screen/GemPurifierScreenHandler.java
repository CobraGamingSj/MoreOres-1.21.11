package net.cobra.moreores.screen;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.data.GemPurifierData;
import net.cobra.moreores.block.entity.GemPurifierBlockEntity;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.registry.ModItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class GemPurifierScreenHandler extends ScreenHandler implements ScreenHandlerInventoryHelper {
    private final Inventory inventory;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;
    public final GemPurifierBlockEntity blockEntity;

    // Client Side Constructor
    public GemPurifierScreenHandler(int syncId, PlayerInventory playerInventory, GemPurifierData data) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(data.blockPos()),
                new ArrayPropertyDelegate(2));
    }

    // Main Constructor
    public GemPurifierScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlerType.GEM_PURIFYING_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 15);

        this.inventory = ((Inventory) blockEntity);
        this.context = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (GemPurifierBlockEntity) blockEntity;

        this.addSlot(new Slot(inventory, 0, 83, 13)); // Input
        this.addSlot(new Slot(inventory, 1, 83, 61)); // Result
        this.addSlot(new Slot(inventory, 2, 39, 36)); // Energy Input
//        this.addSlot(new Slot(inventory, 3, 150, 16));
//        this.addSlot(new Slot(inventory, 4, 150, 34));
//        this.addSlot(new Slot(inventory, 5, 150, 52));
//        this.addSlot(new Slot(inventory, 6, 150, 70));
//        this.addSlot(new Slot(inventory, 7, 132, 16));
//        this.addSlot(new Slot(inventory, 8, 132, 34));
//        this.addSlot(new Slot(inventory, 9, 132, 52));
//        this.addSlot(new Slot(inventory, 10, 132, 70));
//        this.addSlot(new Slot(inventory, 11, 114, 16));
//        this.addSlot(new Slot(inventory, 12, 114, 34));
//        this.addSlot(new Slot(inventory, 13, 114, 52));
//        this.addSlot(new Slot(inventory, 14, 114, 70));

        addPlayerGenericInventory(playerInventory);
        addPlayerHotbarInventory(playerInventory);

        addAdditionalSlots(inventory);

        addProperties(propertyDelegate);
    }

    public boolean isPolishing() {
        return propertyDelegate.get(0) > 0;
    }


    public int progressGetter() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

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

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, ModBlocks.GEM_PURIFIER_BLOCK);
    }

    private void addAdditionalSlots(Inventory inv) {
        int startIndex = 3;
        int startX = 150;
        int startY = 8;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 3; column++) {
                int index = startIndex + row + column * 3;
                int x = startX - column * 18;
                int y = startY + row * 18;
                this.addSlot(new Slot(inv, index, x, y));
            }
        }
    }

    @Override
    public void addPlayerGenericInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 115 + i * 18));
            }
        }
    }

    @Override
    public void addPlayerHotbarInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 173));
        }
    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos, BlockState state, World world) {
        return this.blockEntity;
    }

    public long getEnergy() {
        return this.blockEntity.energyStorage.getAmount();
    }

    public long getMaxEnergy() {
        return this.blockEntity.energyStorage.getCapacity();
    }

    public float getEnergyPercent() {
        SimpleEnergyStorage energyStorage = this.blockEntity.energyStorage;
        long energy = energyStorage.getAmount();
        long maxEnergy = energyStorage.getCapacity();
        if (maxEnergy == 0 || energy == 0)
            return 0.0F;

        return MathHelper.clamp((float) energy / (float) maxEnergy, 0.0F, 1.0F);
    }
}