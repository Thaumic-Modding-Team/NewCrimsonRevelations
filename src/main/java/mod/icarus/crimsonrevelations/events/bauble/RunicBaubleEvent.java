package mod.icarus.crimsonrevelations.events.bauble;

import baubles.api.BaublesApi;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.registry.ModSoundEventsNCR;
import mod.icarus.crimsonrevelations.registry.RegistrarNCR;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class RunicBaubleEvent {
    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        World world = event.getEntity().world;
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        Entity trueSource = damageSource.getTrueSource();

        if (entity instanceof EntityPlayer && trueSource instanceof EntityLivingBase && !world.isRemote) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            int charge = RegistrarNCR.getRunicShielding(player);

            // Kinetic Girdle of Shielding - Explodes when the Runic Shielding is pierced (20 second cooldown).
            if (charge > 0) {
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, ModItemsNCR.RUNIC_GIRDLE_KINETIC) > 2 && !(player.getCooldownTracker().hasCooldown(ModItemsNCR.RUNIC_GIRDLE_KINETIC))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEventsNCR.RUNIC_BAUBLE_KINETIC, SoundCategory.PLAYERS, 0.8F, 1.0F);
                    player.world.createExplosion(player, player.posX, player.posY + player.height / 2.0F, player.posZ, 2.0F, false);

                    List<Entity> entities = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(3.0D, 3.0D, 3.0D));

                    for (Entity target : entities) {
                        if (target instanceof EntityLivingBase) {
                            EntityLivingBase mob = (EntityLivingBase) target;
                            mob.knockBack(player, 2.0F, player.posX - mob.posX, player.posZ - mob.posZ);
                        }
                    }

                    player.addStat(Objects.requireNonNull(StatList.getObjectUseStats(ModItemsNCR.RUNIC_GIRDLE_KINETIC)));
                    player.getCooldownTracker().setCooldown(ModItemsNCR.RUNIC_GIRDLE_KINETIC, 20 * 20);
                }

                // Revitalizing Ring of Shielding - Gives 6 seconds of Regeneration II when the Runic Shielding is pierced (20 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, ModItemsNCR.RUNIC_RING_REGEN) > 0 && !(player.getCooldownTracker().hasCooldown(ModItemsNCR.RUNIC_RING_REGEN))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEventsNCR.RUNIC_BAUBLE_REGEN, SoundCategory.PLAYERS, 1.5F, 1.0F);
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6 * 20, 1, true, true));

                    player.addStat(Objects.requireNonNull(StatList.getObjectUseStats(ModItemsNCR.RUNIC_RING_REGEN)));
                    player.getCooldownTracker().setCooldown(ModItemsNCR.RUNIC_RING_REGEN, 20 * 20);
                }

                // Amulet of Emergency Shielding - Gives 8 points of Absorption when the Runic Shielding is pierced (40 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, ModItemsNCR.RUNIC_AMULET_EMERGENCY) > -1 && !(player.getCooldownTracker().hasCooldown(ModItemsNCR.RUNIC_AMULET_EMERGENCY))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEventsNCR.RUNIC_BAUBLE_EMERGENCY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 40 * 20, 1, true, true));

                    player.addStat(Objects.requireNonNull(StatList.getObjectUseStats(ModItemsNCR.RUNIC_AMULET_EMERGENCY)));
                    player.getCooldownTracker().setCooldown(ModItemsNCR.RUNIC_AMULET_EMERGENCY, 40 * 20);
                }
            }
        }
    }
}
