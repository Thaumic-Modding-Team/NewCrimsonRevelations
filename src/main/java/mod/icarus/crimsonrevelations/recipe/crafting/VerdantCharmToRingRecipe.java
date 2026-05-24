package mod.icarus.crimsonrevelations.recipe.crafting;

import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

public class VerdantCharmToRingRecipe extends ShapelessArcaneRecipe {
    private static final Item charm = ItemsTC.charmVerdant;

    public VerdantCharmToRingRecipe() {
        super(new ResourceLocation(""), "CR_VERDANT_BANDS", 50, new AspectList(), ItemStack.EMPTY, new Object[]{charm});
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
        ItemStack item = ItemStack.EMPTY;

        for (int i = 0; i < var1.getSizeInventory(); i++) {
            ItemStack stack = var1.getStackInSlot(i);

            if (!stack.isEmpty() && stack.getItem() == charm) {
                item = stack;
            }
        }

        if (item.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = new ItemStack(ModItemsNCR.VERDANT_RING);

        if (item.getTagCompound() != null) {
            result.setTagCompound(item.getTagCompound());
        }

        return result;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}