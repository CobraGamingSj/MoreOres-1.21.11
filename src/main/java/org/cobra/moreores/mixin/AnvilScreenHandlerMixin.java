package org.cobra.moreores.mixin;

import org.cobra.moreores.MoreOresModInitializer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void takeOutput(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        System.out.println("ANVIL MIXIN TRIGGERRED");
        if(player.getEntityWorld().isClient()) return;

        if(!(player instanceof ServerPlayerEntity serverPlayer)) return;

        Identifier id = Registries.ITEM.getId(stack.getItem());

        String name = stack.getName().getString();
        if(id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {
            if (stack.hasChangedComponent(DataComponentTypes.CUSTOM_NAME)) {
                if (name.equalsIgnoreCase("CobraGamingSJ")) {
                    System.out.println("NAME MATCHED!");
                    MoreOresModInitializer.giveBirthdayRewards(serverPlayer);
                    MoreOresModInitializer.LOGGER.info("Gave {} rewards", serverPlayer.getName());
                }
            }
        }
    }
}
