package mod.icarus.crimsonrevelations.events.tool;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class ExecutionAxeEvent {
    @SubscribeEvent
    public static void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        // Axe of Execution - Smelts wood chopped, also works with Fortune and gives experience.
        if (event.getHarvester() != null && (event.getHarvester().getHeldItemMainhand().getItem() == ModItemsNCR.EXECUTION_AXE)) {
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
    }

    @SubscribeEvent
    public static void onPlayerBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
        Item heldItem = event.getEntityPlayer().getHeldItemMainhand().getItem();
        BlockPos pos = event.getPos();

        if (heldItem == ModItemsNCR.EXECUTION_AXE) {
            World world = event.getEntityPlayer().world;
            double j = 1.3D;

            // Break Particles
            for (int i = 0; i < 2; i++) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
