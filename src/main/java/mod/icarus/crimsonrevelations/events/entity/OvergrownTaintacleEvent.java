package mod.icarus.crimsonrevelations.events.entity;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXShield;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class OvergrownTaintacleEvent {
    @SubscribeEvent
    public static void onAttackEvent(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();

        // Overgrown Taintacles are immune to arrows
        if (entity instanceof EntityOvergrownTaintacle) {
            if (damageSource.getImmediateSource() instanceof IProjectile && entity.getHealth() <= (entity.getMaxHealth() * ConfigHandlerNCR.overgrown_taintacle.projectileImmunityThreshold)) {
                event.setCanceled(true);
                entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.AMBIENT, 3.0F, 1.5F + entity.world.rand.nextFloat() / 2.0F);
                PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(entity.getEntityId(), damageSource.getImmediateSource().getEntityId()), new NetworkRegistry.TargetPoint(event.getEntity().world.provider.getDimension(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, 32.0D));
            }
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();

        // Overgrown Taintacles are immune to arrows
        if (entity instanceof EntityOvergrownTaintacle) {
            if (damageSource.getImmediateSource() instanceof IProjectile && entity.getHealth() <= (entity.getMaxHealth() * ConfigHandlerNCR.overgrown_taintacle.projectileImmunityThreshold)) {
                event.setAmount(0.0F);
                event.setCanceled(true);
            }
        }
    }
}
