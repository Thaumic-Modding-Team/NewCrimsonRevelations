package mod.icarus.crimsonrevelations.recipe;

import mod.icarus.crimsonrevelations.enchants.CREnchantments;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;

import java.util.List;

public class VisAttunementInfusionRecipe extends InfusionEnchantmentRecipe {
    public VisAttunementInfusionRecipe(AspectList as, Object... components) {
        super(CREnchantments.VIS_ATTUNEMENT, as, components);
    }

    public VisAttunementInfusionRecipe(InfusionEnchantmentRecipe recipe, ItemStack in) {
        super(recipe, in);
    }

    @Override
    public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        //This infusion enchantment is meant for non-discount gear just to make the tooltip handling easier.
        return !(central.getItem() instanceof IVisDiscountGear) && super.matches(input, central, world, player);
    }
}
