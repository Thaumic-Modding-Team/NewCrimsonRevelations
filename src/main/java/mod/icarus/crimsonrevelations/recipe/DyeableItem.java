package mod.icarus.crimsonrevelations.recipe;

import mod.icarus.crimsonrevelations.item.IDyeableGear;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.DyeUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Optional;

// Courtesy of TheCodex6824
public class DyeableItem extends RecipesArmorDyes {
    @Override
    public boolean matches(InventoryCrafting inv, @Nonnull World world) {
        boolean hasDyedItem = false;
        boolean hasDye = false;

        for (int i = 0; i < Math.min(inv.getSizeInventory(), 9); ++i) {
            ItemStack stack = inv.getStackInSlot(i);

            if (stack != null && !stack.isEmpty()) {
                if (stack.getItem() instanceof IDyeableGear) {
                    if (hasDyedItem) {
                        return false;
                    } else {
                        hasDyedItem = true;
                    }
                } else if (DyeUtils.isDye(stack)) {
                    hasDye = true;
                } else {
                    return false;
                }
            }
        }

        return hasDyedItem && hasDye;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack toDye = ItemStack.EMPTY;
        ArrayList<ItemStack> dyes = new ArrayList<>();

        for (int i = 0; i < Math.min(inv.getSizeInventory(), 9); ++i) {
            ItemStack stack = inv.getStackInSlot(i);

            if (stack != null && !stack.isEmpty()) {
                if (stack.getItem() instanceof IDyeableGear) {
                    if (!toDye.isEmpty()) {
                        return ItemStack.EMPTY;
                    } else {
                        toDye = stack;
                    }
                } else if (DyeUtils.isDye(stack)) {
                    dyes.add(stack);
                } else {
                    return ItemStack.EMPTY;
                }
            }
        }

        if (!toDye.isEmpty() && dyes.size() > 0) {
            ItemStack toOutput = toDye.copy();
            toOutput.setCount(Math.min(toOutput.getCount(), 1));

            int oldColor = ((IDyeableGear) toOutput.getItem()).getDyedColor(toOutput);
            float[] totalComponents = new float[]{(oldColor >> 16 & 0xFF) / 255.0F, (oldColor >> 8 & 0xFF) / 255.0F, (oldColor & 0xFF) / 255.0F};
            float totalMaximum = Math.max(totalComponents[0], Math.max(totalComponents[1], totalComponents[2]));

            for (ItemStack d : dyes) {
                Optional<EnumDyeColor> colorValue = DyeUtils.colorFromStack(d);
                if (colorValue.isPresent()) {
                    float[] dyeColor = colorValue.get().getColorComponentValues();
                    totalComponents[0] += dyeColor[0];
                    totalComponents[1] += dyeColor[1];
                    totalComponents[2] += dyeColor[2];
                    totalMaximum += Math.max(dyeColor[0], Math.max(dyeColor[1], dyeColor[2]));
                }
            }

            totalComponents[0] /= dyes.size() + 1;
            totalComponents[1] /= dyes.size() + 1;
            totalComponents[2] /= dyes.size() + 1;
            totalMaximum /= dyes.size() + 1;

            float gainFactor = totalMaximum / Math.max(totalComponents[0], Math.max(totalComponents[1], totalComponents[2]));
            totalComponents[0] *= gainFactor;
            totalComponents[1] *= gainFactor;
            totalComponents[2] *= gainFactor;
            int newColor = ((int) (totalComponents[0] * 255) << 16) + ((int) (totalComponents[1] * 255) << 8) + (int) (totalComponents[2] * 255);
            ((IDyeableGear) toOutput.getItem()).setDyedColor(toOutput, newColor);
            return toOutput;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
