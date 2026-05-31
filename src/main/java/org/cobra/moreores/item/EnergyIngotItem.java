package org.cobra.moreores.item;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.networking.item.EnergyIngotC2SPayload;

public class EnergyIngotItem extends Item {

    private int lightningStrikes = 0;
    private int requiredStrikes = -1;

    public EnergyIngotItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getEntityWorld();

        if(!world.isClient()) {

            if(!attacker.isInCreativeMode()) {
                if (stack.getDamage() < stack.getMaxDamage()) {
                    stack.setDamage(stack.getDamage() + 1);
                }
            }

            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(target.getX(), target.getY(), target.getZ());
            world.spawnEntity(lightning);
        }

        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 4800, 4));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 40, 4));
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if(!world.isClient()) {
            if (world.getBlockState(pos).isOf(ModBlocks.RADIANT_BLOCK)) {
                EntityType<LightningEntity> lightningType = EntityType.LIGHTNING_BOLT;
                LightningEntity lightning = new LightningEntity(lightningType, world);
                lightning.setPos(pos.getX(), pos.getY(), pos.getZ());
                world.spawnEntity(lightning);

                if(requiredStrikes == -1) {
                    requiredStrikes = world.random.nextBetween(4, 7);
                }

                lightningStrikes++;

                if(lightningStrikes >= requiredStrikes) {
                    world.breakBlock(pos, false);
                    lightning.discard();
                    world.spawnEntity(new ItemEntity(
                            world,
                            pos.getX() + 2,
                            pos.getY(),
                            pos.getZ() + 2,
                            new ItemStack(ModItems.RADIANT_DUST, 9)
                    ));
                    lightningStrikes = 0;
                }
                return ActionResult.PASS;
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(world.isClient()) {
            if(hand == Hand.OFF_HAND && MinecraftClient.getInstance().isCtrlPressed()) {
                ClientPlayNetworking.send(new EnergyIngotC2SPayload());
            }
            return ActionResult.CONSUME;
        }

        if (hand == Hand.MAIN_HAND) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 9600, 4, false, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 9600, 4, false, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 9600, 4, false, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 9600, 4, false, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 9600, 4, false, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 9600, 4, false, false, false));
            if(!user.isInCreativeMode()) {
                if (stack.getDamage() < stack.getMaxDamage()) {
                    stack.setDamage(stack.getDamage() + 1);
                }
            }
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 2.0f, 1.0f);
            return ActionResult.SUCCESS;
        }

        return super.use(world, user, hand);
    }
}