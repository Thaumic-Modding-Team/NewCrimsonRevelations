package mod.icarus.crimsonrevelations.events.armor;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.item.armor.ItemCometBoots;
import mod.icarus.crimsonrevelations.item.armor.ItemMeteorBoots;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class BootsEvent {
    @SubscribeEvent
    public static void onAttackEvent(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        ItemStack boots = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        // Prevents screen shaking and damage sound.
        if (boots.getItem() instanceof ItemCometBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((ItemCometBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setCanceled(true);
            }
        } else if (boots.getItem() instanceof ItemMeteorBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((ItemMeteorBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        ItemStack boots = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        // Immune to these damage types.
        if (boots.getItem() instanceof ItemCometBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setAmount(0);
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((ItemCometBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setAmount(0);
                event.setCanceled(true);
            }
        } else if (boots.getItem() instanceof ItemMeteorBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setAmount(0);
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((ItemMeteorBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setAmount(0);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();

        if (damageSource == DamageSource.FALL) {
            ItemStack boots = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (boots.getItem() instanceof ItemCometBoots) {
                float damage = ((ItemCometBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount());

                event.setAmount(damage);
                if (damage == 0) {
                    event.setCanceled(true);
                }
            } else if (boots.getItem() instanceof ItemMeteorBoots) {
                float damage = ((ItemMeteorBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount());

                event.setAmount(damage);
                if (damage == 0) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
