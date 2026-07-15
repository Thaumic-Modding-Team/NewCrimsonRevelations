package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.item.base.ItemPickaxeBase;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.items.ItemsTC;

public class ItemDistortionPickaxe extends ItemPickaxeBase {
    public ItemDistortionPickaxe(String unlocName) {
        super(unlocName, ThaumcraftMaterials.TOOLMAT_ELEMENTAL);
        this.setHarvestLevel("pickaxe", 5);
    }

    @Override
    public boolean getIsRepairable(@NotNull ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(ItemsTC.ingots, 1, 0)) || super.getIsRepairable(stack1, stack2);
    }
}
