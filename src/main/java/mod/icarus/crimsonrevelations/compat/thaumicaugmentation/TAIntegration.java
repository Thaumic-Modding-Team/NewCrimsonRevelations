package mod.icarus.crimsonrevelations.compat.thaumicaugmentation;

import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thaumcraft.common.entities.monster.tainted.EntityTaintSeed;
import thaumcraft.common.entities.monster.tainted.EntityTaintSwarm;
import thecodex6824.thaumicaugmentation.api.world.TABiomes;

public class TAIntegration {
    public static void postInit() {
        // Extra taint mobs
        EntityRegistry.addSpawn(EntityTaintSeed.class, ConfigHandlerNCR.thaumic_augmentation_settings.taintSeedSpawnWeight, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);
        EntityRegistry.addSpawn(EntityTaintSwarm.class, ConfigHandlerNCR.thaumic_augmentation_settings.taintSwarmSpawnWeight, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);

        // Boss
        EntityRegistry.addSpawn(EntityOvergrownTaintacle.class, ConfigHandlerNCR.overgrown_taintacle.spawnWeight, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);
    }
}
