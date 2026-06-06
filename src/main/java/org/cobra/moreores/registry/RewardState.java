package org.cobra.moreores.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RewardState extends PersistentState {
    private final Set<UUID> playerClaimedRewards = new HashSet<>();
    public static final Codec<RewardState> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.list(Codec.STRING).fieldOf("players").forGetter(state ->
                            state.playerClaimedRewards.stream().map(UUID::toString).toList()
                    )
            ).apply(instance, list -> {
                RewardState state = new RewardState();
                for (String s : list) {
                    state.playerClaimedRewards.add(UUID.fromString(s));
                }
                return state;
            })
    );

    public static final PersistentStateType<RewardState> TYPE =
            new PersistentStateType<>(
                    "moreores_birthday_rewards",
                    RewardState::new,
                    CODEC,
                    null // Not required
            );

    public boolean hasClaimed(UUID uuid) {
        return playerClaimedRewards.contains(uuid);
    }

    public void setClaimed(UUID uuid) {
        playerClaimedRewards.add(uuid);
        markDirty();
    }

    public static RewardState get(ServerWorld world) {
       return world.getPersistentStateManager().getOrCreate(TYPE);
    }
}
