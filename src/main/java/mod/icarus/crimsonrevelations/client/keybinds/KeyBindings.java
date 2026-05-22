package mod.icarus.crimsonrevelations.client.keybinds;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.enchants.CREnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class KeyBindings {
    private static final String KEY_CATEGORY = "key.categories." + NewCrimsonRevelations.MODID;
    public static KeyBinding swapChameleonEnchants;

    public static void init() {
        swapChameleonEnchants = new KeyBinding("key.swap_chameleon." + NewCrimsonRevelations.MODID, new IKeyConflictContext() {
            @Override
            public boolean isActive() {
                try {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if(player != null) {
                        ItemStack heldStack = player.getHeldItemMainhand();
                        return !heldStack.isEmpty() && EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldStack, CREnchantments.CHAMELEON) > 0;
                    }
                } catch (Exception ignored) {}
                return false;
            }

            @Override
            public boolean conflicts(IKeyConflictContext other) {
                try {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if(player != null && other.isActive()) {
                        ItemStack heldStack = player.getHeldItemMainhand();
                        return !heldStack.isEmpty() && EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldStack, CREnchantments.CHAMELEON) > 0;
                    }
                } catch (Exception ignored) {}
                return false;
            }
        }, Keyboard.KEY_V, KEY_CATEGORY);
        ClientRegistry.registerKeyBinding(swapChameleonEnchants);
    }
}
