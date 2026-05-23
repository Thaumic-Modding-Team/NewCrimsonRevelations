package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.IRarity;

import javax.annotation.Nonnull;

public class CRItemSword extends ItemSword {
    IRarity rarity;

    public CRItemSword(ToolMaterial material) {
        super(material);
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
