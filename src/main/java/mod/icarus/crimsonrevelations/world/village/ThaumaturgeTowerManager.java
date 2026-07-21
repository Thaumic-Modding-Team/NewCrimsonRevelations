package mod.icarus.crimsonrevelations.world.village;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class ThaumaturgeTowerManager implements VillagerRegistry.IVillageCreationHandler {
    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(ComponentThaumaturgeTower.class, 15, MathHelper.getInt(random, i, 1 + i));
    }

    @Override
    public Class<? extends StructureComponent> getComponentClass() {
        MapGenStructureIO.registerStructureComponent(ComponentThaumaturgeTower.class, NewCrimsonRevelations.MODID + ":thaumaturge_tower");
        return ComponentThaumaturgeTower.class;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing p4, int p5) {
        return ComponentThaumaturgeTower.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }
}