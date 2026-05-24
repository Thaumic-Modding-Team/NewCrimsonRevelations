package mod.icarus.crimsonrevelations.registry;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.BlockEtherealBloom;
import mod.icarus.crimsonrevelations.block.BlockManaPod;
import mod.icarus.crimsonrevelations.block.BlockMaterial;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.tile.TileEtherealBloom;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class ModBlocksNCR {
    public static final BlockMaterial MAGIC_TALLOW_BLOCK = null;
    public static final BlockManaPod MANA_POD = null;

    // Optional Content
    public static final BlockEtherealBloom ETHEREAL_BLOOM = null;

    @SubscribeEvent
    public static void registerBlocks(@Nonnull final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        registry.registerAll(
                RegistrarNCR.setup(new BlockMaterial(Material.ROCK, MapColor.SAND, 4.0F, 15.0F, SoundType.STONE), "magic_tallow_block"),
                RegistrarNCR.setup(new BlockManaPod(), "mana_pod")
        );

        if (ConfigHandlerNCR.ethereal_bloom.enableEtherealBloom) {
            registry.register(RegistrarNCR.setup(new BlockEtherealBloom(), "ethereal_bloom"));
            GameRegistry.registerTileEntity(TileEtherealBloom.class, new ResourceLocation(NewCrimsonRevelations.MODID, "ethereal_bloom"));
        }

        GameRegistry.registerTileEntity(TileManaPod.class, new ResourceLocation(NewCrimsonRevelations.MODID, "mana_pod"));
    }
}
