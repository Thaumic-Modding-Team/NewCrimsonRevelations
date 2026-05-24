package mod.icarus.crimsonrevelations.entity.boss;

import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.entity.EntityItemImportant;
import mod.icarus.crimsonrevelations.registry.ModLootTablesNCR;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.entities.monster.tainted.EntityTaintacle;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.utils.EntityUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityOvergrownTaintacle extends EntityTaintacle implements ITaintedMob {
    protected final BossInfoServer bossInfo;

    public EntityOvergrownTaintacle(World world) {
        super(world);
        this.bossInfo = new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS);
        this.setSize(1.0F, 5.5F);
        this.experienceValue = 50;
        this.isImmuneToFire = true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void removeTrackingPlayer(@Nonnull EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        if (ConfigHandlerNCR.overgrown_taintacle.bossBar) this.bossInfo.removePlayer(player);
    }

    @Override
    public void addTrackingPlayer(@Nonnull EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        if (ConfigHandlerNCR.overgrown_taintacle.bossBar) this.bossInfo.addPlayer(player);
    }

    // Fixes it spawning with lower health
    protected void makeChampion() {
        if (getEntityAttribute(ThaumcraftApiHelper.CHAMPION_MOD).getAttributeValue() > -2.0) return;

        int type = rand.nextInt(ChampionModifier.mods.length);
        IAttributeInstance mod = getEntityAttribute(ThaumcraftApiHelper.CHAMPION_MOD);
        mod.removeModifier(ChampionModifier.mods[type].attributeMod);
        mod.applyModifier(ChampionModifier.mods[type].attributeMod);

        IAttributeInstance health = getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        health.removeModifier(EntityUtils.CHAMPION_HEALTH);
        health.applyModifier(EntityUtils.CHAMPION_HEALTH);

        IAttributeInstance attackDamage = getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        attackDamage.removeModifier(EntityUtils.CHAMPION_DAMAGE);
        attackDamage.applyModifier(EntityUtils.CHAMPION_DAMAGE);

        setHealth(getMaxHealth());
        setCustomNameTag(ChampionModifier.mods[type].getModNameLocalized() + " " + getName());
        enablePersistence();

        switch (type) {
            // Bold
            case 0: {
                IAttributeInstance movementSpeed = getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                movementSpeed.removeModifier(EntityUtils.BOLDBUFF);
                movementSpeed.applyModifier(EntityUtils.BOLDBUFF);
                break;
            }

            // Mighty
            case 3: {
                attackDamage.removeModifier(EntityUtils.MIGHTYBUFF);
                attackDamage.applyModifier(EntityUtils.MIGHTYBUFF);
                break;
            }

            // Warded
            case 5: {
                int warding = (int) (getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2.0);
                setAbsorptionAmount(getAbsorptionAmount() + warding);
                break;
            }

            default:
                break;
        }
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    @Override
    public void setInWeb() {
    }

    @Override
    public IEntityLivingData onInitialSpawn(@Nonnull DifficultyInstance diff, @Nullable IEntityLivingData data) {
        this.makeChampion();
        this.bossInfo.setName(this.getDisplayName());
        return super.onInitialSpawn(diff, data);
    }

    @Override
    public void setCustomNameTag(@Nonnull String name) {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    // Immune to all effects like the Ender Dragon and the Wither
    @Override
    public boolean isPotionApplicable(@Nonnull PotionEffect effect) {
        return false;
    }

    // Special taint death animation
    @Override
    protected void onDeathUpdate() {
        if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot"))) {
            int i = this.getExperiencePoints(this.attackingPlayer);
            i = ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);

            while (i > 0) {
                int j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
            }
        }

        this.setDead();
        this.playSound(SoundsTC.gore, 1.0F, 0.65F / (rand.nextFloat() * 0.4F + 0.8F));

        if (FMLLaunchHandler.side().isClient() && this.world.isRemote) {
            for (int k = 0; k < 40; ++k) {
                FXDispatcher.INSTANCE.splooshFX(this);
                FXDispatcher.INSTANCE.taintsplosionFX(this);
            }
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return ModLootTablesNCR.OVERGROWN_TAINTACLE;
    }

    @Override
    @Nullable
    public EntityItem entityDropItem(ItemStack stack, float offsetY) {
        if (stack.isEmpty()) {
            return null;
        } else {
            EntityItem entity = null;
            if (stack.getItem() == ItemsTC.primordialPearl) {
                entity = new EntityItemImportant(world, posX, posY + offsetY, posZ, stack);
                entity.motionX = 0.0;
                entity.motionY = 0.1;
                entity.motionZ = 0.0;
            } else {
                entity = new EntityItem(world, posX, posY + offsetY, posZ, stack);
            }

            entity.setDefaultPickupDelay();
            entity.setNoDespawn();
            if (captureDrops) {
                capturedDrops.add(entity);
            } else {
                world.spawnEntity(entity);
            }

            return entity;
        }
    }
}
