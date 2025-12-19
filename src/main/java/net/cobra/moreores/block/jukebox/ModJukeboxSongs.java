package net.cobra.moreores.block.jukebox;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.sound.ModSoundEvents;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public interface ModJukeboxSongs{
     RegistryKey<JukeboxSong> TASWELL = ModJukeboxSongs.of("taswell");
     RegistryKey<JukeboxSong> DREITON = ModJukeboxSongs.of("dreiton");
     RegistryKey<JukeboxSong> BIOME_FEST = ModJukeboxSongs.of("biome_fest");
     RegistryKey<JukeboxSong> ARIA_MATH = ModJukeboxSongs.of("aria_math");
     RegistryKey<JukeboxSong> INFINITE_AMETHYST = ModJukeboxSongs.of("infinite_amethyst");
     RegistryKey<JukeboxSong> ENDLESS = ModJukeboxSongs.of("endless");
     RegistryKey<JukeboxSong> FEATHERFALL = ModJukeboxSongs.of("featherfall");
     RegistryKey<JukeboxSong> DEEPER = ModJukeboxSongs.of("deeper");
     RegistryKey<JukeboxSong> WATCHER = ModJukeboxSongs.of("watcher");

    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }

    private static void register(Registerable<JukeboxSong> jukeboxSongRegisterable, RegistryKey<JukeboxSong> jukeboxSongRegistryKey, RegistryEntry.Reference<SoundEvent> soundEventReference, int lengthInSeconds, int comparatorOutput) {
        jukeboxSongRegisterable.register(jukeboxSongRegistryKey, new JukeboxSong(soundEventReference, Text.translatable(Util.createTranslationKey("jukebox_song", jukeboxSongRegistryKey.getValue())), lengthInSeconds, comparatorOutput));
    }

    static void bootstrap(Registerable<JukeboxSong> jukeboxSongRegisterable) {
        ModJukeboxSongs.register(jukeboxSongRegisterable, TASWELL, ModSoundEvents.MUSIC_DISC_TASWELL, 514, 1);
        ModJukeboxSongs.register(jukeboxSongRegisterable, DREITON, ModSoundEvents.MUSIC_DISC_DREITON, 497, 2);
        ModJukeboxSongs.register(jukeboxSongRegisterable, BIOME_FEST, ModSoundEvents.MUSIC_DISC_BIOME_FEST, 376, 3);
        ModJukeboxSongs.register(jukeboxSongRegisterable, ARIA_MATH, ModSoundEvents.MUSIC_DISC_ARIA_MATH, 309, 4);
        ModJukeboxSongs.register(jukeboxSongRegisterable, INFINITE_AMETHYST, ModSoundEvents.MUSIC_DISC_INFINITE_AMETHYST, 271, 5);
        ModJukeboxSongs.register(jukeboxSongRegisterable, ENDLESS, ModSoundEvents.MUSIC_DISC_ENDLESS, 402, 6);
        ModJukeboxSongs.register(jukeboxSongRegisterable, FEATHERFALL, ModSoundEvents.MUSIC_DISC_FEATHERFALL, 344, 7);
        ModJukeboxSongs.register(jukeboxSongRegisterable, DEEPER, ModSoundEvents.MUSIC_DISC_DEEPER, 303, 15);
        ModJukeboxSongs.register(jukeboxSongRegisterable, WATCHER, ModSoundEvents.MUSIC_DISC_WATCHER, 333, 8);
    }
}

