package net.cobra.moreores.sound;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModBlockSoundGroup {

    public static final BlockSoundGroup ENERGY_BLOCK = new BlockSoundGroup(5f, 1f, SoundEvents.BLOCK_HEAVY_CORE_STEP, SoundEvents.BLOCK_HEAVY_CORE_BREAK, SoundEvents.BLOCK_HEAVY_CORE_PLACE, SoundEvents.BLOCK_HEAVY_CORE_HIT, SoundEvents.BLOCK_HEAVY_CORE_FALL);

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModBlockSoundGroups for " + MoreOresModInitializer.MOD_ID + " mod.");
    }

}
