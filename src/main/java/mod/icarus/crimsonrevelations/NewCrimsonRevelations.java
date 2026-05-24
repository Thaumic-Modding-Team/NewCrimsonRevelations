package mod.icarus.crimsonrevelations;

import mod.icarus.crimsonrevelations.registry.*;
import mod.icarus.crimsonrevelations.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = NewCrimsonRevelations.MODID,
        name = NewCrimsonRevelations.NAME,
        version = NewCrimsonRevelations.VERSION,
        dependencies = NewCrimsonRevelations.DEPENDENCIES
)
public class NewCrimsonRevelations {
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = "New Crimson Revelations";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:configanytime@[3.0,)" +
            ";required-after:thaumcraft" +
            ";required-after:thaumicapi" +
            ";after:thaumcraftfix" +
            ";after:thaumicaugmentation";

    public static final String CLIENT_PROXY = "mod.icarus.crimsonrevelations.proxy.ClientProxy";
    public static final String COMMON_PROXY = "mod.icarus.crimsonrevelations.proxy.CommonProxy";

    public static final CreativeTabs tabCR = new CreativeTabsNCR(CreativeTabs.CREATIVE_TAB_ARRAY.length, "CrimsonRevelationsTab");

    @Mod.Instance(MODID)
    public static NewCrimsonRevelations instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
