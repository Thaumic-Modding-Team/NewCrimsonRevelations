package mod.icarus.crimsonrevelations.world.village;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import org.jetbrains.annotations.NotNull;
import thaumcraft.Thaumcraft;
import thaumcraft.api.blocks.BlocksTC;

import java.util.List;
import java.util.Random;

public class ComponentThaumaturgeTower extends StructureVillagePieces.Village {
    private int averageGroundLevel = -1;

    // TODO: Loot table
    public static final ResourceLocation TOWER_CHEST_LOOT = new ResourceLocation(NewCrimsonRevelations.MODID, "loot_tables/structures/thaumaturge_tower");

    public ComponentThaumaturgeTower() {
    }

    public ComponentThaumaturgeTower(StructureVillagePieces.Start startPiece, int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing) {
        super(startPiece, type);
        this.setCoordBaseMode(facing);
        this.boundingBox = boundingBox;
    }

    public static ComponentThaumaturgeTower buildComponent(StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int x, int y, int z, EnumFacing facing, int type) {
        StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 6, 12, 6, facing);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ComponentThaumaturgeTower(startPiece, type, random, box, facing) : null;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {
        super.writeStructureToNBT(tagCompound);
        tagCompound.setInteger("AvgGroundLevel", this.averageGroundLevel);
    }

    @Override
    protected void readStructureFromNBT(@NotNull NBTTagCompound compound, @NotNull TemplateManager template) {
        super.readStructureFromNBT(compound, template);
        this.averageGroundLevel = compound.getInteger("AvgGroundLevel");
    }

    @Override
    public boolean addComponentParts(@NotNull World world, @NotNull Random rand, @NotNull StructureBoundingBox bb) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, bb);
            if (this.averageGroundLevel < 0) {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 12 - 1, 0);
        }

        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState arcaneStone = BlocksTC.stoneArcane.getDefaultState();
        IBlockState arcaneStoneBricks = BlocksTC.stoneArcaneBrick.getDefaultState();
        IBlockState glassPane = Blocks.GLASS_PANE.getDefaultState();
        IBlockState planks = BlocksTC.plankGreatwood.getDefaultState();
        IBlockState trapdoor = Blocks.TRAPDOOR.getDefaultState();

        this.fillWithBlocks(world, bb, 2, 1, 2, 4, 11, 4, air, air, false);

        // Floors
        this.fillWithBlocks(world, bb, 2, 0, 2, 4, 0, 4, planks, planks, false);
        this.fillWithBlocks(world, bb, 2, 5, 2, 4, 5, 4, planks, planks, false);
        this.fillWithBlocks(world, bb, 2, 10, 2, 4, 10, 4, planks, planks, false);

        // Walls
        this.fillWithBlocks(world, bb, 1, 0, 2, 1, 11, 4, arcaneStone, arcaneStone, false);
        this.fillWithBlocks(world, bb, 2, 0, 1, 4, 11, 1, arcaneStone, arcaneStone, false);
        this.fillWithBlocks(world, bb, 5, 0, 2, 5, 11, 4, arcaneStone, arcaneStone, false);
        this.fillWithBlocks(world, bb, 2, 0, 5, 4, 11, 5, arcaneStone, arcaneStone, false);

        // Corners
        int[] corners = {1, 5};
        for (int x : corners) {
            for (int z : corners) {
                this.setBlockState(world, arcaneStoneBricks, x, 0, z, bb);
                this.setBlockState(world, arcaneStoneBricks, x, 5, z, bb);
                this.setBlockState(world, arcaneStoneBricks, x, 10, z, bb);
            }
        }

        // Windows
        this.setBlockState(world, glassPane, 3, 7, 1, bb);
        this.setBlockState(world, glassPane, 3, 8, 1, bb);
        this.setBlockState(world, glassPane, 3, 7, 5, bb);
        this.setBlockState(world, glassPane, 3, 8, 5, bb);
        this.setBlockState(world, glassPane, 3, 2, 5, bb);
        this.setBlockState(world, glassPane, 3, 3, 5, bb);

        // Ladders + Trapdoor
        IBlockState ladderState = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.EAST);

        for (int y = 1; y <= 9; ++y) {
            this.setBlockState(world, ladderState, 2, y, 3, bb);
        }

        this.setBlockState(world, trapdoor, 2, 10, 3, bb);

        // Glowstone
        this.setBlockState(world, Blocks.GLOWSTONE.getDefaultState(), 3, 5, 3, bb);

        // Chest
        this.generateChest(world, bb, rand, 2, 6, 2, TOWER_CHEST_LOOT);

        // Door
        this.setBlockState(world, air, 3, 1, 1, bb);
        this.setBlockState(world, air, 3, 2, 1, bb);
        this.createVillageDoor(world, bb, rand, 3, 1, 1, EnumFacing.NORTH);

        // Stairs
        IBlockState stateAtDoor = this.getBlockStateFromPos(world, 3, 0, 0, bb);
        IBlockState stateBelowDoor = this.getBlockStateFromPos(world, 3, -1, 0, bb);
        if (stateAtDoor.getBlock().isAir(stateAtDoor, world, new BlockPos(this.getXWithOffset(3, 0), this.getYWithOffset(0), this.getZWithOffset(3, 0)))
                && !stateBelowDoor.getBlock().isAir(stateBelowDoor, world, new BlockPos(this.getXWithOffset(3, 0), this.getYWithOffset(-1), this.getZWithOffset(3, 0)))) {
            IBlockState stairs = BlocksTC.stairsArcaneBrick.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
            this.setBlockState(world, stairs, 3, 0, 0, bb);
        }

        for (int z = 0; z < 5; ++z) {
            for (int x = 0; x < 5; ++x) {
                this.clearCurrentPositionBlocksUpwards(world, x, 12, z, bb);
                this.replaceAirAndLiquidDownwards(world, arcaneStone, x, -1, z, bb);
            }
        }

        this.spawnVillagers(world, bb, 7, 1, 1, 1);
        return true;
    }

    @Override
    protected VillagerRegistry.@NotNull VillagerProfession chooseForgeProfession(int count, VillagerRegistry.@NotNull VillagerProfession prof) {
        VillagerRegistry.VillagerProfession thaumaturgeProfession = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(
                new ResourceLocation(Thaumcraft.MODID, "thaumaturge")
        );

        if (thaumaturgeProfession == null) {
            return ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft", "librarian"));
        }

        return thaumaturgeProfession;
    }
}