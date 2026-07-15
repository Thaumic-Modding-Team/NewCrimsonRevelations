package mod.icarus.crimsonrevelations.client.model.equipment.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import thaumcraft.client.renderers.models.gear.ModelKnightArmor;

// Just here so it can render on armor stands properly
public class ModelCultistKnightArmor extends ModelKnightArmor {
    public ModelCultistKnightArmor(float modelSize) {
        super(modelSize);
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
