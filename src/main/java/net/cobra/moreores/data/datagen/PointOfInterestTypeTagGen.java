package net.cobra.moreores.data.datagen;

import net.minecraft.data.DataOutput;
import net.minecraft.data.tag.TagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.PointOfInterestTypeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.concurrent.CompletableFuture;

public class PointOfInterestTypeTagGen extends TagProvider<PointOfInterestType> {
    private static final String ID = "moreores";
    private static final String JEWEL_KEY = "jewel_poi";

    public PointOfInterestTypeTagGen(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, RegistryKeys.POINT_OF_INTEREST_TYPE, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getTagBuilder(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptional(Identifier.of(ID, JEWEL_KEY));
    }
}
