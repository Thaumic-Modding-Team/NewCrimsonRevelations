package mod.icarus.crimsonrevelations.registry;

import mod.icarus.crimsonrevelations.block.BlockEtherealBloom;
import mod.icarus.crimsonrevelations.block.BlockManaPod;
import mod.icarus.crimsonrevelations.block.BlockMaterial;
import mod.icarus.crimsonrevelations.block.BlockTotemHead;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocksNCR {
    public static Block ELDRITCH_TOTEM_DEITY = null;
    public static Block ELDRITCH_TOTEM_GUARDIAN = null;
    public static Block ELDRITCH_TOTEM_POLE = null;
    public static Block ELDRITCH_TOTEM_WISDOM = null;
    public static Block ELDRITCH_TOTEM_WRATH = null;
    public static Block MAGIC_TALLOW_BLOCK = null;
    public static BlockManaPod MANA_POD = null;
    public static Block OBSIDIAN_TILE = null;

    // Optional Content
    public static BlockEtherealBloom ETHEREAL_BLOOM = null;

    public static final List<Block> MOD_BLOCKS = new ArrayList<>();

    public static void initBlocks() {
        MOD_BLOCKS.add(MAGIC_TALLOW_BLOCK = new BlockMaterial("magic_tallow_block", Material.ROCK, MapColor.SAND, 4.0F, 15.0F, SoundType.STONE));
        MOD_BLOCKS.add(MANA_POD = new BlockManaPod("mana_pod"));
        MOD_BLOCKS.add(OBSIDIAN_TILE = new BlockMaterial("obsidian_tile", Material.ROCK, MapColor.OBSIDIAN, 50.0F, 4000.0F, SoundType.STONE));
        MOD_BLOCKS.add(ELDRITCH_TOTEM_POLE = new BlockMaterial("eldritch_totem_pole", Material.ROCK, MapColor.OBSIDIAN, 50.0F, 4000.0F, SoundType.STONE));
        MOD_BLOCKS.add(ELDRITCH_TOTEM_DEITY = new BlockTotemHead("eldritch_totem_deity"));
        MOD_BLOCKS.add(ELDRITCH_TOTEM_GUARDIAN = new BlockTotemHead("eldritch_totem_guardian"));
        MOD_BLOCKS.add(ELDRITCH_TOTEM_WISDOM = new BlockTotemHead("eldritch_totem_wisdom"));
        MOD_BLOCKS.add(ELDRITCH_TOTEM_WRATH = new BlockTotemHead("eldritch_totem_wrath"));

        if (ConfigHandlerNCR.ethereal_bloom.enableEtherealBloom) {
            MOD_BLOCKS.add(ETHEREAL_BLOOM = new BlockEtherealBloom("ethereal_bloom"));
        }
    }
}
