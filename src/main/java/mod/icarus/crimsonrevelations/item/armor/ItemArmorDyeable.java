package mod.icarus.crimsonrevelations.item.armor;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.item.IDyeableGear;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class ItemArmorDyeable extends ItemArmor implements IDyeableGear {
    public ItemArmorDyeable(String unlocName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot) {
        super(material, renderIndex, equipmentSlot);
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
        this.addPropertyOverride(new ResourceLocation("dyed"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(@NotNull ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
                if (getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata())) {
                    return 1.0F;
                }

                return 0.0F;
            }
        });
    }

    @Override
    public int getDyedColor(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!stack.getTagCompound().hasKey("color", NBT.TAG_INT)) {
            stack.getTagCompound().setInteger("color", getDefaultDyedColorForMeta(stack.getMetadata()));
        }

        return stack.getTagCompound().getInteger("color");
    }

    @Override
    public void setDyedColor(ItemStack stack, int color) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setInteger("color", color);
    }

    @Override
    public int getDefaultDyedColorForMeta(int meta) {
        return 8399402;
    }

    @Override
    public boolean hasColor(@NotNull ItemStack stack) {
        return this.getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata()) ? true : false;
    }

    @Override
    public int getColor(@NotNull ItemStack stack) {
        return getDyedColor(stack);
    }

    @Override
    public void removeColor(@NotNull ItemStack stack) {
        setDyedColor(stack, getDefaultDyedColorForMeta(stack.getMetadata()));
    }

    @Override
    public void setColor(@NotNull ItemStack stack, int color) {
        setDyedColor(stack, color);
    }

    @Override
    public @NotNull EnumActionResult onItemUseFirst(EntityPlayer player, World world, @NotNull BlockPos pos, @NotNull EnumFacing side, float hitX, float hitY, float hitZ, @NotNull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        // Right-clicking a filled cauldron with the dyed item will wash it out
        if (state.getBlock() == Blocks.CAULDRON && state.getValue(BlockCauldron.LEVEL) > 0 && getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata())) {
            setDyedColor(stack, getDefaultDyedColorForMeta(stack.getMetadata()));
            world.setBlockState(pos, state.withProperty(BlockCauldron.LEVEL, state.getValue(BlockCauldron.LEVEL) - 1));
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 0.5F, 1.0F);
            return EnumActionResult.SUCCESS;
        }

        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> list, @NotNull ITooltipFlag tooltip) {
        int color = getDyedColor(stack);

        // If it's dyed, show it on the tooltip
        if (color != getDefaultDyedColorForMeta(stack.getMetadata())) {
            if (tooltip.isAdvanced())
                list.add(new TextComponentTranslation("item.color", TextFormatting.GRAY + String.format("#%06X", color)).getFormattedText());
            else {
                list.add(TextFormatting.ITALIC + new TextComponentTranslation("item.dyed").getFormattedText());
            }
        }
    }
}
