package net.cobra.moreores.client.render.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cobra.moreores.block.entity.gem_polisher.util.GemColorUtils;
import net.cobra.moreores.component.type.GemTypeComponent;
import net.cobra.moreores.component.type.ModDataComponentType;
import net.minecraft.client.render.item.tint.PotionTintSource;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.jspecify.annotations.Nullable;

import static net.cobra.moreores.block.entity.gem_polisher.util.GemColorUtils.GEMS;

public record GemTintSource(int defaultColor) implements TintSource {

    public static final MapCodec<GemTintSource> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(Codecs.RGB.fieldOf("default").forGetter(GemTintSource::defaultColor)).apply(instance, GemTintSource::new)
    );

    public GemTintSource() {
        this(0xFFFFFF);
    }

    @Override
    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        GemTypeComponent comp = stack.get(ModDataComponentType.GEM_TYPE);

        if (comp == null) return defaultColor;

        return comp.type().getColor();
    }

    @Override
    public MapCodec<? extends TintSource> getCodec() {
        return CODEC;
    }
}
