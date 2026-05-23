package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CRItemShovel extends ItemSpade {
    EnumRarity rarity;

    public CRItemShovel(ToolMaterial material, EnumRarity rarity) {
        super(material);
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getForgeRarity(@Nonnull ItemStack stack) {
        return rarity;
    }
}
