package mod.icarus.crimsonrevelations.events.tool;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class CultistSwordEvent {
    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        Entity trueSource = damageSource.getTrueSource();

        if (trueSource instanceof EntityLivingBase && trueSource != null) {
            Item heldItem = ((EntityLivingBase) trueSource).getHeldItemMainhand().getItem();

            if (heldItem == ModItemsNCR.CRIMSON_SWORD) {
                // Poison while cultist sword is equipped
                entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 6 * 20, 1));
            }
        }
    }
}
