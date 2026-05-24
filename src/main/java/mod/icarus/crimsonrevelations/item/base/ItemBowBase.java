package mod.icarus.crimsonrevelations.item.base;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ItemBowBase extends ItemBow {
    public float damageMult;
    public float velocityMult;
    public float inaccuracy;
    public float drawTimeMult;
    public IRarity rarity;
    public Ingredient repairMaterial;

    public ItemBowBase(String unlocName, int durability, float damageMult, float velocityMult, float drawTimeMult, float inaccuracy, Ingredient repairMaterial) {
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
        this.maxStackSize = 1;
        this.setMaxDamage(durability);
        this.damageMult = damageMult;
        this.velocityMult = velocityMult;
        this.drawTimeMult = drawTimeMult;
        this.inaccuracy = inaccuracy;
        this.repairMaterial = repairMaterial;
        this.setRarity(EnumRarity.COMMON);
        this.addPropertyOverride(new ResourceLocation("pull"), (ItemStack bow, World world, EntityLivingBase entity) -> {
            if (entity == null) {
                return 0.0F;
            }

            float drawTime = 20.0F * drawTimeMult;

            return (this.getMaxItemUseDuration(bow) - entity.getItemInUseCount()) / drawTime;
        });
    }

    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull EntityLivingBase entity, int timeInUse) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            boolean isInfinityEnchant = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack ammo = this.findAmmo(player);

            float chargeDivider = 1 * drawTimeMult;

            int charge = (int) ((this.getMaxItemUseDuration(stack) - timeInUse) / chargeDivider);
            charge = ForgeEventFactory.onArrowLoose(stack, world, player, charge, !stack.isEmpty() || isInfinityEnchant);
            if (charge < 0) return;

            if ((!ammo.isEmpty() || isInfinityEnchant)) {
                if (ammo.isEmpty()) {
                    ammo = new ItemStack(Items.ARROW);
                }

                float arrowVelocity = getArrowVelocity(charge);

                if ((double) arrowVelocity >= 0.1D) {
                    boolean arrowInfinite = player.capabilities.isCreativeMode || (ammo.getItem() instanceof ItemArrow && ((ItemArrow) ammo.getItem()).isInfinite(ammo, stack, player));

                    if (!world.isRemote) {
                        ItemArrow itemArrow = (ItemArrow) (ammo.getItem() instanceof ItemArrow ? ammo.getItem() : Items.ARROW);
                        EntityArrow entityArrow = itemArrow.createArrow(world, ammo, player);
                        entityArrow = this.customizeArrow(entityArrow);
                        entityArrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, (arrowVelocity * 3.0F) * velocityMult, inaccuracy);

                        if (arrowVelocity == 1.0F) {
                            entityArrow.setIsCritical(true);
                        }

                        entityArrow.setDamage(entityArrow.getDamage() * damageMult);

                        int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (power > 0) {
                            entityArrow.setDamage(entityArrow.getDamage() + (double) power * 0.5D + 0.5D);
                        }

                        int punch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (punch > 0) {
                            entityArrow.setKnockbackStrength(punch);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            entityArrow.setFire(100);
                        }

                        stack.damageItem(1, player);

                        if (arrowInfinite || player.capabilities.isCreativeMode && (ammo.getItem() == Items.SPECTRAL_ARROW || ammo.getItem() == Items.TIPPED_ARROW)) {
                            entityArrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }

                        world.spawnEntity(entityArrow);
                    }

                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

                    if (!arrowInfinite && !player.capabilities.isCreativeMode) {
                        stack.shrink(1);

                        if (stack.isEmpty()) {
                            player.inventory.deleteStack(ammo);
                        }
                    }

                    player.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
                }
            }
        }
    }

    public Item setRarity(@Nonnull IRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public IRarity getForgeRarity(@Nonnull ItemStack stack) {
        return this.rarity;
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair) {
        return repairMaterial.test(repair) || super.getIsRepairable(toRepair, repair);
    }
}
