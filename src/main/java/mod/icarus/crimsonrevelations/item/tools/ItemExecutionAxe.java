package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.enchants.InfusionEnchantments;
import mod.icarus.crimsonrevelations.item.base.ItemAxeBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

import javax.annotation.Nonnull;

public class ItemExecutionAxe extends ItemAxeBase {
    public ItemExecutionAxe() {
        super(ThaumcraftMaterials.TOOLMAT_ELEMENTAL, 10.0F, 1.2F);
    }

    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull IBlockState state, @Nonnull BlockPos pos, @Nonnull EntityLivingBase entity) {
        if (entity instanceof EntityPlayer && state.getBlock().canHarvestBlock(world, pos, (EntityPlayer) entity)) {
            double j = 1.2D;

            for (int i = 0; i < 16; i++) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D);
            }

            entity.playSound(SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, 1.0F, 1.0F);
        }

        return super.onBlockDestroyed(stack, world, state, pos, entity);
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        int timer = 10; // In seconds
        int fire_aspect = 5 * EnchantmentHelper.getFireAspectModifier(attacker); // Fire Aspect

        target.setFire(timer + fire_aspect);
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(Items.BLAZE_POWDER)) || super.getIsRepairable(stack1, stack2);
    }


    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> list) {
        if (this.isInCreativeTab(tab)) {
            ItemStack stack = new ItemStack(this);

            EnumInfusionEnchantment.addInfusionEnchantment(stack, InfusionEnchantments.BEHEADING, 2);
            list.add(stack);
        }
    }
}
