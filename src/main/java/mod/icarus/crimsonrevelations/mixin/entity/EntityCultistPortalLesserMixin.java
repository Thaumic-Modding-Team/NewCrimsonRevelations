package mod.icarus.crimsonrevelations.mixin.entity;

import mod.icarus.crimsonrevelations.entity.EntityCultistArcher;
import mod.icarus.crimsonrevelations.registry.ModLootTablesNCR;
import mod.icarus.crimsonrevelations.network.PacketHandler;
import mod.icarus.crimsonrevelations.network.packets.PacketFXCultistPortal;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
import thaumcraft.common.lib.SoundsTC;

import javax.annotation.Nullable;

@Mixin(value = EntityCultistPortalLesser.class, remap = false)
public class EntityCultistPortalLesserMixin extends EntityMob {
    public EntityCultistPortalLesserMixin(World world) {
        super(world);
    }

    @Override
    protected int getExperiencePoints(@NotNull EntityPlayer player) {
        return 40;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return ModLootTablesNCR.LESSER_CULTIST_PORTAL;
    }

    @Redirect(method = "onUpdate", at = @At(value = "INVOKE",
            target = "Lthaumcraft/common/entities/monster/cult/EntityCultistPortalLesser;spawnMinions()V",
            remap = false), remap = true)
    private void spawnMinionsRedirect(EntityCultistPortalLesser instance) {
        cr$spawnCultists();
    }

    @Unique
    void cr$spawnCultists() {
        EntityLiving cultist;

        switch (this.world.rand.nextInt(4)) {
            case 0:
                cultist = new EntityCultistCleric(this.world);
                break;
            case 1:
                cultist = new EntityCultistArcher(this.world);
                break;
            case 2:
            default:
                cultist = new EntityCultistKnight(this.world);
                break;
        }

        cultist.setPosition(this.posX + this.rand.nextFloat() - this.rand.nextFloat(), this.posY + 0.25, this.posZ + this.rand.nextFloat() - this.rand.nextFloat());
        cultist.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(cultist.getPosition())), null);

        // Restores portal spawning particles from TC5
        PacketHandler.INSTANCE.sendToAll(new PacketFXCultistPortal(cultist.posX, cultist.posY, cultist.posZ, cultist.width, cultist.height));

        this.world.spawnEntity(cultist);
        cultist.playSound(SoundsTC.wandfail, 1.0F, 1.0F);
        this.attackEntityFrom(DamageSource.OUT_OF_WORLD, (5 + this.rand.nextInt(5)));
    }
}
