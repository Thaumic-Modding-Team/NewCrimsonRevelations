package mod.icarus.crimsonrevelations.casters.foci;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.registry.ModSoundEventsNCR;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.NodeSetting;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXGeneric;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;

// Knock them back!
public class FocusEffectPunch extends FocusEffect {
    @Override
    public Aspect getAspect() {
        return Aspect.MAN;
    }

    @Override
    public String getKey() {
        return "focus." + NewCrimsonRevelations.MODID + ".punch";
    }

    @Override
    public NodeSetting[] createSettings() {
        return new NodeSetting[]{new NodeSetting("knockback", "focus.common.knockback", new NodeSetting.NodeSettingIntRange(1, 5))};
    }

    @Override
    public int getComplexity() {
        return this.getSettingValue("knockback") * 3;
    }

    @Override
    public float getDamageForDisplay(float finalPower) {
        return 0.0F;
    }

    @Override
    public String getResearch() {
        return "CR_FOCUS_PUNCH";
    }

    @Override
    public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
        PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.hitVec.x, target.hitVec.y, target.hitVec.z, new String[]{this.getKey()}), new NetworkRegistry.TargetPoint(this.getPackage().world.provider.getDimension(), target.hitVec.x, target.hitVec.y, target.hitVec.z, 64.0D));
        this.getPackage().world.playSound(null, target.hitVec.x, target.hitVec.y, target.hitVec.z, ModSoundEventsNCR.FOCUS_PUNCH_HIT, SoundCategory.PLAYERS, 0.33F, 1.0F + this.getPackage().world.rand.nextFloat() * 0.1F);

        if (target.typeOfHit == RayTraceResult.Type.ENTITY && target.entityHit != null) {
            float knockback = this.getSettingValue("knockback");

            if (target.entityHit instanceof EntityLivingBase) {
                if (trajectory != null) {
                    ((EntityLivingBase) target.entityHit).knockBack(getPackage().getCaster(), knockback * 0.8F, -trajectory.direction.x, -trajectory.direction.z);
                } else {
                    ((EntityLivingBase) target.entityHit).knockBack(getPackage().getCaster(), knockback * 0.8F, -MathHelper.sin(target.entityHit.rotationYaw * 0.02F), MathHelper.cos(target.entityHit.rotationYaw * 0.02F));
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void onCast(Entity caster) {
        caster.world.playSound(null, caster.getPosition().up(), ModSoundEventsNCR.FOCUS_PUNCH_SHOOT, SoundCategory.PLAYERS, 0.9F, 1.0F + caster.world.rand.nextFloat() * 0.1F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderParticleFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        FXGeneric pp = new FXGeneric(world, posX, posY, posZ, velX, velY, velZ);
        int color = 16357381;

        pp.setAlphaF(0.5F);
        pp.setMaxAge(5 + world.rand.nextInt(5));
        int q = world.rand.nextInt(4);
        pp.setParticles(704 + q * 3, 3, 1);
        pp.setRBGColorF(((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F, (color & 0xFF) / 255.0F);
        pp.setSlowDown(0.5D);
        pp.setScale((float) (2.0F + world.rand.nextGaussian() * 0.2F), 3.0F);
        ParticleEngine.addEffect(world, pp);
    }
}