package mod.icarus.crimsonrevelations.casters.foci;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.registry.ModSoundEventsNCR;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
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
import thaumcraft.common.lib.potions.PotionBlurredVision;

public class FocusEffectBlindingFlash extends FocusEffect {
    @Override
    public Aspect getAspect() {
        return Aspect.LIGHT;
    }

    @Override
    public String getKey() {
        return "focus." + NewCrimsonRevelations.MODID + ".blinding_flash";
    }

    @Override
    public NodeSetting[] createSettings() {
        return new NodeSetting[]{new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)), new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(1, 10))};
    }

    @Override
    public int getComplexity() {
        return this.getSettingValue("duration") + this.getSettingValue("power") * 3;
    }

    @Override
    public float getDamageForDisplay(float finalPower) {
        return (3.0F + this.getSettingValue("power")) * finalPower;
    }

    @Override
    public String getResearch() {
        return "CR_FOCUS_BLINDING_FLASH";
    }

    @Override
    public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
        PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.hitVec.x, target.hitVec.y, target.hitVec.z, new String[]{this.getKey()}), new NetworkRegistry.TargetPoint(this.getPackage().world.provider.getDimension(), target.hitVec.x, target.hitVec.y, target.hitVec.z, 64.0D));
        this.getPackage().world.playSound(null, target.hitVec.x, target.hitVec.y, target.hitVec.z, ModSoundEventsNCR.FOCUS_BLINDING_LIGHT_HIT, SoundCategory.PLAYERS, 0.8F, 1.0F + (float) (this.getPackage().world.rand.nextGaussian() * 0.05F));

        if (target.typeOfHit == RayTraceResult.Type.ENTITY && target.entityHit != null) {
            int duration = 20 * this.getSettingValue("duration");
            int potency = (int) (1.0F + this.getSettingValue("power") * finalPower / 2.0F);

            if (((EntityLivingBase) target.entityHit).isEntityUndead()) {
                target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((target.entityHit != null) ? target.entityHit : this.getPackage().getCaster(), this.getPackage().getCaster()).setDamageBypassesArmor().setDamageIsAbsolute(), this.getSettingValue("power") * finalPower * 2.0F);
            } else {
                target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((target.entityHit != null) ? target.entityHit : this.getPackage().getCaster(), this.getPackage().getCaster()), this.getSettingValue("power") * finalPower * 0.5F);
            }

            if (target.entityHit instanceof EntityLivingBase) {
                ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, duration, potency));
                ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(PotionBlurredVision.instance, duration, potency));

                if (!(target.entityHit instanceof EntityPlayer)) {
                    ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, duration, potency));
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void onCast(Entity caster) {
        caster.world.playSound(null, caster.getPosition().up(), ModSoundEventsNCR.FOCUS_BLINDING_LIGHT_SHOOT, SoundCategory.PLAYERS, 1.2F, 1.0F + caster.world.rand.nextFloat() * 0.1F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderParticleFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        FXGeneric pp = new FXGeneric(world, posX, posY, posZ, velX, velY, velZ);
        int color = 16776421;

        pp.setAlphaF(0.0F, 1.0F);
        pp.setGravity(-0.2F);
        pp.setGridSize(64);
        pp.setLoop(true);
        pp.setMaxAge(10 + world.rand.nextInt(5));
        pp.setParticles(512, 16, 1);
        pp.setRandomMovementScale(0.0025F, 0.0F, 0.0025F);
        pp.setRBGColorF(((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F, (color & 0xFF) / 255.0F);
        pp.setScale((float) (5.0F + world.rand.nextGaussian() * 0.2F), 0.1F);
        pp.setSlowDown(0.75D);
        pp.setWind(0.001D);
        ParticleEngine.addEffect(world, pp);
    }
}