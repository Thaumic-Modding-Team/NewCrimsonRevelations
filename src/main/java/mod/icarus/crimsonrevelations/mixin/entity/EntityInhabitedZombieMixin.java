package mod.icarus.crimsonrevelations.mixin.entity;

import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;

@Mixin(value = EntityInhabitedZombie.class, remap = false)
public class EntityInhabitedZombieMixin extends EntityZombie {
    public EntityInhabitedZombieMixin(World world) {
        super(world);
    }

    // Replaces the original equip event with ours, no more fake armor
    @Inject(method = "onInitialSpawn", at = @At(value = "HEAD"), cancellable = true)
    public IEntityLivingData crInitialSpawn(DifficultyInstance diff, IEntityLivingData data, CallbackInfoReturnable<EntityInhabitedZombie> ci) {
        ci.cancel();
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItemsNCR.ANCIENT_CRIMSON_HELMET));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItemsNCR.ANCIENT_CRIMSON_CHESTPLATE));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItemsNCR.ANCIENT_CRIMSON_LEGGINGS));

        return super.onInitialSpawn(diff, data);
    }
}
