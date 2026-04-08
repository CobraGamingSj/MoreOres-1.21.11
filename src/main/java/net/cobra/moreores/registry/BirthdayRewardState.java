package net.cobra.moreores.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BirthdayRewardState extends PersistentState {
    private final Set<UUID> playerClaimedRewards = new HashSet<>();
    public static final Codec<BirthdayRewardState> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.list(Codec.STRING).fieldOf("players").forGetter(state ->
                            state.playerClaimedRewards.stream().map(UUID::toString).toList()
                    )
            ).apply(instance, list -> {
                BirthdayRewardState state = new BirthdayRewardState();
                for (String s : list) {
                    state.playerClaimedRewards.add(UUID.fromString(s));
                }
                return state;
            })
    );

    public static final PersistentStateType<BirthdayRewardState> TYPE =
            new PersistentStateType<>(
                    "moreores_birthday_rewards",
                    BirthdayRewardState::new,
                    CODEC,
                    null // no DataFixTypes needed
            );

    public boolean hasClaimed(UUID uuid) {
        return playerClaimedRewards.contains(uuid);
    }

    public void setClaimed(UUID uuid) {
        playerClaimedRewards.add(uuid);
        markDirty();
    }

    public static BirthdayRewardState get(ServerWorld world) {
       return world.getPersistentStateManager().getOrCreate(TYPE);
    }
}
