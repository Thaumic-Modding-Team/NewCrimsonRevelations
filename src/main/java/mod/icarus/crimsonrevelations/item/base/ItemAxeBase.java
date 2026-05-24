package mod.icarus.crimsonrevelations.item.base;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ItemAxeBase extends ItemAxe {
    IRarity rarity;

    public ItemAxeBase(String unlocName, ToolMaterial material, float damage, float speed) {
        super(material, damage - 1.0F, speed - 4.0F);
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
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
