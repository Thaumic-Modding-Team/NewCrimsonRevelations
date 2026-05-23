package mod.icarus.crimsonrevelations.item;

import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.util.ResearchHelperNCR;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.SoundsTC;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CRItemKnowledgeScribingTools extends CRItem implements IScribeTools {
    public CRItemKnowledgeScribingTools() {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(60);
        this.setHasSubtypes(false);
        this.addPropertyOverride(new ResourceLocation("depleted"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack)) {
                    return 1.0F;
                } else if (stack.getItemDamage() >= stack.getMaxDamage() && getDepletedState(stack)) {
                    return 2.0F;
                }

                return 0.0F;
            }
        });
    }

    public static boolean getDepletedState(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("depleted");
    }

    public static void setDepletedState(ItemStack stack, boolean flag) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setBoolean("depleted", flag);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        ResearchCategory[] categories = ResearchHelperNCR.getResearchCategories();
        int observationProgress = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
        int theoryProgress = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();

        if (stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack)) {
            if (!world.isRemote) {
                if (categories.length > 0) {
                    ThaumcraftApi.internalMethods.addKnowledge(
                            player,
                            IPlayerKnowledge.EnumKnowledgeType.OBSERVATION,
                            categories[player.getRNG().nextInt(categories.length)],
                            MathHelper.getInt(player.getRNG(), observationProgress / 3, observationProgress / 2));

                    ThaumcraftApi.internalMethods.addKnowledge(
                            player,
                            IPlayerKnowledge.EnumKnowledgeType.THEORY,
                            categories[player.getRNG().nextInt(categories.length)],
                            MathHelper.getInt(player.getRNG(), theoryProgress / 3, theoryProgress / 2));
                }

                if (world.rand.nextDouble() <= CRConfig.knowledge_tools.curiosityChance) {
                    // Arcane or Illuminating Curiosity
                    EntityItem curio = new EntityItem(world, player.posX, player.posY, player.posZ, world.rand.nextBoolean() ? new ItemStack(ItemsTC.curio, 1, 0) : new ItemStack(ItemsTC.curio, 1, 4));
                    world.spawnEntity(curio);
                }

                if (FMLLaunchHandler.side().isClient()) {
                    for (int a = 0; a < 40; ++a) {
                        FXDispatcher.INSTANCE.drawNitorFlames((float) player.posX - 1.0F + player.world.rand.nextFloat() * 1.5F, (float) player.getEntityBoundingBox().minY + player.world.rand.nextFloat() * player.height,
                                (float) player.posZ - 1.0F + world.rand.nextFloat() * 1.5F, world.rand.nextGaussian() * 0.0025, world.rand.nextFloat() * 0.06, world.rand.nextGaussian() * 0.0025, 12909506, 0);
                    }
                }
            }

            player.swingArm(EnumHand.MAIN_HAND);
            player.playSound(SoundsTC.scan, 0.8F, 0.8F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
            player.sendStatusMessage(new TextComponentTranslation("message.crimsonrevelations.scribing_tools.knowledge").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE)), true);
            setDepletedState(stack, true);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
    }

    @Override
    public boolean isBookEnchantable(@Nonnull ItemStack stack, @Nonnull ItemStack book) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flag) {
        if (stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack)) {
            tooltip.add(new TextComponentTranslation("tooltip.crimsonrevelations.scribing_tools.active").getFormattedText());
        } else if (stack.getItemDamage() >= stack.getMaxDamage() && getDepletedState(stack)) {
            tooltip.add(TextFormatting.ITALIC + new TextComponentTranslation("tooltip.crimsonrevelations.scribing_tools.inactive").getFormattedText());
        }
    }
}
