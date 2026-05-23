package mod.icarus.crimsonrevelations.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class CRItemBoneBow extends CRItemBow {
    public CRItemBoneBow() {
        // Durability, Damage Multiplier, Velocity Multiplier, Draw Time Multiplier, Inaccuracy, Rarity, Repair Material
        super(512, 1.15F, 1.5F, 0.8F, 0.8F, EnumRarity.RARE, Ingredient.fromStacks(new ItemStack(Items.BONE)));
    }

    @Override
    public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count) {
        // Automatically fire the bow at full charge
        if (Math.min(1.0F, (getMaxItemUseDuration(stack) - count) / 20.0F) >= 1.0F) {
            player.stopActiveHand();
        }
    }

    @Override
    public boolean canContinueUsing(@Nonnull ItemStack oldStack, @Nonnull ItemStack newStack) {
        return true;
    }

    @Override
    public boolean shouldCauseReequipAnimation(@Nonnull ItemStack oldStack, @Nonnull ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        return 3;
    }

    @Override
    public EnumRarity getForgeRarity(@Nonnull ItemStack stack) {
        return rarity;
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair) {
        return repairMaterial.test(repair) || super.getIsRepairable(toRepair, repair);
    }
}
