package mod.icarus.crimsonrevelations.events.entity;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.common.entities.monster.cult.EntityCultist;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class CultistEvent {
    @SubscribeEvent
    public static void onAttackEvent(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        Entity trueSource = damageSource.getTrueSource();

        // Cultists no longer harm other cultists and teammates.
        if (trueSource instanceof EntityCultist && trueSource != null) {
            if (entity.isOnSameTeam(trueSource)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        Entity trueSource = damageSource.getTrueSource();

        // Cultists no longer harm other cultists and teammates.
        if (trueSource instanceof EntityCultist && trueSource != null) {
            if (entity.isOnSameTeam(trueSource)) {
                event.setAmount(0.0F);
                event.setCanceled(true);
            }
        }
    }
}
