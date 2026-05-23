package mod.icarus.crimsonrevelations.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class CRItemBauble extends CRItem implements IBauble {
    BaubleType type;

    public CRItemBauble(BaubleType type) {
        super();
        this.maxStackSize = 1;
        this.type = type;
    }
    
    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        if (this == CRItems.NUTRITION_RING) {
            player.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.75F, 0.9F);
        }
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        if (this == CRItems.NUTRITION_RING) {
            player.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.75F, 0.75F);
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return type;
    }
}
