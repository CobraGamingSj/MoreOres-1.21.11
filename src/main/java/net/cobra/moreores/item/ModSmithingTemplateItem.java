package net.cobra.moreores.item;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;
import java.util.function.Consumer;

public class ModSmithingTemplateItem extends Item {

    public ModSmithingTemplateItem(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, Settings settings) {
        super(settings);
        this.appliesToText = appliesToText;
        this.ingredientsText = ingredientsText;
        this.titleText = titleText;
        this.baseSlotDescriptionText = baseSlotDescriptionText;
        this.additionsSlotDescriptionText = additionsSlotDescriptionText;
        this.emptyBaseSlotTextures = emptyBaseSlotTextures;
        this.emptyAdditionsSlotTextures = emptyAdditionsSlotTextures;
    }
    public static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    public static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;
    public static final Text INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.ingredients"))).formatted(TITLE_FORMATTING);
    public static final Text APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.applies_to"))).formatted(TITLE_FORMATTING);
    private static final Text RUBY_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", MoreOresModInitializer.getId("ruby_upgrade"))).formatted(TITLE_FORMATTING);
    private static final Text RADIANT_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", MoreOresModInitializer.getId("radiant_upgrade"))).formatted(TITLE_FORMATTING);
    private static final Text ARMOR_TRIM_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.armor_trim.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text ARMOR_TRIM_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.armor_trim.ingredients"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text ARMOR_TRIM_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.armor_trim.base_slot_description")));
    private static final Text ARMOR_TRIM_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.armor_trim.additions_slot_description")));
    private static final Text RUBY_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.ruby_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text RUBY_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.ruby_upgrade.ingredients"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text RUBY_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.netherite_upgrade.base_slot_description")));
    private static final Text RUBY_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.netherite_upgrade.additions_slot_description")));

    private static final Text RADIANT_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.radiant_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text RADIANT_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.radiant_upgrade.ingredients"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text RADIANT_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.radiant_upgrade.base_slot_description")));
    private static final Text RADIANT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", MoreOresModInitializer.getId("smithing_template.radiant_upgrade.additions_slot_description")));

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.ofVanilla("item/empty_slot_hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.ofVanilla("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.ofVanilla("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.ofVanilla("item/empty_slot_ingot");
    private static final Identifier EMPTY_SLOT_REDSTONE_DUST_TEXTURE = Identifier.ofVanilla("item/empty_slot_redstone_dust");
    private static final Identifier EMPTY_SLOT_QUARTZ_TEXTURE = Identifier.ofVanilla("item/empty_slot_quartz");
    private static final Identifier EMPTY_SLOT_EMERALD_TEXTURE = Identifier.ofVanilla("item/empty_slot_emerald");
    private static final Identifier EMPTY_SLOT_DIAMOND_TEXTURE = Identifier.ofVanilla("item/empty_slot_diamond");
    private static final Identifier EMPTY_SLOT_LAPIS_LAZULI_TEXTURE = Identifier.ofVanilla("item/empty_slot_lapis_lazuli");
    private static final Identifier EMPTY_SLOT_AMETHYST_SHARD_TEXTURE = Identifier.ofVanilla("item/empty_slot_amethyst_shard");
    private final Text appliesToText;
    private final Text ingredientsText;
    private final Text titleText;
    private final Text baseSlotDescriptionText;
    private final Text additionsSlotDescriptionText;
    private final List<Identifier> emptyBaseSlotTextures;
    private final List<Identifier> emptyAdditionsSlotTextures;

    public static ModSmithingTemplateItem of(RegistryKey<ArmorTrimPattern> trimPattern) {
        return ModSmithingTemplateItem.of(trimPattern.getValue());
    }

    public static ModSmithingTemplateItem of(Identifier trimPatternIn) {
        return new ModSmithingTemplateItem(ARMOR_TRIM_APPLIES_TO_TEXT, ARMOR_TRIM_INGREDIENTS_TEXT, Text.translatable(Util.createTranslationKey("trim_pattern", trimPatternIn)).formatted(TITLE_FORMATTING), ARMOR_TRIM_BASE_SLOT_DESCRIPTION_TEXT, ARMOR_TRIM_ADDITIONS_SLOT_DESCRIPTION_TEXT, ModSmithingTemplateItem.getArmorTrimEmptyBaseSlotTextures(), ModSmithingTemplateItem.getArmorTrimEmptyAdditionsSlotTextures(), new Settings());
    }

    private static List<Identifier> getArmorTrimEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE);
    }

    public static ModSmithingTemplateItem createRubyUpgrade(Settings settings) {
        return new ModSmithingTemplateItem(
                RUBY_UPGRADE_APPLIES_TO_TEXT,
                RUBY_UPGRADE_INGREDIENTS_TEXT,
                RUBY_UPGRADE_TEXT,
                RUBY_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                RUBY_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getRubyUpgradeEmptyBaseSlotTextures(),
                getRubyUpgradeEmptyAdditionsSlotTextures(),
                settings);
    }

    public static ModSmithingTemplateItem createRadiantUpgrade(Settings settings) {
        return new ModSmithingTemplateItem(
                RADIANT_UPGRADE_APPLIES_TO_TEXT,
                RADIANT_UPGRADE_INGREDIENTS_TEXT,
                RADIANT_UPGRADE_TEXT,
                RADIANT_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                RADIANT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getRadiantUpgradeEmptyBaseSlotTextures(),
                getRadiantUpgradeEmptyAdditionsSlotTextures(),
                settings);
    }

    public static List<Identifier> getArmorTrimEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE, EMPTY_SLOT_REDSTONE_DUST_TEXTURE, EMPTY_SLOT_LAPIS_LAZULI_TEXTURE, EMPTY_SLOT_QUARTZ_TEXTURE, EMPTY_SLOT_DIAMOND_TEXTURE, EMPTY_SLOT_EMERALD_TEXTURE, EMPTY_SLOT_AMETHYST_SHARD_TEXTURE);
    }

    public static List<Identifier> getRubyUpgradeEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE);
    }

    public static List<Identifier> getRadiantUpgradeEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE);
    }

    public static List<Identifier> getRubyUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE);
    }

    public static List<Identifier> getRadiantUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        textConsumer.accept(this.titleText);
        textConsumer.accept(ScreenTexts.EMPTY);
        textConsumer.accept(APPLIES_TO_TEXT);
        textConsumer.accept(ScreenTexts.space().append(this.appliesToText));
        textConsumer.accept(INGREDIENTS_TEXT);
        textConsumer.accept(ScreenTexts.space().append(this.ingredientsText));
    }

    public Text getBaseSlotDescription() {
        return this.baseSlotDescriptionText;
    }

    public Text getAdditionsSlotDescription() {
        return this.additionsSlotDescriptionText;
    }

    public List<Identifier> getEmptyBaseSlotTextures() {
        return this.emptyBaseSlotTextures;
    }

    public List<Identifier> getEmptyAdditionsSlotTextures() {
        return this.emptyAdditionsSlotTextures;
    }
}