package net.cobra.moreores.mixin;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.registry.BirthdayRewardState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
                    giveBirthdayRewards(serverPlayer);
                    MoreOresModInitializer.LOGGER.info("Gave {} rewards", serverPlayer.getName());
                }
            }
        }
    }

    private static void giveBirthdayRewards(ServerPlayerEntity serverPlayer) {
        ServerWorld world = (ServerWorld) serverPlayer.getEntityWorld();
        BirthdayRewardState state = BirthdayRewardState.get(world);

        if(state.hasClaimed(serverPlayer.getUuid())) {
            serverPlayer.sendMessage(Text.literal("⚠️ You can claim the reward only once!").formatted(Formatting.RED));
            return;
        }

        serverPlayer.giveItemStack(new ItemStack(ModItems.RUBY, 32));
        serverPlayer.giveItemStack(new ItemStack(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, 9));
        serverPlayer.giveItemStack(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 5));
        serverPlayer.sendMessage(
                Text.literal("🎉 [MoreOres+] ")
                        .formatted(Formatting.GOLD)
                        .append(Text.literal("Secret unlocked! ")
                                .formatted(Formatting.YELLOW))
                        .append(Text.literal("Happy Birthday CobraGamingSJ ❤️")
                                .formatted(Formatting.LIGHT_PURPLE)),
                false
        );

        state.setClaimed(serverPlayer.getUuid());
    }
}
