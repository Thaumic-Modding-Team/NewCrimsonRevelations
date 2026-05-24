package mod.icarus.crimsonrevelations.config;

import mod.icarus.crimsonrevelations.enchants.InfusionEnchantmentBeheading;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

public class ConfigLists {
    public static List<Item> chameleonItems = new ArrayList<>();
    public static List<Potion> manaBeanEffects = new ArrayList<>();

    public static void initLists() {
        manaBeanEffects.clear();

        try {
            for (String entry : ConfigHandlerNCR.mana_beans.effectList) {
                ResourceLocation resLoc = new ResourceLocation(entry);

                if (ForgeRegistries.POTIONS.containsKey(resLoc)) {
                    manaBeanEffects.add(ForgeRegistries.POTIONS.getValue(resLoc));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (String entry : ConfigHandlerNCR.infusion_enchants.chameleonBlacklist) {
                ResourceLocation resLoc = new ResourceLocation(entry);

                if (ForgeRegistries.ITEMS.containsKey(resLoc)) {
                    chameleonItems.add(ForgeRegistries.ITEMS.getValue(resLoc));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        registerMobHeadDrops();
    }

    private static void registerMobHeadDrops() {
        // Map to group drops by entity for multiple drop support
        Map<String, List<ItemDrop>> entityDrops = new HashMap<>();
        // Parse config entries
        for (String entry : ConfigHandlerNCR.infusion_enchants.beheadingMobHeadDrops) {
            String[] parts = entry.split(";");
            if (parts.length < 3) {
                continue;
            }
            String entityRL = parts[0];
            String subtypes = parts[1];
            String itemResource = parts[2];
            // Validate subtypes
            boolean applyToSubtypes;
            try {
                applyToSubtypes = Boolean.parseBoolean(subtypes);
            } catch (Exception e) {
                continue;
            }
            // Parse item (e.g. minecraft:skull:0)
            String[] itemParts = itemResource.split(":");
            if (itemParts.length < 2 || itemParts.length > 3) {
                continue;
            }
            String itemName = itemParts[0] + ":" + itemParts[1];
            int metadata;
            try {
                metadata = itemParts.length == 3 ? Integer.parseInt(itemParts[2]) : 0;
            } catch (NumberFormatException e) {
                continue;
            }
            // Parse max quantity
            int maxQuantity = 1;
            if (parts.length == 4) {
                try {
                    maxQuantity = Integer.parseInt(parts[3]);
                    if (maxQuantity < 1) {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            ResourceLocation itemLocation;
            try {
                itemLocation = new ResourceLocation(itemName);
                if (!Loader.isModLoaded(itemLocation.getNamespace())) {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
            Item headItem = ForgeRegistries.ITEMS.getValue(itemLocation);
            if (headItem == null) {
                continue;
            }
            // Store drop information with subtypes
            ItemDrop drop = new ItemDrop(headItem, metadata, maxQuantity, applyToSubtypes);
            entityDrops.computeIfAbsent(entityRL, k -> new ArrayList<>()).add(drop);
        }
        // Register drops for each entity
        for (Map.Entry<String, List<ItemDrop>> entry : entityDrops.entrySet()) {
            String entityRL = entry.getKey();
            List<ItemDrop> drops = entry.getValue();
            if (entityRL.equals("minecraft:player")) {
                // EntityPlayerMP is the one that shows in the living drop event rather than EntityPlayer
                InfusionEnchantmentBeheading.registerHeadDrop(EntityPlayerMP.class, entity -> {
                    List<ItemStack> dropStacks = new ArrayList<>();
                    for (ItemDrop drop : drops) {
                        ItemStack stack = new ItemStack(drop.item, drop.maxQuantity, drop.metadata);
                        if (entity instanceof EntityPlayer && drop.item instanceof ItemSkull) {
                            NBTUtil.writeGameProfile(stack.getOrCreateSubCompound("SkullOwner"), ((EntityPlayer) entity).getGameProfile());
                        }
                        dropStacks.add(stack);
                    }
                    return dropStacks.isEmpty() ? null : dropStacks.get(new Random().nextInt(dropStacks.size()));
                });
            } else {
                ResourceLocation entityLocation;
                try {
                    entityLocation = new ResourceLocation(entityRL);
                    if (!Loader.isModLoaded(entityLocation.getNamespace())) {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
                EntityEntry entityEntry = ForgeRegistries.ENTITIES.getValue(entityLocation);
                if (entityEntry == null) {
                    continue;
                }
                Class<? extends Entity> entityClass = entityEntry.getEntityClass();
                if (!EntityLivingBase.class.isAssignableFrom(entityClass)) {
                    continue;
                }
                for (ItemDrop drop : drops) {
                    ItemStack headStack = new ItemStack(drop.item, drop.maxQuantity, drop.metadata);
                    if (drop.subtypes) {
                        InfusionEnchantmentBeheading.registerHeadDropForAll((Class<? extends EntityLivingBase>) entityClass, headStack);
                    } else {
                        InfusionEnchantmentBeheading.registerHeadDrop((Class<? extends EntityLivingBase>) entityClass, headStack);
                    }
                }
            }
        }
    }

    private static class ItemDrop {
        final Item item;
        final int metadata;
        final int maxQuantity;
        final boolean subtypes;

        ItemDrop(Item item, int metadata, int maxQuantity, boolean subtypes) {
            this.item = item;
            this.metadata = metadata;
            this.maxQuantity = maxQuantity;
            this.subtypes = subtypes;
        }
    }
}
