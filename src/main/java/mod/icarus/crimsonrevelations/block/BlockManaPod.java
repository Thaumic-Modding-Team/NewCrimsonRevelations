package mod.icarus.crimsonrevelations.block;

import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.item.misc.ItemManaBean;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.internal.WorldCoordinates;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockManaPod extends Block implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 8);
    public static float W1 = 0.0625F;
    public static float W2 = 0.125F;
    public static float W3 = 0.1875F;
    public static float W4 = 0.25F;
    public static float W5 = 0.3125F;
    public static float W6 = 0.375F;
    public static float W7 = 0.4375F;
    public static float W8 = 0.5F;
    public static float W9 = 0.5625F;
    public static float W10 = 0.625F;
    public static float W11 = 0.6875F;
    public static float W12 = 0.75F;
    public static float W13 = 0.8125F;
    public static float W14 = 0.875F;
    public static float W15 = 0.9375F;
    static HashMap<WorldCoordinates, Aspect> st = new HashMap<>();

    public BlockManaPod() {
        super(Material.PLANTS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), 0));
        setTickRandomly(true);
        this.setHardness(0.5F);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }

    protected int getAge(IBlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 8;
    }

    public IBlockState withAge(int age) {
        return this.getDefaultState().withProperty(this.getAgeProperty(), age);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.withAge(meta);
    }

    @Override
    public int getMetaFromState(@Nonnull IBlockState state) {
        return this.getAge(state);
    }

    protected PropertyInteger getAgeProperty() {
        return AGE;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        getBoundingBox(state, world, pos);
        return super.getCollisionBoundingBox(state, world, pos);
    }

    @Override
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos) {
        int l = getMetaFromState(state);

        switch (l) {
            case 0:
                return new AxisAlignedBB(0.25F, W12, 0.25F, 0.75F, 1.0F, 0.75F);
            case 1:
                return new AxisAlignedBB(0.25F, W10, 0.25F, 0.75F, 1.0F, 0.75F);
            case 2:
                return new AxisAlignedBB(0.25F, W8, 0.25F, 0.75F, 1.0F, 0.75F);
            case 3:
                return new AxisAlignedBB(0.25F, W6, 0.25F, 0.75F, 1.0F, 0.75F);
            case 4:
                return new AxisAlignedBB(0.25F, W5, 0.25F, 0.75F, 1.0F, 0.75F);
            case 5:
                return new AxisAlignedBB(0.25F, W4, 0.25F, 0.75F, 1.0F, 0.75F);
            case 6:
                return new AxisAlignedBB(0.25F, W3, 0.25F, 0.75F, 1.0F, 0.75F);
            case 7:
                return new AxisAlignedBB(0.25F, W2, 0.25F, 0.75F, 1.0F, 0.75F);
        }

        return new AxisAlignedBB(0.25F, W2, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBox(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
        getBoundingBox(state, world, pos);
        return super.getSelectedBoundingBox(state, world, pos);
    }

    @Override
    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
        if (!canBlockStay(world, pos, state)) {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        } else if (world.rand.nextInt(30) == 0) {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileManaPod) {
                ((TileManaPod) tile).checkGrowth();
            }

            st.remove(new WorldCoordinates(pos, world.provider.getDimension()));
        }
    }

    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        Biome biome = world.getBiome(pos);
        boolean magicBiome = false;

        if (biome != null) {
            magicBiome = BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL);
        }

        Block i1 = world.getBlockState(pos.up()).getBlock();

        return (magicBiome && (i1 instanceof BlockLog || i1 == BlocksTC.logGreatwood || i1 == BlocksTC.logSilverwood));
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing) {
        Biome biome = world.getBiome(pos);
        boolean magicBiome = biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL);
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
        boolean isLog = blockAbove instanceof BlockLog || blockAbove == BlocksTC.logGreatwood || blockAbove == BlocksTC.logSilverwood;
        return facing == EnumFacing.DOWN && isLog && magicBiome;
    }

    @Override
    public int getLightValue(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return getMetaFromState(state);
    }

    @Override
    public void neighborChanged(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Block block, @Nonnull BlockPos fromPos) {
        if (!canBlockStay(world, pos, state)) {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public void breakBlock(World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileManaPod && ((TileManaPod) tile).aspect != null) {
            st.put(new WorldCoordinates(pos, world.provider.getDimension()), ((TileManaPod) tile).aspect);
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune) {
        int metadata = getMetaFromState(state);

        if (metadata >= 2) {
            byte b0 = 1;

            if (metadata >= 7 && ((World) world).rand.nextFloat() > 0.33F) {
                b0 = 2;
            }

            Aspect aspect = Aspect.PLANT;

            if (st.containsKey(new WorldCoordinates(pos, ((World) world).provider.getDimension()))) {
                aspect = st.get(new WorldCoordinates(pos, ((World) world).provider.getDimension()));
            } else {
                TileEntity tile = world.getTileEntity(pos);
                if (tile instanceof TileManaPod && ((TileManaPod) tile).aspect != null) {
                    aspect = ((TileManaPod) tile).aspect;
                }
            }

            for (int k1 = 0; k1 < b0; ++k1) {
                ItemStack i = new ItemStack(ModItemsNCR.MANA_BEAN);

                ((ItemManaBean) i.getItem()).setAspects(i, (new AspectList()).add(aspect, ConfigHandlerNCR.mana_beans.aspectCount));
                drops.add(i);
            }

            st.remove(new WorldCoordinates(pos, ((World) world).provider.getDimension()));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getItem(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        return ModItemsNCR.MANA_BEAN.getDefaultInstance();
    }

    @Override
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune) {
        return Item.getItemById(0);
    }

    @Override
    public boolean isPassable(@Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileManaPod();
    }

    @Override
    public boolean canGrow(@Nonnull World world, @Nonnull BlockPos pos, IBlockState iBlockState, boolean b) {
        return iBlockState.getValue(AGE) < 8;
    }

    @Override
    public boolean canUseBonemeal(@Nonnull World world, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        return false;
    }

    @Override
    public void grow(World world, @Nonnull Random random, @Nonnull BlockPos pos, IBlockState state) {
        world.setBlockState(pos, state.withProperty(AGE, state.getValue(AGE) + 1), 8);
    }
}
