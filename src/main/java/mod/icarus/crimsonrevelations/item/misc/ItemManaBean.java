package mod.icarus.crimsonrevelations.item.misc;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.config.ConfigLists;
import mod.icarus.crimsonrevelations.registry.ModBlocksNCR;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import mod.icarus.crimsonrevelations.utils.helpers.ResearchHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategory;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("deprecation")
public class ItemManaBean extends ItemFood implements IEssentiaContainerItem {
    static Aspect[] displayAspects = (Aspect[]) Aspect.aspects.values().toArray((Object[]) new Aspect[0]);
    public final int itemUseDuration;
    Random rand;

    public ItemManaBean(String unlocName) {
        super(1, 0.5F, true);
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
        this.rand = new Random();
        this.itemUseDuration = 10;
        setHasSubtypes(true);
        setMaxDamage(0);
        setAlwaysEdible();
    }

    public static ItemStack makeManaBean(Aspect aspect, int stackSize) {
        if (aspect == null) {
            return null;
        } else {
            ItemStack stack = new ItemStack(ModItemsNCR.MANA_BEAN, stackSize, 0);
            ModItemsNCR.MANA_BEAN.setAspects(stack, (new AspectList()).add(aspect, ConfigHandlerNCR.mana_beans.aspectCount));
            return stack;
        }
    }

    @Override
    public int getMaxItemUseDuration(@NotNull ItemStack stack) {
        return this.itemUseDuration;
    }

    // Apply various random effects from a configurable list after eating
    @Override
    protected void onFoodEaten(@NotNull ItemStack stack, World world, @NotNull EntityPlayer player) {
        if (!world.isRemote) {
            Potion effect = ConfigLists.manaBeanEffects.get(world.rand.nextInt(ConfigLists.manaBeanEffects.size()));

            // Chance for an eaten bean to grant theories and observations for research
            if (world.rand.nextDouble() <= ConfigHandlerNCR.mana_beans.researchChance) {
                ResearchCategory[] rc = ResearchHelper.getResearchCategories();
                int oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
                int tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();

                ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc[player.getRNG().nextInt(rc.length)], MathHelper.getInt(player.getRNG(), oProg / 2, oProg / 2));
                ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, rc[player.getRNG().nextInt(rc.length)], MathHelper.getInt(player.getRNG(), tProg / 2, tProg / 2));
            }

            if (effect != null) {
                if (effect.isInstant()) {
                    effect.affectEntity(player, player, player, 0, 3.0D);
                } else {
                    player.addPotionEffect(new PotionEffect(effect, (15 * 20) + (world.rand.nextInt(30) * 20), 0));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> list) {
        if (tab == NewCrimsonRevelations.tabCR || tab == CreativeTabs.SEARCH) {

            for (Aspect tag : Aspect.aspects.values()) {
                ItemStack stack = new ItemStack(this);
                this.setAspects(stack, (new AspectList()).add(tag, ConfigHandlerNCR.mana_beans.aspectCount));
                list.add(stack);
            }
        }
    }

    // Get aspect colors
    @SideOnly(Side.CLIENT)
    public int getColor(ItemStack stack, int par2) {
        if (getAspects(stack) != null) {
            return getAspects(stack).getAspects()[0].getColor();
        }

        int id = (int) (System.currentTimeMillis() / 500L % displayAspects.length);
        return displayAspects[id].getColor();
    }

    // Add aspect count to our beans (5 by default)
    @Override
    public void onUpdate(@NotNull ItemStack stack, World world, @NotNull Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && !stack.hasTagCompound()) {
            setAspects(stack, (new AspectList()).add(displayAspects[this.rand.nextInt(displayAspects.length)], ConfigHandlerNCR.mana_beans.aspectCount));
        }

        super.onUpdate(stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public void onCreated(ItemStack stack, @NotNull World world, @NotNull EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            setAspects(stack, (new AspectList()).add(displayAspects[this.rand.nextInt(displayAspects.length)], ConfigHandlerNCR.mana_beans.aspectCount));
        }
    }

    @Override
    public AspectList getAspects(ItemStack stack) {
        if (stack.hasTagCompound()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(stack.getTagCompound());
            return (aspects.size() > 0) ? aspects : null;
        }

        return null;
    }

    @Override
    public void setAspects(ItemStack stack, AspectList aspects) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        aspects.writeToNBT(stack.getTagCompound());
    }

    @Override
    public boolean ignoreContainedAspects() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public @NotNull String getItemStackDisplayName(@NotNull ItemStack stack) {
        return (getAspects(stack) != null && !(getAspects(stack)).aspects.isEmpty()) ?
                (String.format(super.getItemStackDisplayName(stack), getAspects(stack).getAspects()[0].getName())) : I18n.format(getTranslationKey(stack) + ".default.name");
    }

    @Override
    public @NotNull EnumActionResult onItemUse(EntityPlayer player, @NotNull World world, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!player.canPlayerEdit(pos, facing, player.getHeldItem(hand)) || facing.getIndex() != 0) {
            return EnumActionResult.FAIL;
        }

        Biome biome = world.getBiome(pos);
        boolean magicBiome = false;

        if (biome != null) {
            magicBiome = BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL);
        }

        if (!magicBiome) {
            return EnumActionResult.FAIL;
        }

        Block block = world.getBlockState(pos).getBlock();

        if (block instanceof BlockLog || block == BlocksTC.logGreatwood || block == BlocksTC.logSilverwood) {
            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());

            if (world.isAirBlock(pos1)) {
                IBlockState state = ModBlocksNCR.MANA_POD.getStateForPlacement(world, pos1, facing, hitX, hitY, hitZ, 0, player);
                world.setBlockState(pos1, state, 2);
                TileEntity tile = world.getTileEntity(pos1);

                if (tile instanceof TileManaPod && getAspects(player.getHeldItem(hand)) != null && getAspects(player.getHeldItem(hand)).size() > 0) {
                    ((TileManaPod) tile).aspect = getAspects(player.getHeldItem(hand)).getAspects()[0];
                }

                if (!player.capabilities.isCreativeMode) {
                    player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
                }
            }

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.SUCCESS;
    }
}
