package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.item.base.ItemPickaxeBase;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

public class ItemDistortedPickaxe extends ItemPickaxeBase {
    public ItemDistortedPickaxe() {
        super(ThaumcraftMaterials.TOOLMAT_ELEMENTAL);
        this.setHarvestLevel("pickaxe", 5);
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(ItemsTC.ingots, 1, 0)) || super.getIsRepairable(stack1, stack2);
    }
}
