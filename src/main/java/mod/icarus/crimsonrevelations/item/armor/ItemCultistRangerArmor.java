package mod.icarus.crimsonrevelations.item.armor;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.model.equipment.armor.ModelCultistRangerArmor;
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
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.api.items.IWarpingGear;

public class ItemCultistRangerArmor extends ItemArmorDyeable implements IVisDiscountGear, IWarpingGear {
    protected static final String TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_ranger_armor.png").toString();
    protected static final String TEXTURE_PATH_DYED = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_ranger_armor_dyed.png").toString();
    protected static final String TEXTURE_PATH_DYED_OVERLAY = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_ranger_armor_dyed_overlay.png").toString();
    ModelBiped model1 = null;
    ModelBiped model2 = null;

    public ItemCultistRangerArmor(String unlocName, EntityEquipmentSlot slot) {
        super(unlocName, ModMaterialsNCR.ARMOR_CULTIST_RANGER, 4, slot);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@NotNull EntityLivingBase entity, @NotNull ItemStack stack, @NotNull EntityEquipmentSlot slot, @NotNull ModelBiped bipedModel) {
        if (this.model1 == null) {
            this.model1 = new ModelCultistRangerArmor(0.5F);
        }

        if (this.model2 == null) {
            this.model2 = new ModelCultistRangerArmor(1.0F);
        }

        EntityEquipmentSlot type = ((ItemArmor) stack.getItem()).armorType;
        ModelBiped model = (type == EntityEquipmentSlot.LEGS) ? this.model1 : this.model2;
        return ModRenderRegistryNCR.getCustomArmorModel(entity, stack, slot, model);
    }

    @Override
    public String getArmorTexture(@NotNull ItemStack stack, @NotNull Entity entity, @NotNull EntityEquipmentSlot slot, @NotNull String type) {
        // If dye is never used on it, it'll use a dyeless texture instead with the original crimson cult colors
        if (this.getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata())) {
            return type == null ? TEXTURE_PATH_DYED : TEXTURE_PATH_DYED_OVERLAY;
        }

        return TEXTURE_PATH;
    }

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
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
