package mod.icarus.crimsonrevelations.client.renderer.tile;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.tile.TileEtherealBloom;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.Thaumcraft;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;

@SideOnly(Side.CLIENT)
public class TileEtherealBloomTESR extends TileEntitySpecialRenderer<TileEtherealBloom> {
    private static final ResourceLocation tx1 = new ResourceLocation(Thaumcraft.MODID, "textures/models/crystalcapacitor.png");
    private static final ResourceLocation tx2 = new ResourceLocation(Thaumcraft.MODID, "textures/models/bloom_leaves.png");
    private static final ResourceLocation tx3 = new ResourceLocation(Thaumcraft.MODID, "textures/models/bloom_stalk.png");
    public static final ResourceLocation texture = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/misc/nodes.png");
    private final ModelCube model = new ModelCube();

    @Override
    public void render(TileEtherealBloom tile, double x, double y, double z, float pt, int ds, float alpha) {
        int a;
        float rc1;
        float rc2 = rc1 = (float) tile.growthCounter + pt;
        float rc3 = rc1 - 33.0F;
        float rc4 = rc1 - 66.0F;

        if (rc1 > 100.0F) {
            rc1 = 100.0F;
        }

        if (rc2 > 50.0F) {
            rc2 = 50.0F;
        }

        if (rc3 < 0.0F) {
            rc3 = 0.0F;
        }

        if (rc3 > 33.0F) {
            rc3 = 33.0F;
        }

        if (rc4 < 0.0F) {
            rc4 = 0.0F;
        }

        if (rc4 > 33.0F) {
            rc4 = 33.0F;
        }

        float scale1 = rc1 / 100.0F;
        float scale2 = rc2 / 60.0F + 0.17F;
        float scale3 = rc3 / 33.0F;
        float scale4 = rc4 / 33.0F * 0.7F;

        GlStateManager.pushMatrix();
        GlStateManager.alphaFunc(516, 0.004F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

        GlStateManager.pushMatrix();
        GlStateManager.depthMask(false);
        GlStateManager.disableCull();

        int i = tile.counter % 32;
        this.bindTexture(texture);
        float bloomScale = scale1 * 1.3F;

        UtilsFX.renderFacingQuad((double) tile.getPos().getX() + 0.5D, (float) tile.getPos().getY() + scale1,
                (double) tile.getPos().getZ() + 0.5D, 32, 32, 192 + i, bloomScale, 11197951, scale1, 1, pt
        );

        UtilsFX.renderFacingQuad((double) tile.getPos().getX() + 0.5D, (float) tile.getPos().getY() + scale1,
                (double) tile.getPos().getZ() + 0.5D, 32, 32, 192 + i, bloomScale * 0.7F, 16777215, scale1 * 0.4F, 1, pt
        );

        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();

        float pulse = MathHelper.sin(((float) tile.counter + pt) / 24.0F) * 0.08F;
        float adjustedHeadX = scale4 * (1.0F + pulse);
        float adjustedHeadY = scale4 * (1.0F + pulse * 0.5F);
        float adjustedHeadZ = scale4 * (1.0F + pulse);

        GlStateManager.translate(x + 0.5D - (adjustedHeadX / 8.0F), y + scale1 - (adjustedHeadY / 6.0F), z + 0.5D - (adjustedHeadZ / 8.0F));
        GlStateManager.scale(adjustedHeadX / 4.0F, adjustedHeadY / 3.0F, adjustedHeadZ / 4.0F);
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        this.bindTexture(tx1);
        float adjustedAlpha = 0.75F + (pulse * 1.5F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, MathHelper.clamp(adjustedAlpha, 0.6F, 0.9F));
        this.model.render();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();

        float r1 = MathHelper.sin(((float) tile.counter + pt) / 12.0F) * 2.0F;
        float r2 = MathHelper.sin(((float) tile.counter + pt) / 11.0F) * 2.0F;

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 0.25D, z + 0.5D);
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);

        for (a = 0; a < 4; ++a) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(scale3, scale1, scale3);
            GlStateManager.rotate((90.0F * a), 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(r1, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(r2, 0.0F, 0.0F, 1.0F);
            UtilsFX.renderQuadCentered(tx2, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
            GlStateManager.popMatrix();
        }

        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 0.6D, z + 0.5D);
        GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);

        for (a = 0; a < 4; ++a) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(scale4, scale1 * 0.7F, scale4);
            GlStateManager.rotate((90.0F * a), 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(r2, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(r1, 0.0F, 0.0F, 1.0F);
            UtilsFX.renderQuadCentered(tx2, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
            GlStateManager.popMatrix();
        }

        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y, z + 0.5D);
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);

        for (a = 0; a < 4; ++a) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(scale1 / 2.0F, 0.0F, 0.0F);
            GlStateManager.scale(scale1, scale2, scale2);
            GlStateManager.rotate((90.0F * a), 1.0F, 0.0F, 0.0F);
            UtilsFX.renderQuadCentered(tx3, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
            GlStateManager.popMatrix();
        }

        GlStateManager.popMatrix();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.popMatrix();
    }
}