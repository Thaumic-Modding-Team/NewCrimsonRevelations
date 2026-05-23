package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.ItemStack;

public interface IDyeableGear {
    int getDyedColor(ItemStack stack);

    void setDyedColor(ItemStack stack, int color);

    int getDefaultDyedColorForMeta(int meta);
}
