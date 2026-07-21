package mod.icarus.crimsonrevelations.registry;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class ModVillagersNCR {
    public static VillagerRegistry.VillagerProfession THAUMATURGE;

    @SubscribeEvent
    public static void onProfessionRegister(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {
        if (!ConfigHandlerNCR.villagers.enableVillagers) return;
        THAUMATURGE = new VillagerRegistry.VillagerProfession(
                NewCrimsonRevelations.MODID + ":thaumaturge",
                NewCrimsonRevelations.MODID + ":textures/entity/villager/thaumaturge.png",
                NewCrimsonRevelations.MODID + ":textures/entity/villager/zombie/zombie_thaumaturge.png"
        );
        event.getRegistry().register(THAUMATURGE);
        registerCareers();
    }

    public static void registerCareers() {
        if (THAUMATURGE == null) return;

        VillagerRegistry.VillagerCareer thaumaturge = new VillagerRegistry.VillagerCareer(THAUMATURGE, "thaumaturge");
        VillagerRegistry.VillagerCareer astronomer = new VillagerRegistry.VillagerCareer(THAUMATURGE, "astronomer");
        VillagerRegistry.VillagerCareer theorist = new VillagerRegistry.VillagerCareer(THAUMATURGE, "theorist");
        VillagerRegistry.VillagerCareer wellnessCoach = new VillagerRegistry.VillagerCareer(THAUMATURGE, "wellness_coach");

        // Thaumaturge
        // Level 1
        thaumaturge.addTrade(1, (merchant, recipeList, random) -> {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1 + random.nextInt(2)), new ItemStack(ItemsTC.alumentum, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1 + random.nextInt(2)), new ItemStack(BlocksTC.nitor.get(EnumDyeColor.YELLOW), 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1 + random.nextInt(2)), new ItemStack(ItemsTC.scribingTools, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(ItemsTC.thaumonomicon, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2 + random.nextInt(2)), new ItemStack(ItemsTC.salisMundus, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 7 + random.nextInt(3)), new ItemStack(ItemsTC.amuletVis, 1, 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 10 + random.nextInt(3)), new ItemStack(ItemsTC.thaumometer, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 7 + random.nextInt(3)), new ItemStack(ModItemsNCR.PROTECTION_RING, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.PAPER, 24 + random.nextInt(13)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.amber, 4 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.quicksilver, 4 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.DYE, 8 + random.nextInt(3), 0), new ItemStack(Items.EMERALD, 1)));
        });

        // Level 2
        thaumaturge.addTrade(2, (merchant, recipeList, random) -> {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 7 + random.nextInt(3)), new ItemStack(ItemsTC.baubles, 1, 3)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 10 + random.nextInt(3)), new ItemStack(ItemsTC.goggles, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 10 + random.nextInt(3)), new ItemStack(ItemsTC.focus1, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 14 + random.nextInt(5)), new ItemStack(ItemsTC.casterBasic, 1)));
        });

        // Astronomer
        // Level 1
        astronomer.addTrade(1, (merchant, recipeList, random) -> {
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 5), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 6), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 7), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 8), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 9), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 10), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 11), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 12), new ItemStack(Items.EMERALD, 1)));
        });

        // Level 2
        astronomer.addTrade(2, (merchant, recipeList, random) -> {
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 0), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 1), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 2), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 3), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.celestialNotes, 1, 4), new ItemStack(Items.EMERALD, 1)));
        });

        // Theorist
        // Level 1
        theorist.addTrade(1, (merchant, recipeList, random) -> {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 6 + random.nextInt(5)), new ItemStack(ItemsTC.curio, 1, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 6 + random.nextInt(5)), new ItemStack(ItemsTC.curio, 1, 4)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.nuggets, 1 + random.nextInt(2), 10), new ItemStack(Items.EMERALD, 1)));
        });

        // Level 2
        theorist.addTrade(2, (merchant, recipeList, random) -> {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 6 + random.nextInt(5)), new ItemStack(ItemsTC.curio, 1, 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 6 + random.nextInt(5)), new ItemStack(ItemsTC.curio, 1, 2)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 10 + random.nextInt(5)), new ItemStack(ItemsTC.pechWand, 1)));
        });

        // Wellness Coach
        // Level 1
        wellnessCoach.addTrade(1, (merchant, recipeList, random) -> {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2), new ItemStack(ModItemsNCR.THAUMIC_LITMUS_PAPER, 3 + random.nextInt(2))));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 5 + random.nextInt(3)), new ItemStack(ItemsTC.sanitySoap, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 3 + random.nextInt(2)), new ItemStack(ItemsTC.bathSalts, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 10 + random.nextInt(3)), new ItemStack(ItemsTC.sanityChecker, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ItemsTC.salisMundus, 3 + random.nextInt(2)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(BlocksTC.vishroom, 2 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
        });
    }
}
