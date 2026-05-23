package mod.icarus.crimsonrevelations.item;

import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import mod.icarus.crimsonrevelations.init.CRItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import thaumcraft.common.lib.SoundsTC;

import javax.annotation.Nonnull;

public class CRItemArrow extends ItemArrow {
    IRarity rarity;

    public CRItemArrow() {
        super();
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

    @Override
    public EntityArrow createArrow(@Nonnull World world, @Nonnull ItemStack stack, @Nonnull EntityLivingBase shooter) {
        if (this == CRItems.AER_ARROW) {
            EntityPrimalArrow aerArrow = new EntityPrimalArrow(world, shooter, CRItems.AER_ARROW);
            aerArrow.setArrowType(0);
            aerArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aerArrow;
        } else if (this == CRItems.AQUA_ARROW) {
            EntityPrimalArrow aquaArrow = new EntityPrimalArrow(world, shooter, CRItems.AQUA_ARROW);
            aquaArrow.setArrowType(1);
            aquaArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aquaArrow;
        } else if (this == CRItems.IGNIS_ARROW) {
            EntityPrimalArrow ignisArrow = new EntityPrimalArrow(world, shooter, CRItems.IGNIS_ARROW);
            ignisArrow.setArrowType(2);
            ignisArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ignisArrow;
        } else if (this == CRItems.ORDO_ARROW) {
            EntityPrimalArrow ordoArrow = new EntityPrimalArrow(world, shooter, CRItems.ORDO_ARROW);
            ordoArrow.setArrowType(3);
            ordoArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ordoArrow;
        } else if (this == CRItems.PERDITIO_ARROW) {
            EntityPrimalArrow perditioArrow = new EntityPrimalArrow(world, shooter, CRItems.PERDITIO_ARROW);
            perditioArrow.setArrowType(4);
            perditioArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return perditioArrow;
        } else if (this == CRItems.TERRA_ARROW) {
            EntityPrimalArrow terraArrow = new EntityPrimalArrow(world, shooter, CRItems.TERRA_ARROW);
            terraArrow.setArrowType(5);
            terraArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return terraArrow;
        }

        return new EntitySpectralArrow(world, shooter);
    }

    @Override
    public boolean isInfinite(@Nonnull ItemStack stack, @Nonnull ItemStack item, @Nonnull EntityPlayer player) {
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, item);

        if (this == CRItems.AER_ARROW ||
                this == CRItems.AQUA_ARROW ||
                this == CRItems.IGNIS_ARROW ||
                this == CRItems.ORDO_ARROW ||
                this == CRItems.PERDITIO_ARROW ||
                this == CRItems.TERRA_ARROW) {
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
