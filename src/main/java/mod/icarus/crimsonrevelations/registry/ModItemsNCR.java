package mod.icarus.crimsonrevelations.registry;

import baubles.api.BaubleType;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.BlockManaPod;
import mod.icarus.crimsonrevelations.client.renderer.tile.TileEtherealBloomTESR;
import mod.icarus.crimsonrevelations.client.renderer.tile.TileManaPodTESR;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.item.armor.*;
import mod.icarus.crimsonrevelations.item.base.*;
import mod.icarus.crimsonrevelations.item.base.ItemBaubleBase;
import mod.icarus.crimsonrevelations.item.bauble.ItemRunicBauble;
import mod.icarus.crimsonrevelations.item.bauble.ItemVerdantRing;
import mod.icarus.crimsonrevelations.item.misc.ItemManaBean;
import mod.icarus.crimsonrevelations.item.tools.*;
import mod.icarus.crimsonrevelations.tile.TileEtherealBloom;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;

import javax.annotation.Nonnull;
import java.util.Objects;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class ModItemsNCR {
    public static final ItemArrowBase AER_ARROW = null;
    public static final ItemAncientCultistArmor ANCIENT_CRIMSON_CHESTPLATE = null;
    public static final ItemAncientCultistArmor ANCIENT_CRIMSON_HELMET = null;
    public static final ItemAncientCultistArmor ANCIENT_CRIMSON_LEGGINGS = null;
    public static final ItemArrowBase AQUA_ARROW = null;
    public static final ItemBoneBow BONE_BOW = null;
    public static final ItemCometBoots COMET_BOOTS = null;
    public static final ItemCultistArcherArmor CRIMSON_ARCHER_CHESTPLATE = null;
    public static final ItemCultistArcherArmor CRIMSON_ARCHER_HELMET = null;
    public static final ItemCultistArcherArmor CRIMSON_ARCHER_LEGGINGS = null;
    public static final ItemBase CRIMSON_FABRIC = null;
    public static final ItemCultistPaladinArmor CRIMSON_PALADIN_CHESTPLATE = null;
    public static final ItemCultistPaladinArmor CRIMSON_PALADIN_HELMET = null;
    public static final ItemCultistPaladinArmor CRIMSON_PALADIN_LEGGINGS = null;
    public static final ItemBase CRIMSON_PLATE = null;
    public static final ItemCultistRangerArmor CRIMSON_RANGER_CHESTPLATE = null;
    public static final ItemCultistRangerArmor CRIMSON_RANGER_HELMET = null;
    public static final ItemCultistRangerArmor CRIMSON_RANGER_LEGGINGS = null;
    public static final ItemSwordBase CRIMSON_SWORD = null;
    public static final ItemBase EMBELLISHED_CRIMSON_FABRIC = null;
    public static final ItemExecutionAxe EXECUTION_AXE = null;
    public static final ItemArrowBase IGNIS_ARROW = null;
    public static final ItemKnowledgeScribingTools KNOWLEDGE_SCRIBING_TOOLS = null;
    public static final ItemManaBean MANA_BEAN = null;
    public static final ItemMeteorBoots METEOR_BOOTS = null;
    public static final ItemArrowBase ORDO_ARROW = null;
    public static final ItemArrowBase PERDITIO_ARROW = null;
    public static final ItemPrimordialScribingTools PRIMORDIAL_SCRIBING_TOOLS = null;
    public static final ItemRunicBauble PROTECTION_RING = null;
    public static final ItemRunicBauble RUNIC_AMULET = null;
    public static final ItemRunicBauble RUNIC_AMULET_EMERGENCY = null;
    public static final ItemRunicBauble RUNIC_GIRDLE = null;
    public static final ItemRunicBauble RUNIC_GIRDLE_KINETIC = null;
    public static final ItemRunicBauble RUNIC_RING = null;
    public static final ItemRunicBauble RUNIC_RING_CHARGED = null;
    public static final ItemRunicBauble RUNIC_RING_REGEN = null;
    public static final ItemSanitationScribingTools SANITATION_SCRIBING_TOOLS = null;
    public static final ItemTechnomancerScribingTools TECHNOMANCER_SCRIBING_TOOLS = null;
    public static final ItemArrowBase TERRA_ARROW = null;
    public static final ItemVerdantRing VERDANT_RING = null;

    // Optional Content
    public static final ItemPickaxeBase DISTORTION_PICKAXE = null;
    public static final ItemBaubleBase NUTRITION_RING = null;
    public static final ItemPurifyingShovel PURIFYING_SHOVEL = null;
    public static final ItemLitmusPaper THAUMIC_LITMUS_PAPER = null;

    @SubscribeEvent
    public static void registerItems(@Nonnull final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        registry.registerAll(
                RegistrarNCR.setup(new ItemBase().setRarity(EnumRarity.UNCOMMON), "crimson_fabric"),
                RegistrarNCR.setup(new ItemBase().setRarity(EnumRarity.UNCOMMON), "embellished_crimson_fabric"),
                RegistrarNCR.setup(new ItemBase().setRarity(EnumRarity.UNCOMMON), "crimson_plate"),
                RegistrarNCR.setup(new ItemSwordBase(ModMaterialsNCR.TOOL_CULTIST).setRarity(EnumRarity.UNCOMMON), "crimson_sword"),
                RegistrarNCR.setup(new ItemExecutionAxe(), "execution_axe").setRarity(EnumRarity.RARE),

                RegistrarNCR.setup(new ItemAncientCultistArmor(EntityEquipmentSlot.HEAD), "ancient_crimson_helmet"),
                RegistrarNCR.setup(new ItemAncientCultistArmor(EntityEquipmentSlot.CHEST), "ancient_crimson_chestplate"),
                RegistrarNCR.setup(new ItemAncientCultistArmor(EntityEquipmentSlot.LEGS), "ancient_crimson_leggings"),
                RegistrarNCR.setup(new ItemCultistArcherArmor(EntityEquipmentSlot.HEAD), "crimson_archer_helmet"),
                RegistrarNCR.setup(new ItemCultistArcherArmor(EntityEquipmentSlot.CHEST), "crimson_archer_chestplate"),
                RegistrarNCR.setup(new ItemCultistArcherArmor(EntityEquipmentSlot.LEGS), "crimson_archer_leggings"),
                RegistrarNCR.setup(new ItemCultistPaladinArmor(EntityEquipmentSlot.HEAD), "crimson_paladin_helmet"),
                RegistrarNCR.setup(new ItemCultistPaladinArmor(EntityEquipmentSlot.CHEST), "crimson_paladin_chestplate"),
                RegistrarNCR.setup(new ItemCultistPaladinArmor(EntityEquipmentSlot.LEGS), "crimson_paladin_leggings"),
                RegistrarNCR.setup(new ItemCultistRangerArmor(EntityEquipmentSlot.HEAD), "crimson_ranger_helmet"),
                RegistrarNCR.setup(new ItemCultistRangerArmor(EntityEquipmentSlot.CHEST), "crimson_ranger_chestplate"),
                RegistrarNCR.setup(new ItemCultistRangerArmor(EntityEquipmentSlot.LEGS), "crimson_ranger_leggings"),

                RegistrarNCR.setup(new ItemBoneBow().setRarity(EnumRarity.RARE), "bone_bow"),
                RegistrarNCR.setup(new ItemArrowBase().setRarity(EnumRarity.UNCOMMON), "aer_arrow"),
                RegistrarNCR.setup(new ItemArrowBase().setRarity(EnumRarity.UNCOMMON), "aqua_arrow"),
                RegistrarNCR.setup(new ItemArrowBase().setRarity(EnumRarity.UNCOMMON), "ignis_arrow"),
                RegistrarNCR.setup(new ItemArrowBase().setRarity(EnumRarity.UNCOMMON), "ordo_arrow"),
                RegistrarNCR.setup(new ItemArrowBase().setRarity(EnumRarity.UNCOMMON), "perditio_arrow"),
                RegistrarNCR.setup(new ItemArrowBase().setRarity(EnumRarity.UNCOMMON), "terra_arrow"),

                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.RING, 1), "protection_ring"),
                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.RING, 5).setRarity(EnumRarity.UNCOMMON), "runic_ring"),
                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.AMULET, 8).setRarity(EnumRarity.UNCOMMON), "runic_amulet"),
                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.BELT, 10).setRarity(EnumRarity.UNCOMMON), "runic_girdle"),
                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.RING, 4).setRarity(EnumRarity.RARE), "runic_ring_charged"),
                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.RING, 4).setRarity(EnumRarity.RARE), "runic_ring_regen"),
                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.AMULET, 7).setRarity(EnumRarity.RARE), "runic_amulet_emergency"),
                RegistrarNCR.setup(new ItemRunicBauble(BaubleType.BELT, 9).setRarity(EnumRarity.RARE), "runic_girdle_kinetic"),
                RegistrarNCR.setup(new ItemVerdantRing(), "verdant_ring"),

                RegistrarNCR.setup(new ItemCometBoots(), "comet_boots"),
                RegistrarNCR.setup(new ItemMeteorBoots(), "meteor_boots"),

                RegistrarNCR.setup(new ItemTechnomancerScribingTools(), "technomancer_scribing_tools").setRarity(EnumRarity.RARE),
                RegistrarNCR.setup(new ItemKnowledgeScribingTools(), "knowledge_scribing_tools").setRarity(ModRaritiesNCR.RARITY_KNOWLEDGE),
                RegistrarNCR.setup(new ItemSanitationScribingTools(), "sanitation_scribing_tools").setRarity(EnumRarity.RARE),
                RegistrarNCR.setup(new ItemPrimordialScribingTools(), "primordial_scribing_tools").setRarity(EnumRarity.EPIC),

                RegistrarNCR.setup(new ItemManaBean(), "mana_bean")
        );

        if (ConfigHandlerNCR.distortion_pickaxe.enableDistortionPickaxe) {
            registry.register(RegistrarNCR.setup(new ItemDistortedPickaxe().setRarity(EnumRarity.RARE), "distortion_pickaxe"));
        }

        if (ConfigHandlerNCR.nutrition_ring.enableNutritionRing) {
            registry.register(RegistrarNCR.setup(new ItemBaubleBase(BaubleType.RING).setRarity(EnumRarity.RARE), "nutrition_ring"));
        }

        if (ConfigHandlerNCR.purifying_shovel.enablePurifyingShovel) {
            registry.register(RegistrarNCR.setup(new ItemPurifyingShovel().setRarity(EnumRarity.RARE), "purifying_shovel"));
        }

        if (ConfigHandlerNCR.thaumic_litmus_paper.enableThaumicLitmusPaper) {
            registry.register(RegistrarNCR.setup(new ItemLitmusPaper().setRarity(EnumRarity.UNCOMMON), "thaumic_litmus_paper"));
        }

        // Item Blocks
        ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(NewCrimsonRevelations.MODID))
                .filter(block -> !(block instanceof BlockDoor)) // Doors should not have an item block registered
                .filter(block -> !(block instanceof BlockSlab)) // Slabs should not have an item block registered
                .filter(block -> !(block instanceof BlockManaPod)) // Mana Pods should not have an item block registered
                .forEach(block -> registry.register(RegistrarNCR.setup(new ItemBlock(block), block.getRegistryName())));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        final IForgeRegistry<IRecipe> registry = event.getRegistry();

        // Special recipes go here
        registry.register(new ShapelessOreRecipe(new ResourceLocation(Thaumcraft.MODID, "inkwell"), KNOWLEDGE_SCRIBING_TOOLS, new ItemStack(KNOWLEDGE_SCRIBING_TOOLS, 1, OreDictionary.WILDCARD_VALUE),
                ThaumcraftApiHelper.makeCrystal(Aspect.SENSES)).setRegistryName(NewCrimsonRevelations.MODID, "knowledge_scribing_tools_refill"));
        registry.register(new ShapelessOreRecipe(new ResourceLocation(Thaumcraft.MODID, "inkwell"), SANITATION_SCRIBING_TOOLS, new ItemStack(SANITATION_SCRIBING_TOOLS, 1, OreDictionary.WILDCARD_VALUE),
                ThaumcraftApiHelper.makeCrystal(Aspect.MIND)).setRegistryName(NewCrimsonRevelations.MODID, "sanitation_scribing_tools_refill"));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModelsEvent(@Nonnull final ModelRegistryEvent event) {
        // Item Models
        for (final Item item : ForgeRegistries.ITEMS.getValues()) {
            if (Objects.requireNonNull(item.getRegistryName()).getNamespace().equals(NewCrimsonRevelations.MODID)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            }
        }

        ClientRegistry.bindTileEntitySpecialRenderer(TileEtherealBloom.class, new TileEtherealBloomTESR());
        ClientRegistry.bindTileEntitySpecialRenderer(TileManaPod.class, new TileManaPodTESR());
    }
}
