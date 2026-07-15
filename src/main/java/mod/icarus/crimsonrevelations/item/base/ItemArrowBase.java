package mod.icarus.crimsonrevelations.item.base;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import org.jetbrains.annotations.NotNull;
import thaumcraft.common.lib.SoundsTC;

import java.util.Objects;

public class ItemArrowBase extends net.minecraft.item.ItemArrow {
    IRarity rarity;

    public ItemArrowBase(String unlocName) {
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
        this.setRarity(EnumRarity.COMMON);
    }

    public Item setRarity(@NotNull IRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
        return this.rarity;
    }

    @Override
    public @NotNull EntityArrow createArrow(@NotNull World world, @NotNull ItemStack stack, @NotNull EntityLivingBase shooter) {
        if (this == ModItemsNCR.AER_ARROW) {
            EntityPrimalArrow aerArrow = new EntityPrimalArrow(world, shooter, ModItemsNCR.AER_ARROW);
            aerArrow.setArrowType(0);
            aerArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aerArrow;
        } else if (this == ModItemsNCR.AQUA_ARROW) {
            EntityPrimalArrow aquaArrow = new EntityPrimalArrow(world, shooter, ModItemsNCR.AQUA_ARROW);
            aquaArrow.setArrowType(1);
            aquaArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aquaArrow;
        } else if (this == ModItemsNCR.IGNIS_ARROW) {
            EntityPrimalArrow ignisArrow = new EntityPrimalArrow(world, shooter, ModItemsNCR.IGNIS_ARROW);
            ignisArrow.setArrowType(2);
            ignisArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ignisArrow;
        } else if (this == ModItemsNCR.ORDO_ARROW) {
            EntityPrimalArrow ordoArrow = new EntityPrimalArrow(world, shooter, ModItemsNCR.ORDO_ARROW);
            ordoArrow.setArrowType(3);
            ordoArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ordoArrow;
        } else if (this == ModItemsNCR.PERDITIO_ARROW) {
            EntityPrimalArrow perditioArrow = new EntityPrimalArrow(world, shooter, ModItemsNCR.PERDITIO_ARROW);
            perditioArrow.setArrowType(4);
            perditioArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return perditioArrow;
        } else if (this == ModItemsNCR.TERRA_ARROW) {
            EntityPrimalArrow terraArrow = new EntityPrimalArrow(world, shooter, ModItemsNCR.TERRA_ARROW);
            terraArrow.setArrowType(5);
            terraArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return terraArrow;
        }

        return new EntitySpectralArrow(world, shooter);
    }

    @Override
    public boolean isInfinite(@NotNull ItemStack stack, @NotNull ItemStack item, @NotNull EntityPlayer player) {
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, item);

        if (this == ModItemsNCR.AER_ARROW ||
                this == ModItemsNCR.AQUA_ARROW ||
                this == ModItemsNCR.IGNIS_ARROW ||
                this == ModItemsNCR.ORDO_ARROW ||
                this == ModItemsNCR.PERDITIO_ARROW ||
                this == ModItemsNCR.TERRA_ARROW) {
            if (enchantLevel <= 0) {
                return false;
            } else {

                // Primal arrows have a 1/3 chance to work with the Infinity enchant
                return (player.getRNG().nextInt(3) == 0);
            }
        }

        return false;
    }
}
