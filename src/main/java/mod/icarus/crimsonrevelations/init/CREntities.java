package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.entity.EntityCultistArcher;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thaumcraft.common.lib.SoundsTC;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class CREntities {
    public static void registerDispenserBehavior() {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CRItems.AER_ARROW, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                EntityPrimalArrow entity = new EntityPrimalArrow(worldIn, position.getX(), position.getY(), position.getZ(), CRItems.AER_ARROW);
                entity.pickupStatus = EntityPrimalArrow.PickupStatus.ALLOWED;
                entity.setArrowType(0);
                entity.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (entity.world.rand.nextFloat() * 0.4F + 0.8F));
                return entity;
            }
        });

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CRItems.AQUA_ARROW, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                EntityPrimalArrow entity = new EntityPrimalArrow(worldIn, position.getX(), position.getY(), position.getZ(), CRItems.AQUA_ARROW);
                entity.pickupStatus = EntityPrimalArrow.PickupStatus.ALLOWED;
                entity.setArrowType(1);
                entity.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (entity.world.rand.nextFloat() * 0.4F + 0.8F));
                return entity;
            }
        });

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CRItems.IGNIS_ARROW, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                EntityPrimalArrow entity = new EntityPrimalArrow(worldIn, position.getX(), position.getY(), position.getZ(), CRItems.IGNIS_ARROW);
                entity.pickupStatus = EntityPrimalArrow.PickupStatus.ALLOWED;
                entity.setArrowType(2);
                entity.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (entity.world.rand.nextFloat() * 0.4F + 0.8F));
                return entity;
            }
        });

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CRItems.ORDO_ARROW, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                EntityPrimalArrow entity = new EntityPrimalArrow(worldIn, position.getX(), position.getY(), position.getZ(), CRItems.ORDO_ARROW);
                entity.pickupStatus = EntityPrimalArrow.PickupStatus.ALLOWED;
                entity.setArrowType(3);
                entity.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (entity.world.rand.nextFloat() * 0.4F + 0.8F));
                return entity;
            }
        });

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CRItems.PERDITIO_ARROW, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                EntityPrimalArrow entity = new EntityPrimalArrow(worldIn, position.getX(), position.getY(), position.getZ(), CRItems.PERDITIO_ARROW);
                entity.pickupStatus = EntityPrimalArrow.PickupStatus.ALLOWED;
                entity.setArrowType(4);
                entity.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (entity.world.rand.nextFloat() * 0.4F + 0.8F));
                return entity;
            }
        });

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CRItems.TERRA_ARROW, new BehaviorProjectileDispense() {
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                EntityPrimalArrow entity = new EntityPrimalArrow(worldIn, position.getX(), position.getY(), position.getZ(), CRItems.TERRA_ARROW);
                entity.pickupStatus = EntityPrimalArrow.PickupStatus.ALLOWED;
                entity.setArrowType(5);
                entity.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (entity.world.rand.nextFloat() * 0.4F + 0.8F));
                return entity;
            }
        });
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        int id = 0;

        registerEntity("cultist_archer", EntityCultistArcher.class, id++, 64, 3, true, 9438728, 0x2F8405);
        registerEntity("overgrown_taintacle", EntityOvergrownTaintacle.class, id++, 64, 3, true, 0x1C1A2F, 0x5649B4);

        registerEntity("primal_arrow", EntityPrimalArrow.class, id++, 64, 1, true);
    }

    public static void registerEntitySpawns() {
        // TODO: New mobs go here
    }

    public static void registerEntity(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int eggColor1, int eggColor2) {
        EntityRegistry.registerModEntity(new ResourceLocation(NewCrimsonRevelations.MODID, name), clazz, NewCrimsonRevelations.MODID + "." + name, id, NewCrimsonRevelations.instance, trackingRange,
                updateFrequency, sendVelocityUpdates, eggColor1, eggColor2);
    }

    public static void registerEntity(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
        EntityRegistry.registerModEntity(new ResourceLocation(NewCrimsonRevelations.MODID, name), clazz, NewCrimsonRevelations.MODID + "." + name, id, NewCrimsonRevelations.instance, trackingRange,
                updateFrequency, sendVelocityUpdates);
    }
}
