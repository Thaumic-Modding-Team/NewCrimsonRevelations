package mod.icarus.crimsonrevelations.registry;

import com.google.common.base.Preconditions;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.BlockManaPod;
import mod.icarus.crimsonrevelations.client.renderer.tile.TileEtherealBloomTESR;
import mod.icarus.crimsonrevelations.client.renderer.tile.TileManaPodTESR;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.recipe.crafting.DyeableItemRecipe;
import mod.icarus.crimsonrevelations.tile.TileEtherealBloom;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.events.PlayerEvents;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"rawtypes", "unchecked"})
@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class RegistrarNCR {
    private static Field lastChargeField;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        ModBlocksNCR.initBlocks();
        ModBlocksNCR.MOD_BLOCKS.forEach(registry::register);

        if (ConfigHandlerNCR.ethereal_bloom.enableEtherealBloom) {
            GameRegistry.registerTileEntity(TileEtherealBloom.class, new ResourceLocation(NewCrimsonRevelations.MODID, "ethereal_bloom"));
        }

        GameRegistry.registerTileEntity(TileManaPod.class, new ResourceLocation(NewCrimsonRevelations.MODID, "mana_pod"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ModItemsNCR.initItems();
        ModItemsNCR.MOD_ITEMS.forEach(registry::register);

        ModBlocksNCR.MOD_BLOCKS.stream()
                .filter(block -> !(block instanceof BlockDoor))
                .filter(block -> !(block instanceof BlockSlab))
                .filter(block -> !(block instanceof BlockManaPod))
                .forEach(block -> registry.register(new ItemBlock(block)
                        .setRegistryName(Objects.requireNonNull(block.getRegistryName()))
                        .setTranslationKey(block.getTranslationKey())
                        .setCreativeTab(block.getCreativeTab())
                ));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocksNCR.MOD_BLOCKS.stream()
                .filter(block -> !(block instanceof BlockDoor))
                .filter(block -> !(block instanceof BlockSlab))
                .filter(block -> !(block instanceof BlockManaPod))
                .forEach(block -> {
                    ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory");
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, loc);
                });

        ModItemsNCR.MOD_ITEMS.forEach(item -> {
            ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, loc);
        });

        if (ConfigHandlerNCR.ethereal_bloom.enableEtherealBloom) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEtherealBloom.class, new TileEtherealBloomTESR());
        }

        ClientRegistry.bindTileEntitySpecialRenderer(TileManaPod.class, new TileManaPodTESR());
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        final IForgeRegistry<IRecipe> registry = event.getRegistry();
        ModRecipesNCR.initRecipes(event);

        // Special recipes go here
        registry.register(new ShapelessOreRecipe(new ResourceLocation(Thaumcraft.MODID, "inkwell"), ModItemsNCR.KNOWLEDGE_SCRIBING_TOOLS, new ItemStack(ModItemsNCR.KNOWLEDGE_SCRIBING_TOOLS, 1, OreDictionary.WILDCARD_VALUE),
                ThaumcraftApiHelper.makeCrystal(Aspect.SENSES)).setRegistryName(NewCrimsonRevelations.MODID, "knowledge_scribing_tools_refill"));
        registry.register(new ShapelessOreRecipe(new ResourceLocation(Thaumcraft.MODID, "inkwell"), ModItemsNCR.SANITATION_SCRIBING_TOOLS, new ItemStack(ModItemsNCR.SANITATION_SCRIBING_TOOLS, 1, OreDictionary.WILDCARD_VALUE),
                ThaumcraftApiHelper.makeCrystal(Aspect.MIND)).setRegistryName(NewCrimsonRevelations.MODID, "sanitation_scribing_tools_refill"));
        registry.register(new DyeableItemRecipe().setRegistryName(new ResourceLocation(NewCrimsonRevelations.MODID, "dyeable_item")));
    }

    public static <T extends IForgeRegistryEntry> T setup(@NotNull final T entry, @NotNull final String name) {
        return setup(entry, new ResourceLocation(NewCrimsonRevelations.MODID, name));
    }

    public static <T extends IForgeRegistryEntry> T setup(@NotNull final T entry, @NotNull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry to setup must not be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign must not be null!");

        entry.setRegistryName(registryName);
        if (entry instanceof Block)
            ((Block) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(NewCrimsonRevelations.tabCR);
        if (entry instanceof Item)
            ((Item) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(NewCrimsonRevelations.tabCR);
        return entry;
    }

    // Gets biomes from selected entity.
    public static Biome[] getEntityBiomes(Class<? extends Entity> spawn) {
        List<Biome> biomes = new ArrayList<>();

        for (Biome biome : Biome.REGISTRY) {
            List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.MONSTER);

            for (Biome.SpawnListEntry list : spawnList)
                if (list.entityClass == spawn) {
                    biomes.add(biome);
                    break;
                }
        }

        return biomes.toArray(new Biome[0]);
    }

    // Get Runic Shielding amount
    public static int getRunicShielding(EntityPlayer player) {
        try {
            if (lastChargeField == null)
                lastChargeField = PlayerEvents.class.getDeclaredField("lastCharge");
            if (!lastChargeField.isAccessible())
                lastChargeField.setAccessible(true);

            HashMap<Integer, Integer> lastCharge = (HashMap<Integer, Integer>) lastChargeField.get(null);
            return lastCharge.getOrDefault(player.getEntityId(), 0);
        } catch (Exception ignored) {
            return 0;
        }
    }
}
