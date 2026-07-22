package mod.icarus.crimsonrevelations.world;

import mod.icarus.crimsonrevelations.registry.ModBlocksNCR;
import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import org.jetbrains.annotations.NotNull;
import thaumcraft.Thaumcraft;

import java.util.Random;

// TODO: Replace with a configurable list of blocks and dimensions to spawn on?
@SuppressWarnings("ConstantConditions")
public class WorldGenEldritchRuins extends WorldGenerator implements IWorldGenerator {
    // TODO: Add config options
    private static final int HILLTOP_STONES_WEIGHT = 100;
    private static final int TOTEM_WEIGHT = 150;

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider) {
        // TODO: Also add these structures to Augmentation's Emptiness dimension
        // Don't spawn in superflat
        if (world.provider.getDimension() != 0 || world.getWorldInfo().getTerrainType().getName().startsWith("flat")) {
            return;
        }

        int x = (chunkX * 16) + 3 + rand.nextInt(10);
        int z = (chunkZ * 16) + 3 + rand.nextInt(10);

        BlockPos surfacePos = getFlatSurface(world, x, z);
        if (surfacePos == null) return;

        // Try to generate Hilltop Stones first, otherwise attempt to generate an Eldritch Totem if it fails
        if (rand.nextInt(HILLTOP_STONES_WEIGHT) == 0) {
            if (this.generateHilltopStones(world, rand, surfacePos)) {
                return;
            }
        }

        if (rand.nextInt(TOTEM_WEIGHT) == 0) {
            this.generate(world, rand, surfacePos);
        }
    }

    // Hilltop Stones (Wisp Shrine)
    public boolean generateHilltopStones(World world, Random rand, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (y < 60 || y > 120) {
            return false;
        }

        if (containsTree(world, x, y, z, 3, 6)) {
            return false;
        }

        if (!isValidGroundBlock(world, x, y, z) ||
                !isValidGroundBlock(world, x - 2, y, z - 2) ||
                !isValidGroundBlock(world, x + 2, y, z - 2) ||
                !isValidGroundBlock(world, x - 2, y, z + 2) ||
                !isValidGroundBlock(world, x + 2, y, z + 2)) {
            return false;
        }

        IBlockState filler = world.getBiome(pos).fillerBlock;
        if (filler == null) {
            filler = Blocks.DIRT.getDefaultState();
        }

        boolean genVines = !world.getBiome(pos).isHighHumidity();
        IBlockState obsidianTile = getSafeState(ModBlocksNCR.OBSIDIAN_TILE, Blocks.OBSIDIAN.getDefaultState());
        IBlockState obsidianBlock = Blocks.OBSIDIAN.getDefaultState();
        IBlockState totemPole = getSafeState(ModBlocksNCR.ELDRITCH_TOTEM_POLE, Blocks.STONE.getDefaultState());
        clearFoliage(world, x, y, z);

        for (int xx = x - 3; xx <= x + 3; ++xx) {
            for (int zz = z - 3; zz <= z + 3; ++zz) {
                if ((xx == x - 3 || xx == x + 3) && (zz == z - 3 || zz == z + 3)) {
                    continue; // Round corners
                }

                BlockPos ringPos = new BlockPos(xx, y, zz);
                world.setBlockState(ringPos, rand.nextBoolean() ? obsidianTile : obsidianBlock, 2);

                boolean stopTotem = false;
                for (int yy = 1; yy < 5; ++yy) {
                    BlockPos foundationPos = new BlockPos(xx, y - yy, zz);
                    IBlockState state = world.getBlockState(foundationPos);
                    Block block = state.getBlock();
                    if (block instanceof BlockGrass || block == Blocks.SNOW_LAYER || block instanceof IPlantable || world.isAirBlock(foundationPos)) {
                        world.setBlockState(foundationPos, filler, 2);
                    }

                    // Spawner + Chest
                    if (xx == x && zz == z && yy == 1) {
                        BlockPos centerPillarPos = new BlockPos(x, y + yy, z);
                        BlockPos chestPos = centerPillarPos.up();
                        BlockPos spawnerPos = new BlockPos(x, y + yy - 1, z);
                        world.setBlockState(centerPillarPos, obsidianTile, 2);
                        world.setBlockState(chestPos, Blocks.CHEST.getDefaultState(), 2);
                        TileEntityChest chest = (TileEntityChest) world.getTileEntity(chestPos);
                        // TODO: Unique loot table
                        if (chest != null) {
                            chest.setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, rand.nextLong());
                        }
                        world.setBlockState(spawnerPos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                        TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(spawnerPos);
                        if (spawner != null) {
                            // Wisp Spawner
                            spawner.getSpawnerBaseLogic().setEntityId(new ResourceLocation(Thaumcraft.MODID, "wisp"));
                        }
                    }

                    // Totem Poles
                    if (!stopTotem && ((xx == x - 3 || xx == x + 3) && Math.abs((zz - z) % 2) == 1 || (zz == z - 3 || zz == z + 3) && Math.abs((xx - x) % 2) == 1)) {
                        BlockPos totemPos = new BlockPos(xx, y + yy, zz);
                        if (yy == 1 || (yy < 4 && rand.nextInt(3) != 0)) {
                            world.setBlockState(totemPos, totemPole, 2);
                        } else {
                            world.setBlockState(totemPos, getRandomTotemHead(rand), 2);
                            stopTotem = true;
                            if (genVines) {
                                if (rand.nextInt(3) == 0 && world.isAirBlock(totemPos.west()))
                                    growVines(world, totemPos.west(), EnumFacing.EAST);
                                if (rand.nextInt(3) == 0 && world.isAirBlock(totemPos.east()))
                                    growVines(world, totemPos.east(), EnumFacing.WEST);
                                if (rand.nextInt(3) == 0 && world.isAirBlock(totemPos.north()))
                                    growVines(world, totemPos.north(), EnumFacing.SOUTH);
                                if (rand.nextInt(3) == 0 && world.isAirBlock(totemPos.south()))
                                    growVines(world, totemPos.south(), EnumFacing.NORTH);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    // Eldritch Totem
    @Override
    public boolean generate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        int topY = pos.getY();

        if (topY < 60 || topY > 120) {
            return false;
        }

        if (containsTree(world, pos.getX(), topY, pos.getZ(), 1, 5)) {
            return false;
        }

        if (isValidGroundBlock(world, pos.getX(), topY, pos.getZ())) {
            int count = 1;
            while (count < 3) {
                BlockPos checkPos = pos.up(count);
                if (isReplaceableSpace(world, checkPos)) {
                    count++;
                } else {
                    break;
                }
            }

            if (count >= 2) {
                IBlockState obsidianTile = getSafeState(ModBlocksNCR.OBSIDIAN_TILE, Blocks.OBSIDIAN.getDefaultState());
                IBlockState totemHead = getRandomTotemHead(rand);
                IBlockState totemPole = getSafeState(ModBlocksNCR.ELDRITCH_TOTEM_POLE, Blocks.STONE.getDefaultState());
                world.setBlockState(pos, obsidianTile, 2);
                BlockPos underBase = pos.down();
                if (world.getBlockState(underBase).getBlock() instanceof BlockGrass) {
                    IBlockState filler = world.getBiome(pos).fillerBlock;
                    world.setBlockState(underBase, filler != null ? filler : Blocks.DIRT.getDefaultState(), 2);
                }

                count = 1;
                boolean cappedWithHead = false;
                while (count < 5 && isReplaceableSpace(world, pos.up(count))) {
                    BlockPos pillarPos = pos.up(count);
                    if (count > 1 && rand.nextInt(4) == 0) {
                        world.setBlockState(pillarPos, totemHead, 2);
                        cappedWithHead = true;
                        break;
                    } else {
                        world.setBlockState(pillarPos, totemPole, 2);
                    }
                    count++;
                }

                if (!cappedWithHead && count >= 5 && isReplaceableSpace(world, pos.up(5))) {
                    world.setBlockState(pos.up(5), totemHead, 2);
                }

                return true;
            }
        }

        return false;
    }

    private BlockPos getFlatSurface(World world, int x, int z) {
        BlockPos top = world.getHeight(new BlockPos(x, 0, z));
        while (top.getY() > 40) {
            IBlockState state = world.getBlockState(top);
            Block block = state.getBlock();
            if (world.isAirBlock(top) || block == Blocks.SNOW_LAYER || block instanceof IPlantable) {
                top = top.down();
            } else {
                break;
            }
        }

        IBlockState finalState = world.getBlockState(top);
        Block finalBlock = finalState.getBlock();

        if (finalBlock.isLeaves(finalState, world, top) || finalBlock.isWood(world, top)) {
            return null;
        }

        if (finalBlock instanceof BlockGrass || finalBlock instanceof BlockDirt || finalBlock instanceof BlockStone || finalBlock instanceof BlockSand) {
            return top;
        }

        return null;
    }

    private boolean containsTree(World world, int x, int y, int z, int radius, int height) {
        for (int xx = x - radius; xx <= x + radius; ++xx) {
            for (int zz = z - radius; zz <= z + radius; ++zz) {
                for (int yy = 0; yy <= height; ++yy) {
                    BlockPos pos = new BlockPos(xx, y + yy, zz);
                    IBlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();
                    if (block.isLeaves(state, world, pos) || block.isWood(world, pos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidGroundBlock(World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        boolean isGround = block instanceof BlockStone || block instanceof BlockGrass || block instanceof BlockDirt || block instanceof BlockSand;
        if (!isGround) return false;
        IBlockState above = world.getBlockState(pos.up());
        Block blockAbove = above.getBlock();
        return world.isAirBlock(pos.up()) || blockAbove instanceof IPlantable || blockAbove == Blocks.SNOW_LAYER;
    }

    private void clearFoliage(World world, int x, int y, int z) {
        for (int xx = x - 3; xx <= x + 3; ++xx) {
            for (int zz = z - 3; zz <= z + 3; ++zz) {
                for (int yy = 1; yy <= 6; ++yy) {
                    BlockPos clearPos = new BlockPos(xx, y + yy, zz);
                    IBlockState state = world.getBlockState(clearPos);
                    Block block = state.getBlock();
                    if (block instanceof IPlantable || block == Blocks.SNOW_LAYER) {
                        world.setBlockToAir(clearPos);
                    }
                }
            }
        }
    }

    private void growVines(World world, BlockPos pos, EnumFacing facing) {
        PropertyBool property = BlockVine.getPropertyFor(facing);
        IBlockState state = Blocks.VINE.getDefaultState().withProperty(property, true);
        world.setBlockState(pos, state, 2);
        int length = 4;
        BlockPos current = pos.down();
        while (world.isAirBlock(current) && length > 0) {
            world.setBlockState(current, state, 2);
            current = current.down();
            length--;
        }
    }

    private boolean isReplaceableSpace(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        return world.isAirBlock(pos) || block == Blocks.SNOW_LAYER || block instanceof IPlantable;
    }

    private IBlockState getRandomTotemHead(Random rand) {
        switch (rand.nextInt(4)) {
            case 0:
                return getSafeState(ModBlocksNCR.ELDRITCH_TOTEM_DEITY, Blocks.STONE.getDefaultState());
            case 1:
                return getSafeState(ModBlocksNCR.ELDRITCH_TOTEM_GUARDIAN, Blocks.STONE.getDefaultState());
            case 2:
                return getSafeState(ModBlocksNCR.ELDRITCH_TOTEM_WISDOM, Blocks.STONE.getDefaultState());
            default:
                return getSafeState(ModBlocksNCR.ELDRITCH_TOTEM_WRATH, Blocks.STONE.getDefaultState());
        }
    }

    private IBlockState getSafeState(Block block, IBlockState state) {
        return (block != null) ? block.getDefaultState() : state;
    }
}