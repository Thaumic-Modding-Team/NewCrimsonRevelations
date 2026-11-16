package mod.icarus.crimsonrevelations.events.bauble;

import baubles.api.BaublesApi;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class NutritionRingEvent {
    @SubscribeEvent
    public static void onAttackEvent(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            // TODO: This should be nerfed
            // Ring of Nutriment - Starvation Immunity
            if (BaublesApi.isBaubleEquipped(player, ModItemsNCR.NUTRITION_RING) >= 0) {

                // Prevents screen shaking and damage sound.
                if (event.getSource() == DamageSource.STARVE) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            // TODO: This should be nerfed
            // Ring of Nutriment - Starvation Immunity
            if (BaublesApi.isBaubleEquipped(player, ModItemsNCR.NUTRITION_RING) >= 0) {
                if (event.getSource() == DamageSource.STARVE) {
                    event.setAmount(0.0F);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            // Ring of Nutriment boosts eaten foods: +1-4 Hunger and +0.5 Saturation
            if (event.getItem().getItem() instanceof ItemFood) {
                if (BaublesApi.isBaubleEquipped(player, ModItemsNCR.NUTRITION_RING) >= 0) {
                    int random = player.world.rand.nextInt(4);
                    player.getFoodStats().addStats(1 + random, 0.5F);
                    player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1.0F, 0.5F);
                }
            }
        }
    }
}
