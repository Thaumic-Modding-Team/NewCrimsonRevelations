package mod.icarus.crimsonrevelations.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.icarus.crimsonrevelations.enchants.InfusionEnchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
import thaumcraft.common.lib.events.PlayerEvents;

@Mixin(value = PlayerEvents.class, remap = false)
public class PlayerEventsMixin {
    @ModifyReturnValue(method = "getFinalDiscount", at = @At("RETURN"))
    private static int modifyFinalDiscountMixin(int discount, @Local(argsOnly = true, ordinal = 0)ItemStack stack) {
        if(!stack.isEmpty()) {
            int level = EnumInfusionEnchantment.getInfusionEnchantmentLevel(stack, InfusionEnchantments.VIS_ATTUNEMENT);
            if(level > 0) {
                discount += level;
            }
        }
        return discount;
    }
}
