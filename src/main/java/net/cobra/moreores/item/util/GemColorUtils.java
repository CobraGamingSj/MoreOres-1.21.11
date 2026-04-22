//package net.cobra.moreores.item.util;
//
//import net.cobra.moreores.MoreOresModInitializer;
//import net.cobra.moreores.block.ModBlocks;
//import net.cobra.moreores.block.entity.gem_polisher.GemPurifierBlockEntity;
//import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
//
//public class GemColorUtils {
//
////    private static final Map<Identifier, Integer> COLOR_CACHE = new java.util.HashMap<>();
////
////    public static final Map<GemType, Identifier> GEMS = Map.ofEntries(
////            Map.entry(GemType.RUBY, getPaletteTexture("ruby")),
////            Map.entry(GemType.SAPPHIRE, getPaletteTexture("sapphire")),
////            Map.entry(GemType.GREEN_SAPPHIRE, getPaletteTexture("green_sapphire")),
////            Map.entry(GemType.BLUE_GARNET, getPaletteTexture("blue_garnet")),
////            Map.entry(GemType.PINK_GARNET, getPaletteTexture("pink_garnet")),
////            Map.entry(GemType.GREEN_GARNET, getPaletteTexture("green_garnet")),
////            Map.entry(GemType.KYAWTHUITE, getPaletteTexture("kyawthuite")),
////            Map.entry(GemType.TOPAZ, getPaletteTexture("topaz")),
////            Map.entry(GemType.WHITE_TOPAZ, getPaletteTexture("white_topaz")),
////            Map.entry(GemType.PERIDOT, getPaletteTexture("peridot")),
////            Map.entry(GemType.JADE, getPaletteTexture("jade")),
////            Map.entry(GemType.PYROPE, getPaletteTexture("pyrope"))
////    );
////
////    private static Identifier getPaletteTexture(String name) {
////        return MoreOresModInitializer.getId("textures/trims/color_palettes/" + name + ".png");
////    }
////
////    public static int getCachedColor(Identifier identifier) {
////        return COLOR_CACHE.computeIfAbsent(identifier, gem -> {
////            try {
////                var resources = MinecraftClient.getInstance().getResourceManager().getResource(identifier).orElseThrow();
////
////                var image = NativeImage.read(resources.getInputStream());
////
////                int x = image.getWidth() / 2;
////                int y = image.getHeight() / 2;
////                return image.getColor(x, y);
////            } catch (Exception e) {
////                return 0xFFFFFF;
////            }
////        });
////    }
//
//    public static void register() {
//        MoreOresModInitializer.LOGGER.info("Loading Gem Color Provider for {} mod.", MoreOresModInitializer.MOD_ID);
//        ColorProviderRegistry.BLOCK.register(
//                (state, world, pos, tintIndex) -> {
//                    if (tintIndex != 0) return -1;
//
//                    if (world == null || pos == null) return 0xFFFFFF;
//
//                    var be = world.getBlockEntity(pos);
//                    if (be instanceof GemPurifierBlockEntity entity) {
//                        GemType gem = entity.getGem();
//                        return gem != null ? gem.getColor() : 0xFFFFFF;
//                    }
//
//                    return 0xFFFFFF;
//                },
//                ModBlocks.GEM_PURIFIER_BLOCK
//        );
//    }
//}
