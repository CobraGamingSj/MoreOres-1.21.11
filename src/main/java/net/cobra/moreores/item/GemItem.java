package net.cobra.moreores.item;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.function.Consumer;

public class GemItem extends Item {
    public GemItem(Settings settings, String name) {
        super(settings.rarity(Rarity.RARE).fireproof().trimMaterial(RegistryKey.of(RegistryKeys.TRIM_MATERIAL, MoreOresModInitializer.getId(name))));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.literal("Gemstone").formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}
