package mod.icarus.crimsonrevelations.entity;

import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.lib.SoundsTC;

import javax.annotation.Nullable;

public class EntityCultistArcher extends EntityCultist implements IRangedAttackMob {
    private static final DataParameter<Integer> ARROW_TYPE;
    private static final DataParameter<Boolean> SWINGING_ARMS;

    static {
        ARROW_TYPE = EntityDataManager.createKey(EntityCultistArcher.class, DataSerializers.VARINT);
        SWINGING_ARMS = EntityDataManager.createKey(EntityCultistArcher.class, DataSerializers.BOOLEAN);
    }

    private final EntityAIAttackRangedBow<EntityCultistArcher> aiArrowAttack = new EntityAIAttackRangedBow<>(this, 1.0F, 20, 15.0F);
    private final EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.0, false) {
        @Override
        public void resetTask() {
            super.resetTask();
            EntityCultistArcher.this.setSwingingArms(false);
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            EntityCultistArcher.this.setSwingingArms(true);
        }
    };

    public EntityCultistArcher(World world) {
        super(world);
        this.setCombatTask();
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
        this.tasks.addTask(7, new EntityAIWander(this, 0.8));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new AICultistHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityEldritchGuardian.class, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, AbstractIllager.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ARROW_TYPE, 0);
        this.dataManager.register(SWINGING_ARMS, false);
    }

    @Override
    protected void setLoot(DifficultyInstance diff) {
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItemsNCR.CRIMSON_ARCHER_HELMET));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItemsNCR.CRIMSON_ARCHER_CHESTPLATE));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItemsNCR.CRIMSON_ARCHER_LEGGINGS));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
        this.setHeldItem(this.getActiveHand(), new ItemStack(Items.BOW));
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data) {
        data = super.onInitialSpawn(difficulty, data);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCombatTask();

        if (!this.world.isRemote) {
            this.setArrowType(this.rand.nextInt(6));
        }

        return data;
    }

    @Override
    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance diff) {
        final float f = diff.getClampedAdditionalDifficulty();
        if (this.getHeldItemMainhand() != null && !this.getHeldItemMainhand().isEmpty() && this.rand.nextFloat() < 0.25F * f) {
            EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItemMainhand(), (int) (5.0F + f * this.rand.nextInt(18)), false);
        }
    }

    public void setCombatTask() {
        if (this.world != null && !this.world.isRemote) {
            this.tasks.removeTask(this.aiAttackOnCollide);
            this.tasks.removeTask(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItemMainhand();
            if (itemstack.getItem() instanceof ItemBow) {
                int i = 20;

                if (this.world.getDifficulty() != EnumDifficulty.HARD) {
                    i = 40;
                }

                this.aiArrowAttack.setAttackCooldown(i);
                this.tasks.addTask(4, this.aiArrowAttack);
            } else {
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(@NotNull EntityLivingBase target, float distanceFactor) {
        EntityArrow entityarrow = this.getArrow(distanceFactor);

        if (this.getHeldItemMainhand().getItem() instanceof ItemBow) {
            entityarrow = ((ItemBow) this.getHeldItemMainhand().getItem()).customizeArrow(entityarrow);
        }

        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.2F, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }

    protected EntityArrow getArrow(float distanceFactor) {
        int type = this.getArrowType();
        Item arrowItem = getArrowItemFromType(type);
        EntityPrimalArrow primalArrow = new EntityPrimalArrow(this.world, this, arrowItem);
        primalArrow.setArrowType(type);
        primalArrow.setEnchantmentEffectsFromEntity(this, distanceFactor);
        this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsTC.hhoff, this.getSoundCategory(), 0.6F, 0.8F / (this.rand.nextFloat() * 0.4F + 0.8F));
        return primalArrow;
    }

    private Item getArrowItemFromType(int type) {
        switch (type) {
            case 1:
                return ModItemsNCR.AQUA_ARROW;
            case 2:
                return ModItemsNCR.IGNIS_ARROW;
            case 3:
                return ModItemsNCR.ORDO_ARROW;
            case 4:
                return ModItemsNCR.PERDITIO_ARROW;
            case 5:
                return ModItemsNCR.TERRA_ARROW;
            case 0:
            default:
                return ModItemsNCR.AER_ARROW;
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("ArrowType", this.getArrowType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("ArrowType", 99)) {
            this.setArrowType(compound.getInteger("ArrowType"));
        }
        this.setCombatTask();
    }

    @Override
    public void setItemStackToSlot(@NotNull EntityEquipmentSlot slot, @NotNull ItemStack stack) {
        super.setItemStackToSlot(slot, stack);

        if (!this.world.isRemote && slot == EntityEquipmentSlot.MAINHAND) {
            this.setCombatTask();
        }
    }

    public int getArrowType() {
        return this.dataManager.get(ARROW_TYPE);
    }

    public void setArrowType(int type) {
        this.dataManager.set(ARROW_TYPE, type);
    }

    public boolean isSwingingArms() {
        return this.dataManager.get(SWINGING_ARMS);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, swingingArms);
    }
}
