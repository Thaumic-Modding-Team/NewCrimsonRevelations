package mod.icarus.crimsonrevelations.world;

import mod.icarus.crimsonrevelations.registry.ModBlocksNCR;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class WorldGenObsidianTotem extends WorldGenerator implements IWorldGenerator {
    // TODO: Add config option
    private static final int TOTEM_RARITY = 100;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() != 0) {
            return;
        }

        // Don't spawn in superflat
        if (world.getWorldInfo().getTerrainType().getName().startsWith("flat")) {
            return;
        }

        if (random.nextInt(TOTEM_RARITY) == 0) {
            int x = (chunkX * 16) + random.nextInt(16);
            int z = (chunkZ * 16) + random.nextInt(16);
            this.generate(world, random, new BlockPos(x, 0, z));
        }
    }

    @Override
    public boolean generate(World world, @NotNull Random random, @NotNull BlockPos pos) {
        BlockPos surfacePos = world.getHeight(pos);
        int topY = surfacePos.getY() - 1;

        if (topY < 40 || topY > 120) {
            return false;
        }

        BlockPos groundPos = new BlockPos(pos.getX(), topY, pos.getZ());
        IBlockState groundState = world.getBlockState(groundPos);

        while (groundState.getBlock().isLeaves(groundState, world, groundPos) && groundPos.getY() > 40) {
            groundPos = groundPos.down();
            groundState = world.getBlockState(groundPos);
        }

        if (groundState.getBlock() == Blocks.SNOW_LAYER || groundState.getBlock() instanceof IPlantable) {
            groundPos = groundPos.down();
            groundState = world.getBlockState(groundPos);
        }

        // TODO: Replace with a configurable list?
        Block b = groundState.getBlock();
        if (b instanceof BlockGrass || b instanceof BlockSand || b instanceof BlockDirt || b instanceof BlockStone || b instanceof BlockGravel) {
            int count = 1;
            while (count < 3) {
                BlockPos checkPos = groundPos.up(count);
                IBlockState state = world.getBlockState(checkPos);
                Block block = state.getBlock();

                if (world.isAirBlock(checkPos) || block == Blocks.SNOW_LAYER || block instanceof IPlantable) {
                    count++;
                } else {
                    break;
                }
            }

            if (count >= 2) {
                IBlockState obsidianTile = ModBlocksNCR.OBSIDIAN_TILE.getDefaultState();
                IBlockState totemHead = random.nextBoolean() ? ModBlocksNCR.ELDRITCH_TOTEM_DEITY.getDefaultState() : random.nextBoolean() ? ModBlocksNCR.ELDRITCH_TOTEM_GUARDIAN.getDefaultState()
                        : random.nextBoolean() ? ModBlocksNCR.ELDRITCH_TOTEM_WISDOM.getDefaultState() : ModBlocksNCR.ELDRITCH_TOTEM_WRATH.getDefaultState();
                IBlockState totemPole = ModBlocksNCR.ELDRITCH_TOTEM_POLE.getDefaultState();

                // Bottom block
                world.setBlockState(groundPos, obsidianTile, 2);

                boolean placedHead = false;
                count = 1;
                while (count < 5) {
                    BlockPos pillarPos = groundPos.up(count);
                    IBlockState stateAtPos = world.getBlockState(pillarPos);
                    Block blockAtPos = stateAtPos.getBlock();

                    if (world.isAirBlock(pillarPos) || blockAtPos == Blocks.SNOW_LAYER || blockAtPos == Blocks.TALLGRASS) {
                        if (count > 1 && random.nextInt(4) == 0) {
                            world.setBlockState(pillarPos, totemHead, 2);
                            placedHead = true;
                            break;
                        } else {
                            world.setBlockState(pillarPos, totemPole, 2);
                        }
                    } else {
                        break;
                    }

                    count++;
                }

                if (!placedHead && count >= 5) {
                    BlockPos topPos = groundPos.up(5);
                    world.setBlockState(topPos, totemHead, 2);
                }

                return true;
            }
        }

        return false;
    }
}