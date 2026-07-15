package mod.icarus.crimsonrevelations.entity.projectile;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import org.jetbrains.annotations.NotNull;

public class EntityPrimalArrow extends EntityArrow implements IEntityAdditionalSpawnData {
    private static final DataParameter<Integer> ARROW_TYPE = EntityDataManager.createKey(EntityPrimalArrow.class, DataSerializers.VARINT);
    private Item item;

    private int knockbackStrength;
    public int ticksInGround = 0;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile = Blocks.AIR;
    private int inData = 0;

    public EntityPrimalArrow(World world) {
        super(world);
    }

    public EntityPrimalArrow(World world, double x, double y, double z, Item arrow) {
        super(world, x, y, z);
        this.item = arrow;
    }

    public EntityPrimalArrow(World world, EntityLivingBase shooter, Item arrow) {
        super(world, shooter);
        this.item = arrow;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ARROW_TYPE, 0);
    }

    public int getArrowType() {
        return this.dataManager.get(ARROW_TYPE);
    }

    public void setArrowType(int type) {
        this.dataManager.set(ARROW_TYPE, type);
    }

    @Override
    public void setKnockbackStrength(int knockbackStrength) {
        this.knockbackStrength = knockbackStrength;
    }

    @Override
    protected @NotNull ItemStack getArrowStack() {
        return new ItemStack(item, 1, 0);
    }

    @Override
    public void onUpdate() {
        if (!this.world.isRemote) {
            this.setFlag(6, this.isGlowing());
        }

        this.onEntityUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float hVelocity = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
            this.rotationPitch = (float) (MathHelper.atan2(this.motionY, hVelocity) * (180D / Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (iblockstate.getMaterial() != Material.AIR) {
            AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);
            if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            if ((block != this.inTile || block.getMetaFromState(iblockstate) != this.inData) && !this.world.collidesWithAnyBlock(this.getEntityBoundingBox().grow(0.05D))) {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksInGround = 0;
            } else {
                this.ticksInGround++;
                if (this.ticksInGround >= 1200) {
                    this.setDead();
                }
            }
            this.timeInGround++;
        } else {
            this.timeInGround = 0;
            Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
            vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (raytraceresult != null) {
                vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
            }

            Entity entity = this.findEntityOnPath(vec3d1, vec3d);

            if (entity != null) {
                raytraceresult = new RayTraceResult(entity);
            }

            if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;
                if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
                    raytraceresult = null;
                }
            }

            if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onHit(raytraceresult);
            }

            if (this.getIsCritical()) {
                for (int k = 0; k < 4; ++k) {
                    this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) k / 4.0D, this.posY + this.motionY * (double) k / 4.0D, this.posZ + this.motionZ * (double) k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;

            float f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
            this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f4) * (180D / Math.PI));

            while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
                this.prevRotationPitch -= 360.0F;
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float motionMultiplier = 0.99F;

            if (this.isInWater()) {
                for (int i = 0; i < 4; ++i) {
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }
                if (this.getArrowType() != 0 || this.getArrowType() != 1) {
                    // Motion change does not affect Water/Air Arrows
                    motionMultiplier = 0.6F;
                }
            }

            if (this.isWet()) {
                this.extinguish();
            }

            this.motionX *= motionMultiplier;
            this.motionY *= motionMultiplier;
            this.motionZ *= motionMultiplier;

            if (!this.hasNoGravity()) {
                this.motionY -= 0.05D;
            }

            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }

    @Override
    public void onEntityUpdate() {
        // Water Arrows are never set on fire
        if (this.getArrowType() == 1) {
            this.isImmuneToFire = true;
        }

        super.onEntityUpdate();
    }

    protected float computeTotalDamage() {
        float motionMagnitude = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        int baseDamage = MathHelper.ceil((double) motionMagnitude * this.getDamage());

        if (this.getIsCritical()) {
            baseDamage += this.rand.nextInt(baseDamage / 2 + 2);
        }
        double damage = baseDamage;
        switch (this.getArrowType()) {
            case 2:
                // More Damage - Fire
                damage = damage * 1.25D;
                break;
            case 3:
            case 4:
                // Less Damage - Entropy/Order
                damage = damage * 0.8D;
                break;
        }
        return (float) damage;
    }

    protected DamageSource getDamageSource() {
        Entity shooter = (this.shootingEntity == null) ? this : this.shootingEntity;
        DamageSource damageSource = new EntityDamageSourceIndirect("arrow", this, shooter);
        switch (this.getArrowType()) {
            case 2:
                // Fire Damage - Fire
                damageSource = damageSource.setProjectile().setFireDamage();
                break;
            case 5:
                // Armor Penetration - Earth
                damageSource = damageSource.setProjectile().setDamageBypassesArmor();
                break;
            default:
                // Default Damage - Air/Entropy/Earth
                damageSource = damageSource.setProjectile();
                break;
        }
        return damageSource;
    }

    protected int getFireDuration() {
        int duration = 0;
        // Water Arrows will never light targets on fire
        if (this.isBurning() && this.getArrowType() != 1) {
            duration += 5;
        }
        // Fire Arrows will always light targets on fire (fire enchantments will extend duration)
        if (this.getArrowType() == 2) {
            duration += 5;
        }
        return duration;
    }

    @Override
    protected void onHit(RayTraceResult raytraceResult) {
        Entity entity = raytraceResult.entityHit;

        if (entity != null) {
            int fireDuration = this.getFireDuration();
            if (fireDuration > 0 && !(entity instanceof EntityEnderman) && !entity.isOnSameTeam(this.shootingEntity)) {
                entity.setFire(fireDuration);
            }

            if (entity.attackEntityFrom(this.getDamageSource(), this.computeTotalDamage())) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
                    if (!this.world.isRemote) {
                        entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
                    }

                    if (this.knockbackStrength > 0 || this.getArrowType() == 0) {
                        float hVelocity = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                        // Aer Arrows apply extra knockback except we are also doing this check to account for Punch or anything else overriding the knockback strength
                        if (hVelocity > 0.0F && this.getArrowType() != 0) {
                            entitylivingbase.addVelocity(this.motionX * (double) this.knockbackStrength * 0.6D / (double) hVelocity, 0.1D, this.motionZ * (double) this.knockbackStrength * 0.6D / (double) hVelocity);
                        } else if (hVelocity > 0.0F && this.getArrowType() == 0) {
                            entitylivingbase.addVelocity(this.motionX * (1.0D + (double) this.knockbackStrength) * 0.6D / (double) hVelocity, 0.1D, this.motionZ * (1.0D + (double) this.knockbackStrength * 0.6D) / (double) hVelocity);
                        }
                    }

                    if (this.shootingEntity instanceof EntityLivingBase) {
                        EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity, entitylivingbase);
                    }

                    this.arrowHit(entitylivingbase);

                    if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                        ((EntityPlayerMP) this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                }

                this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                if (!(entity instanceof EntityEnderman)) {
                    this.setDead();
                }
            } else {
                this.motionX *= -0.1D;
                this.motionY *= -0.1D;
                this.motionZ *= -0.1D;
                this.rotationYaw += 180.0F;
                this.prevRotationYaw += 180.0F;
                if (!this.world.isRemote && this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ < 0.001D) {
                    if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED) {
                        this.entityDropItem(this.getArrowStack(), 0.1F);
                    }
                    this.setDead();
                }
            }
        } else {
            BlockPos blockpos = raytraceResult.getBlockPos();
            this.xTile = blockpos.getX();
            this.yTile = blockpos.getY();
            this.zTile = blockpos.getZ();
            IBlockState iblockstate = this.world.getBlockState(blockpos);
            this.inTile = iblockstate.getBlock();
            this.inData = this.inTile.getMetaFromState(iblockstate);
            this.motionX = (float) (raytraceResult.hitVec.x - this.posX);
            this.motionY = (float) (raytraceResult.hitVec.y - this.posY);
            this.motionZ = (float) (raytraceResult.hitVec.z - this.posZ);
            float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.posX -= this.motionX / (double) f2 * 0.05D;
            this.posY -= this.motionY / (double) f2 * 0.05D;
            this.posZ -= this.motionZ / (double) f2 * 0.05D;
            this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            this.inGround = true;
            this.arrowShake = 7;
            this.setIsCritical(false);

            if (iblockstate.getMaterial() != Material.AIR) {
                this.inTile.onEntityCollision(this.world, blockpos, iblockstate, this);
            }
        }
    }

    @Override
    protected void arrowHit(@NotNull EntityLivingBase living) {
        super.arrowHit(living);
        switch (this.getArrowType()) {
            case 1:
                // Slowness V Effect - Water (10 seconds)
                living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10 * 20, 4));
                break;
            case 3:
                // Weakness V Effect - Order (10 seconds)
                living.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 10 * 20, 4));
                break;
            case 4:
                // Wither III Effect - Entropy (10 seconds)
                living.addPotionEffect(new PotionEffect(MobEffects.WITHER, 10 * 20, 2));
                break;
        }
    }

    @Override
    public void writeEntityToNBT(@NotNull NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("arrowType", getArrowType());
    }

    @Override
    public void readEntityFromNBT(@NotNull NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setArrowType(compound.getInteger("arrowType"));
    }

    // Fixes buggy projectile behavior on the client
    @Override
    public void writeSpawnData(ByteBuf data) {
        data.writeInt(shootingEntity != null ? shootingEntity.getEntityId() : -1);
    }

    @Override
    public void readSpawnData(ByteBuf data) {
        final Entity shooter = world.getEntityByID(data.readInt());

        if (shooter instanceof EntityLivingBase) {
            this.shootingEntity = shooter;
        }
    }
}
