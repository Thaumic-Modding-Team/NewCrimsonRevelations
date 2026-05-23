package mod.icarus.crimsonrevelations.tile;

import mod.icarus.crimsonrevelations.init.CRBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileThaumcraft;

import java.util.ArrayList;

public class CRTileManaPod extends TileThaumcraft implements IAspectContainer {
    public Aspect aspect = null;

    @Override
    public void readSyncNBT(NBTTagCompound nbt) {
        this.aspect = Aspect.getAspect(nbt.getString("Aspect"));
    }

    @Override
    public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
        if (this.aspect != null) {
            nbt.setString("Aspect", this.aspect.getTag());
        }

        return nbt;
    }

    public void checkGrowth() {
        int l = getBlockMetadata();

        if (l < 7) {
            l++;
            this.world.setBlockState(this.getPos(), CRBlocks.MANA_POD.withAge(l + 1), 3);
        }

        if (l > 2) {
            if (l == 3) {
                AspectList aspects = new AspectList();

                if (this.aspect != null) {
                    aspects.add(this.aspect, 1);
                }

                for (int d = 2; d < 6; d++) {
                    EnumFacing dir = EnumFacing.byIndex(d);
                    int x = this.getPos().getX() + dir.getXOffset();
                    int y = this.getPos().getY() + dir.getYOffset();
                    int z = this.getPos().getZ() + dir.getZOffset();
                    TileEntity tile = this.world.getTileEntity(new BlockPos(x, y, z));

                    if (tile instanceof CRTileManaPod && ((CRTileManaPod) tile).aspect != null) {
                        aspects.add(((CRTileManaPod) tile).aspect, 1);
                    }
                }

                if (aspects.size() > 1) {
                    Aspect[] aa = aspects.getAspects();
                    ArrayList<Aspect> outList = new ArrayList<>();

                    for (int i = 0; i < aa.length; i++) {
                        outList.add(aa[i]);

                        for (int j = 0; j < aa.length; j++) {
                            if (i != j) {
                                Aspect combo = ResearchManager.getCombinationResult(aa[i], aa[j]);

                                if (combo != null) {
                                    outList.add(combo);
                                    outList.add(combo);
                                }
                            }
                        }
                    }

                    if (!outList.isEmpty()) {
                        this.aspect = outList.get(this.world.rand.nextInt(outList.size()));
                        markDirty();
                    }
                }

                if (aspects.size() >= 1 && this.aspect == null) {
                    this.aspect = aspects.getAspectsSortedByAmount()[0];
                    markDirty();
                }
            }

            if (this.aspect == null) {
                if (this.world.rand.nextInt(8) == 0) {
                    this.aspect = Aspect.PLANT;
                } else {
                    ArrayList<Aspect> outlist = Aspect.getPrimalAspects();
                    this.aspect = outlist.get(this.world.rand.nextInt(outlist.size()));
                }

                markDirty();
            }
        }
    }

    @Override
    public AspectList getAspects() {
        return (this.aspect != null && getBlockMetadata() == 7) ? (new AspectList()).add(this.aspect, 1) : null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList aspects) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList aspects) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }
}
