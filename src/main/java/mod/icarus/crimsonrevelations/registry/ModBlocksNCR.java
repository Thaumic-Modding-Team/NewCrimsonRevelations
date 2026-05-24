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
import java.util.ArrayList;
import java.util.List;

public class ModBlocksNCR {
    public static Block MAGIC_TALLOW_BLOCK = null;
    public static BlockManaPod MANA_POD = null;

    // Optional Content
    public static BlockEtherealBloom ETHEREAL_BLOOM = null;

    public static final List<Block> MOD_BLOCKS = new ArrayList<>();

    public static void initBlocks() {
        MOD_BLOCKS.add(MAGIC_TALLOW_BLOCK = new BlockMaterial("magic_tallow_block", Material.ROCK, MapColor.SAND, 4.0F, 15.0F, SoundType.STONE));
        MOD_BLOCKS.add(MANA_POD = new BlockManaPod("mana_pod"));

        if (ConfigHandlerNCR.ethereal_bloom.enableEtherealBloom) {
            MOD_BLOCKS.add(ETHEREAL_BLOOM = new BlockEtherealBloom("ethereal_bloom"));
        }
    }
}
