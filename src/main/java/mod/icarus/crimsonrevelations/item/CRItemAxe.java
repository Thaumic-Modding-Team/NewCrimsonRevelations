package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CRItemAxe extends ItemAxe {
    EnumRarity rarity;

    public CRItemAxe(ToolMaterial material, EnumRarity rarity, float damage, float speed) {
        super(material, damage - 1.0F, speed - 4.0F);
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getForgeRarity(@Nonnull ItemStack stack) {
        return rarity;
    }
}
