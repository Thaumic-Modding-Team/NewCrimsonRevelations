package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.registry.ModSoundEventsNCR;
import mod.icarus.crimsonrevelations.item.base.ItemBase;
import mod.icarus.crimsonrevelations.utils.helpers.ResearchHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.potions.PotionWarpWard;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPrimordialScribingTools extends ItemBase implements IScribeTools {
    public ItemPrimordialScribingTools(String unlocName) {
        super(unlocName);
        this.maxStackSize = 1;
        this.setMaxDamage(200);
        this.setHasSubtypes(false);
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, EntityPlayer player, @NotNull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        ResearchCategory[] categories = ResearchHelper.getResearchCategories();
        int observationProgress = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
        int theoryProgress = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();
        IPlayerWarp warp = ThaumcraftCapabilities.getWarp(player);

        if (stack.getItemDamage() >= stack.getMaxDamage()) {
            if (!world.isRemote) {
                if (categories.length > 0) {
                    ThaumcraftApi.internalMethods.addKnowledge(
                            player,
                            IPlayerKnowledge.EnumKnowledgeType.OBSERVATION,
                            categories[player.getRNG().nextInt(categories.length)],
                            MathHelper.getInt(player.getRNG(), observationProgress / 2, observationProgress));

                    ThaumcraftApi.internalMethods.addKnowledge(
                            player,
                            IPlayerKnowledge.EnumKnowledgeType.THEORY,
                            categories[player.getRNG().nextInt(categories.length)],
                            MathHelper.getInt(player.getRNG(), theoryProgress / 2, theoryProgress));
                }

                if (world.rand.nextDouble() <= ConfigHandlerNCR.primordial_tools.curiosityChance) {
                    // Eldritch or Twisted Curiosity
                    EntityItem curio = new EntityItem(world, player.posX, player.posY, player.posZ, world.rand.nextBoolean() ? new ItemStack(ItemsTC.curio, 1, 3) : new ItemStack(ItemsTC.curio, 1, 5));
                    world.spawnEntity(curio);
                }

                if (FMLLaunchHandler.side().isClient()) {
                    for (int a = 0; a < 40; ++a) {
                        FXDispatcher.INSTANCE.drawNitorFlames((float) player.posX - 1.0F + player.world.rand.nextFloat() * 1.5F, (float) player.getEntityBoundingBox().minY + player.world.rand.nextFloat() * player.height,
                                (float) player.posZ - 1.0F + world.rand.nextFloat() * 1.5F, world.rand.nextGaussian() * 0.0025, world.rand.nextFloat() * 0.06, world.rand.nextGaussian() * 0.0025, 8022271, 0);
                    }
                }

                // Removes some normal warp.
                if (warp.get(EnumWarpType.NORMAL) > 10) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -10, EnumWarpType.NORMAL);
                } else if (warp.get(EnumWarpType.NORMAL) > 0) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -1, EnumWarpType.NORMAL);
                }

                // Removes all temporary warp.
                if (warp.get(EnumWarpType.TEMPORARY) > 0) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -warp.get(EnumWarpType.TEMPORARY), EnumWarpType.TEMPORARY);
                }
            }

            player.swingArm(EnumHand.MAIN_HAND);
            player.playSound(ModSoundEventsNCR.RUNIC_BAUBLE_REGEN, 0.8F, 0.8F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);

            // 1 hour of Warp Ward.
            player.addPotionEffect(new PotionEffect(PotionWarpWard.instance, ConfigHandlerNCR.primordial_tools.warpWardDuration * 1200, 0, false, false));

            player.sendStatusMessage(new TextComponentTranslation("message.crimsonrevelations.scribing_tools.primordial").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE)), true);
            this.setDamage(stack, -this.getMaxDamage(stack)); // Won't restore on Creative mode but I guess that's normal behavior...?
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
    }

    @Override
    public boolean isBookEnchantable(@NotNull ItemStack stack, @NotNull ItemStack book) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getItemDamage() >= stack.getMaxDamage();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip, @NotNull ITooltipFlag flag) {
        if (stack.getItemDamage() >= stack.getMaxDamage()) {
            tooltip.add(new TextComponentTranslation("tooltip.crimsonrevelations.scribing_tools.active").getFormattedText());
        }
    }
}
