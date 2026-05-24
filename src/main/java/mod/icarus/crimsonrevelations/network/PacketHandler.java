package mod.icarus.crimsonrevelations.network;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.network.packets.PacketCycleChameleon;
import mod.icarus.crimsonrevelations.network.packets.PacketFXArcBolt;
import mod.icarus.crimsonrevelations.network.packets.PacketFXCultistPortal;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static SimpleNetworkWrapper INSTANCE;

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(PacketFXArcBolt.class, PacketFXArcBolt.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketFXCultistPortal.class, PacketFXCultistPortal.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketCycleChameleon.class, PacketCycleChameleon.class, id++, Side.SERVER);
    }

    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(NewCrimsonRevelations.MODID);
    }
}
