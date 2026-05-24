package mod.icarus.crimsonrevelations.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.ItemsTC;

public class CreativeTabsNCR extends CreativeTabs {
    public CreativeTabsNCR(int length, String name) {
        super(length, name);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemsTC.curio, 1, 6);
    }
}
