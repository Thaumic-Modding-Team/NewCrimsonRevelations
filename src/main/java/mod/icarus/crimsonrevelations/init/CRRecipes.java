package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.enchants.CREnchantments;
import mod.icarus.crimsonrevelations.recipe.ChameleonInfusionRecipe;
import mod.icarus.crimsonrevelations.recipe.VerdantCharmToRing;
import mod.icarus.crimsonrevelations.recipe.VerdantRingToCharm;
import mod.icarus.crimsonrevelations.recipe.VisAttunementInfusionRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.*;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class CRRecipes {
    public static void initArcaneCrafting() {
        // defaultGroup is meant for recipe books and is not really needed here.
        ResourceLocation defaultGroup = new ResourceLocation("");

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "embellished_crimson_fabric"), new ShapelessArcaneRecipe(
                defaultGroup, "CRIMSON_REVELATIONS_BASE", 10,
                new AspectList(),
                new ItemStack(CRItems.EMBELLISHED_CRIMSON_FABRIC),
                new Object[]{CRItems.CRIMSON_FABRIC, CRItems.CRIMSON_PLATE}));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "glyphstone"), new ShapedArcaneRecipe(
                defaultGroup, "CR_ANCIENT_STONE", 15,
                new AspectList(),
                new ItemStack(BlocksTC.stoneAncientGlyphed, 6, 0),
                "SBS",
                "SBS",
                "SBS",
                'S', BlocksTC.stoneAncient, 'B', Items.BOOK));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_helm"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 25,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateHelm),
                "PPP",
                "P P",
                'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_chestplate"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateChest),
                "P P",
                "FEF",
                "PPP",
                'F', CRItems.CRIMSON_FABRIC, 'E', new ItemStack(CRItems.EMBELLISHED_CRIMSON_FABRIC), 'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_greaves"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 40,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateLegs),
                "PBP",
                "P P",
                "P P",
                'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_hood"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 25,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeHelm),
                "FFF",
                "F F",
                'F', CRItems.CRIMSON_FABRIC));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_robes"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeChest),
                "P P",
                "FEF",
                "FFF",
                'F', CRItems.CRIMSON_FABRIC, 'E', new ItemStack(CRItems.EMBELLISHED_CRIMSON_FABRIC), 'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_leggings"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 40,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeLegs),
                "PBP",
                "F F",
                "F F",
                'F', CRItems.CRIMSON_FABRIC, 'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_archer_helm"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 25,
                new AspectList(),
                new ItemStack(CRItems.CRIMSON_ARCHER_HELMET),
                "PPP",
                "F F",
                'F', CRItems.CRIMSON_FABRIC, 'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_archer_chestplate"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 50,
                new AspectList(),
                new ItemStack(CRItems.CRIMSON_ARCHER_CHESTPLATE),
                "P P",
                "FEF",
                "FPF",
                'F', CRItems.CRIMSON_FABRIC, 'E', new ItemStack(CRItems.EMBELLISHED_CRIMSON_FABRIC), 'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_archer_greaves"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 40,
                new AspectList(),
                new ItemStack(CRItems.CRIMSON_ARCHER_LEGGINGS),
                "PBP",
                "P P",
                "F F",
                'F', CRItems.CRIMSON_FABRIC, 'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_boots"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_ARMORY", 15,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonBoots),
                "F F",
                "P P",
                'P', CRItems.CRIMSON_PLATE, 'F', CRItems.CRIMSON_FABRIC));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_sword"), new ShapedArcaneRecipe(
                defaultGroup, "CR_BASIC_CRIMSON_WEAPONRY", 50,
                new AspectList(),
                new ItemStack(CRItems.CRIMSON_SWORD),
                "EPE",
                "EIE",
                "EPE",
                'P', CRItems.CRIMSON_PLATE, 'I', new ItemStack(Items.IRON_SWORD, 1, OreDictionary.WILDCARD_VALUE), 'E', Items.SPIDER_EYE));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "knowledge_scribing_tools"), new ShapelessArcaneRecipe(
                defaultGroup, "CR_KNOWLEDGE_SCRIBING_TOOLS", 50,
                new AspectList().add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.ORDER, 1).add(Aspect.WATER, 1),
                new ItemStack(CRItems.KNOWLEDGE_SCRIBING_TOOLS),
                new Object[]{new ItemStack(ItemsTC.scribingTools, 1, OreDictionary.WILDCARD_VALUE),
                        ItemsTC.salisMundus, ThaumcraftApiHelper.makeCrystal(Aspect.SENSES), ThaumcraftApiHelper.makeCrystal(Aspect.SENSES)}));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "aer_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "CR_PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.AER_ARROW, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.AIR))));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "aqua_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "CR_PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.AQUA_ARROW, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.WATER))));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ignis_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "CR_PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.IGNIS_ARROW, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.FIRE))));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ordo_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "CR_PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.ORDO_ARROW, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.ORDER))));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "perditio_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "CR_PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.PERDITIO_ARROW, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY))));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "terra_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "CR_PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.TERRA_ARROW, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.EARTH))));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "sanitation_scribing_tools"), new ShapelessArcaneRecipe(
                defaultGroup, "CR_SANITATION_SCRIBING_TOOLS", 50,
                new AspectList().add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.ORDER, 1).add(Aspect.WATER, 1),
                new ItemStack(CRItems.SANITATION_SCRIBING_TOOLS),
                new Object[]{new ItemStack(ItemsTC.scribingTools, 1, OreDictionary.WILDCARD_VALUE),
                        new ItemStack(ItemsTC.sanitySoap, 1, OreDictionary.WILDCARD_VALUE), ThaumcraftApiHelper.makeCrystal(Aspect.MIND), ThaumcraftApiHelper.makeCrystal(Aspect.MIND)}));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "technomancer_scribing_tools"), new ShapelessArcaneRecipe(
                defaultGroup, "CR_TECHNOMANCER_SCRIBING_TOOLS", 25,
                new AspectList(),
                new ItemStack(CRItems.TECHNOMANCER_SCRIBING_TOOLS),
                new Object[]{new ItemStack(ItemsTC.scribingTools, 1, OreDictionary.WILDCARD_VALUE),
                        ItemsTC.visResonator}));

        // Optional Arcane Workbench Recipes
        if (CRConfig.thaumic_litmus_paper.enableThaumicLitmusPaper) {
            ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "thaumic_litmus_paper"), new ShapelessArcaneRecipe(
                    defaultGroup, "CR_THAUMIC_LITMUS_PAPER", 10,
                    new AspectList().add(Aspect.WATER, 1),
                    new ItemStack(CRItems.THAUMIC_LITMUS_PAPER, 4),
                    new Object[]{"paper", ItemsTC.salisMundus}));
        }

        // Special Arcane Workbench Recipes
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "verdant_charm_to_ring"), new VerdantCharmToRing());
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "verdant_ring_to_charm"), new VerdantRingToCharm());
    }

    public static void initCrucible() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ancientstone"),
                new CrucibleRecipe("CR_ANCIENT_STONE", new ItemStack(BlocksTC.stoneAncient), new ItemStack(BlocksTC.stoneArcane), new AspectList().add(Aspect.ELDRITCH, 5)));

        // Optional Alchemy Recipes
        if (CRConfig.ethereal_bloom.enableEtherealBloom) {
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ethereal_bloom"),
                    new CrucibleRecipe("CR_ETHEREAL_BLOOM", new ItemStack(CRBlocks.ETHEREAL_BLOOM), BlocksTC.shimmerleaf, new AspectList().add(Aspect.LIGHT, 20).add(Aspect.PLANT, 40).add(Aspect.LIFE, 40).add(Aspect.FLUX, 40)));
        }
    }

    public static void initInfusion() {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "bone_bow"),
                new InfusionRecipe("CR_BONE_BOW", new ItemStack(CRItems.BONE_BOW), 2,
                        new AspectList().add(Aspect.AIR, 40).add(Aspect.FLIGHT, 40).add(Aspect.ENTROPY, 40),
                        Items.BOW,
                        ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY, 1),
                        new ItemStack(ItemsTC.nuggets, 1, 10),
                        Blocks.BONE_BLOCK));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "comet_boots"),
                new InfusionRecipe("CR_COMET_BOOTS", new ItemStack(CRItems.COMET_BOOTS), 4,
                        new AspectList().add(Aspect.MOTION, 100).add(Aspect.WATER, 50).add(Aspect.FLIGHT, 100).add(Aspect.AIR, 50),
                        new ItemStack(ItemsTC.travellerBoots),
                        "oreCrystalWater",
                        "blockQuartz",
                        "blockQuartz",
                        "blockQuartz",
                        "quicksilver",
                        Items.SNOWBALL,
                        "oreCrystalAir"));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimsonblade"),
                new InfusionRecipe("CR_CRIMSON_BLADE", new ItemStack(ItemsTC.crimsonBlade), 7,
                        new AspectList().add(Aspect.AVERSION, 75).add(Aspect.DEATH, 75).add(Aspect.TRAP, 25).add(Aspect.DESIRE, 25),
                        ItemsTC.voidSword,
                        ThaumcraftApiHelper.makeCrystal(Aspect.AVERSION),
                        ThaumcraftApiHelper.makeCrystal(Aspect.DEATH),
                        "plateVoid",
                        "plateVoid",
                        CRItems.CRIMSON_FABRIC,
                        CRItems.CRIMSON_FABRIC));

        ItemStack executionAxeStack = new ItemStack(CRItems.EXECUTION_AXE);
        EnumInfusionEnchantment.addInfusionEnchantment(executionAxeStack, CREnchantments.BEHEADING, 2);
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "execution_axe"),
                new InfusionRecipe("CR_EXECUTION_AXE", executionAxeStack, 4,
                        new AspectList().add(Aspect.AVERSION, 75).add(Aspect.FIRE, 75).add(Aspect.DEATH, 25).add(Aspect.MAN, 25),
                        ItemsTC.thaumiumAxe,
                        ThaumcraftApiHelper.makeCrystal(Aspect.AVERSION),
                        ThaumcraftApiHelper.makeCrystal(Aspect.FIRE),
                        Items.FIRE_CHARGE,
                        new ItemStack(ItemsTC.nuggets, 1, 10),
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "meteor_boots"),
                new InfusionRecipe("CR_METEOR_BOOTS", new ItemStack(CRItems.METEOR_BOOTS), 4,
                        new AspectList().add(Aspect.MOTION, 100).add(Aspect.FIRE, 50).add(Aspect.FLIGHT, 100).add(Aspect.ENTROPY, 50),
                        new ItemStack(ItemsTC.travellerBoots),
                        "oreCrystalFire",
                        "obsidian",
                        "obsidian",
                        "obsidian",
                        Items.MAGMA_CREAM,
                        Items.FIRE_CHARGE,
                        "oreCrystalEntropy"));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "praetor_helm"),
                new InfusionRecipe("CR_PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorHelm), 2,
                        new AspectList().add(Aspect.METAL, 40).add(Aspect.AVERSION, 25).add(Aspect.PROTECT, 20),
                        ItemsTC.crimsonPlateHelm,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "praetor_chestplate"),
                new InfusionRecipe("CR_PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorChest), 2,
                        new AspectList().add(Aspect.METAL, 40).add(Aspect.AVERSION, 25).add(Aspect.PROTECT, 30),
                        ItemsTC.crimsonPlateChest,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.EMBELLISHED_CRIMSON_FABRIC));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "praetor_greaves"),
                new InfusionRecipe("CR_PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorLegs), 2,
                        new AspectList().add(Aspect.METAL, 40).add(Aspect.AVERSION, 25).add(Aspect.PROTECT, 25),
                        ItemsTC.crimsonPlateLegs,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.CRIMSON_PLATE,
                        CRItems.EMBELLISHED_CRIMSON_FABRIC));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "primordial_scribing_tools"),
                new InfusionRecipe("CR_PRIMORDIAL_SCRIBING_TOOLS", new ItemStack(CRItems.PRIMORDIAL_SCRIBING_TOOLS), 10,
                        new AspectList().add(Aspect.AIR, 75).add(Aspect.EARTH, 75).add(Aspect.ENTROPY, 75).add(Aspect.FIRE, 75).add(Aspect.ORDER, 75).add(Aspect.WATER, 75),
                        ItemsTC.scribingTools,
                        ItemsTC.primordialPearl,
                        ItemsTC.voidSeed,
                        ItemsTC.voidSeed,
                        CRItems.KNOWLEDGE_SCRIBING_TOOLS,
                        CRItems.SANITATION_SCRIBING_TOOLS,
                        ItemsTC.voidSeed,
                        ItemsTC.voidSeed));

        ItemStack runicAmuletStack = new ItemStack(CRItems.RUNIC_AMULET);
        runicAmuletStack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 8));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "runic_amulet"),
                new InfusionRecipe("CR_RUNIC_BAUBLES", runicAmuletStack, 2,
                        new AspectList().add(Aspect.ENERGY, 50).add(Aspect.MAGIC, 45).add(Aspect.PROTECT, 25),
                        new ItemStack(ItemsTC.baubles, 1, 4),
                        ItemsTC.salisMundus,
                        "gemAmber",
                        "gemAmber",
                        ItemsTC.fabric,
                        "nitor",
                        new ItemStack(ItemsTC.nuggets, 1, 10)));

        ItemStack runicGirdleStack = new ItemStack(CRItems.RUNIC_GIRDLE);
        runicGirdleStack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 10));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "runic_girdle"),
                new InfusionRecipe("CR_RUNIC_BAUBLES", runicGirdleStack, 2,
                        new AspectList().add(Aspect.ENERGY, 60).add(Aspect.MAGIC, 50).add(Aspect.PROTECT, 30),
                        new ItemStack(ItemsTC.baubles, 1, 6),
                        ItemsTC.salisMundus,
                        "gemAmber",
                        "gemAmber",
                        "gemAmber",
                        ItemsTC.fabric,
                        "nitor",
                        new ItemStack(ItemsTC.nuggets, 1, 10)));

        ItemStack runicRingStack = new ItemStack(CRItems.RUNIC_RING);
        runicRingStack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 5));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "runic_ring"),
                new InfusionRecipe("CR_RUNIC_BAUBLES", runicRingStack, 2,
                        new AspectList().add(Aspect.ENERGY, 40).add(Aspect.MAGIC, 40).add(Aspect.PROTECT, 20),
                        new ItemStack(ItemsTC.baubles, 1, 5),
                        ItemsTC.salisMundus,
                        "gemAmber",
                        ItemsTC.fabric,
                        "nitor",
                        new ItemStack(ItemsTC.nuggets, 1, 10)));

        ItemStack runicAmuletEmergencyStack = new ItemStack(CRItems.RUNIC_AMULET_EMERGENCY);
        runicAmuletEmergencyStack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 7));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "runic_amulet_emergency"),
                new InfusionRecipe("CR_SPECIAL_RUNIC_BAUBLES", runicAmuletEmergencyStack, 4,
                        new AspectList().add(Aspect.EARTH, 50).add(Aspect.MAGIC, 25).add(Aspect.PROTECT, 50).add(Aspect.ENERGY, 25),
                        new ItemStack(CRItems.RUNIC_AMULET),
                        ItemsTC.salisMundus,
                        ThaumcraftApiHelper.makeCrystal(Aspect.EARTH, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.EARTH, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.PROTECT, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.EARTH, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.EARTH, 1)));

        ItemStack runicGirdleKineticStack = new ItemStack(CRItems.RUNIC_GIRDLE_KINETIC);
        runicGirdleKineticStack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 9));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "runic_girdle_kinetic"),
                new InfusionRecipe("CR_SPECIAL_RUNIC_BAUBLES", runicGirdleKineticStack, 4,
                        new AspectList().add(Aspect.AIR, 50).add(Aspect.MAGIC, 25).add(Aspect.PROTECT, 25).add(Aspect.MOTION, 50),
                        new ItemStack(CRItems.RUNIC_GIRDLE),
                        ItemsTC.salisMundus,
                        ThaumcraftApiHelper.makeCrystal(Aspect.AIR, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.AIR, 1),
                        "gunpowder",
                        ThaumcraftApiHelper.makeCrystal(Aspect.AIR, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.AIR, 1)));

        ItemStack runicRingChargedStack = new ItemStack(CRItems.RUNIC_RING_CHARGED);
        runicRingChargedStack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 4));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "runic_ring_charged"),
                new InfusionRecipe("CR_SPECIAL_RUNIC_BAUBLES", runicRingChargedStack, 4,
                        new AspectList().add(Aspect.FIRE, 50).add(Aspect.MAGIC, 25).add(Aspect.ENERGY, 50).add(Aspect.PROTECT, 25),
                        new ItemStack(CRItems.RUNIC_RING),
                        ItemsTC.salisMundus,
                        ThaumcraftApiHelper.makeCrystal(Aspect.FIRE, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.FIRE, 1),
                        "gemAmber",
                        ThaumcraftApiHelper.makeCrystal(Aspect.FIRE, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.FIRE, 1)));

        ItemStack runicRingRegenStack = new ItemStack(CRItems.RUNIC_RING_REGEN);
        runicRingRegenStack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 4));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "runic_ring_regen"),
                new InfusionRecipe("CR_SPECIAL_RUNIC_BAUBLES", runicRingRegenStack, 4,
                        new AspectList().add(Aspect.WATER, 50).add(Aspect.MAGIC, 25).add(Aspect.LIFE, 50).add(Aspect.PROTECT, 25),
                        new ItemStack(CRItems.RUNIC_RING),
                        ItemsTC.salisMundus,
                        ThaumcraftApiHelper.makeCrystal(Aspect.WATER, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.WATER, 1),
                        Items.GHAST_TEAR,
                        ThaumcraftApiHelper.makeCrystal(Aspect.WATER, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.WATER, 1)));

        ItemStack verdantBandStack = new ItemStack(CRItems.VERDANT_RING);
        verdantBandStack.setTagInfo("type", new NBTTagByte((byte) 0));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "verdant_band"),
                new InfusionRecipe("CR_VERDANT_BANDS", new ItemStack(CRItems.VERDANT_RING), 5,
                        new AspectList().add(Aspect.LIFE, 60).add(Aspect.ORDER, 30).add(Aspect.PLANT, 60),
                        new ItemStack(ItemsTC.baubles, 1, 5),
                        new ItemStack(ItemsTC.nuggets, 1, 10),
                        ThaumcraftApiHelper.makeCrystal(Aspect.LIFE, 1),
                        Items.MILK_BUCKET,
                        ThaumcraftApiHelper.makeCrystal(Aspect.PLANT, 1)));

        ItemStack potion1 = new ItemStack(Items.POTIONITEM);
        potion1.setTagInfo("Potion", new NBTTagString("minecraft:strong_healing"));
        ItemStack verdantBandLifeStack = new ItemStack(CRItems.VERDANT_RING);
        verdantBandLifeStack.setTagInfo("type", new NBTTagByte((byte) 1));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "verdant_band_life"),
                new InfusionRecipe("CR_VERDANT_BANDS", verdantBandLifeStack, 5,
                        new AspectList().add(Aspect.LIFE, 80).add(Aspect.MAN, 80),
                        new ItemStack(CRItems.VERDANT_RING),
                        Items.GOLDEN_APPLE,
                        ThaumcraftApiHelper.makeCrystal(Aspect.LIFE, 1),
                        potion1,
                        ThaumcraftApiHelper.makeCrystal(Aspect.MAN, 1)));

        ItemStack potion2 = new ItemStack(Items.POTIONITEM);
        potion2.setTagInfo("Potion", new NBTTagString("minecraft:strong_regeneration"));
        ItemStack verdantBandSustainStack = new ItemStack(CRItems.VERDANT_RING);
        verdantBandSustainStack.setTagInfo("type", new NBTTagByte((byte) 2));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "verdant_band_sustain"),
                new InfusionRecipe("CR_VERDANT_BANDS", verdantBandSustainStack, 5,
                        new AspectList().add(Aspect.DESIRE, 80).add(Aspect.AIR, 80),
                        new ItemStack(CRItems.VERDANT_RING),
                        ItemsTC.tripleMeatTreat,
                        ThaumcraftApiHelper.makeCrystal(Aspect.DESIRE, 1),
                        potion2,
                        ThaumcraftApiHelper.makeCrystal(Aspect.AIR, 1)));

        // Infusion Enchantment Recipes
        InfusionEnchantmentRecipe beheadingInfusion = new InfusionEnchantmentRecipe(CREnchantments.BEHEADING,
                new AspectList().add(Aspect.FIRE, 60).add(Aspect.AVERSION, 60),
                new IngredientNBTTC(new ItemStack(Items.ENCHANTED_BOOK)),
                new ItemStack(Items.SKULL, 1, 0));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "beheading_infusion"), beheadingInfusion);
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "beheading_infusion_fake"), new InfusionEnchantmentRecipe(
                beheadingInfusion, new ItemStack(Items.IRON_AXE)));

        InfusionEnchantmentRecipe chameleonInfusion = new ChameleonInfusionRecipe(
                new AspectList().add(Aspect.MIND, 60).add(Aspect.MAGIC, 60).add(Aspect.VOID, 20),
                new IngredientNBTTC(new ItemStack(Items.ENCHANTED_BOOK)),
                "quicksilver",
                new ItemStack(ItemsTC.nuggets, 1, 10));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "chameleon_infusion"), chameleonInfusion);
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "chameleon_infusion_fake"), new InfusionEnchantmentRecipe(
                chameleonInfusion, new ItemStack(Items.IRON_SWORD)));

        InfusionEnchantmentRecipe visAttunementInfusion = new VisAttunementInfusionRecipe(
                new AspectList().add(Aspect.MAGIC, 100).add(Aspect.AURA, 80),
                new IngredientNBTTC(new ItemStack(Items.ENCHANTED_BOOK)),
                new ItemStack(ItemsTC.fabric));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "vis_attunement_infusion"), visAttunementInfusion);
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "vis_attunement_infusion_fake"), new InfusionEnchantmentRecipe(
                visAttunementInfusion, new ItemStack(Items.IRON_CHESTPLATE)));

        // Optional Infusion Recipes
        if (CRConfig.distortion_pickaxe.enableDistortionPickaxe) {
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "distortion_pickaxe"),
                    new InfusionRecipe("CR_DISTORTION_PICKAXE", new ItemStack(CRItems.DISTORTION_PICKAXE), 1,
                            new AspectList().add(Aspect.ENTROPY, 40).add(Aspect.TOOL, 40).add(Aspect.FLUX, 40),
                            ItemsTC.thaumiumPick,
                            ThaumcraftApiHelper.makeCrystal(Aspect.FLUX, 1),
                            ThaumcraftApiHelper.makeCrystal(Aspect.FLUX, 1),
                            "quicksilver",
                            new ItemStack(ItemsTC.nuggets, 1, 10),
                            BlocksTC.logGreatwood));
        }

        if (CRConfig.nutrition_ring.enableNutritionRing) {
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "nutrition_ring"),
                    new InfusionRecipe("CR_NUTRITION_RING", new ItemStack(CRItems.NUTRITION_RING), 1,
                            new AspectList().add(Aspect.LIFE, 40).add(Aspect.ENERGY, 30).add(Aspect.PLANT, 40),
                            new ItemStack(ItemsTC.baubles, 1, 5),
                            new ItemStack(ItemsTC.nuggets, 1, 10),
                            ThaumcraftApiHelper.makeCrystal(Aspect.LIFE, 1),
                            ThaumcraftApiHelper.makeCrystal(Aspect.ENERGY, 1),
                            ThaumcraftApiHelper.makeCrystal(Aspect.PLANT, 1)));
        }

        if (CRConfig.purifying_shovel.enablePurifyingShovel) {
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "purifying_shovel"),
                    new InfusionRecipe("CR_PURIFYING_SHOVEL", new ItemStack(CRItems.PURIFYING_SHOVEL), 1,
                            new AspectList().add(Aspect.LIFE, 40).add(Aspect.LIGHT, 40).add(Aspect.TOOL, 40),
                            ItemsTC.thaumiumShovel,
                            ThaumcraftApiHelper.makeCrystal(Aspect.AURA, 1),
                            ThaumcraftApiHelper.makeCrystal(Aspect.AURA, 1),
                            "quicksilver",
                            new ItemStack(ItemsTC.nuggets, 1, 10),
                            BlocksTC.logSilverwood));
        }
    }
}
