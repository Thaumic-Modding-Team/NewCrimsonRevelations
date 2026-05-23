package mod.icarus.crimsonrevelations.item.armor;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.model.gear.ModelCultistArcherArmor;
import mod.icarus.crimsonrevelations.init.CRMaterials;
import mod.icarus.crimsonrevelations.init.CRRenderRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class CRItemCultistArcherArmor extends CRItemArmorDyeable {
    protected static final String TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_archer_armor.png").toString();
    protected static final String TEXTURE_PATH_DYED = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_archer_armor_dyed.png").toString();
    protected static final String TEXTURE_PATH_DYED_OVERLAY = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_archer_armor_dyed_overlay.png").toString();
    ModelBiped model1 = null;
    ModelBiped model2 = null;

    public CRItemCultistArcherArmor(EntityEquipmentSlot slot) {
        super(CRMaterials.ARMOR_CULTIST_ARCHER, 4, slot);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, @Nonnull ItemStack stack, @Nonnull EntityEquipmentSlot slot, @Nonnull ModelBiped bipedModel) {
        if (this.model1 == null) {
            this.model1 = new ModelCultistArcherArmor(0.5f);
        }

        if (this.model2 == null) {
            this.model2 = new ModelCultistArcherArmor(1.0F);
        }

        EntityEquipmentSlot type = ((ItemArmor) stack.getItem()).armorType;
        ModelBiped model = (type == EntityEquipmentSlot.LEGS) ? this.model1 : this.model2;
        return CRRenderRegistry.getCustomArmorModel(entity, stack, slot, model);
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, @Nonnull Entity entity, @Nonnull EntityEquipmentSlot slot, @Nonnull String type) {
        // If dye is never used on it, it'll use a dyeless texture instead with the original crimson cult colors
        if (this.getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata())) {
            return type == null ? TEXTURE_PATH_DYED : TEXTURE_PATH_DYED_OVERLAY;
        }

        return TEXTURE_PATH;
    }

    @Override
    public EnumRarity getForgeRarity(@Nonnull ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }
}
