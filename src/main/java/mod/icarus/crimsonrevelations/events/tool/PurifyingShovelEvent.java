package mod.icarus.crimsonrevelations.events.tool;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.FXDispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class PurifyingShovelEvent {
    @SubscribeEvent
    public static void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        // Shovel of the Purifier - Has a chance to convert harvested tainted blocks to flux crystals
        if (event.getHarvester() != null && (event.getHarvester().getHeldItemMainhand().getItem() == ModItemsNCR.PURIFYING_SHOVEL)) {
            if (event.getState().getBlock().canHarvestBlock(event.getWorld(), event.getPos(), event.getHarvester())) {
                List<ItemStack> to_be_removed = new ArrayList<ItemStack>();
                List<ItemStack> to_be_added = new ArrayList<ItemStack>();

                if (event.getWorld().rand.nextDouble() <= 0.1D) {
                    to_be_added.add(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX, 1 + new Random().nextInt(2 + event.getFortuneLevel() - 1)));

                    // If the tainted block drops something remove it in favor of the crystal
                    if (!event.getDrops().isEmpty()) {
                        to_be_removed.addAll(event.getDrops());
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

        if (heldItem == ModItemsNCR.PURIFYING_SHOVEL) {
            World world = event.getEntityPlayer().world;
            BlockPos pos1;
            pos1 = pos.add(0, 0, 0);
            double j = 1.2D;

            // Break Particles
            if (world.getBlockState(pos1).getMaterial() == ThaumcraftMaterials.MATERIAL_TAINT) {
                for (int i = 0; i < 2; i++) {
                    FXDispatcher.INSTANCE.drawWispyMotes(pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2,
                            pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D, 10, 0.4F + world.rand.nextFloat() * 0.3F, 0.2F + world.rand.nextFloat() * 0.2F, 0.5F + world.rand.nextFloat() * 0.3F, -0.2F);
                }
            }
        }
    }
}
