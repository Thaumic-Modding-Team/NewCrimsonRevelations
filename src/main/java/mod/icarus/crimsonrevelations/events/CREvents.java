package mod.icarus.crimsonrevelations.events;

import baubles.api.BaublesApi;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.CRBlockManaPod;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.init.CRRegistry;
import mod.icarus.crimsonrevelations.init.CRSoundEvents;
import mod.icarus.crimsonrevelations.item.CRItemManaBean;
import mod.icarus.crimsonrevelations.item.armor.CRItemCometBoots;
import mod.icarus.crimsonrevelations.item.armor.CRItemMeteorBoots;
import mod.icarus.crimsonrevelations.world.WorldGenManaPods;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXShield;
import thaumcraft.common.world.biomes.BiomeGenMagicalForest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CREvents {
    @SubscribeEvent
    public static void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        // Axe of Execution - Smelts wood chopped, also works with Fortune and gives experience.
        if (event.getHarvester() != null && (event.getHarvester().getHeldItemMainhand().getItem() == CRItems.EXECUTION_AXE)) {
            if (event.getState().getBlock().canHarvestBlock(event.getWorld(), event.getPos(), event.getHarvester())) {
                List<ItemStack> to_be_removed = new ArrayList<ItemStack>();
                List<ItemStack> to_be_added = new ArrayList<ItemStack>();

                for (ItemStack input : event.getDrops()) {
                    ItemStack result = FurnaceRecipes.instance().getSmeltingResult(input);

                    if (!result.isEmpty()) {
                        int i = input.getCount() * result.getCount();
                        float f = FurnaceRecipes.instance().getSmeltingExperience(result);

                        to_be_added.add(new ItemStack(result.getItem(), i + new Random().nextInt(2 + event.getFortuneLevel() - 1), result.getItemDamage()));
                        to_be_removed.add(input);

                        if (f == 0.0F) {
                            i = 0;
                        } else if (f < 1.0F) {
                            int j = MathHelper.floor((float) i * f);

                            if (j < MathHelper.ceil((float) i * f) && Math.random() < (double) ((float) i * f - (float) j)) {
                                ++j;
                            }

                            i = j;
                        }

                        while (i > 0) {
                            int k = EntityXPOrb.getXPSplit(i);
                            i -= k;
                            event.getHarvester().world.spawnEntity(new EntityXPOrb(event.getWorld(), event.getPos().getX(), event.getPos().getY() + 0.5D, event.getPos().getZ(), k));
                        }
                    }
                }

                event.getDrops().addAll(to_be_added);
                event.getDrops().removeAll(to_be_removed);
            }
        }

        // Shovel of the Purifier - Has a chance to convert harvested tainted blocks to flux crystals
        if (event.getHarvester() != null && (event.getHarvester().getHeldItemMainhand().getItem() == CRItems.PURIFYING_SHOVEL)) {
            if (event.getState().getBlock().canHarvestBlock(event.getWorld(), event.getPos(), event.getHarvester())) {
                List<ItemStack> to_be_removed = new ArrayList<ItemStack>();
                List<ItemStack> to_be_added = new ArrayList<ItemStack>();

                if (event.getWorld().rand.nextDouble() <= 0.1D) {
                    to_be_added.add(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX, 1 + new Random().nextInt(2 + event.getFortuneLevel() - 1)));

                    // If the tainted block drops something remove it in favor of the crystal
                    if (!event.getDrops().isEmpty()) {
                        to_be_removed.addAll(event.getDrops());
                    }
                }

                event.getDrops().addAll(to_be_added);
                event.getDrops().removeAll(to_be_removed);
            }
        }
    }

    @SubscribeEvent
    public static void onAttackEvent(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        Entity trueSource = damageSource.getTrueSource();
        ItemStack boots = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        // Cultists no longer harm other cultists and teammates.
        if (trueSource instanceof EntityCultist && trueSource != null) {
            if (entity.isOnSameTeam(trueSource)) {
                event.setCanceled(true);
            }
        }

        // Overgrown Taintacles are immune to arrows
        if (entity instanceof EntityOvergrownTaintacle) {
            if (damageSource.getImmediateSource() instanceof IProjectile && entity.getHealth() <= (entity.getMaxHealth() * CRConfig.overgrown_taintacle.projectileImmunityThreshold)) {
                event.setCanceled(true);
                entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.AMBIENT, 3.0F, 1.5F + entity.world.rand.nextFloat() / 2.0F);
                PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(entity.getEntityId(), damageSource.getImmediateSource().getEntityId()), new NetworkRegistry.TargetPoint(event.getEntity().world.provider.getDimension(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, 32.0D));
            }
        }

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            // Ring of Nutriment - Starvation Immunity
            if (BaublesApi.isBaubleEquipped(player, CRItems.NUTRITION_RING) >= 0) {

                // Prevents screen shaking and damage sound.
                if (event.getSource() == DamageSource.STARVE) {
                    event.setCanceled(true);
                }
            }
        }

        // Prevents screen shaking and damage sound.
        if (boots.getItem() instanceof CRItemCometBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((CRItemCometBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setCanceled(true);
            }
        } else if (boots.getItem() instanceof CRItemMeteorBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((CRItemMeteorBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        Entity trueSource = damageSource.getTrueSource();
        ItemStack boots = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        // Cultists no longer harm other cultists and teammates.
        if (trueSource instanceof EntityCultist && trueSource != null) {
            if (entity.isOnSameTeam(trueSource)) {
                event.setAmount(0.0F);
                event.setCanceled(true);
            }
        }

        // Overgrown Taintacles are immune to arrows
        if (entity instanceof EntityOvergrownTaintacle) {
            if (damageSource.getImmediateSource() instanceof IProjectile && entity.getHealth() <= (entity.getMaxHealth() * CRConfig.overgrown_taintacle.projectileImmunityThreshold)) {
                event.setAmount(0.0F);
                event.setCanceled(true);
            }
        }

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            // Ring of Nutriment - Starvation Immunity
            if (BaublesApi.isBaubleEquipped(player, CRItems.NUTRITION_RING) >= 0) {
                if (event.getSource() == DamageSource.STARVE) {
                    event.setAmount(0.0F);
                    event.setCanceled(true);
                }
            }
        }

        // Immune to these damage types.
        if (boots.getItem() instanceof CRItemCometBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setAmount(0);
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((CRItemCometBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setAmount(0);
                event.setCanceled(true);
            }
        } else if (boots.getItem() instanceof CRItemMeteorBoots) {
            if (event.getSource() == DamageSource.HOT_FLOOR) {
                event.setAmount(0);
                event.setCanceled(true);
            } else if (event.getSource() == DamageSource.FALL && ((CRItemMeteorBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount()) <= 0) {
                event.setAmount(0);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onDropsEvent(LivingDropsEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        Aspect[] aspects = Aspect.getPrimalAspects().toArray(new Aspect[0]);

        // Pechs drop primal mana beans.
        if (!entity.world.isRemote && CRConfig.mana_beans.pechLoot && entity instanceof EntityPech) {
            for (int i = 0; i < 1 + event.getLootingLevel(); ++i) {
                if (entity.getRNG().nextBoolean()) {
                    ItemStack is = new ItemStack(CRItems.MANA_BEAN);
                    ((CRItemManaBean) is.getItem()).setAspects(is, new AspectList().add(aspects[entity.getRNG().nextInt(aspects.length)], 1));
                    event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, is));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        World world = event.getEntity().world;
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        Entity trueSource = damageSource.getTrueSource();

        if (event.getSource() == DamageSource.FALL) {
            ItemStack boots = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (boots.getItem() instanceof CRItemCometBoots) {
                float damage = ((CRItemCometBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount());

                event.setAmount(damage);
                if (damage == 0) {
                    event.setCanceled(true);
                }
            } else if (boots.getItem() instanceof CRItemMeteorBoots) {
                float damage = ((CRItemMeteorBoots) boots.getItem()).getAdjustedFallDamage(boots, event.getAmount());

                event.setAmount(damage);
                if (damage == 0) {
                    event.setCanceled(true);
                }
            }
        }

        if (trueSource instanceof EntityLivingBase && trueSource != null) {
            Item heldItem = ((EntityLivingBase) trueSource).getHeldItemMainhand().getItem();

            if (heldItem == CRItems.CRIMSON_SWORD) {
                // Poison while cultist sword is equipped
                entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 6 * 20, 1));
            }
        }

        if (entity instanceof EntityPlayer && trueSource instanceof EntityLivingBase && !world.isRemote) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            int charge = CRRegistry.getRunicShielding(player);

            // Kinetic Girdle of Shielding - Explodes when the Runic Shielding is pierced (20 second cooldown).
            if (charge > 0) {
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_GIRDLE_KINETIC) > 2 && !(player.getCooldownTracker().hasCooldown(CRItems.RUNIC_GIRDLE_KINETIC))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_KINETIC, SoundCategory.PLAYERS, 0.8F, 1.0F);
                    player.world.createExplosion(player, player.posX, player.posY + player.height / 2.0F, player.posZ, 2.0F, false);

                    List<Entity> entities = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(3.0D, 3.0D, 3.0D));

                    for (Entity target : entities) {
                        if (target instanceof EntityLivingBase) {
                            EntityLivingBase mob = (EntityLivingBase) target;
                            mob.knockBack(player, 2.0F, player.posX - mob.posX, player.posZ - mob.posZ);
                        }
                    }

                    player.addStat(StatList.getObjectUseStats(CRItems.RUNIC_GIRDLE_KINETIC));
                    player.getCooldownTracker().setCooldown(CRItems.RUNIC_GIRDLE_KINETIC, 20 * 20);
                }

                // Revitalizing Ring of Shielding - Gives 6 seconds of Regeneration II when the Runic Shielding is pierced (20 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_RING_REGEN) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.RUNIC_RING_REGEN))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_REGEN, SoundCategory.PLAYERS, 1.5F, 1.0F);
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6 * 20, 1, true, true));

                    player.addStat(StatList.getObjectUseStats(CRItems.RUNIC_RING_REGEN));
                    player.getCooldownTracker().setCooldown(CRItems.RUNIC_RING_REGEN, 20 * 20);
                }

                // Amulet of Emergency Shielding - Gives 8 points of Absorption when the Runic Shielding is pierced (40 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_AMULET_EMERGENCY) > -1 && !(player.getCooldownTracker().hasCooldown(CRItems.RUNIC_AMULET_EMERGENCY))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_EMERGENCY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 40 * 20, 1, true, true));

                    player.addStat(StatList.getObjectUseStats(CRItems.RUNIC_AMULET_EMERGENCY));
                    player.getCooldownTracker().setCooldown(CRItems.RUNIC_AMULET_EMERGENCY, 40 * 20);
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
                if (BaublesApi.isBaubleEquipped(player, CRItems.NUTRITION_RING) >= 0) {
                    int random = player.world.rand.nextInt(4);
                    player.getFoodStats().addStats(1 + random, 0.5F);
                    player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1.0F, 0.5F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
        Item heldItem = event.getEntityPlayer().getHeldItemMainhand().getItem();
        BlockPos pos = event.getPos();

        if (heldItem == CRItems.EXECUTION_AXE) {
            World world = event.getEntityPlayer().world;
            double j = 1.3D;

            // Break Particles
            for (int i = 0; i < 2; i++) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D);
            }
        }

        // Pickaxe of Warped Distortion mines faster on harder blocks and mines slower on softer blocks
        if (heldItem == CRItems.DISTORTION_PICKAXE) {
            EntityPlayer player = event.getEntityPlayer();
            World world = player.world;
            BlockPos pos1;
            pos1 = pos.add(0, 0, 0);

            float hardness = world.getBlockState(pos1).getBlockHardness(world, pos1);
            double j = 1.2D;

            // Particles and sounds
            if (hardness < 5.0F) {
                for (int i = 0; i < 2; i++) {
                    FXDispatcher.INSTANCE.drawWispyMotes(pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2,
                            pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D, 10, 0.3F + world.rand.nextFloat() * 0.3F, 0.0F, 0.7F + world.rand.nextFloat() * 0.3F, -0.05F);
                }

                if (player.isSwingInProgress && player.ticksExisted % 5 == 0 && CRConfig.distortion_pickaxe.miningSounds) {
                    player.world.playSound(null, pos1.getX(), pos1.getY(), pos1.getZ(), CRSoundEvents.MISC_DISTORTION_PICKAXE_CLANK, SoundCategory.PLAYERS, 0.175F, 0.5F / (world.rand.nextFloat() * 0.4F + 0.8F));
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    FXDispatcher.INSTANCE.drawWispyMotes(pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2,
                            pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D, 10, 0.3F + world.rand.nextFloat() * 0.3F, 0.1F + world.rand.nextFloat() * 0.2F, 0.7F + world.rand.nextFloat() * 0.3F, -0.2F);
                }

                if (player.isSwingInProgress && player.ticksExisted % 5 == 0 && CRConfig.distortion_pickaxe.miningSounds) {
                    player.world.playSound(null, pos1.getX(), pos1.getY(), pos1.getZ(), CRSoundEvents.MISC_DISTORTION_PICKAXE_CLANK, SoundCategory.PLAYERS, 0.175F, 0.75F / (world.rand.nextFloat() * 0.4F + 0.8F));
                }
            }

            if (hardness == 0.0F) {
                event.setNewSpeed(0.0F);
            } else if (hardness < 5.0F) {
                event.setNewSpeed(0.1F);
            } else if (hardness < 20.0F) {
                event.setNewSpeed(hardness / 2.0F);
            } else {
                event.setNewSpeed(5.0F + hardness);
            }
        }

        if (heldItem == CRItems.PURIFYING_SHOVEL) {
            World world = event.getEntityPlayer().world;
            BlockPos pos1;
            pos1 = pos.add(0, 0, 0);
            double j = 1.2D;

            // Break Particles
            if (world.getBlockState(pos1).getMaterial() == ThaumcraftMaterials.MATERIAL_TAINT) {
                for (int i = 0; i < 2; i++) {
                    FXDispatcher.INSTANCE.drawWispyMotes(pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2,
                            pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D, 10, 0.4F + world.rand.nextFloat() * 0.3F, 0.2F + world.rand.nextFloat() * 0.2F, 0.5F + world.rand.nextFloat() * 0.3F, -0.2F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onUseBonemeal(BonemealEvent event) {
        // Prevent bonemeal from working on mana beans
        if (event.getBlock().getBlock() instanceof CRBlockManaPod) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onDecorateBiome(DecorateBiomeEvent event) {
        if (event.getWorld().getBiome(event.getPos()) instanceof BiomeGenMagicalForest) {
            WorldGenManaPods worldGenManaPods = new WorldGenManaPods();

            for (int k = 0; k < CRConfig.mana_beans.generationFrequency; k++) {
                int l = event.getPos().getX() + event.getWorld().rand.nextInt(16) + 8;
                byte b0 = 64;
                int i1 = event.getPos().getZ() + event.getWorld().rand.nextInt(16) + 8;
                worldGenManaPods.generate(event.getWorld(), event.getWorld().rand, new BlockPos(l, b0, i1));
            }
        }
    }
}
