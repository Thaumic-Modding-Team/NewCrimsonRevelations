package mod.icarus.crimsonrevelations.block;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.registry.ModBlocksNCR;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.item.misc.ItemManaBean;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.internal.WorldCoordinates;
import vazkii.botania.api.item.IHornHarvestable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("deprecation")
@Optional.InterfaceList({
        @Optional.Interface(modid = "botania", iface = "vazkii.botania.api.item.IHornHarvestable"),
        @Optional.Interface(modid = "magicultureintegrations", iface = "com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop")
})
public class BlockManaPod extends Block implements IGrowable, IHarvestableCrop, IHornHarvestable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 8);
    public static float W1 = 0.125F;
    public static float W2 = 0.1875F;
    public static float W3 = 0.25F;
    public static float W4 = 0.3125F;
    public static float W5 = 0.375F;
    public static float W6 = 0.5F;
    public static float W7 = 0.625F;
    public static float W8 = 0.75F;
    static HashMap<WorldCoordinates, Aspect> st = new HashMap<>();

    public BlockManaPod(String unlocName) {
        super(Material.PLANTS);
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), 0));
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
    }

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
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
    public @NotNull IBlockState getStateFromMeta(int meta) {
        return this.withAge(meta);
    }

    @Override
    public int getMetaFromState(@NotNull IBlockState state) {
        return this.getAge(state);
    }

    protected PropertyInteger getAgeProperty() {
        return AGE;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public @NotNull BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        getBoundingBox(state, world, pos);
        return super.getCollisionBoundingBox(state, world, pos);
    }

    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        int l = getMetaFromState(state);

        switch (l) {
            case 0:
                return new AxisAlignedBB(0.25F, W8, 0.25F, 0.75F, 1.0F, 0.75F);
            case 1:
                return new AxisAlignedBB(0.25F, W7, 0.25F, 0.75F, 1.0F, 0.75F);
            case 2:
                return new AxisAlignedBB(0.25F, W6, 0.25F, 0.75F, 1.0F, 0.75F);
            case 3:
                return new AxisAlignedBB(0.25F, W5, 0.25F, 0.75F, 1.0F, 0.75F);
            case 4:
                return new AxisAlignedBB(0.25F, W4, 0.25F, 0.75F, 1.0F, 0.75F);
            case 5:
                return new AxisAlignedBB(0.25F, W3, 0.25F, 0.75F, 1.0F, 0.75F);
            case 6:
                return new AxisAlignedBB(0.25F, W2, 0.25F, 0.75F, 1.0F, 0.75F);
            case 7:
                return new AxisAlignedBB(0.25F, W1, 0.25F, 0.75F, 1.0F, 0.75F);
        }

        return new AxisAlignedBB(0.25F, W1, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public @NotNull AxisAlignedBB getSelectedBoundingBox(@NotNull IBlockState state, @NotNull World world, @NotNull BlockPos pos) {
        getBoundingBox(state, world, pos);
        return super.getSelectedBoundingBox(state, world, pos);
    }

    @Override
    public void updateTick(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull Random rand) {
        if (canBlockStay(world, pos)) {
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

    public boolean canBlockStay(World world, BlockPos pos) {
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
        return (!(blockAbove instanceof BlockLog) && blockAbove != BlocksTC.logGreatwood && blockAbove != BlocksTC.logSilverwood);
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, @NotNull BlockPos pos, @NotNull EnumFacing facing) {
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
        boolean isLog = blockAbove instanceof BlockLog || blockAbove == BlocksTC.logGreatwood || blockAbove == BlocksTC.logSilverwood;
        return facing == EnumFacing.DOWN && isLog;
    }

    @Override
    public int getLightValue(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return getMetaFromState(state);
    }

    @Override
    public void neighborChanged(@NotNull IBlockState state, @NotNull World world, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos fromPos) {
        if (canBlockStay(world, pos)) {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public void breakBlock(World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileManaPod && ((TileManaPod) tile).aspect != null) {
            st.put(new WorldCoordinates(pos, world.provider.getDimension()), ((TileManaPod) tile).aspect);
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public void getDrops(@NotNull NonNullList<ItemStack> drops, @NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull IBlockState state, int fortune) {
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

    @Override
    public @NotNull ItemStack getPickBlock(@NotNull IBlockState state, net.minecraft.util.math.@NotNull RayTraceResult target, @NotNull World world, @NotNull BlockPos pos, EntityPlayer player) {
        return getManaBeanWithAspect(world, pos);
    }

    @Deprecated
    @SideOnly(Side.CLIENT)
    @Override
    public @NotNull ItemStack getItem(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return getManaBeanWithAspect(world, pos);
    }

    private @NotNull ItemStack getManaBeanWithAspect(@NotNull World world, @NotNull BlockPos pos) {
        ItemStack beanStack = new ItemStack(ModItemsNCR.MANA_BEAN);
        TileEntity tile = world.getTileEntity(pos);

        Aspect aspect = Aspect.PLANT;
        if (tile instanceof TileManaPod && ((TileManaPod) tile).aspect != null) {
            aspect = ((TileManaPod) tile).aspect;
        }

        ((ItemManaBean) beanStack.getItem()).setAspects(beanStack, new AspectList().add(aspect, ConfigHandlerNCR.mana_beans.aspectCount));
        return beanStack;
    }

    @Override
    public @NotNull Item getItemDropped(@NotNull IBlockState state, @NotNull Random rand, int fortune) {
        return Item.getItemById(0);
    }

    @Override
    public boolean isPassable(@NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return true;
    }

    @Override
    public boolean hasTileEntity(@NotNull IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileManaPod();
    }

    @Override
    public boolean canGrow(@NotNull World world, @NotNull BlockPos pos, IBlockState iBlockState, boolean b) {
        return iBlockState.getValue(AGE) < 8;
    }

    @Override
    public boolean canUseBonemeal(@NotNull World world, @NotNull Random random, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return false;
    }

    @Override
    public void grow(World world, @NotNull Random random, @NotNull BlockPos pos, IBlockState state) {
        world.setBlockState(pos, state.withProperty(AGE, state.getValue(AGE) + 1), 8);
    }

    //##########################################################
    // Botania Integration
    @Override
    public boolean canHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Override
    public void harvestByHorn(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        IBlockState state = world.getBlockState(blockPos);
        if (state.getBlock() == ModBlocksNCR.MANA_POD && enumHornType == EnumHornType.WILD) {
            if (state.getValue(AGE) >= 8) {
                world.setBlockState(blockPos, state.getBlock().getDefaultState());
                world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, blockPos, Block.getStateId(state));
                NonNullList<ItemStack> drops = NonNullList.create();
                state.getBlock().getDrops(drops, world, blockPos, state, 0);
                drops.forEach(stack -> Block.spawnAsEntity(world, blockPos, stack));
            }
        }
    }

    //##########################################################
    // Magiculture Integrations Integration

    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof IHarvestableCrop) {
            return this.getAge(state) >= 8 ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer entityPlayer, World world, BlockPos pos, boolean silkTouch, int fortune) {
        IBlockState state = world.getBlockState(pos);
        NonNullList<ItemStack> drops = NonNullList.create();
        if (state.getBlock() instanceof IHarvestableCrop && ((IHarvestableCrop) state.getBlock()).getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            state.getBlock().getDrops(drops, world, pos, state, fortune);
            world.setBlockState(pos, state.getBlock().getDefaultState());
        }
        return drops;
    }
}