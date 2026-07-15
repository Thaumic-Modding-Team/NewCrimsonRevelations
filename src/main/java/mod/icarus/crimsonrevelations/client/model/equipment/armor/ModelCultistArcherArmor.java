package mod.icarus.crimsonrevelations.client.model.equipment.armor;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import thaumcraft.client.renderers.models.gear.ModelCustomArmor;

public class ModelCultistArcherArmor extends ModelCustomArmor {
    public ModelRenderer GauntletstrapR1;
    public ModelRenderer GauntletstrapR2;
    public ModelRenderer GauntletR;
    public ModelRenderer GauntletL;
    public ModelRenderer GauntletstrapL1;
    public ModelRenderer GauntletstrapL2;
    public ModelRenderer ShoulderL1;
    public ModelRenderer ShoulderL2;
    public ModelRenderer ShoulderL3;
    public ModelRenderer ShoulderL4;
    public ModelRenderer ShoulderL5;
    public ModelRenderer ShoulderR1;
    public ModelRenderer ShoulderR4;
    public ModelRenderer ShoulderR2;
    public ModelRenderer ShoulderR3;
    public ModelRenderer ShoulderR5;
    public ModelRenderer Helmet;
    public ModelRenderer Helmet1;
    public ModelRenderer Helmet2;
    public ModelRenderer Chestthing;
    public ModelRenderer Mbelt;
    public ModelRenderer clothchestL;
    public ModelRenderer clothchestR;
    public ModelRenderer BeltR;
    public ModelRenderer Backplate;
    public ModelRenderer MbeltL;
    public ModelRenderer MbeltR;
    public ModelRenderer BeltL;
    public ModelRenderer Chestplate;
    public ModelRenderer Quiver;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderL;

    public ModelRenderer LegpanelL1;
    public ModelRenderer LegpanelL4;
    public ModelRenderer LegpanelL5;
    public ModelRenderer LegpanelL6;
    public ModelRenderer BackpanelL1;
    public ModelRenderer BackpanelL2;
    public ModelRenderer BackpanelL3;
    public ModelRenderer BackpanelL4;

    public ModelRenderer LegpanelR6;
    public ModelRenderer LegpanelR5;
    public ModelRenderer LegpanelR4;
    public ModelRenderer BackpanelR1;
    public ModelRenderer BackpanelR2;
    public ModelRenderer BackpanelR3;
    public ModelRenderer BackpanelR4;

    public final ModelRenderer FrontclothR1;
    private final ModelRenderer FrontclothR2;
    private final ModelRenderer FrontclothL1;
    private final ModelRenderer FrontclothL2;
    private final ModelRenderer ClothBackR1;
    private final ModelRenderer ClothBackR2;
    private final ModelRenderer ClothBackR3;
    private final ModelRenderer ClothBackL1;
    private final ModelRenderer ClothBackL2;
    private final ModelRenderer ClothBackL3;

    public ModelRenderer MbeltB;

    public ModelCultistArcherArmor(float modelSize) {
        super(modelSize, 0, 128, 64);
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.BackpanelR3 = new ModelRenderer(this, 84, 3);
        this.BackpanelR3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelR3.addBox(0.0F, 2.5F, -2.5F, 2, 2, 5, 0.0F);
        this.setRotation(BackpanelR3, 0.0F, -0.0F, 0.13962634015954636F);

        this.ShoulderL3 = new ModelRenderer(this, 0, 50);
        this.ShoulderL3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderL3.addBox(2.299999952316284F, 3.0F, -3.0F, 1, 2, 6, 0.0F);
        this.setRotation(ShoulderL3, 0.0F, -0.0F, -0.6457718014717102F);

        this.Quiver = new ModelRenderer(this, 85, 18);
        this.Quiver.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Quiver.addBox(1.0F, 0.0F, 3.0F, 5, 8, 4, 0.0F);
        this.setRotation(Quiver, 0.0F, -0.0F, 0.767944872379303F);

        this.Chestplate = new ModelRenderer(this, 16, 25);
        this.Chestplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Chestplate.addBox(-4.0F, 1.0F, -3.0F, 8, 6, 1, 0.0F);

        this.ShoulderL2 = new ModelRenderer(this, 0, 58);
        this.ShoulderL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderL2.addBox(3.299999952316284F, 3.0F, -2.5F, 1, 1, 5, 0.0F);
        this.setRotation(ShoulderL2, 0.0F, -0.0F, -0.6457718014717102F);

        this.BackpanelL1 = new ModelRenderer(this, 104, 9);
        this.BackpanelL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelL1.addBox(2.0F, 2.5F, -2.5F, 1, 6, 5, 0.0F);
        this.setRotation(BackpanelL1, 0.0F, -0.0F, -0.13962633907794952F);

        this.Helmet1 = new ModelRenderer(this, 2, 1);
        this.Helmet1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Helmet1.addBox(-5.5F, -6.0F, -5.5F, 11, 1, 11, 0.0F);

        this.Mbelt = new ModelRenderer(this, 16, 55);
        this.Mbelt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mbelt.addBox(-4.0F, 7.0F, -3.0F, 8, 5, 1, 0.0F);

        this.MbeltB = new ModelRenderer(this, 16, 55);
        this.MbeltB.addBox(-4.0F, 7.0F, -4.0F, 8, 5, 1);
        this.MbeltB.setTextureSize(128, 64);
        setRotation(this.MbeltB, 0.0F, 3.141593F, 0.0F);

        this.ShoulderR = new ModelRenderer(this, 16, 45);
        this.ShoulderR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderR.addBox(-3.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);

        this.GauntletstrapR1 = new ModelRenderer(this, 84, 58);
        this.GauntletstrapR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GauntletstrapR1.addBox(-1.5F, 3.5F, -2.5F, 3, 1, 5, 0.0F);

        this.GauntletL = new ModelRenderer(this, 114, 53);
        this.GauntletL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GauntletL.addBox(1.5F, 3.5F, -2.5F, 2, 6, 5, 0.0F);

        this.ShoulderL = new ModelRenderer(this, 16, 45);
        this.ShoulderL.mirror = true;
        this.ShoulderL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderL.addBox(-1.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);

        this.LegpanelL5 = new ModelRenderer(this, 76, 42);
        this.LegpanelL5.mirror = true;
        this.LegpanelL5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LegpanelL5.addBox(1.0F, 2.5F, -2.5F, 2, 3, 1, 0.0F);
        this.setRotation(LegpanelL5, -0.43633231520652765F, -0.0F, 0.0F);

        this.BackpanelL2 = new ModelRenderer(this, 84, 10);
        this.BackpanelL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelL2.addBox(-2.0F, -0.5F, -2.5F, 5, 3, 5, 0.0F);
        this.setRotation(BackpanelL2, 0.0F, -0.0F, -0.13962634015954636F);

        this.GauntletstrapL2 = new ModelRenderer(this, 84, 58);
        this.GauntletstrapL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GauntletstrapL2.addBox(-1.5F, 6.5F, -2.5F, 3, 1, 5, 0.0F);

        this.BackpanelR1 = new ModelRenderer(this, 84, 10);
        this.BackpanelR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelR1.addBox(-3.0F, -0.5F, -2.5F, 5, 3, 5, 0.0F);
        this.setRotation(BackpanelR1, 0.0F, -0.0F, 0.13962633907794952F);

        this.ShoulderR5 = new ModelRenderer(this, 0, 38);
        this.ShoulderR5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderR5.addBox(-3.299999952316284F, -2.0F, 3.0F, 1, 6, 1, 0.0F);
        this.setRotation(ShoulderR5, 0.0F, -0.0F, 0.6457718014717102F);

        this.BackpanelL3 = new ModelRenderer(this, 84, 3);
        this.BackpanelL3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelL3.addBox(-2.0F, 2.5F, -2.5F, 2, 2, 5, 0.0F);
        this.setRotation(BackpanelL3, 0.0F, -0.0F, -0.13962634015954636F);

        this.Helmet = new ModelRenderer(this, 48, 0);
        this.Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Helmet.addBox(-4.5F, -5.0F, -4.5F, 9, 5, 9, 0.0F);

        this.GauntletstrapL1 = new ModelRenderer(this, 84, 58);
        this.GauntletstrapL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GauntletstrapL1.addBox(-1.5F, 3.5F, -2.5F, 3, 1, 5, 0.0F);

        this.clothchestL = new ModelRenderer(this, 108, 38);
        this.clothchestL.mirror = true;
        this.clothchestL.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.clothchestL.addBox(0.10000000149011612F, -11.5F, -3.5F, 2, 8, 1, 0.0F);

        this.ShoulderR1 = new ModelRenderer(this, 0, 39);
        this.ShoulderR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderR1.addBox(-4.300000190734863F, -2.0F, -3.0F, 1, 5, 6, 0.0F);
        this.setRotation(ShoulderR1, 0.0F, -0.0F, 0.6457718014717102F);

        this.MbeltR = new ModelRenderer(this, 16, 36);
        this.MbeltR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.MbeltR.addBox(-5.0F, 8.0F, -3.0F, 1, 3, 6, 0.0F);

        this.LegpanelR6 = new ModelRenderer(this, 82, 38);
        this.LegpanelR6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LegpanelR6.addBox(-3.0F, 4.5F, -1.5F, 2, 3, 1, 0.0F);
        this.setRotation(LegpanelR6, -0.43633231520652765F, -0.0F, 0.0F);

        this.Chestthing = new ModelRenderer(this, 56, 50);
        this.Chestthing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Chestthing.addBox(-2.5F, 1.0F, -4.0F, 5, 7, 1, 0.0F);

        this.BackpanelR4 = new ModelRenderer(this, 102, 1);
        this.BackpanelR4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelR4.addBox(-2.0F, 2.5F, -2.5F, 2, 3, 5, 0.0F);
        this.setRotation(BackpanelR4, 0.0F, -0.0F, 0.13962634015954636F);

        this.ShoulderR4 = new ModelRenderer(this, 0, 38);
        this.ShoulderR4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderR4.addBox(-3.299999952316284F, -2.0F, -4.0F, 1, 6, 1, 0.0F);
        this.setRotation(ShoulderR4, 0.0F, -0.0F, 0.6457718014717102F);

        this.clothchestR = new ModelRenderer(this, 108, 38);
        this.clothchestR.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.clothchestR.addBox(-6.099999904632568F, -11.5F, -3.5F, 2, 8, 1, 0.0F);

        this.ShoulderR3 = new ModelRenderer(this, 0, 50);
        this.ShoulderR3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderR3.addBox(-3.299999952316284F, 3.0F, -3.0F, 1, 2, 6, 0.0F);
        this.setRotation(ShoulderR3, 0.0F, -0.0F, 0.6457718014717102F);

        this.LegpanelR4 = new ModelRenderer(this, 76, 38);
        this.LegpanelR4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LegpanelR4.addBox(-3.0F, 0.5F, -3.5F, 2, 3, 1, 0.0F);
        this.setRotation(LegpanelR4, -0.43633231520652765F, -0.0F, 0.0F);

        this.ShoulderL1 = new ModelRenderer(this, 0, 39);
        this.ShoulderL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderL1.addBox(3.299999952316284F, -2.0F, -3.0F, 1, 5, 6, 0.0F);
        this.setRotation(ShoulderL1, 0.0F, -0.0F, -0.6457718014717102F);

        this.BeltR = new ModelRenderer(this, 16, 36);
        this.BeltR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BeltR.addBox(-5.0F, 4.0F, -3.0F, 1, 3, 6, 0.0F);

        this.GauntletstrapR2 = new ModelRenderer(this, 84, 58);
        this.GauntletstrapR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GauntletstrapR2.addBox(-1.5F, 6.5F, -2.5F, 3, 1, 5, 0.0F);

        this.LegpanelL1 = new ModelRenderer(this, 123, 9);
        this.LegpanelL1.mirror = true;
        this.LegpanelL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LegpanelL1.addBox(1.0F, 8.800000190734863F, 1.2999999523162842F, 1, 2, 1, 0.0F);
        this.setRotation(LegpanelL1, 0.22689279913902285F, -0.0F, 0.0F);

        this.Backplate = new ModelRenderer(this, 38, 46);
        this.Backplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Backplate.addBox(-4.0F, 1.0F, 2.0F, 8, 11, 1, 0.0F);

        this.BackpanelL4 = new ModelRenderer(this, 102, 1);
        this.BackpanelL4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelL4.addBox(0.0F, 2.5F, -2.5F, 2, 3, 5, 0.0F);
        this.setRotation(BackpanelL4, 0.0F, -0.0F, -0.13962634015954636F);

        this.LegpanelR5 = new ModelRenderer(this, 76, 42);
        this.LegpanelR5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LegpanelR5.addBox(-3.0F, 2.5F, -2.5F, 2, 3, 1, 0.0F);
        this.setRotation(LegpanelR5, -0.43633231520652765F, -0.0F, 0.0F);

        this.BackpanelR2 = new ModelRenderer(this, 104, 9);
        this.BackpanelR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpanelR2.addBox(-3.0F, 2.5F, -2.5F, 1, 6, 5, 0.0F);
        this.setRotation(BackpanelR2, 0.0F, -0.0F, 0.13962634015954636F);

        this.ShoulderL4 = new ModelRenderer(this, 0, 38);
        this.ShoulderL4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderL4.addBox(2.299999952316284F, -2.0F, -4.0F, 1, 6, 1, 0.0F);
        this.setRotation(ShoulderL4, 0.0F, -0.0F, -0.6457718014717102F);

        this.LegpanelL6 = new ModelRenderer(this, 82, 38);
        this.LegpanelL6.mirror = true;
        this.LegpanelL6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LegpanelL6.addBox(1.0F, 4.5F, -1.5F, 2, 3, 1, 0.0F);
        this.setRotation(LegpanelL6, -0.43633231520652765F, -0.0F, 0.0F);

        this.ShoulderR2 = new ModelRenderer(this, 0, 58);
        this.ShoulderR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderR2.addBox(-4.300000190734863F, 3.0F, -2.5F, 1, 1, 5, 0.0F);
        this.setRotation(ShoulderR2, 0.0F, -0.0F, 0.6457718014717102F);

        this.ShoulderL5 = new ModelRenderer(this, 0, 38);
        this.ShoulderL5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderL5.addBox(2.299999952316284F, -2.0F, 3.0F, 1, 6, 1, 0.0F);
        this.setRotation(ShoulderL5, 0.0F, -0.0F, -0.6457718014717102F);

        this.BeltL = new ModelRenderer(this, 16, 36);
        this.BeltL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BeltL.addBox(4.0F, 4.0F, -3.0F, 1, 3, 6, 0.0F);

        this.Helmet2 = new ModelRenderer(this, 0, 13);
        this.Helmet2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Helmet2.addBox(-4.5F, -9.0F, -4.5F, 9, 3, 9, 0.0F);

        this.MbeltL = new ModelRenderer(this, 16, 36);
        this.MbeltL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.MbeltL.addBox(4.0F, 8.0F, -3.0F, 1, 3, 6, 0.0F);

        this.LegpanelL4 = new ModelRenderer(this, 76, 38);
        this.LegpanelL4.mirror = true;
        this.LegpanelL4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LegpanelL4.addBox(1.0F, 0.5F, -3.5F, 2, 3, 1, 0.0F);
        this.setRotation(LegpanelL4, -0.43633231520652765F, -0.0F, 0.0F);

        this.GauntletR = new ModelRenderer(this, 100, 53);
        this.GauntletR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GauntletR.addBox(-3.5F, 3.5F, -2.5F, 2, 6, 5, 0.0F);

        this.FrontclothR1 = new ModelRenderer(this, 108, 38);
        this.FrontclothR1.addBox(0.0F, 0.0F, 0.0F, 3, 8, 1);
        this.FrontclothR1.setRotationPoint(-3.0F, 11.0F, -2.9F);
        this.FrontclothR1.setTextureSize(128, 64);
        setRotation(this.FrontclothR1, -0.1047198F, 0.0F, 0.0F);

        this.FrontclothR2 = new ModelRenderer(this, 108, 47);
        this.FrontclothR2.addBox(0.0F, 7.5F, 1.7F, 3, 3, 1);
        this.FrontclothR2.setRotationPoint(-3.0F, 11.0F, -2.9F);
        this.FrontclothR2.setTextureSize(128, 64);
        setRotation(this.FrontclothR2, -0.3316126F, 0.0F, 0.0F);

        this.FrontclothL1 = new ModelRenderer(this, 108, 38);
        this.FrontclothL1.mirror = true;
        this.FrontclothL1.addBox(0.0F, 0.0F, 0.0F, 3, 8, 1);
        this.FrontclothL1.setRotationPoint(0.0F, 11.0F, -2.9F);
        this.FrontclothL1.setTextureSize(128, 64);
        setRotation(this.FrontclothL1, -0.1047198F, 0.0F, 0.0F);

        this.FrontclothL2 = new ModelRenderer(this, 108, 47);
        this.FrontclothL2.mirror = true;
        this.FrontclothL2.addBox(0.0F, 7.5F, 1.7F, 3, 3, 1);
        this.FrontclothL2.setRotationPoint(0.0F, 11.0F, -2.9F);
        this.FrontclothL2.setTextureSize(128, 64);
        setRotation(this.FrontclothL2, -0.3316126F, 0.0F, 0.0F);

        this.ClothBackR1 = new ModelRenderer(this, 118, 16);
        this.ClothBackR1.mirror = true;
        this.ClothBackR1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
        this.ClothBackR1.setRotationPoint(-4.0F, 11.5F, 2.9F);
        this.ClothBackR1.setTextureSize(128, 64);
        setRotation(this.ClothBackR1, 0.1047198F, 0.0F, 0.0F);

        this.ClothBackR2 = new ModelRenderer(this, 123, 9);
        this.ClothBackR2.addBox(0.0F, 7.8F, -0.9F, 1, 2, 1);
        this.ClothBackR2.setRotationPoint(-4.0F, 11.5F, 2.9F);
        this.ClothBackR2.setTextureSize(128, 64);
        setRotation(this.ClothBackR2, 0.2268928F, 0.0F, 0.0F);

        this.ClothBackR3 = new ModelRenderer(this, 120, 12);
        this.ClothBackR3.mirror = true;
        this.ClothBackR3.addBox(1.0F, 7.8F, -0.9F, 3, 3, 1);
        this.ClothBackR3.setRotationPoint(-4.0F, 11.5F, 2.9F);
        this.ClothBackR3.setTextureSize(128, 64);
        setRotation(this.ClothBackR3, 0.2268928F, 0.0F, 0.0F);

        this.ClothBackL1 = new ModelRenderer(this, 118, 16);
        this.ClothBackL1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
        this.ClothBackL1.setRotationPoint(0.0F, 11.5F, 2.9F);
        this.ClothBackL1.setTextureSize(128, 64);
        setRotation(this.ClothBackL1, 0.1047198F, 0.0F, 0.0F);

        this.ClothBackL2 = new ModelRenderer(this, 123, 9);
        this.ClothBackL2.mirror = true;
        this.ClothBackL2.addBox(3.0F, 7.8F, -0.9F, 1, 2, 1);
        this.ClothBackL2.setRotationPoint(0.0F, 11.5F, 2.9F);
        this.ClothBackL2.setTextureSize(128, 64);
        setRotation(this.ClothBackL2, 0.2268928F, 0.0F, 0.0F);

        this.ClothBackL3 = new ModelRenderer(this, 120, 12);
        this.ClothBackL3.addBox(0.0F, 7.8F, -0.9F, 3, 3, 1);
        this.ClothBackL3.setRotationPoint(0.0F, 11.5F, 2.9F);
        this.ClothBackL3.setTextureSize(128, 64);
        setRotation(this.ClothBackL3, 0.2268928F, 0.0F, 0.0F);

        this.bipedHeadwear.cubeList.clear();
        this.bipedHead.cubeList.clear();
        this.bipedHead.addChild(this.Helmet);
        this.bipedHead.addChild(this.Helmet1);
        this.bipedHead.addChild(this.Helmet2);

        this.bipedBody.cubeList.clear();
        this.bipedRightLeg.cubeList.clear();
        this.bipedLeftLeg.cubeList.clear();

        this.bipedBody.addChild(this.Mbelt);
        this.bipedBody.addChild(this.MbeltL);
        this.bipedBody.addChild(this.MbeltR);
        this.bipedBody.addChild(this.MbeltB);

        if (modelSize >= 1.0F) {
            this.bipedBody.addChild(this.Quiver);
            this.bipedBody.addChild(this.Chestthing);
            this.bipedBody.addChild(this.Backplate);
            this.bipedBody.addChild(this.Chestplate);
            this.bipedBody.addChild(this.clothchestL);
            this.bipedBody.addChild(this.clothchestR);
            this.bipedBody.addChild(this.BeltL);
            this.bipedBody.addChild(this.BeltR);
        } else {
            this.bipedBody.addChild(this.FrontclothR1);
            this.bipedBody.addChild(this.FrontclothR2);
            this.bipedBody.addChild(this.FrontclothL1);
            this.bipedBody.addChild(this.FrontclothL2);

            this.bipedBody.addChild(this.ClothBackR1);
            this.bipedBody.addChild(this.ClothBackR2);
            this.bipedBody.addChild(this.ClothBackR3);

            this.bipedBody.addChild(this.ClothBackL1);
            this.bipedBody.addChild(this.ClothBackL2);
            this.bipedBody.addChild(this.ClothBackL3);
        }

        this.bipedRightArm.cubeList.clear();
        this.bipedRightArm.addChild(this.ShoulderR);
        this.bipedRightArm.addChild(this.ShoulderR1);
        this.bipedRightArm.addChild(this.ShoulderR2);
        this.bipedRightArm.addChild(this.ShoulderR3);
        this.bipedRightArm.addChild(this.ShoulderR4);
        this.bipedRightArm.addChild(this.ShoulderR5);
        this.bipedRightArm.addChild(this.GauntletR);
        this.bipedRightArm.addChild(this.GauntletstrapR1);
        this.bipedRightArm.addChild(this.GauntletstrapR2);

        this.bipedLeftArm.cubeList.clear();
        this.bipedLeftArm.addChild(this.ShoulderL);
        this.bipedLeftArm.addChild(this.ShoulderL1);
        this.bipedLeftArm.addChild(this.ShoulderL2);
        this.bipedLeftArm.addChild(this.ShoulderL3);
        this.bipedLeftArm.addChild(this.ShoulderL4);
        this.bipedLeftArm.addChild(this.ShoulderL5);
        this.bipedLeftArm.addChild(this.GauntletL);
        this.bipedLeftArm.addChild(this.GauntletstrapL1);
        this.bipedLeftArm.addChild(this.GauntletstrapL2);

        this.bipedRightLeg.addChild(this.BackpanelR1);
        this.bipedRightLeg.addChild(this.BackpanelR2);
        this.bipedRightLeg.addChild(this.BackpanelR3);
        this.bipedRightLeg.addChild(this.BackpanelR4);

        this.bipedLeftLeg.addChild(this.BackpanelL1);
        this.bipedLeftLeg.addChild(this.BackpanelL2);
        this.bipedLeftLeg.addChild(this.BackpanelL3);
        this.bipedLeftLeg.addChild(this.BackpanelL4);
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

    public void render(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        GlStateManager.pushMatrix();

        float a = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        float b = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
        float c = Math.min(a, b);

        this.FrontclothR1.rotateAngleX = (this.FrontclothL1.rotateAngleX = c - 0.1047198F);
        this.FrontclothR2.rotateAngleX = (this.FrontclothL2.rotateAngleX = c - 0.3316126F);

        this.ClothBackR1.rotateAngleX = (this.ClothBackL1.rotateAngleX = -c + 0.1047198F);
        this.ClothBackR2.rotateAngleX = (this.ClothBackL2.rotateAngleX = this.ClothBackR3.rotateAngleX = this.ClothBackL3.rotateAngleX = -c + 0.2268928F);

        if (isChild) {
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            bipedHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            bipedBody.render(scale);
            bipedRightArm.render(scale);
            bipedLeftArm.render(scale);
            bipedRightLeg.render(scale);
            bipedLeftLeg.render(scale);
            bipedHeadwear.render(scale);
            GlStateManager.popMatrix();
        } else {
            GlStateManager.scale(1.01F, 1.01F, 1.01F);
            bipedHead.render(scale);
            GlStateManager.popMatrix();
            bipedBody.render(scale);
            bipedRightArm.render(scale);
            bipedLeftArm.render(scale);
            bipedRightLeg.render(scale);
            bipedLeftLeg.render(scale);
            bipedHeadwear.render(scale);
        }
    }
}
