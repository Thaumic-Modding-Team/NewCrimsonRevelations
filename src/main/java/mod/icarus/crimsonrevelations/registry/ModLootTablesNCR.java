package mod.icarus.crimsonrevelations.registry;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class ModLootTablesNCR {
    // Loot Tables
    public static final ResourceLocation LESSER_CULTIST_PORTAL = new ResourceLocation(NewCrimsonRevelations.MODID, ("entities/lesser_cultist_portal"));

    // Boss Loot Tables
    public static final ResourceLocation OVERGROWN_TAINTACLE = new ResourceLocation(NewCrimsonRevelations.MODID, ("entities/boss/overgrown_taintacle"));

    public static void init() {
        // Sets the Runic Shielding NBT to the Protection Ring
        ItemStack shieldedRing = new ItemStack(ModItemsNCR.PROTECTION_RING);
        shieldedRing.setTagInfo("TC.RUNIC", new NBTTagByte((byte) 1));

        // bagTypes: 0 = Common, 1 = Uncommon, 2 = Rare
        ThaumcraftApi.addLootBagItem(new ItemStack(ModItemsNCR.CRIMSON_FABRIC), 10, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(ModItemsNCR.CRIMSON_FABRIC), 50, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(ModItemsNCR.CRIMSON_PLATE), 10, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(ModItemsNCR.CRIMSON_PLATE), 50, 1, 2);
        ThaumcraftApi.addLootBagItem(shieldedRing, 5, 0, 1, 2);
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        // Sets the Runic Shielding NBT to the Protection Ring
        NBTTagCompound shieldingNBT = new NBTTagCompound();
        shieldingNBT.setInteger("TC.RUNIC", 1);
        LootFunction setShielding = new SetNBT(new LootCondition[0], shieldingNBT);

        // Crimson Cultists
        if (event.getName().equals(new ResourceLocation(Thaumcraft.MODID, "cultist"))) {
            LootPool crimson_material_pool = event.getTable().getPool("crimson_material");

            if (crimson_material_pool == null) {
                crimson_material_pool = new LootPool(new LootEntry[0], new LootCondition[0], new RandomValueRange(1, 1), new RandomValueRange(1, 1), "crimson_material");
                event.getTable().addPool(crimson_material_pool);
            }

            if (crimson_material_pool != null) {
                crimson_material_pool.addEntry(new LootEntryItem(new ItemStack(ModItemsNCR.CRIMSON_FABRIC).getItem(), 1, 0,
                        new LootFunction[]{new SetCount(new LootCondition[]{new KilledByPlayer(false)}, new RandomValueRange(0, 1)),
                                new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 3)},
                        new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                crimson_material_pool.addEntry(new LootEntryItem(new ItemStack(ModItemsNCR.CRIMSON_PLATE).getItem(), 1, 0,
                        new LootFunction[]{new SetCount(new LootCondition[]{new KilledByPlayer(false)}, new RandomValueRange(0, 1)),
                                new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 3)},
                        new LootCondition[0], "crimsonrevelations:crimson_plate"));
            }
        }

        // Structures
        if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID) || event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
            LootPool main = event.getTable().getPool("main");
            main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 5, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
        }

        if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE) || event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING) ||
                event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR) || event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)) {
            LootPool main = event.getTable().getPool("main");
            main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 3, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
        }

        // Thaumic Augmentation Eldritch Spires
        if (Loader.isModLoaded("thaumicaugmentation")) {
            if (event.getName().equals(new ResourceLocation("thaumicaugmentation", "block/loot_common"))) {
                LootPool main = event.getTable().getPool("loot_common");
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_FABRIC, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PLATE, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_plate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 5, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
            }

            if (event.getName().equals(new ResourceLocation("thaumicaugmentation", "block/loot_uncommon"))) {
                LootPool main = event.getTable().getPool("loot_uncommon");
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_FABRIC, 15, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 2))}, new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PLATE, 15, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 2))}, new LootCondition[0], "crimsonrevelations:crimson_plate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 5, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
            }

            if (event.getName().equals(new ResourceLocation("thaumicaugmentation", "block/loot_rare"))) {
                LootPool main = event.getTable().getPool("loot_rare");
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_FABRIC, 15, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3))}, new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PLATE, 15, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3))}, new LootCondition[0], "crimsonrevelations:crimson_plate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 5, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
            }

            if (event.getName().equals(new ResourceLocation("thaumicaugmentation", "generic/pedestal_common"))) {
                LootPool main = event.getTable().getPool("pedestal_common");
                main.addEntry(new LootEntryItem(ModItemsNCR.ANCIENT_CRIMSON_CHESTPLATE, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:ancient_crimson_chestplate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.ANCIENT_CRIMSON_HELMET, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:ancient_crimson_helmet"));
                main.addEntry(new LootEntryItem(ModItemsNCR.ANCIENT_CRIMSON_LEGGINGS, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:ancient_crimson_leggings"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_FABRIC, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PLATE, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_plate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 5, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
            }

            if (event.getName().equals(new ResourceLocation("thaumicaugmentation", "generic/pedestal_uncommon"))) {
                LootPool main = event.getTable().getPool("pedestal_uncommon");
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_FABRIC, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PLATE, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_plate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_RANGER_CHESTPLATE, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_ranger_chestplate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_RANGER_HELMET, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_ranger_helmet"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_RANGER_LEGGINGS, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_ranger_leggings"));
                main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 5, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
            }

            if (event.getName().equals(new ResourceLocation("thaumicaugmentation", "generic/pedestal_rare"))) {
                LootPool main = event.getTable().getPool("pedestal_rare");
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_FABRIC, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PALADIN_CHESTPLATE, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_paladin_chestplate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PALADIN_HELMET, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_paladin_helmet"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PALADIN_LEGGINGS, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_paladin_leggings"));
                main.addEntry(new LootEntryItem(ModItemsNCR.CRIMSON_PLATE, 10, 0, new LootFunction[0], new LootCondition[0], "crimsonrevelations:crimson_plate"));
                main.addEntry(new LootEntryItem(ModItemsNCR.PROTECTION_RING, 5, 0, new LootFunction[]{setShielding}, new LootCondition[0], "crimsonrevelations:protection_ring"));
            }
        }
    }
}
