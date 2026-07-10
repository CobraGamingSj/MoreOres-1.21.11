package org.cobra.moreores.block.entity.gem;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ImplementedInventory;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.block.entity.TickableBlockEntity;
import org.cobra.moreores.client.gui.screen.TestScreenHandler;
import org.jspecify.annotations.Nullable;

public class TestGemBlockEntity extends BlockEntity implements TickableBlockEntity, ImplementedInventory, ExtendedScreenHandlerFactory {
    public final DefaultedList<ItemStack> main = DefaultedList.ofSize(1, ItemStack.EMPTY);
    
    public TestGemBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.TEST, pos, state);
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return null;
    }

    @Override
    public Text getDisplayName() {
        return ModBlocks.TEST_BLOCK.getName();
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new TestScreenHandler(null, syncId);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return main;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state) {
        
    }
}
