package mod.icarus.crimsonrevelations.client.model;

import mod.icarus.crimsonrevelations.entity.EntityCultistArcher;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelCultistArcher extends ModelBiped {
    public ModelCultistArcher() {
        this(0.0F);
    }

    public ModelCultistArcher(float modelSize) {
        super(modelSize, 0.0F, 64, 32);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;
        ItemStack stack = entity.getHeldItem(EnumHand.MAIN_HAND);

        if (stack.getItem() instanceof ItemBow && ((EntityCultistArcher) entity).isSwingingArms()) {
            if (entity.getPrimaryHand() == EnumHandSide.RIGHT) {
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = ArmPose.BOW_AND_ARROW;
            }
        }

        super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, @Nonnull Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        ItemStack stack = ((EntityLivingBase) entity).getHeldItemMainhand();
        EntityCultistArcher marksman = (EntityCultistArcher) entity;

        if (marksman.isSwingingArms() && (stack.isEmpty() || !(stack.getItem() instanceof ItemBow))) {
            float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
            this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
            this.bipedRightArm.rotateAngleX = (-(float) Math.PI / 2F);
            this.bipedLeftArm.rotateAngleX = (-(float) Math.PI / 2F);
            ModelRenderer render = this.bipedRightArm;
            render.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            render = this.bipedLeftArm;
            render.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            render = this.bipedRightArm;
            render.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            render = this.bipedLeftArm;
            render.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            render = this.bipedRightArm;
            render.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            render = this.bipedLeftArm;
            render.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        }
    }

    @Override
    public void postRenderArm(float scale, @Nonnull EnumHandSide hand) {
        float f = hand == EnumHandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer render = this.getArmForSide(hand);
        render.rotationPointX += f;
        render.postRender(scale);
        render.rotationPointX -= f;
    }
}
