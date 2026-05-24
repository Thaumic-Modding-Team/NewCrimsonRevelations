package mod.icarus.crimsonrevelations.item.armor;

import mod.icarus.crimsonrevelations.client.model.equipment.armor.ModelCultistKnightArmor;
import mod.icarus.crimsonrevelations.registry.ModMaterialsNCR;
import mod.icarus.crimsonrevelations.registry.ModRenderRegistryNCR;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.Thaumcraft;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.api.items.IWarpingGear;

import javax.annotation.Nonnull;

public class ItemAncientCultistArmor extends ItemArmor implements IVisDiscountGear, IWarpingGear {
    protected static final String TEXTURE_PATH = new ResourceLocation(Thaumcraft.MODID, "textures/entity/armor/zombie_plate_armor.png").toString();
    ModelBiped model1 = null;
    ModelBiped model2 = null;

    public ItemAncientCultistArmor(EntityEquipmentSlot slot) {
        super(ModMaterialsNCR.ARMOR_ANCIENT_CULTIST, 4, slot);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, @Nonnull ItemStack stack, @Nonnull EntityEquipmentSlot slot, @Nonnull ModelBiped bipedModel) {
        if (this.model1 == null) {
            this.model1 = new ModelCultistKnightArmor(0.5F);
        }

        if (this.model2 == null) {
            this.model2 = new ModelCultistKnightArmor(1.0F);
        }

        EntityEquipmentSlot type = ((ItemArmor) stack.getItem()).armorType;
        ModelBiped model = (type == EntityEquipmentSlot.LEGS) ? this.model1 : this.model2;
        return ModRenderRegistryNCR.getCustomArmorModel(entity, stack, slot, model);
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, @Nonnull Entity entity, @Nonnull EntityEquipmentSlot slot, @Nonnull String type) {
        return TEXTURE_PATH;
    }

    @Override
    public IRarity getForgeRarity(@Nonnull ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player) {
        return 4;
    }

    @Override
    public int getWarp(ItemStack stack, EntityPlayer player) {
        return 1;
    }
}
