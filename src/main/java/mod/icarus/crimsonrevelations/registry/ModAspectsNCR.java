package mod.icarus.crimsonrevelations.registry;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class ModAspectsNCR {
    @SubscribeEvent
    public static void registerAspects(AspectRegistryEvent event) {
        if (Loader.isModLoaded("thaumicaugmentation") && ConfigHandlerNCR.mod_integration_settings.enableTAIntegration)
            ThaumcraftApi.registerEntityTag(NewCrimsonRevelations.MODID + ".overgrown_taintacle", new AspectList().add(Aspect.FLUX, 30).add(Aspect.ELDRITCH, 30).add(Aspect.AVERSION, 30).add(Aspect.PLANT, 30));
    }
}
