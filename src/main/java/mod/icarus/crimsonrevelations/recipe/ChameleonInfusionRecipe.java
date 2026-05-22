package mod.icarus.crimsonrevelations.recipe;

import mod.icarus.crimsonrevelations.config.CRConfigLists;
import mod.icarus.crimsonrevelations.enchants.CREnchantments;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;

import java.util.List;

public class ChameleonInfusionRecipe extends InfusionEnchantmentRecipe {
    public ChameleonInfusionRecipe(AspectList as, Object... components) {
        super(CREnchantments.CHAMELEON, as, components);
    }

    public ChameleonInfusionRecipe(InfusionEnchantmentRecipe recipe, ItemStack in) {
        super(recipe, in);
    }

    @Override
    public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        boolean item = CRConfigLists.chameleonItems.contains(central.getItem());

        //Blacklisted items will not have this infusion enchantment applied, in case there are any tools that have incompatibilities
        return !(item) && super.matches(input, central, world, player);
    }
}
