package mod.icarus.crimsonrevelations.item;

import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.init.CRSoundEvents;
import mod.icarus.crimsonrevelations.network.CRPacketHandler;
import mod.icarus.crimsonrevelations.network.packets.CRPacketFXArcBolt;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.api.potions.PotionVisExhaust;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
import thaumcraft.common.lib.potions.PotionThaumarhia;

import javax.annotation.Nonnull;

public class CRItemPurifyingShovel extends CRItemShovel {
    public CRItemPurifyingShovel() {
        super(ThaumcraftMaterials.TOOLMAT_ELEMENTAL, EnumRarity.RARE);
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, IBlockState state) {
        if (state.getMaterial() == ThaumcraftMaterials.MATERIAL_TAINT) {
            return efficiency * 1.5F;
        }

        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(ItemsTC.ingots, 1, 0)) || super.getIsRepairable(stack1, stack2);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, EntityPlayer player, @Nonnull EnumHand hand) {
        if (player.isSneaking() && CRConfig.purifying_shovel.enableSpecial && (player.isPotionActive(PotionFluxTaint.instance) || player.isPotionActive(PotionInfectiousVisExhaust.instance) ||
                player.isPotionActive(PotionThaumarhia.instance) || player.isPotionActive(PotionVisExhaust.instance))) {
            player.swingArm(hand);

            for (int a = 0; a < 20; ++a) {
                FXDispatcher.INSTANCE.smokeSpiral(player.posX, player.getEntityBoundingBox().minY + player.height / 2.0F, player.posZ, 1.5F,
                        player.world.rand.nextInt(360), (int) (player.getEntityBoundingBox().minY - 2.0F), 12751838);
            }

            // Remove Taint based potion effects
            if (player.isPotionActive(PotionFluxTaint.instance)) {
                player.removePotionEffect(PotionFluxTaint.instance);
            }

            if (player.isPotionActive(PotionInfectiousVisExhaust.instance)) {
                player.removePotionEffect(PotionInfectiousVisExhaust.instance);
            }

            if (player.isPotionActive(PotionThaumarhia.instance)) {
                player.removePotionEffect(PotionThaumarhia.instance);
            }

            if (player.isPotionActive(PotionVisExhaust.instance)) {
                player.removePotionEffect(PotionVisExhaust.instance);
            }

            player.getHeldItem(hand).damageItem(CRConfig.purifying_shovel.specialCost, player);
            player.getCooldownTracker().setCooldown(this, 10 * 20);
            world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.MISC_PURIFYING_SHOVEL_PURIFY, SoundCategory.PLAYERS, 1.0F, 1.0F);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public EnumActionResult onItemUse(@Nonnull EntityPlayer player, @Nonnull World world, BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        int purified = 0;

        for (int ex = pos.getX() - 5; ex < pos.getX() + 6; ex++) {
            for (int wy = pos.getY() - 4; wy < pos.getY() + 5; wy++) {
                for (int zee = pos.getZ() - 5; zee < pos.getZ() + 6; zee++) {
                    BlockPos aim = new BlockPos(ex, wy, zee);
                    Block target = world.getBlockState(aim).getBlock();

                    // Cleanse nearby flux goo
                    if (target != null && target == BlocksTC.fluxGoo) {
                        purified++;
                        world.setBlockToAir(new BlockPos(ex, wy, zee));

                        for (int k = 0; k < world.rand.nextInt(2); ++k) {
                            CRPacketHandler.INSTANCE.sendToAllAround(new CRPacketFXArcBolt(new Vec3d(player.posX, player.posY + 1.0D, player.posZ), new Vec3d(aim.getX(), aim.getY(), aim.getZ()), 12751838, 0.5F * 0.66F), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64.0D));
                        }
                    }
                }
            }
        }

        if (purified > 0) {
            player.getHeldItem(hand).damageItem(Math.min(purified, CRConfig.purifying_shovel.cleanseMaxCost), player);
            player.swingArm(hand);
            world.playSound(player, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundsTC.zap, SoundCategory.PLAYERS, 1.0F, 1.0F);
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }
}
