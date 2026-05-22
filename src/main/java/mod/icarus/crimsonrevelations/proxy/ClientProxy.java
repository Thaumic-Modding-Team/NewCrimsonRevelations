package mod.icarus.crimsonrevelations.proxy;

import mod.icarus.crimsonrevelations.client.keybinds.KeyBindings;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.init.CRRenderRegistry;
import mod.icarus.crimsonrevelations.item.CRItemManaBean;
import mod.icarus.crimsonrevelations.item.IDyeableGear;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy {
    public void preInit() {
        super.preInit();
        CRRenderRegistry.preInit();
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

            if (item == CRItems.MANA_BEAN) {
                return ((CRItemManaBean) item).getColor(stack, tintIndex);
            }

            return 16777215;
        };

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, CRItems.CRIMSON_RANGER_CHESTPLATE);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, CRItems.CRIMSON_RANGER_HELMET);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, CRItems.CRIMSON_RANGER_LEGGINGS);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemManaBeanColorHandler, CRItems.MANA_BEAN);
    }
}
