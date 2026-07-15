package mod.icarus.crimsonrevelations.client.renderer;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.UtilsFX;

@SideOnly(Side.CLIENT)
public class RenderPrimalArrow extends RenderArrow<EntityPrimalArrow> {
    public static final ResourceLocation TEXTURE_AER = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_aer.png");
    public static final ResourceLocation TEXTURE_AQUA = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_aqua.png");
    public static final ResourceLocation TEXTURE_IGNIS = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_ignis.png");
    public static final ResourceLocation TEXTURE_ORDO = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_ordo.png");
    public static final ResourceLocation TEXTURE_PERDITIO = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_perditio.png");
    public static final ResourceLocation TEXTURE_TERRA = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_terra.png");

    public static final ResourceLocation TEXTURE_PARTICLE = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/misc/particles.png");

    public RenderPrimalArrow(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(@NotNull EntityPrimalArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        int type = entity.getArrowType();
        if (type < 8) {
            GlStateManager.pushMatrix();
            this.bindTexture(TEXTURE_PARTICLE);
            int textureIndex = 72 + (entity.ticksExisted % 8);
            int color = getAspectColor(type);
            float alpha = 1.0F;
            int blendMode = (type == 4) ? 771 : 1;
            double renderX = entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks;
            double renderY = entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks;
            double renderZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks;
            UtilsFX.renderFacingQuad(renderX, renderY, renderZ, 16, 16, textureIndex, 0.35F, color, alpha, blendMode, partialTicks);
            GlStateManager.popMatrix();
        }
    }

    private int getAspectColor(int type) {
        Aspect aspect = null;
        switch (type) {
            case 0:
                aspect = Aspect.AIR;
                break;
            case 1:
                aspect = Aspect.WATER;
                break;
            case 2:
                aspect = Aspect.FIRE;
                break;
            case 3:
                aspect = Aspect.ORDER;
                break;
            case 4:
                aspect = Aspect.ENTROPY;
                break;
            case 5:
                aspect = Aspect.EARTH;
                break;
        }
        if (aspect != null) {
            return aspect.getColor();
        }
        return type;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPrimalArrow entity) {
        switch (entity.getArrowType()) {
            case 5:
                return TEXTURE_TERRA;
            case 4:
                return TEXTURE_PERDITIO;
            case 3:
                return TEXTURE_ORDO;
            case 2:
                return TEXTURE_IGNIS;
            case 1:
                return TEXTURE_AQUA;
            case 0:
            default:
                return TEXTURE_AER;
        }
    }
}
