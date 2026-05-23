package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CRItemPickaxe extends ItemPickaxe {
    EnumRarity rarity;

    public CRItemPickaxe(ToolMaterial material, EnumRarity rarity) {
        super(material);
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getForgeRarity(@Nonnull ItemStack stack) {
        return rarity;
    }
}
