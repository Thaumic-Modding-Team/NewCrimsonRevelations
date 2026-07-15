package mod.icarus.crimsonrevelations.item.armor;

import com.invadermonky.thaumicapi.handlers.PlayerMovementAbilityHandler;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.registry.ModMaterialsNCR;
import mod.icarus.crimsonrevelations.registry.ModRaritiesNCR;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
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
import thaumcraft.common.lib.SoundsTC;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ItemMeteorBoots extends ItemArmor implements ISpecialArmor, IRechargable, IVisDiscountGear {
    protected static final String TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/meteor_boots.png").toString();

    // Calculate attribute bonuses.    
    protected static final BiFunction<EntityPlayer, PlayerMovementAbilityHandler.MovementType, Float> MOVEMENT_FUNC = (player, type) -> {
        float boost = 0;
        switch (type) {
            case DRY_GROUND:
                boost = (float) ConfigHandlerNCR.meteor_boots.landSpeed;
                return player.isSneaking() ? boost / 4.0F : boost;
            case WATER_GROUND:
                boost = Math.max((float) ConfigHandlerNCR.meteor_boots.landSpeed / (float) ConfigHandlerNCR.meteor_boots.sneakReduction,
                        (float) ConfigHandlerNCR.meteor_boots.waterSpeedBoost);
                return player.isSneaking() ? boost / (float) ConfigHandlerNCR.meteor_boots.sneakReduction : boost;
            case WATER_SWIM:
                boost = (float) ConfigHandlerNCR.meteor_boots.waterSpeedBoost;
                return player.isSneaking() ? boost / (float) ConfigHandlerNCR.meteor_boots.sneakReduction : boost;
            case JUMP_BEGIN:
                return (float) ConfigHandlerNCR.meteor_boots.jumpBoost;
            case JUMP_FACTOR:
                return (float) ConfigHandlerNCR.meteor_boots.jumpFactor;
            case STEP_HEIGHT:
                return !player.isSneaking() ? (float) ConfigHandlerNCR.meteor_boots.stepHeight : 0.0F;
            default:
                return boost;
        }
    };

    protected static final Predicate<EntityPlayer> CONTINUE_FUNC = player ->
            player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemMeteorBoots;

    public ItemMeteorBoots(String unlocName) {
        super(ModMaterialsNCR.BOOTS_METEOR, 4, EntityEquipmentSlot.FEET);
        this.setRegistryName(NewCrimsonRevelations.MODID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(NewCrimsonRevelations.tabCR);
    }

    public static boolean getSmashingState(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("isSmashing");
    }

    public static void setSmashingState(ItemStack stack, boolean flag) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setBoolean("isSmashing", flag);
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
        return ModRaritiesNCR.RARITY_FIREY;
    }

    @Override
    public void onArmorTick(@NotNull World world, EntityPlayer player, @NotNull ItemStack stack) {
        boolean hasCharge = RechargeHelper.getCharge(stack) > 1;
        double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(player.motionY);
        BlockPos playerPos = new BlockPos(player.posX, player.posY, player.posZ);
        boolean isActuallyInAir = world.isAirBlock(playerPos.down()) && world.isAirBlock(playerPos.down().down());

        // Activate smash state once the sneak key is pressed. Do not activate while flying.
        if (player.isSneaking() && !player.capabilities.isFlying && !player.isElytraFlying() && !player.onGround && isActuallyInAir && player.fallDistance > 0.5F && hasCharge) {
            if (!getSmashingState(stack) && !(player.getCooldownTracker().hasCooldown(ModItemsNCR.METEOR_BOOTS))) {
                setSmashingState(stack, true);
                player.playSound(SoundsTC.rumble, 1.0F, 0.8F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
                if (!player.capabilities.isCreativeMode) RechargeHelper.consumeCharge(stack, player, 2);
            }
        }

        // Fall faster during smash state but pause it while flying.
        if (getSmashingState(stack) && !player.capabilities.isFlying && !player.isElytraFlying() && hasCharge) {
            player.motionY -= 0.4D;

            float currentFall = player.fallDistance;
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }

            float maxFall = stack.getTagCompound().getFloat("smashFallDistance");
            if (currentFall > maxFall) {
                stack.getTagCompound().setFloat("smashFallDistance", currentFall);
            }

            if (player.ticksExisted % 4 == 0) {
                player.playSound(SoundsTC.rumble, 0.4F, 3.0F);
            }

            if (!world.isRemote) {
                ((WorldServer) world).spawnParticle(EnumParticleTypes.FLAME, player.posX + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F,
                        player.posY - 1.5D, player.posZ + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, 10, 0.0D, 0.0D, 0.0D, 0.2D);
            }

            // Explode on ground impact and reset smash state.
            if (player.onGround) {
                float fallDist = stack.hasTagCompound() ? stack.getTagCompound().getFloat("smashFallDistance") : 0.0F;
                if (fallDist <= 0.0F) {
                    fallDist = player.fallDistance;
                }

                if (stack.hasTagCompound()) {
                    stack.getTagCompound().removeTag("smashFallDistance");
                }

                int radius = Math.min(8, 4 + (int) (fallDist / 5.0F));
                float damage = 12.0F + (fallDist * 3.0F) + (float) world.rand.nextInt(6);

                AxisAlignedBB area = new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius);
                setSmashingState(stack, false);

                for (EntityLivingBase nearbyLivingEntity : world.getEntitiesWithinAABB(EntityLivingBase.class, area, EntitySelectors.IS_ALIVE)) {
                    if (nearbyLivingEntity != player && !nearbyLivingEntity.isOnSameTeam(player)) {
                        nearbyLivingEntity.setFire(5 + (int) (fallDist / 3.0F) + (world.rand.nextInt(5)));
                        float knockback = 1.0F + (fallDist * 0.05F);
                        nearbyLivingEntity.knockBack(player, knockback, player.posX - nearbyLivingEntity.posX, player.posZ - nearbyLivingEntity.posZ);
                        nearbyLivingEntity.attackEntityFrom(DamageSource.ON_FIRE, damage);
                    }
                }

                if (!world.isRemote) {
                    int particleAmount = Math.min(80, 40 + (int) (fallDist * 3.0F));
                    double particleDistance = 2.0D + (fallDist * 0.1D);

                    BlockPos landPos = new BlockPos(player.posX, player.posY, player.posZ).down();
                    IBlockState state = world.getBlockState(landPos);
                    if (state.getBlock().isAir(state, world, landPos)) {
                        state = world.getBlockState(landPos.down());
                    }
                    if (state.getBlock().isAir(state, world, landPos.down())) {
                        state = Blocks.STONE.getDefaultState();
                    }
                    int blockId = Block.getStateId(state);

                    ((WorldServer) world).spawnParticle(EnumParticleTypes.LAVA, player.posX, player.posY, player.posZ, particleAmount, particleDistance, 0.0D, particleDistance, 0.0D);
                    ((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY, player.posZ, particleAmount, particleDistance, 0.0D, particleDistance, 0.15D);
                    ((WorldServer) world).spawnParticle(EnumParticleTypes.BLOCK_DUST, player.posX, player.posY, player.posZ, particleAmount * 2, particleDistance, 0.0D, particleDistance, 1.0D, blockId);
                }

                float soundVolume = Math.min(8.0F, 4.0F + (fallDist * 0.1F));
                player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, soundVolume, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
                player.getCooldownTracker().setCooldown(ModItemsNCR.METEOR_BOOTS, 5 * 20);
            }
        }

        // Particles when sprinting or jumping
        if (!world.isRemote) {
            if (!player.isInWater() && (motion > 0.1F || player.isSprinting())) {
                ((WorldServer) world).spawnParticle(EnumParticleTypes.FLAME, player.posX + Math.random() - 0.5F,
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

    public float getAdjustedFallDamage(ItemStack bootStack, float damage) {
        if (bootStack.getItem() == ModItemsNCR.METEOR_BOOTS && RechargeHelper.getCharge(bootStack) > 0) {
            damage = Math.max(0, damage / 5.0F - 1.0F);
        }
        return damage;
    }
}
