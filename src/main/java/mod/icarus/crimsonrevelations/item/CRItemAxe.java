package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;

import javax.annotation.Nonnull;

public class CRItemAxe extends ItemAxe {
    IRarity rarity;

    public CRItemAxe(ToolMaterial material, float damage, float speed) {
        super(material, damage - 1.0F, speed - 4.0F);
        this.setRarity(EnumRarity.COMMON);
    }

    public Item setRarity(@Nonnull IRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public IRarity getForgeRarity(@Nonnull ItemStack stack) {
        return this.rarity;
    }
}
