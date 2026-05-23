package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import javax.annotation.Nonnull;

public class CRItemSword extends ItemSword {
    EnumRarity rarity;

    public CRItemSword(ToolMaterial material, EnumRarity rarity) {
        super(material);
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getForgeRarity(@Nonnull ItemStack stack) {
        return rarity;
    }
}
