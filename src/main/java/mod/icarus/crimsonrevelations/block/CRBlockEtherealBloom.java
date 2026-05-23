package mod.icarus.crimsonrevelations.block;

import mod.icarus.crimsonrevelations.tile.CRTileEtherealBloom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CRBlockEtherealBloom extends BlockBush {
    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 1.125D, 0.7D);

    public CRBlockEtherealBloom() {
        super(Material.PLANTS);
        this.setSoundType(SoundType.PLANT);
        this.setLightLevel(0.8F);
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityLivingBase placer, @Nonnull ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        CRTileEtherealBloom tile = (CRTileEtherealBloom) world.getTileEntity(pos);

        if (tile != null) {
            tile.growthCounter = 0;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isFullBlock();
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() != Blocks.AIR;
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new CRTileEtherealBloom();
    }

    @Override
    public EnumPlantType getPlantType(@Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return EnumPlantType.Cave;
    }

    @Override
    public EnumBlockRenderType getRenderType(@Nonnull IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.NONE;
    }

    @Override
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos) {
        return BUSH_AABB;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }
}