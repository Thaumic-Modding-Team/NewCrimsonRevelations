package mod.icarus.crimsonrevelations;

import mod.icarus.crimsonrevelations.client.keybinds.KeyBindings;
import mod.icarus.crimsonrevelations.compat.CRCompatHandler;
import mod.icarus.crimsonrevelations.config.CRConfigLists;
import mod.icarus.crimsonrevelations.init.*;
import mod.icarus.crimsonrevelations.item.CRItemManaBean;
import mod.icarus.crimsonrevelations.item.IDyeableGear;
import mod.icarus.crimsonrevelations.network.CRPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = NewCrimsonRevelations.MODID, name = NewCrimsonRevelations.NAME, version = NewCrimsonRevelations.VERSION, dependencies = NewCrimsonRevelations.DEPENDENCIES)
public class NewCrimsonRevelations {
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = "New Crimson Revelations";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:configanytime@[3.0,)" +
            ";required-after:thaumcraft" +
            ";required-after:thaumicapi" +
            ";after:thaumcraftfix" +
            ";after:thaumicaugmentation";

    public static final CreativeTabs tabCR = new CRCreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "CrimsonRevelationsTab");

    @Mod.Instance
    public static NewCrimsonRevelations instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CRPacketHandler.init();

        CREntities.registerDispenserBehavior();

        CRCompatHandler.init();
        CRConfigLists.initLists();
        CRLootTables.init();
        CRResearchRegistry.init();

        CRRecipes.initArcaneCrafting();
        CRRecipes.initCrucible();
        CRRecipes.initInfusion();
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        CREntities.registerEntitySpawns();

        CRCompatHandler.postInit();
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void preInitClient(FMLPreInitializationEvent event) {
        CRRenderRegistry.preInit();
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void initClient(FMLInitializationEvent event) {
        KeyBindings.init();

        IItemColor itemColorHandler = (stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof IDyeableGear) {
                return ((IDyeableGear) stack.getItem()).getDyedColor(stack);
            } else {
                return -1;
            }
        };

        IItemColor itemManaBeanColorHandler = (stack, tintIndex) -> {
            Item item = stack.getItem();

            if (item == CRItems.MANA_BEAN) {
                return ((CRItemManaBean) item).getColor(stack, tintIndex);
            }

            return 16777215;
        };

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, CRItems.CRIMSON_RANGER_CHESTPLATE);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, CRItems.CRIMSON_RANGER_HELMET);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, CRItems.CRIMSON_RANGER_LEGGINGS);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemManaBeanColorHandler, CRItems.MANA_BEAN);
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void postinitClient(FMLPostInitializationEvent event) {
    }
}
