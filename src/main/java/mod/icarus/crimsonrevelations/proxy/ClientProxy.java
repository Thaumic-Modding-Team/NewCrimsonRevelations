package mod.icarus.crimsonrevelations.proxy;

import mod.icarus.crimsonrevelations.client.keybinds.KeyBindings;
import mod.icarus.crimsonrevelations.registry.ModItemsNCR;
import mod.icarus.crimsonrevelations.registry.ModRenderRegistryNCR;
import mod.icarus.crimsonrevelations.item.misc.ItemManaBean;
import mod.icarus.crimsonrevelations.item.IDyeableGear;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy {
    public void preInit() {
        super.preInit();
        ModRenderRegistryNCR.preInit();
    }

    public void init() {
        super.init();
        this.registerColorHandlers();
        KeyBindings.init();
    }

    public void postInit() {
        super.postInit();
    }

    private void registerColorHandlers() {
        IItemColor itemColorHandler = (stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof IDyeableGear) {
                return ((IDyeableGear) stack.getItem()).getDyedColor(stack);
            } else {
                return -1;
            }
        };

        IItemColor itemManaBeanColorHandler = (stack, tintIndex) -> {
            Item item = stack.getItem();

            if (item == ModItemsNCR.MANA_BEAN) {
                return ((ItemManaBean) item).getColor(stack, tintIndex);
            }

            return 16777215;
        };

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItemsNCR.CRIMSON_ARCHER_CHESTPLATE);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItemsNCR.CRIMSON_ARCHER_LEGGINGS);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItemsNCR.CRIMSON_PALADIN_CHESTPLATE);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItemsNCR.CRIMSON_PALADIN_LEGGINGS);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItemsNCR.CRIMSON_RANGER_CHESTPLATE);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItemsNCR.CRIMSON_RANGER_HELMET);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItemsNCR.CRIMSON_RANGER_LEGGINGS);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemManaBeanColorHandler, ModItemsNCR.MANA_BEAN);
    }
}
