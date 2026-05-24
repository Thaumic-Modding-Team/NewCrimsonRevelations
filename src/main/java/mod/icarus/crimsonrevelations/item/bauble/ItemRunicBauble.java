package mod.icarus.crimsonrevelations.item.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mod.icarus.crimsonrevelations.item.base.ItemBaubleBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.NonNullList;
import thaumcraft.common.lib.SoundsTC;

import javax.annotation.Nonnull;

public class ItemRunicBauble extends ItemBaubleBase implements IBauble {
    BaubleType type;
    int amount;

    public ItemRunicBauble(BaubleType type, int amount) {
        super(type);
        this.maxStackSize = 1;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        player.playSound(SoundsTC.hhon, 0.75F, 1.0F);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        player.playSound(SoundsTC.hhoff, 0.75F, 1.0F);
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> list) {
        if (this.isInCreativeTab(tab)) {
            ItemStack stack = new ItemStack(this);
            stack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) amount));
            list.add(stack);
        }
    }
}
