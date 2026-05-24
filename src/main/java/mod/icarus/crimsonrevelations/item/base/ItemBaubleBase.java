package mod.icarus.crimsonrevelations.item.base;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;

public class ItemBaubleBase extends ItemBase implements IBauble {
    BaubleType type;

    public ItemBaubleBase(BaubleType type) {
        super();
        this.maxStackSize = 1;
        this.type = type;
    }
    
    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        if (this == ModItemsNCR.NUTRITION_RING) {
            player.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.75F, 0.9F);
        }
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        if (this == ModItemsNCR.NUTRITION_RING) {
            player.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.75F, 0.75F);
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return type;
    }
}
