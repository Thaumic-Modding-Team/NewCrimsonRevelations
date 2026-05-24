package mod.icarus.crimsonrevelations.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class EarlyMixinsNCR implements IEarlyMixinLoader, IFMLLoadingPlugin {
    private static final Map<String, Supplier<Boolean>> clientsideMixinConfigs = ImmutableMap.copyOf(new HashMap<String, Supplier<Boolean>>() {
        {

        }
    });

    private static final Map<String, Supplier<Boolean>> commonMixinConfigs = ImmutableMap.copyOf(new HashMap<String, Supplier<Boolean>>() {
        {
        }
    });

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
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
