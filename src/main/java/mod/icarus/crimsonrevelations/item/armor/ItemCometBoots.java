package mod.icarus.crimsonrevelations.item.armor;

import com.invadermonky.thaumicapi.handlers.PlayerMovementAbilityHandler;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.registry.ModMaterialsNCR;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.Constants.NBT;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.api.items.RechargeHelper;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ItemCometBoots extends ItemArmor implements ISpecialArmor, IRechargable, IVisDiscountGear {
    protected static final String TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/comet_boots.png").toString();

    // Calculate attribute bonuses.    
    protected static final BiFunction<EntityPlayer, PlayerMovementAbilityHandler.MovementType, Float> MOVEMENT_FUNC = (player, type) -> {
        float boost = 0;
        switch (type) {
            case DRY_GROUND:
                boost = (float) ConfigHandlerNCR.comet_boots.landSpeed;
                return player.isSneaking() ? boost / 4.0F : boost;
            case WATER_GROUND:
                boost = Math.max((float) ConfigHandlerNCR.comet_boots.landSpeed / (float) ConfigHandlerNCR.comet_boots.sneakReduction,
                        (float) ConfigHandlerNCR.comet_boots.waterSpeedBoost);
                return player.isSneaking() ? boost / (float) ConfigHandlerNCR.comet_boots.sneakReduction : boost;
            case WATER_SWIM:
                boost = (float) ConfigHandlerNCR.comet_boots.waterSpeedBoost;
                return player.isSneaking() ? boost / (float) ConfigHandlerNCR.comet_boots.sneakReduction : boost;
            case JUMP_BEGIN:
                return (float) ConfigHandlerNCR.comet_boots.jumpBoost;
            case JUMP_FACTOR:
                return (float) ConfigHandlerNCR.comet_boots.jumpFactor;
            case STEP_HEIGHT:
                return !player.isSneaking() ? (float) ConfigHandlerNCR.comet_boots.stepHeight : 0.0F;
            default:
                return boost;
        }
    };

    protected static final Predicate<EntityPlayer> CONTINUE_FUNC = player ->
            player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemCometBoots;

    public ItemCometBoots(String unlocName) {
        super(ModMaterialsNCR.BOOTS_COMET, 4, EntityEquipmentSlot.FEET);
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @NotNull ItemStack stack, DamageSource source, int damage, int slot) {
        if (source != DamageSource.FALL || source != DamageSource.HOT_FLOOR || source != DamageSource.IN_FIRE
                || source != DamageSource.ON_FIRE) {
            stack.damageItem(damage, entity);
        }
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player) {
        return 3;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, @NotNull ItemStack stack, int slot) {
        return 0;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 0.0, armor.getMaxDamage() - armor.getItemDamage());
    }

    @Override
    public String getArmorTexture(@NotNull ItemStack stack, @NotNull Entity entity, @NotNull EntityEquipmentSlot slot, @NotNull String type) {
        return TEXTURE_PATH;
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> list) {
        if (tab == NewCrimsonRevelations.tabCR || tab == CreativeTabs.SEARCH) {
            ItemStack base = new ItemStack(this, 1, 0);
            list.add(base);
            ItemStack charged = base.copy();
            RechargeHelper.rechargeItemBlindly(charged, null, getMaxCharge(charged, null));
            list.add(charged);
        }
    }

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    public void onArmorTick(@NotNull World world, EntityPlayer player, @NotNull ItemStack stack) {
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(player.motionY);

        // Built-in Frost Walker effect, Frost Walker enchantment will improve the effect
        if (RechargeHelper.getCharge(stack) > 0) {
            if (!player.world.isRemote) {
                boolean isLastOnGround = player.onGround;

                player.onGround = true;
                EnchantmentFrostWalker.freezeNearby(player, player.world, new BlockPos(player), 3 + EnchantmentHelper.getEnchantmentLevel(Enchantments.FROST_WALKER, boots));
                player.onGround = isLastOnGround;
            }
        }

        // Particles when sprinting or jumping
        if (!world.isRemote) {
            if (!player.isInWater() && (motion > 0.1F || player.isSprinting())) {
                ((WorldServer) world).spawnParticle(EnumParticleTypes.END_ROD, player.posX + Math.random() - 0.5F,
                        player.getEntityBoundingBox().minY + 0.25F + ((Math.random() - 0.5) * 0.25F), player.posZ + Math.random() - 0.5F, 1, 0.0D, 0.025D, 0.0D, 0.0D);
            }
        }

        // Speed and jump height boost.
        if (!world.isRemote && player.ticksExisted % 20 == 0) {
            int current = 0;
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("energyRemaining", NBT.TAG_INT)) {
                current = stack.getTagCompound().getInteger("energyRemaining");
            } else if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }

            if (current > 0) {
                --current;
            }

            if (current <= 0 && RechargeHelper.consumeCharge(stack, player, 2)) {
                current = 60;
            }

            stack.getTagCompound().setInteger("energyRemaining", current);
        }

        if (PlayerMovementAbilityHandler.isValidSideForMovement(player)) {
            boolean apply = !player.capabilities.isFlying && !player.isElytraFlying() && RechargeHelper.getCharge(stack) > 0;

            if (apply && !PlayerMovementAbilityHandler.playerHasAbility(player, MOVEMENT_FUNC, CONTINUE_FUNC)) {
                PlayerMovementAbilityHandler.put(player, MOVEMENT_FUNC, CONTINUE_FUNC);
            } else if (!apply && PlayerMovementAbilityHandler.playerHasAbility(player, MOVEMENT_FUNC, CONTINUE_FUNC)) {
                PlayerMovementAbilityHandler.remove(player, MOVEMENT_FUNC, CONTINUE_FUNC);
            }
        }
    }

    public int getMaxCharge(ItemStack stack, EntityLivingBase player) {
        return 330;
    }

    public EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase player) {
        return EnumChargeDisplay.PERIODIC;
    }

    public float getAdjustedFallDamage(ItemStack stack, float damage) {
        if (stack.getItem() == ModItemsNCR.COMET_BOOTS && RechargeHelper.getCharge(stack) > 0) {
            damage = Math.max(0, damage / 5.0F - 1.0F);
        }
        return damage;
    }
}
