package mod.icarus.crimsonrevelations.tile;

import mod.icarus.crimsonrevelations.registry.ModSoundEventsNCR;
import mod.icarus.crimsonrevelations.utils.TCVec3;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.common.blocks.world.taint.ITaintBlock;

public class TileEtherealBloom extends TileEntity implements ITickable {
    public int counter;
    public int rad;
    public int rad1;
    public int growthCounter;
    public int foundTaint;

    public static final int BLOOM_SLEEP = 300;
    public int sleepcounter;
    public boolean sleep;

    public TileEtherealBloom() {
        this.counter = 0;
        this.rad1 = 0;
        this.growthCounter = 100;
        this.foundTaint = BLOOM_SLEEP;
        this.sleepcounter = BLOOM_SLEEP * 4;
        this.sleep = false;
    }

    public void resetSleep() {
        this.foundTaint = BLOOM_SLEEP;
        this.sleepcounter = BLOOM_SLEEP * 4;
        this.sleep = false;
    }

    @Override
    public void update() {
        if (this.world == null) return;

        if (this.counter == 0) {
            this.rad = this.counter = this.world.rand.nextInt(100);
        }

        ++this.counter;

        if (this.foundTaint == 0) {
            --this.sleepcounter;

            this.sleep = (this.sleepcounter > 0);
            if (!this.sleep) {
                this.counter = 0;
                this.sleepcounter = BLOOM_SLEEP * 4;
            }
        }

        if (!this.world.isRemote && this.counter % 20 == 0 && !this.sleep) {
            cleanseFlux();

            this.rad = (int) ((double) this.rad + 5.0D + Math.sqrt(1 + this.rad1) * 5.0D + (double) this.world.rand.nextInt(5));
            if (this.rad > 360) {
                this.rad -= 360;
                this.rad1 += 5 + this.world.rand.nextInt(5);
                if (this.rad1 > 87) {
                    this.rad1 -= 87;
                }
            }

            boolean foundsomething = false;
            TCVec3 vsource = TCVec3.createVectorHelper((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D);

            for (int q = 1; q < 8; ++q) {
                TCVec3 vtar = TCVec3.createVectorHelper(q, 0.0D, 0.0D);
                vtar.rotateAroundZ((float) this.rad1 / 180.0F * (float) Math.PI);
                vtar.rotateAroundY((float) this.rad / 180.0F * (float) Math.PI);
                TCVec3 vres1 = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
                TCVec3 vres2 = vsource.addVector(-vtar.xCoord, -vtar.yCoord, -vtar.zCoord);
                BlockPos t1 = new BlockPos(MathHelper.floor(vres1.xCoord), MathHelper.floor(vres1.yCoord), MathHelper.floor(vres1.zCoord));

                while (this.world.isAirBlock(t1) && t1.getY() > 0) {
                    t1 = t1.down();
                }

                BlockPos t2 = new BlockPos(MathHelper.floor(vres2.xCoord), MathHelper.floor(vres2.yCoord), MathHelper.floor(vres2.zCoord));
                while (this.world.isAirBlock(t2) && t2.getY() > 0) {
                    t2 = t2.down();
                }

                if (this.clearBlock(t1)) {
                    foundsomething = true;
                }

                if (this.clearBlock(t2)) {
                    foundsomething = true;
                }

                if (!foundsomething) continue;

                this.resetSleep();
                break;
            }

            if (this.foundTaint > 0 && !foundsomething) {
                --this.foundTaint;
            }
        }

        if (this.world.isRemote && this.growthCounter == 0) {
            this.world.playSound((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D, ModSoundEventsNCR.MISC_ROOTS, SoundCategory.BLOCKS, 1.0F, 0.6F, false);
        }

        ++this.growthCounter;
        if (this.growthCounter > 100000) {
            this.growthCounter = 100;
        }
    }

    private void cleanseFlux() {
        float currentFlux = AuraHelper.getFlux(this.world, this.pos);
        if (currentFlux > 0.0F) {
            float amountToDrain = Math.min(0.2F, currentFlux);
            AuraHelper.drainFlux(this.world, this.pos, amountToDrain, false);
            this.resetSleep();
        }
    }

    private boolean clearBlock(BlockPos p) {
        IBlockState state = this.world.getBlockState(p);
        Block bt = state.getBlock();

        if (bt instanceof ITaintBlock) {
            ((ITaintBlock) bt).die(this.world, p, state);
            return true;
        }

        return false;
    }
}