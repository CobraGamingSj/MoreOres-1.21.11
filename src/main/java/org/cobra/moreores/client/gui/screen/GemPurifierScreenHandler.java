package org.cobra.moreores.client.gui.screen;

import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.networking.block.data.GemPurifierDataSynchronizer;
import org.cobra.moreores.registry.ModItemTags;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class GemPurifierScreenHandler extends AbstractGemPFScreenHandler implements ScreenHandlerInventoryHelper {
    private final Inventory inventory;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;
    public final GemPurifierBlockEntity blockEntity;

    // Client Side Constructor
    public GemPurifierScreenHandler(int syncId, PlayerInventory playerInventory, GemPurifierDataSynchronizer data) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(data.blockPos()),
                new ArrayPropertyDelegate(2));
    }

    // Main Constructor
    public GemPurifierScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlerType.GEM_PURIFYING_SCREEN_HANDLER, syncId, blockEntity.getPos());
        checkSize((Inventory) blockEntity, 16);

        this.inventory = ((Inventory) blockEntity);
        this.context = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (GemPurifierBlockEntity) blockEntity;

        this.addSlot(new Slot(inventory, 0, 79, 11) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.RAW_GEMSTONE) || stack.isIn(ModItemTags.RAW_GEMSTONE_BLOCKS);
            }
        }); // Input
        this.addSlot(new Slot(inventory, 1, 79, 61) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.GEMSTONE_BLOCKS);
            }
        }); // Result
        this.addSlot(new Slot(inventory, 2, 40, 20) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
            }
        }); // Energy Input
        this.addSlot(new Slot(inventory, 3, 12, 20)); // Water Source

        addFirstAdditionalInventory(inventory);
        addSecondAdditionalInventory(inventory);

        addPlayerGenericInventory(playerInventory);
        addPlayerHotbarInventory(playerInventory);

        addProperties(propertyDelegate);
    }

    public boolean isPolishing() {
        return propertyDelegate.get(0) > 0;
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

    public void addFirstAdditionalInventory(Inventory playerInventory) {
        for (int i = 0; i < 8; ++i) {
            this.addSlot(new Slot(playerInventory, 4 + i, 26 + i * 18, 95));
        }
    }

    public void addSecondAdditionalInventory(Inventory playerInventory) {
        for (int i = 0; i < 4; ++i) {
            this.addSlot(new Slot(playerInventory, 12 +  i, 179, 115 + i * 18));
        }
    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos, BlockState state, World world) {
        return this.blockEntity;
    }

    public long getEnergy() {
        return this.blockEntity.energyAmount();
    }

    public long getEnergyCap() {
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