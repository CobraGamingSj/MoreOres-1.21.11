package org.cobra.moreores.networking.item;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.datafixer.fix.BlockEntityUuidFix;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cobra.moreores.MoreOresModInitializer;

public record EnergyIngotC2SPayload() implements CustomPayload {
    public static final Id<EnergyIngotC2SPayload> ID = new Id<>(MoreOresModInitializer.id("energy_ingot_sync"));

    public static final PacketCodec<RegistryByteBuf, EnergyIngotC2SPayload> PACKET_CODEC = PacketCodec.of(
            (value, buf) -> {}, buf -> new EnergyIngotC2SPayload()
    );
    
    public void handle(ServerPlayNetworking.Context context) {
        context.server().execute(() -> {
            PlayerEntity user = context.player();
            World world = user.getEntityWorld();

            EntityType<LightningEntity> lightningType = EntityType.LIGHTNING_BOLT;
            LightningEntity lightning = new LightningEntity(lightningType, world);
            lightning.setPos(user.getX(), user.getY(), user.getZ());
            world.spawnEntity(lightning);

            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 60));
            user.clearStatusEffects();
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS, 2.0f, 1.0f);
        });
    }
    
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
