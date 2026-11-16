package mod.icarus.crimsonrevelations.events.entity;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.item.misc.ItemManaBean;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.entities.monster.EntityPech;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class ManaBeanLootEvent {
    @SubscribeEvent
    public static void onDropsEvent(LivingDropsEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        Aspect[] aspects = Aspect.getPrimalAspects().toArray(new Aspect[0]);

        // Pechs drop primal mana beans.
        if (!entity.world.isRemote && ConfigHandlerNCR.mana_beans.pechLoot && entity instanceof EntityPech) {
            for (int i = 0; i < 1 + event.getLootingLevel(); ++i) {
                if (entity.getRNG().nextBoolean()) {
                    ItemStack is = new ItemStack(ModItemsNCR.MANA_BEAN);
                    ((ItemManaBean) is.getItem()).setAspects(is, new AspectList().add(aspects[entity.getRNG().nextInt(aspects.length)], 1));
                    event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, is));
                }
            }
        }
    }
}
