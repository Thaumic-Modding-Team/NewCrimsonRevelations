package mod.icarus.crimsonrevelations.registry;

import com.google.common.base.Preconditions;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.recipe.crafting.DyeableItemRecipe;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.common.lib.events.PlayerEvents;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class RegistrarNCR {
    private static Field lastChargeField;

    @Nonnull
    public static <T extends IForgeRegistryEntry> T setup(@Nonnull final T entry, @Nonnull final String name) {
        return setup(entry, new ResourceLocation(NewCrimsonRevelations.MODID, name));
    }

    @Nonnull
    public static <T extends IForgeRegistryEntry> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
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

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().register(new DyeableItemRecipe().setRegistryName(new ResourceLocation(NewCrimsonRevelations.MODID, "dyeable_item")));
    }
}
