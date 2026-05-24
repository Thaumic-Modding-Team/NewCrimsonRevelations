package mod.icarus.crimsonrevelations.registry;

import baubles.api.BaubleType;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.item.armor.*;
import mod.icarus.crimsonrevelations.item.base.*;
import mod.icarus.crimsonrevelations.item.base.ItemBaubleBase;
import mod.icarus.crimsonrevelations.item.bauble.ItemRunicBauble;
import mod.icarus.crimsonrevelations.item.bauble.ItemVerdantRing;
import mod.icarus.crimsonrevelations.item.misc.ItemManaBean;
import mod.icarus.crimsonrevelations.item.tools.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItemsNCR {
    public static Item AER_ARROW;
    public static ItemAncientCultistArmor ANCIENT_CRIMSON_CHESTPLATE;
    public static ItemAncientCultistArmor ANCIENT_CRIMSON_HELMET;
    public static ItemAncientCultistArmor ANCIENT_CRIMSON_LEGGINGS;
    public static Item AQUA_ARROW;
    public static Item BONE_BOW;
    public static ItemCometBoots COMET_BOOTS ;
    public static ItemCultistArcherArmor CRIMSON_ARCHER_CHESTPLATE;
    public static ItemCultistArcherArmor CRIMSON_ARCHER_HELMET;
    public static ItemCultistArcherArmor CRIMSON_ARCHER_LEGGINGS;
    public static Item CRIMSON_FABRIC;
    public static ItemCultistPaladinArmor CRIMSON_PALADIN_CHESTPLATE;
    public static ItemCultistPaladinArmor CRIMSON_PALADIN_HELMET;
    public static ItemCultistPaladinArmor CRIMSON_PALADIN_LEGGINGS;
    public static Item CRIMSON_PLATE;
    public static ItemCultistRangerArmor CRIMSON_RANGER_CHESTPLATE;
    public static ItemCultistRangerArmor CRIMSON_RANGER_HELMET;
    public static ItemCultistRangerArmor CRIMSON_RANGER_LEGGINGS;
    public static Item CRIMSON_SWORD;
    public static Item EMBELLISHED_CRIMSON_FABRIC;
    public static Item EXECUTION_AXE;
    public static Item IGNIS_ARROW;
    public static Item KNOWLEDGE_SCRIBING_TOOLS;
    public static ItemManaBean MANA_BEAN;
    public static ItemMeteorBoots METEOR_BOOTS;
    public static Item ORDO_ARROW ;
    public static Item PERDITIO_ARROW;
    public static Item PRIMORDIAL_SCRIBING_TOOLS;
    public static Item PROTECTION_RING;
    public static Item RUNIC_AMULET;
    public static Item RUNIC_AMULET_EMERGENCY;
    public static Item RUNIC_GIRDLE;
    public static Item RUNIC_GIRDLE_KINETIC;
    public static Item RUNIC_RING;
    public static Item RUNIC_RING_CHARGED;
    public static Item RUNIC_RING_REGEN;
    public static Item SANITATION_SCRIBING_TOOLS;
    public static Item TECHNOMANCER_SCRIBING_TOOLS ;
    public static Item TERRA_ARROW;
    public static ItemVerdantRing VERDANT_RING;

    // Optional Content
    public static Item DISTORTION_PICKAXE;
    public static Item NUTRITION_RING;
    public static Item PURIFYING_SHOVEL;
    public static Item THAUMIC_LITMUS_PAPER;

    public static final List<Item> MOD_ITEMS = new ArrayList<>();

    public static void initItems() {
        MOD_ITEMS.add(CRIMSON_FABRIC = new ItemBase("crimson_fabric").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(EMBELLISHED_CRIMSON_FABRIC = new ItemBase("embellished_crimson_fabric").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(CRIMSON_PLATE = new ItemBase("crimson_plate").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(CRIMSON_SWORD = new ItemSwordBase("crimson_sword", ModMaterialsNCR.TOOL_CULTIST).setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(EXECUTION_AXE = new ItemExecutionAxe("execution_axe").setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(ANCIENT_CRIMSON_HELMET = new ItemAncientCultistArmor("ancient_crimson_helmet", EntityEquipmentSlot.HEAD));
        MOD_ITEMS.add(ANCIENT_CRIMSON_CHESTPLATE = new ItemAncientCultistArmor("ancient_crimson_chestplate", EntityEquipmentSlot.CHEST));
        MOD_ITEMS.add(ANCIENT_CRIMSON_LEGGINGS = new ItemAncientCultistArmor("ancient_crimson_leggings", EntityEquipmentSlot.LEGS));
        MOD_ITEMS.add(CRIMSON_ARCHER_HELMET = new ItemCultistArcherArmor("crimson_archer_helmet", EntityEquipmentSlot.HEAD));
        MOD_ITEMS.add(CRIMSON_ARCHER_CHESTPLATE = new ItemCultistArcherArmor("crimson_archer_chestplate", EntityEquipmentSlot.CHEST));
        MOD_ITEMS.add(CRIMSON_ARCHER_LEGGINGS = new ItemCultistArcherArmor("crimson_archer_leggings", EntityEquipmentSlot.LEGS));
        MOD_ITEMS.add(CRIMSON_PALADIN_HELMET = new ItemCultistPaladinArmor("crimson_paladin_helmet", EntityEquipmentSlot.HEAD));
        MOD_ITEMS.add(CRIMSON_PALADIN_CHESTPLATE = new ItemCultistPaladinArmor("crimson_paladin_chestplate", EntityEquipmentSlot.CHEST));
        MOD_ITEMS.add(CRIMSON_PALADIN_LEGGINGS = new ItemCultistPaladinArmor("crimson_paladin_leggings", EntityEquipmentSlot.LEGS));
        MOD_ITEMS.add(CRIMSON_RANGER_HELMET = new ItemCultistRangerArmor("crimson_ranger_helmet", EntityEquipmentSlot.HEAD));
        MOD_ITEMS.add(CRIMSON_RANGER_CHESTPLATE = new ItemCultistRangerArmor("crimson_ranger_chestplate", EntityEquipmentSlot.CHEST));
        MOD_ITEMS.add(CRIMSON_RANGER_LEGGINGS = new ItemCultistRangerArmor("crimson_ranger_leggings", EntityEquipmentSlot.LEGS));
        MOD_ITEMS.add(BONE_BOW = new ItemBoneBow("bone_bow").setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(AER_ARROW = new ItemArrowBase("aer_arrow").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(AQUA_ARROW = new ItemArrowBase("aqua_arrow").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(IGNIS_ARROW = new ItemArrowBase("ignis_arrow").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(ORDO_ARROW = new ItemArrowBase("ordo_arrow").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(PERDITIO_ARROW = new ItemArrowBase("perditio_arrow").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(TERRA_ARROW = new ItemArrowBase("terra_arrow").setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(PROTECTION_RING = new ItemRunicBauble("protection_ring", BaubleType.RING, 1));
        MOD_ITEMS.add(RUNIC_RING = new ItemRunicBauble("runic_ring", BaubleType.RING, 5).setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(RUNIC_AMULET = new ItemRunicBauble("runic_amulet", BaubleType.AMULET, 8).setRarity(EnumRarity.UNCOMMON));
        MOD_ITEMS.add(RUNIC_GIRDLE = new ItemRunicBauble("runic_girdle", BaubleType.BELT, 10).setRarity(EnumRarity.UNCOMMON));

        MOD_ITEMS.add(RUNIC_RING_CHARGED = new ItemRunicBauble("runic_ring_charged", BaubleType.RING, 4).setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(RUNIC_RING_REGEN = new ItemRunicBauble("runic_ring_regen", BaubleType.RING, 4).setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(RUNIC_AMULET_EMERGENCY = new ItemRunicBauble("runic_amulet_emergency", BaubleType.AMULET, 7).setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(RUNIC_GIRDLE_KINETIC = new ItemRunicBauble("runic_girdle_kinetic", BaubleType.BELT, 9).setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(VERDANT_RING = new ItemVerdantRing("verdant_ring"));

        MOD_ITEMS.add(COMET_BOOTS = new ItemCometBoots("comet_boots"));
        MOD_ITEMS.add(METEOR_BOOTS = new ItemMeteorBoots("meteor_boots"));

        MOD_ITEMS.add(TECHNOMANCER_SCRIBING_TOOLS = new ItemTechnomancerScribingTools("technomancer_scribing_tools").setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(KNOWLEDGE_SCRIBING_TOOLS = new ItemKnowledgeScribingTools("knowledge_scribing_tools").setRarity(ModRaritiesNCR.RARITY_KNOWLEDGE));
        MOD_ITEMS.add(SANITATION_SCRIBING_TOOLS = new ItemSanitationScribingTools("sanitation_scribing_tools").setRarity(EnumRarity.RARE));
        MOD_ITEMS.add(PRIMORDIAL_SCRIBING_TOOLS = new ItemPrimordialScribingTools("primordial_scribing_tools").setRarity(EnumRarity.EPIC));

        MOD_ITEMS.add(MANA_BEAN = new ItemManaBean("mana_bean"));

        if (ConfigHandlerNCR.distortion_pickaxe.enableDistortionPickaxe) {
            MOD_ITEMS.add(DISTORTION_PICKAXE = new ItemDistortionPickaxe("distortion_pickaxe").setRarity(EnumRarity.RARE));
        }

        if (ConfigHandlerNCR.nutrition_ring.enableNutritionRing) {
            MOD_ITEMS.add(NUTRITION_RING = new ItemBaubleBase("nutrition_ring", BaubleType.RING).setRarity(EnumRarity.RARE));
        }

        if (ConfigHandlerNCR.purifying_shovel.enablePurifyingShovel) {
            MOD_ITEMS.add(PURIFYING_SHOVEL = new ItemPurifyingShovel("purifying_shovel").setRarity(EnumRarity.RARE));
        }

        if (ConfigHandlerNCR.thaumic_litmus_paper.enableThaumicLitmusPaper) {
            MOD_ITEMS.add(THAUMIC_LITMUS_PAPER = new ItemLitmusPaper("thaumic_litmus_paper").setRarity(EnumRarity.UNCOMMON));
        }
    }
}
