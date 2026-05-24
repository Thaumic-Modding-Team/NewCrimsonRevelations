package mod.icarus.crimsonrevelations.world;

import mod.icarus.crimsonrevelations.registry.ModBlocksNCR;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class WorldGenManaPods extends WorldGenerator {
    @Override
    public boolean generate(World world, @Nonnull Random rand, BlockPos pos) {
        int x = pos.getX();
        int z = pos.getZ();
        int y = pos.getY();

        for (; y < Math.min(128, world.getHeight(x, z)); y++) {
            BlockPos currentPos = new BlockPos(x, y, z);

            if (world.isAirBlock(currentPos) && world.isAirBlock(currentPos.down())) {
                if (ModBlocksNCR.MANA_POD.canPlaceBlockOnSide(world, currentPos, EnumFacing.DOWN)) {
                    world.setBlockState(currentPos, ModBlocksNCR.MANA_POD.withAge(2 + rand.nextInt(5)), 2);
                    TileEntity tile = world.getTileEntity(currentPos);
                    if (tile instanceof TileManaPod) {
                        ((TileManaPod) tile).checkGrowth();
                    }
                    return true;
                }
            } else {
                x += rand.nextInt(4) - rand.nextInt(4);
                z += rand.nextInt(4) - rand.nextInt(4);
            }
        }
        return false;
    }
}
