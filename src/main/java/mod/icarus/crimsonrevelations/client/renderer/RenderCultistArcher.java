package mod.icarus.crimsonrevelations.client.renderer;

import mod.icarus.crimsonrevelations.client.model.ModelCultistArcher;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import thaumcraft.Thaumcraft;
import thaumcraft.common.entities.monster.cult.EntityCultist;

@SideOnly(Side.CLIENT)
public class RenderCultistArcher extends RenderBiped<EntityCultist> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(Thaumcraft.MODID, "textures/entity/cultist.png");

    public RenderCultistArcher(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelCultistArcher(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this));
    }

    @Override
    protected @NotNull ResourceLocation getEntityTexture(@NotNull EntityCultist entity) {
        return TEXTURES;
    }
}
