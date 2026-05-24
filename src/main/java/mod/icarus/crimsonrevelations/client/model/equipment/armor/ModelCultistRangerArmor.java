package mod.icarus.crimsonrevelations.client.model.equipment.armor;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.math.MathHelper;
import thaumcraft.client.renderers.models.gear.ModelCustomArmor;

import javax.annotation.Nonnull;

public class ModelCultistRangerArmor extends ModelCustomArmor {
    public ModelRenderer Helmet;
    public ModelRenderer[] Mask;
    public ModelRenderer capsthingy;
    public ModelRenderer HelmetB;
    public ModelRenderer HelmetR;
    public ModelRenderer HelmetL;
    public ModelRenderer Chestthing;
    public ModelRenderer Mbelt;
    public ModelRenderer clothchestL;
    public ModelRenderer clothchestR;
    public ModelRenderer BeltR;
    public ModelRenderer BackpackRoll;
    public ModelRenderer MbeltL;
    public ModelRenderer MbeltR;
    public ModelRenderer BeltL;
    public ModelRenderer Chestplate;
    public ModelRenderer Backplate;
    public ModelRenderer Backpack;
    public ModelRenderer ShoulderplateR1;
    public ModelRenderer ShoulderplateR2;
    public ModelRenderer ShoulderplateR3;
    public ModelRenderer ShoulderplateTopR;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderplateL1;
    public ModelRenderer ShoulderplateL2;
    public ModelRenderer ShoulderplateL3;
    public ModelRenderer ShoulderplateLtop;
    public ModelRenderer ShoulderL;

    public ModelRenderer bottleR3;
    public ModelRenderer bottleR2;
    public ModelRenderer bottleR1;

    public ModelRenderer bottleL1;
    public ModelRenderer bottleL2;
    public ModelRenderer bottleL3;

    public ModelRenderer FrontclothR1;
    public ModelRenderer FrontclothR2;
    public ModelRenderer FrontclothL1;
    public ModelRenderer FrontclothL2;
    public ModelRenderer ClothBackR1;
    public ModelRenderer ClothBackR2;
    public ModelRenderer ClothBackR3;
    public ModelRenderer ClothBackL1;
    public ModelRenderer ClothBackL2;
    public ModelRenderer ClothBackL3;
    public ModelRenderer SideclothL2;
    public ModelRenderer SideclothR1;
    public ModelRenderer SideclothR3;
    public ModelRenderer SideclothR2;
    public ModelRenderer SidepanelR1;
    public ModelRenderer LegpanelR6;
    public ModelRenderer LegpanelR5;
    public ModelRenderer LegpanelR4;
    public ModelRenderer SideclothL3;
    public ModelRenderer SideclothL1;
    public ModelRenderer SidepanelL1;
    public ModelRenderer LegpanelL6;
    public ModelRenderer LegpanelL5;
    public ModelRenderer LegpanelL4;

    public ModelCultistRangerArmor(float modelSize) {
        super(modelSize, 0, 128, 64);
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.Mask = new ModelRenderer[2];

        this.Mask[0] = new ModelRenderer(this, 74, 3);
        this.Mask[0].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mask[0].addBox(-4.5F, -5.0F, -4.6F, 9, 5, 1);

        this.Mask[1] = new ModelRenderer(this, 96, 3);
        this.Mask[1].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mask[1].addBox(-4.5F, -5.0F, -4.6F, 9, 5, 1);

        this.Helmet = new ModelRenderer(this, 36, 6);
        this.Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Helmet.addBox(-4.5F, -9.0F, -4.5F, 9, 4, 9, 0.0F);

        this.ShoulderplateL1 = new ModelRenderer(this, 56, 33);
        this.ShoulderplateL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateL1.addBox(3.5F, -1.5F, -3.5F, 1, 4, 7, 0.0F);
        this.setRotation(ShoulderplateL1, 0.0F, -0.0F, -0.43633231520652765F);

        this.clothchestR = new ModelRenderer(this, 108, 38);
        this.clothchestR.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.clothchestR.addBox(-6.099999904632568F, -11.5F, -3.5F, 2, 8, 1, 0.0F);

        this.clothchestL = new ModelRenderer(this, 108, 38);
        this.clothchestL.mirror = true;
        this.clothchestL.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.clothchestL.addBox(0.10000000149011612F, -11.5F, -3.5F, 2, 8, 1, 0.0F);

        this.Mbelt = new ModelRenderer(this, 16, 55);
        this.Mbelt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mbelt.addBox(-4.0F, 7.0F, -3.0F, 8, 5, 1, 0.0F);

        this.HelmetL = new ModelRenderer(this, 16, 11);
        this.HelmetL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HelmetL.addBox(5.5F, -3.0F, -4.5F, 1, 5, 9, 0.0F);
        this.setRotation(HelmetL, 0.0F, -0.0F, -0.5235987901687622F);

        this.ShoulderplateR1 = new ModelRenderer(this, 56, 33);
        this.ShoulderplateR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateR1.addBox(-4.5F, -1.5F, -3.5F, 1, 4, 7, 0.0F);
        this.setRotation(ShoulderplateR1, 0.0F, -0.0F, 0.43633231520652765F);

        this.ShoulderplateR3 = new ModelRenderer(this, 40, 33);
        this.ShoulderplateR3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateR3.addBox(-2.5F, 3.5F, -3.5F, 1, 3, 7, 0.0F);
        this.setRotation(ShoulderplateR3, 0.0F, -0.0F, 0.43633231520652765F);

        this.HelmetB = new ModelRenderer(this, 36, 19);
        this.HelmetB.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HelmetB.addBox(-4.5F, -3.0F, 5.5F, 9, 5, 1, 0.0F);
        this.setRotation(HelmetB, 0.5235987901687622F, -0.0F, 0.0F);

        this.Chestthing = new ModelRenderer(this, 56, 50);
        this.Chestthing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Chestthing.addBox(-2.5F, 1.0F, -4.0F, 5, 7, 1, 0.0F);

        this.ShoulderR = new ModelRenderer(this, 16, 45);
        this.ShoulderR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderR.addBox(-3.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);

        this.capsthingy = new ModelRenderer(this, 21, 0);
        this.capsthingy.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.capsthingy.addBox(-4.5F, -6.0F, -6.5F, 9, 1, 2, 0.0F);

        this.ShoulderplateTopR = new ModelRenderer(this, 56, 25);
        this.ShoulderplateTopR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateTopR.addBox(-5.5F, -2.5F, -3.5F, 2, 1, 7, 0.0F);
        this.setRotation(ShoulderplateTopR, 0.0F, -0.0F, 0.43633231520652765F);

        this.bottleR1 = new ModelRenderer(this, 76, 10);
        this.bottleR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottleR1.addBox(-5.550000190734863F, 0.5F, -1.5F, 3, 1, 3, 0.0F);
        this.setRotation(bottleR1, 0.0F, -0.0F, 0.2617993950843811F);

        this.ShoulderplateR2 = new ModelRenderer(this, 40, 33);
        this.ShoulderplateR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateR2.addBox(-3.5F, 1.5F, -3.5F, 1, 3, 7, 0.0F);
        this.setRotation(ShoulderplateR2, 0.0F, -0.0F, 0.43633231520652765F);

        this.bottleR3 = new ModelRenderer(this, 88, 10);
        this.bottleR3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottleR3.addBox(-6.050000190734863F, 2.5F, -2.0F, 4, 4, 4, 0.0F);
        this.setRotation(bottleR3, 0.0F, -0.0F, 0.2617993950843811F);

        this.BeltL = new ModelRenderer(this, 16, 36);
        this.BeltL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BeltL.addBox(4.0F, 4.0F, -3.0F, 1, 3, 6, 0.0F);

        this.bottleL3 = new ModelRenderer(this, 88, 10);
        this.bottleL3.mirror = true;
        this.bottleL3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottleL3.addBox(1.9500000476837158F, 2.5F, -2.0F, 4, 4, 4, 0.0F);
        this.setRotation(bottleL3, 0.0F, -0.0F, -0.2617993950843811F);

        this.Chestplate = new ModelRenderer(this, 16, 25);
        this.Chestplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Chestplate.addBox(-4.0F, 1.0F, -3.0F, 8, 6, 1, 0.0F);

        this.HelmetR = new ModelRenderer(this, 16, 11);
        this.HelmetR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HelmetR.addBox(-6.5F, -3.0F, -4.5F, 1, 5, 9, 0.0F);
        this.setRotation(HelmetR, 0.0F, -0.0F, 0.5235987901687622F);

        this.ShoulderplateL3 = new ModelRenderer(this, 40, 33);
        this.ShoulderplateL3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateL3.addBox(1.5F, 3.5F, -3.5F, 1, 3, 7, 0.0F);
        this.setRotation(ShoulderplateL3, 0.0F, -0.0F, -0.43633231520652765F);

        this.ShoulderplateLtop = new ModelRenderer(this, 56, 25);
        this.ShoulderplateLtop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateLtop.addBox(3.5F, -2.5F, -3.5F, 2, 1, 7, 0.0F);
        this.setRotation(ShoulderplateLtop, 0.0F, -0.0F, -0.43633231520652765F);

        this.bottleL2 = new ModelRenderer(this, 88, 18);
        this.bottleL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottleL2.addBox(2.950000047683716F, 1.5F, -1.0F, 2, 1, 2, 0.0F);
        this.setRotation(bottleL2, 0.0F, -0.0F, -0.2617993950843811F);

        this.BeltR = new ModelRenderer(this, 16, 36);
        this.BeltR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BeltR.addBox(-5.0F, 4.0F, -3.0F, 1, 3, 6, 0.0F);

        this.BackpackRoll = new ModelRenderer(this, 76, 32);
        this.BackpackRoll.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackRoll.addBox(-4.5F, 7.0F, 4.0F, 9, 3, 3, 0.0F);

        this.MbeltL = new ModelRenderer(this, 16, 36);
        this.MbeltL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.MbeltL.addBox(4.0F, 8.0F, -3.0F, 1, 3, 6, 0.0F);

        this.ShoulderL = new ModelRenderer(this, 16, 45);
        this.ShoulderL.mirror = true;
        this.ShoulderL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderL.addBox(-1.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);

        this.MbeltR = new ModelRenderer(this, 16, 36);
        this.MbeltR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.MbeltR.addBox(-5.0F, 8.0F, -3.0F, 1, 3, 6, 0.0F);

        this.ShoulderplateL2 = new ModelRenderer(this, 40, 33);
        this.ShoulderplateL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ShoulderplateL2.addBox(2.5F, 1.5F, -3.5F, 1, 3, 7, 0.0F);
        this.setRotation(ShoulderplateL2, 0.0F, -0.0F, -0.43633231520652765F);

        this.bottleR2 = new ModelRenderer(this, 88, 18);
        this.bottleR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottleR2.addBox(-5.050000190734863F, 1.5F, -1.0F, 2, 1, 2, 0.0F);
        this.setRotation(bottleR2, 0.0F, -0.0F, 0.2617993950843811F);

        this.bottleL1 = new ModelRenderer(this, 76, 10);
        this.bottleL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottleL1.addBox(2.450000047683716F, 0.5F, -1.5F, 3, 1, 3, 0.0F);
        this.setRotation(bottleL1, 0.0F, -0.0F, -0.2617993950843811F);

        this.Backplate = new ModelRenderer(this, 36, 45);
        this.Backplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Backplate.addBox(-4.0F, 1.0F, 2.0F, 8, 11, 2, 0.0F);

        this.Backpack = new ModelRenderer(this, 79, 45);
        this.Backpack.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Backpack.addBox(-5.5F, 0.0F, 4.0F, 11, 7, 7, 0.0F);

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

        this.SideclothL2 = new ModelRenderer(this, 116, 34);
        this.SideclothL2.addBox(0.5F, 5.5F, -2.5F, 1, 3, 5);
        this.SideclothL2.setTextureSize(128, 64);
        setRotation(this.SideclothL2, 0.0F, 0.0F, -0.296706F);

        this.SideclothR1 = new ModelRenderer(this, 116, 42);
        this.SideclothR1.addBox(-2.5F, 0.5F, -2.5F, 1, 5, 5);
        this.SideclothR1.setTextureSize(128, 64);
        setRotation(this.SideclothR1, 0.0F, 0.0F, 0.122173F);

        this.SideclothR2 = new ModelRenderer(this, 116, 34);
        this.SideclothR2.addBox(-1.5F, 5.5F, -2.5F, 1, 3, 5);
        this.SideclothR2.setTextureSize(128, 64);
        setRotation(this.SideclothR2, 0.0F, 0.0F, 0.296706F);

        this.SideclothR3 = new ModelRenderer(this, 116, 1);
        this.SideclothR3.addBox(0.4F, 8.4F, -2.5F, 1, 3, 5);
        this.SideclothR3.setTextureSize(128, 64);
        setRotation(this.SideclothR3, 0.0F, 0.0F, 0.5235988F);

        this.SidepanelR1 = new ModelRenderer(this, 116, 25);
        this.SidepanelR1.addBox(-2.5F, 0.5F, -2.5F, 1, 4, 5);
        this.SidepanelR1.setTextureSize(128, 64);
        this.SidepanelR1.mirror = true;
        setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);

        this.LegpanelR6 = new ModelRenderer(this, 82, 38);
        this.LegpanelR6.addBox(-3.0F, 4.5F, -1.5F, 2, 3, 1);
        this.LegpanelR6.setTextureSize(128, 64);
        setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);

        this.LegpanelR5 = new ModelRenderer(this, 76, 42);
        this.LegpanelR5.addBox(-3.0F, 2.5F, -2.5F, 2, 3, 1);
        this.LegpanelR5.setTextureSize(128, 64);
        setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);

        this.LegpanelR4 = new ModelRenderer(this, 76, 38);
        this.LegpanelR4.addBox(-3.0F, 0.5F, -3.5F, 2, 3, 1);
        this.LegpanelR4.setTextureSize(128, 64);
        setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);

        this.SideclothL3 = new ModelRenderer(this, 116, 1);
        this.SideclothL3.addBox(-1.4F, 8.4F, -2.5F, 1, 3, 5);
        this.SideclothL3.setTextureSize(128, 64);
        setRotation(this.SideclothL3, 0.0F, 0.0F, -0.5235988F);

        this.SideclothL1 = new ModelRenderer(this, 116, 42);
        this.SideclothL1.addBox(1.5F, 0.5F, -2.5F, 1, 5, 5);
        this.SideclothL1.setTextureSize(128, 64);
        setRotation(this.SideclothL1, 0.0F, 0.0F, -0.122173F);

        this.LegpanelL4 = new ModelRenderer(this, 76, 38);
        this.LegpanelL4.mirror = true;
        this.LegpanelL4.addBox(1.0F, 0.5F, -3.5F, 2, 3, 1);
        this.LegpanelL4.setTextureSize(128, 64);
        setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);

        this.LegpanelL5 = new ModelRenderer(this, 76, 42);
        this.LegpanelL5.mirror = true;
        this.LegpanelL5.addBox(1.0F, 2.5F, -2.5F, 2, 3, 1);
        this.LegpanelL5.setTextureSize(128, 64);
        setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);

        this.LegpanelL6 = new ModelRenderer(this, 82, 38);
        this.LegpanelL6.mirror = true;
        this.LegpanelL6.addBox(1.0F, 4.5F, -1.5F, 2, 3, 1);
        this.LegpanelL6.setTextureSize(128, 64);
        setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);

        this.SidepanelL1 = new ModelRenderer(this, 116, 25);
        this.SidepanelL1.addBox(1.5F, 0.5F, -2.5F, 1, 4, 5);
        this.SidepanelL1.setTextureSize(128, 64);
        setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);

        this.bipedHeadwear.cubeList.clear();
        this.bipedHead.cubeList.clear();
        this.bipedHead.addChild(this.Helmet);
        this.bipedHead.addChild(this.HelmetB);
        this.bipedHead.addChild(this.HelmetL);
        this.bipedHead.addChild(this.HelmetR);
        this.bipedHead.addChild(this.capsthingy);
        this.bipedHead.addChild(this.Mask[0]);
        this.bipedHead.addChild(this.Mask[1]);

        this.bipedBody.cubeList.clear();
        this.bipedBody.addChild(this.Mbelt);
        this.bipedBody.addChild(this.MbeltL);
        this.bipedBody.addChild(this.MbeltR);

        if (modelSize < 1.0F) {
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
        } else {
            this.bipedBody.addChild(this.Chestplate);
            this.bipedBody.addChild(this.Chestthing);
            this.bipedBody.addChild(this.clothchestL);
            this.bipedBody.addChild(this.clothchestR);
            this.bipedBody.addChild(this.BeltL);
            this.bipedBody.addChild(this.BeltR);
            this.bipedBody.addChild(this.Backplate);
            this.bipedBody.addChild(this.Backpack);
            this.bipedBody.addChild(this.BackpackRoll);
        }

        this.bipedRightArm.cubeList.clear();
        this.bipedRightArm.addChild(this.ShoulderR);
        this.bipedRightArm.addChild(this.ShoulderplateR1);
        this.bipedRightArm.addChild(this.ShoulderplateR2);
        this.bipedRightArm.addChild(this.ShoulderplateR3);
        this.bipedRightArm.addChild(this.ShoulderplateTopR);

        this.bipedLeftArm.cubeList.clear();
        this.bipedLeftArm.addChild(this.ShoulderL);
        this.bipedLeftArm.addChild(this.ShoulderplateL1);
        this.bipedLeftArm.addChild(this.ShoulderplateL2);
        this.bipedLeftArm.addChild(this.ShoulderplateL3);
        this.bipedLeftArm.addChild(this.ShoulderplateLtop);

        this.bipedRightLeg.cubeList.clear();
        this.bipedRightLeg.addChild(this.LegpanelR4);
        this.bipedRightLeg.addChild(this.LegpanelR5);
        this.bipedRightLeg.addChild(this.LegpanelR6);
        this.bipedRightLeg.addChild(this.SidepanelR1);

        this.bipedRightLeg.addChild(this.SideclothR1);
        this.bipedRightLeg.addChild(this.SideclothR2);
        this.bipedRightLeg.addChild(this.SideclothR3);

        this.bipedRightLeg.addChild(this.bottleR1);
        this.bipedRightLeg.addChild(this.bottleR2);
        this.bipedRightLeg.addChild(this.bottleR3);

        this.bipedLeftLeg.cubeList.clear();
        this.bipedLeftLeg.addChild(this.LegpanelL4);
        this.bipedLeftLeg.addChild(this.LegpanelL5);
        this.bipedLeftLeg.addChild(this.LegpanelL6);
        this.bipedLeftLeg.addChild(this.SidepanelL1);

        this.bipedLeftLeg.addChild(this.SideclothL1);
        this.bipedLeftLeg.addChild(this.SideclothL2);
        this.bipedLeftLeg.addChild(this.SideclothL3);

        this.bipedLeftLeg.addChild(this.bottleL1);
        this.bipedLeftLeg.addChild(this.bottleL2);
        this.bipedLeftLeg.addChild(this.bottleL3);
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

    public void render(@Nonnull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
