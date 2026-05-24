package mod.icarus.crimsonrevelations.item.base;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ItemBase extends Item {
    IRarity rarity;

    public ItemBase(String unlocName) {
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
