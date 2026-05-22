package mod.icarus.crimsonrevelations.enchants;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.keybinds.KeyBindings;
import mod.icarus.crimsonrevelations.network.CRPacketHandler;
import mod.icarus.crimsonrevelations.network.packets.CRPacketCycleChameleon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class InfusionEnchantmentChameleon {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if(player != null) {
            ItemStack heldStack = player.getHeldItemMainhand();
            if(!heldStack.isEmpty()) {
                int chameleonLvl = EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldStack, InfusionEnchantments.CHAMELEON);
                if(chameleonLvl > 0 && KeyBindings.swapChameleonEnchants.isKeyDown()) {
                    CRPacketHandler.INSTANCE.sendToServer(new CRPacketCycleChameleon());
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if(!stack.isEmpty() && GuiScreen.isShiftKeyDown()) {
            //TODO: Add shift tooltip with chameleon enchantment lists.
        }
    }
}
