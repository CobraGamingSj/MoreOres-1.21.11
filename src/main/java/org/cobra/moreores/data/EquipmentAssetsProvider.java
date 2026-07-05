package org.cobra.moreores.data;


import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import org.cobra.moreores.item.equipment.ModEquipmentAssetKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EquipmentAssetsProvider implements DataProvider {
    private final DataOutput.PathResolver pathResolver;

    public EquipmentAssetsProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        this.pathProvider = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<RegistryKey<EquipmentAsset>, EquipmentModel> consumer) {
        for (RegistryKey<EquipmentAsset> key : ModEquipmentAssetKeys.EQUIPMENT_ASSETS) {
            consumer.accept(key,
                    EquipmentModel.builder()
                            .addHumanoidLayers(key.getValue())
                            .addLayers(EquipmentModel.LayerType.NAUTILUS_BODY, 
                                    new EquipmentModel.Layer(key.getValue()))
                            .build());
        }
    }

    @Override
    public CompletableFuture<?> run(final DataWriter cache) {
        Map<RegistryKey<EquipmentAsset>, EquipmentModel> equipmentAssets = new HashMap<>();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
            }
        });
        return DataProvider.writeAllToPath(cache, EquipmentModel.CODEC, this.pathProvider::resolveJson, equipmentAssets);
    }

    @Override
    public String getName() {
        return "Equipment Asset Definitions";
    }
}
