package mod.icarus.crimsonrevelations.client.renderer.tile;

import mod.icarus.crimsonrevelations.client.model.ModelManaPod;
import mod.icarus.crimsonrevelations.tile.TileManaPod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.Thaumcraft;
import thaumcraft.api.aspects.Aspect;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class TileManaPodTESR extends TileEntitySpecialRenderer<TileManaPod> {
    private static final ResourceLocation POD_0_TEX = new ResourceLocation(Thaumcraft.MODID, "textures/models/manapod_0.png");
    private static final ResourceLocation POD_2_TEX = new ResourceLocation(Thaumcraft.MODID, "textures/models/manapod_2.png");
    private final ModelManaPod model = new ModelManaPod();

    @Override
    public void render(TileManaPod pod, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        int meta;
        Aspect aspect = Aspect.PLANT;

        if (pod.getWorld() == null) {
            meta = 5;
        } else {
            meta = pod.getBlockMetadata();

            if (pod.aspect != null) {
                aspect = pod.aspect;
            }
        }

        if (meta > 1) {
            float br = 0.14509805F;
            float bg = 0.6156863F;
            float bb = 0.45882353F;
            float fr = br;
            float fg = bg;
            float fb = bb;

            if (pod.aspect != null) {
                Color color = new Color(aspect.getColor());
                float ar = color.getRed() / 255.0F;
                float ag = color.getGreen() / 255.0F;
                float ab = color.getBlue() / 255.0F;

                if (meta == 7) {
                    fr = ar;
                    fg = ag;
                    fb = ab;
                } else {
                    float m = (meta - 2);
                    fr = (br + ar * m) / (m + 1.0F);
                    fg = (bg + ag * m) / (m + 1.0F);
                    fb = (bb + ab * m) / (m + 1.0F);
                }
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableLighting();
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.translate(x + 0.5D, y + 0.75D, z + 0.5D);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);

            if (meta > 2) {
                EntityPlayerSP entityClientPlayerMP = (Minecraft.getMinecraft()).player;
                float scale = MathHelper.sin((entityClientPlayerMP.ticksExisted + pod.hashCode() % 100) / 8.0F) * 0.1F + 0.9F;
                GlStateManager.pushMatrix();

                int j = meta * 10 + (int) (150.0F * scale);
                int k = j % 65536;
                int l = j / 65536;

                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k, l);
                GlStateManager.translate(0.0D, 0.1D, 0.0D);
                GlStateManager.scale(0.125D * meta * scale, 0.125D * meta * scale, 0.125D * meta * scale);
                this.bindTexture(POD_0_TEX);
                this.model.pod0.render(0.0625F);
                GlStateManager.popMatrix();
            }

            GlStateManager.scale(0.15D * meta, 0.15D * meta, 0.15D * meta);
            GlStateManager.color(fr, fg, fb, 0.9F);
            this.bindTexture(POD_2_TEX);
            this.model.pod2.render(0.0625F);
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.disableLighting();
            GlStateManager.popMatrix();
        }
    }
}
