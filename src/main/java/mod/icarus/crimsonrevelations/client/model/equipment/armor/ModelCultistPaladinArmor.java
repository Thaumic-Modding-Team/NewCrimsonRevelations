package mod.icarus.crimsonrevelations.client.model.equipment.armor;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import thaumcraft.client.renderers.models.gear.ModelLeaderArmor;

public class ModelCultistPaladinArmor extends ModelLeaderArmor {
    public ModelRenderer WingL1;
    public ModelRenderer WingL2;
    public ModelRenderer WingL3;
    public ModelRenderer WingL4;
    public ModelRenderer WingR1;
    public ModelRenderer WingR2;
    public ModelRenderer WingR3;
    public ModelRenderer WingR4;

    public ModelCultistPaladinArmor(float modelSize) {
        super(modelSize);

        this.WingR1 = new ModelRenderer(this, 41, 26);
        this.WingR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingR1.addBox(-5.0F, -7.0F, -0.5F, 1, 4, 4, 0.0F);
        this.setRotation(WingR1, 0.10471975803375246F, -0.2617993950843811F, 0.0F);

        this.WingR2 = new ModelRenderer(this, 41, 34);
        this.WingR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingR2.addBox(-5.0F, -7.0F, 3.5F, 1, 3, 2, 0.0F);
        this.setRotation(WingR2, 0.10471975803375246F, -0.2617993950843811F, 0.0F);

        this.WingR3 = new ModelRenderer(this, 47, 34);
        this.WingR3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingR3.addBox(-5.0F, -8.0F, 5.5F, 1, 3, 1, 0.0F);
        this.setRotation(WingR3, 0.10471975803375246F, -0.2617993950843811F, 0.0F);

        this.WingR4 = new ModelRenderer(this, 41, 39);
        this.WingR4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingR4.addBox(-5.0F, -8.0F, 4.5F, 1, 1, 1, 0.0F);
        this.setRotation(WingR4, 0.10471975803375246F, -0.2617993950843811F, 0.0F);

        this.WingL1 = new ModelRenderer(this, 41, 26);
        this.WingL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingL1.addBox(4.0F, -7.0F, -0.5F, 1, 4, 4, 0.0F);
        this.setRotation(WingL1, 0.10471975803375246F, 0.2617993950843811F, 0.0F);

        this.WingL2 = new ModelRenderer(this, 41, 34);
        this.WingL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingL2.addBox(4.0F, -7.0F, 3.5F, 1, 3, 2, 0.0F);
        this.setRotation(WingL2, 0.10471975803375246F, 0.2617993950843811F, 0.0F);

        this.WingL3 = new ModelRenderer(this, 47, 34);
        this.WingL3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingL3.addBox(4.0F, -8.0F, 5.5F, 1, 3, 1, 0.0F);
        this.setRotation(WingL3, 0.10471975803375246F, 0.2617993950843811F, 0.0F);

        this.WingL4 = new ModelRenderer(this, 41, 39);
        this.WingL4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingL4.addBox(4.0F, -8.0F, 4.5F, 1, 1, 1, 0.0F);
        this.setRotation(WingL4, 0.10471975803375246F, 0.2617993950843811F, 0.0F);

        super.bipedHead.addChild(this.WingL1);
        super.bipedHead.addChild(this.WingL2);
        super.bipedHead.addChild(this.WingL3);
        super.bipedHead.addChild(this.WingL4);
        super.bipedHead.addChild(this.WingR1);
        super.bipedHead.addChild(this.WingR2);
        super.bipedHead.addChild(this.WingR3);
        super.bipedHead.addChild(this.WingR4);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            if (entity instanceof EntityArmorStand) {
                EntityArmorStand stand = (EntityArmorStand) entity;

                bipedHead.rotateAngleX = 0.017453292F * stand.getHeadRotation().getX();
                bipedHead.rotateAngleY = 0.017453292F * stand.getHeadRotation().getY();
                bipedHead.rotateAngleZ = 0.017453292F * stand.getHeadRotation().getZ();
                bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);

                bipedBody.rotateAngleX = 0.017453292F * stand.getBodyRotation().getX();
                bipedBody.rotateAngleY = 0.017453292F * stand.getBodyRotation().getY();
                bipedBody.rotateAngleZ = 0.017453292F * stand.getBodyRotation().getZ();
                bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

                bipedLeftArm.rotateAngleX = 0.017453292F * stand.getLeftArmRotation().getX();
                bipedLeftArm.rotateAngleY = 0.017453292F * stand.getLeftArmRotation().getY();
                bipedLeftArm.rotateAngleZ = 0.017453292F * stand.getLeftArmRotation().getZ();
                bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

                bipedRightArm.rotateAngleX = 0.017453292F * stand.getRightArmRotation().getX();
                bipedRightArm.rotateAngleY = 0.017453292F * stand.getRightArmRotation().getY();
                bipedRightArm.rotateAngleZ = 0.017453292F * stand.getRightArmRotation().getZ();
                bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

                bipedBody.rotateAngleX = 0.017453292F * stand.getBodyRotation().getX();
                bipedBody.rotateAngleY = 0.017453292F * stand.getBodyRotation().getY();
                bipedBody.rotateAngleZ = 0.017453292F * stand.getBodyRotation().getZ();
                bipedLeftArm.rotateAngleX = 0.017453292F * stand.getLeftArmRotation().getX();
                bipedLeftArm.rotateAngleY = 0.017453292F * stand.getLeftArmRotation().getY();
                bipedLeftArm.rotateAngleZ = 0.017453292F * stand.getLeftArmRotation().getZ();
                bipedRightArm.rotateAngleX = 0.017453292F * stand.getRightArmRotation().getX();
                bipedRightArm.rotateAngleY = 0.017453292F * stand.getRightArmRotation().getY();
                bipedRightArm.rotateAngleZ = 0.017453292F * stand.getRightArmRotation().getZ();
                bipedLeftLeg.rotateAngleX = 0.017453292F * stand.getLeftLegRotation().getX();
                bipedLeftLeg.rotateAngleY = 0.017453292F * stand.getLeftLegRotation().getY();
                bipedLeftLeg.rotateAngleZ = 0.017453292F * stand.getLeftLegRotation().getZ();
                bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);

                bipedRightLeg.rotateAngleX = 0.017453292F * stand.getRightLegRotation().getX();
                bipedRightLeg.rotateAngleY = 0.017453292F * stand.getRightLegRotation().getY();
                bipedRightLeg.rotateAngleZ = 0.017453292F * stand.getRightLegRotation().getZ();
                bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);

                copyModelAngles(bipedHead, bipedHeadwear);
            } else {
                super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
            }
        }
    }
}
