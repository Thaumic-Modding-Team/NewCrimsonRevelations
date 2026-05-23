package mod.icarus.crimsonrevelations.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.common.lib.SoundsTC;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CRItemLitmusPaper extends CRItem {
    public CRItemLitmusPaper() {
        super(EnumRarity.UNCOMMON);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        IPlayerWarp warp = ThaumcraftCapabilities.getWarp(player);
        int permWarp = warp.get(IPlayerWarp.EnumWarpType.PERMANENT);
        int normWarp = warp.get(IPlayerWarp.EnumWarpType.NORMAL);
        int tempWarp = warp.get(IPlayerWarp.EnumWarpType.TEMPORARY);

        // What does the paper say?
        if (!world.isRemote) {
            player.sendMessage(new TextComponentTranslation("-").setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
            player.sendMessage(new TextComponentTranslation("status.crimsonrevelations.warp.permanent", permWarp).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
            player.sendMessage(new TextComponentTranslation("status.crimsonrevelations.warp.normal", normWarp).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
            player.sendMessage(new TextComponentTranslation("status.crimsonrevelations.warp.temporary", tempWarp).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
            player.sendMessage(new TextComponentTranslation("-").setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
        }

        if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
        }

        player.playSound(SoundsTC.erase, 0.7F, 1.0F);
        player.getCooldownTracker().setCooldown(this, 200);
        player.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
