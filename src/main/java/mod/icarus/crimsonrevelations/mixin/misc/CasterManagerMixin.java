package mod.icarus.crimsonrevelations.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.icarus.crimsonrevelations.enchants.InfusionEnchantments;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.common.items.casters.CasterManager;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

@Mixin(value = CasterManager.class, remap = false)
public class CasterManagerMixin {
    @ModifyReturnValue(method = "getTotalVisDiscount", at = @At("RETURN"))
    private static float visAttunementInfusionAdjustmentMixin(float original, @Local(ordinal = 0, argsOnly = true) EntityPlayer player) {
        if(player == null) return original;

        int extraDiscount = 0;
        for(ItemStack armor : player.inventory.armorInventory) {
            if(!armor.isEmpty()) {
                int level = EnumInfusionEnchantment.getInfusionEnchantmentLevel(armor, InfusionEnchantments.VIS_ATTUNEMENT);
                if(level > 0) {
                    extraDiscount += level;
                }
            }
        }
        return original + (extraDiscount / 100f);
    }
}
