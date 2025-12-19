package net.cobra.moreores.sound;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {

    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_ARIA_MATH = ModSoundEvents.registerReference("music_disc.aria_math");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_DREITON = ModSoundEvents.registerReference("music_disc.dreiton");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_BIOME_FEST = ModSoundEvents.registerReference("music_disc.biome_fest");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_TASWELL = ModSoundEvents.registerReference("music_disc.taswell");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_INFINITE_AMETHYST = ModSoundEvents.registerReference("music_disc.infinite_amethyst");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_ENDLESS = ModSoundEvents.registerReference("music_disc.endless");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_FEATHERFALL = ModSoundEvents.registerReference("music_disc.featherfall");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_DEEPER = ModSoundEvents.registerReference("music_disc.deeper");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_WATCHER = ModSoundEvents.registerReference("music_disc.watcher");

    private static RegistryEntry<SoundEvent> register(Identifier id, Identifier soundId, float distanceToTravel) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId, distanceToTravel));
    }

    private static SoundEvent register(String id) {
        Identifier ID = Identifier.of(MoreOresModInitializer.MOD_ID, id);
        return ModSoundEvents.register(ID);
    }

    private static SoundEvent register(Identifier id) {
        return ModSoundEvents.register(id, id);
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        Identifier ID = Identifier.of(MoreOresModInitializer.MOD_ID, id);
        return ModSoundEvents.registerReference(ID);
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return ModSoundEvents.registerReference(id, id);
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }


    public static void register() {

    }
}