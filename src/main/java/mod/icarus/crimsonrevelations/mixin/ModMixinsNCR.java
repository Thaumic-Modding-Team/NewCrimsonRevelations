package mod.icarus.crimsonrevelations.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModMixinsNCR implements ILateMixinLoader {
    private static final Map<String, Supplier<Boolean>> clientsideMixinConfigs = ImmutableMap.copyOf(new HashMap<String, Supplier<Boolean>>() {
        {

        }
    });

    private static final Map<String, Supplier<Boolean>> commonMixinConfigs = ImmutableMap.copyOf(new HashMap<String, Supplier<Boolean>>() {
        {
            put("mixins.crimsonrevelations.entities.json", () -> true);
            put("mixins.crimsonrevelations.events.json", () -> true);
            put("mixins.crimsonrevelations.misc.json", () -> true);
        }
    });

    private static boolean loaded(String modid) {
        return Loader.isModLoaded(modid);
    }

    @Override
    public List<String> getMixinConfigs() {
        List<String> configs = new ArrayList<>();
        if (FMLLaunchHandler.side().isClient()) {
            configs.addAll(clientsideMixinConfigs.keySet());
        }
        configs.addAll(commonMixinConfigs.keySet());
        return configs;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        Supplier<Boolean> sidedSupplier = FMLLaunchHandler.side().isClient() ? clientsideMixinConfigs.get(mixinConfig) : null;
        Supplier<Boolean> commonSupplier = commonMixinConfigs.get(mixinConfig);
        return sidedSupplier != null ? sidedSupplier.get() : commonSupplier == null || commonSupplier.get();
    }
}
