package net.cobra.moreores.item;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class AliasedBlockItem extends BlockItem {
    public AliasedBlockItem(String pathKey, Block block) {
        super(block, new Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MoreOresModInitializer.MOD_ID, pathKey))).useItemPrefixedTranslationKey());
    }
}
