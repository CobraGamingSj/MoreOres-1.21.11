package net.cobra.moreores.client.gui.screen;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.networking.block.data.GemPFEnergyData;
import net.cobra.moreores.block.entity.gem.GemFusionBlockEntity;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.registry.ModItemTags;
import net.cobra.moreores.screen.EnergySlot;
import net.cobra.moreores.screen.GemPurifierInputSlot;
import net.cobra.moreores.screen.GemPurifierResultSlot;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class GemFusionScreenHandler extends AbstractGemPFScreenHandler {
    private final Inventory inventory;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;
    public final GemFusionBlockEntity blockEntity;

    public GemFusionScreenHandler(int syncId, PlayerInventory playerInventory, GemPFEnergyData data) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(data.blockPos()),
                new ArrayPropertyDelegate(2));
    }

    public GemFusionScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity entity, PropertyDelegate delegate) {
        super(ModScreenHandlerType.GEM_FUSION_SCREEN_HANDLER, syncId, entity.getPos());
        checkSize((Inventory) entity, 17);

        this.inventory = (Inventory) entity;
        this.context = ScreenHandlerContext.create(entity.getWorld(), entity.getPos());
        this.propertyDelegate = delegate;
        this.blockEntity = (GemFusionBlockEntity) entity;

        this.addSlot(new GemPurifierInputSlot(inventory, 0, 47, 22) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.GEMSTONE_BLOCKS) || stack.isIn(ModItemTags.RAW_GEMSTONE_BLOCKS) ||
                        stack.isIn(ModItemTags.RAW_GEMSTONE) || stack.isIn(ModItemTags.GEMSTONE);
            }
        }); // Input Before

        this.addSlot(new GemPurifierInputSlot(inventory, 1, 87, 22) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.GEMSTONE_BLOCKS) || stack.isIn(ModItemTags.RAW_GEMSTONE_BLOCKS) ||
                        stack.isIn(ModItemTags.RAW_GEMSTONE) || stack.isIn(ModItemTags.GEMSTONE);
            }
        }); // Input After
        this.addSlot(new GemPurifierResultSlot(inventory, 2, 67, 72)); // Result
        this.addSlot(new EnergySlot(inventory, 3, 13, 21)); // Energy Input
        this.addSlot(new Slot(inventory, 4, 39, 59) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.RADIANT_DUST);
            }
        });

        addFirstAdditionalInventory(inventory);
        addSecondAdditionalInventory(inventory);

        addPlayerGenericInventory(playerInventory);
        addPlayerHotbarInventory(playerInventory);

        addProperties(delegate);
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

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, ModBlocks.GEM_FUSION_BLOCK);
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