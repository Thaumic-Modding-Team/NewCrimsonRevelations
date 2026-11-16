package mod.icarus.crimsonrevelations.events.world;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.BlockManaPod;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.registry.ModBiomesNCR;
import mod.icarus.crimsonrevelations.world.WorldGenManaPods;
import mod.icarus.crimsonrevelations.world.biome.BiomeTaintedWasteland;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.world.biomes.BiomeGenMagicalForest;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class ManaPodWorldEvent {
    @SubscribeEvent
    public static void onAttackEvent(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();

        // Do not damage tainted mobs in Tainted Wasteland biomes
        if (entity instanceof ITaintedMob) {
            if (damageSource == DamageSource.STARVE && entity.world.getBiome(entity.getPosition()) == ModBiomesNCR.TAINTED_WASTELAND) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onDecorateBiome(DecorateBiomeEvent event) {
        if (event.getWorld().getBiome(event.getPos()) instanceof BiomeGenMagicalForest || event.getWorld().getBiome(event.getPos()) instanceof BiomeTaintedWasteland) {
            WorldGenManaPods worldGenManaPods = new WorldGenManaPods();

            for (int k = 0; k < ConfigHandlerNCR.mana_beans.generationFrequency; k++) {
                int l = event.getPos().getX() + event.getWorld().rand.nextInt(16) + 8;
                byte b0 = 64;
                int i1 = event.getPos().getZ() + event.getWorld().rand.nextInt(16) + 8;
                worldGenManaPods.generate(event.getWorld(), event.getWorld().rand, new BlockPos(l, b0, i1));
            }
        }
    }

    @SubscribeEvent
    public static void onUseBonemeal(BonemealEvent event) {
        // Prevent bonemeal from working on mana beans
        if (event.getBlock().getBlock() instanceof BlockManaPod) {
            event.setCanceled(true);
        }
    }
}
