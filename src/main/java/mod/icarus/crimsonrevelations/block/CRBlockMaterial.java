package mod.icarus.crimsonrevelations.block;

import mod.icarus.crimsonrevelations.init.CRBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class CRBlockMaterial extends Block {
    public CRBlockMaterial(Material material, MapColor mapColor, float hardness, float resistance, SoundType soundType) {
        super(material, mapColor);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
    }

    public CRBlockMaterial(Material material, MapColor mapColor, float hardness, SoundType soundType) {
        super(material, mapColor);
        this.setHardness(hardness);
        this.setSoundType(soundType);
    }

    @Override
    public boolean isFireSource(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        return this == CRBlocks.MAGIC_TALLOW_BLOCK;
    }
}
