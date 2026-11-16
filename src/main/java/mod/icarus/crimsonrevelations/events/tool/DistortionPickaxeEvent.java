package mod.icarus.crimsonrevelations.events.tool;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.ConfigHandlerNCR;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.registry.ModSoundEventsNCR;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.client.fx.FXDispatcher;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class DistortionPickaxeEvent {
    @SubscribeEvent
    public static void onPlayerBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
        Item heldItem = event.getEntityPlayer().getHeldItemMainhand().getItem();
        BlockPos pos = event.getPos();

        // Pickaxe of Warped Distortion mines faster on harder blocks and mines slower on softer blocks
        if (heldItem == ModItemsNCR.DISTORTION_PICKAXE) {
            EntityPlayer player = event.getEntityPlayer();
            World world = player.world;
            BlockPos pos1;
            pos1 = pos.add(0, 0, 0);

            float hardness = world.getBlockState(pos1).getBlockHardness(world, pos1);
            double j = 1.2D;

            // Particles and sounds
            if (hardness < 5.0F) {
                for (int i = 0; i < 2; i++) {
                    FXDispatcher.INSTANCE.drawWispyMotes(pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2,
                            pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D, 10, 0.3F + world.rand.nextFloat() * 0.3F, 0.0F, 0.7F + world.rand.nextFloat() * 0.3F, -0.05F);
                }

                if (player.isSwingInProgress && player.ticksExisted % 5 == 0 && ConfigHandlerNCR.distortion_pickaxe.miningSounds) {
                    player.world.playSound(null, pos1.getX(), pos1.getY(), pos1.getZ(), ModSoundEventsNCR.MISC_DISTORTION_PICKAXE_CLANK, SoundCategory.PLAYERS, 0.175F, 0.5F / (world.rand.nextFloat() * 0.4F + 0.8F));
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    FXDispatcher.INSTANCE.drawWispyMotes(pos.getX() + 0.5D + world.rand.nextDouble() * j - j / 2, pos.getY() + 0.5D + world.rand.nextDouble() * j - j / 2,
                            pos.getZ() + 0.5D + world.rand.nextDouble() * j - j / 2, 0.0D, 0.0D, 0.0D, 10, 0.3F + world.rand.nextFloat() * 0.3F, 0.1F + world.rand.nextFloat() * 0.2F, 0.7F + world.rand.nextFloat() * 0.3F, -0.2F);
                }

                if (player.isSwingInProgress && player.ticksExisted % 5 == 0 && ConfigHandlerNCR.distortion_pickaxe.miningSounds) {
                    player.world.playSound(null, pos1.getX(), pos1.getY(), pos1.getZ(), ModSoundEventsNCR.MISC_DISTORTION_PICKAXE_CLANK, SoundCategory.PLAYERS, 0.175F, 0.75F / (world.rand.nextFloat() * 0.4F + 0.8F));
                }
            }

            if (hardness == 0.0F) {
                event.setNewSpeed(0.0F);
            } else if (hardness < 5.0F) {
                event.setNewSpeed(0.1F);
            } else if (hardness < 20.0F) {
                event.setNewSpeed(hardness / 2.0F);
            } else {
                event.setNewSpeed(5.0F + hardness);
            }
        }
    }
}
