package mod.icarus.crimsonrevelations.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.client.fx.FXDispatcher;

import java.awt.*;

public class PacketFXArcBolt implements IMessage, IMessageHandler<PacketFXArcBolt, IMessage> {
    private Vec3d source;
    private Vec3d target;
    private int color;
    private float width;

    public PacketFXArcBolt() {
    }

    public PacketFXArcBolt(Vec3d source, Vec3d target, int color, float width) {
        this.source = source;
        this.target = target;
        this.color = color;
        this.width = width;
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeDouble(this.source.x);
        buffer.writeDouble(this.source.y);
        buffer.writeDouble(this.source.z);
        buffer.writeDouble(this.target.x);
        buffer.writeDouble(this.target.y);
        buffer.writeDouble(this.target.z);
        buffer.writeInt(this.color);
        buffer.writeFloat(this.width);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.source = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
        this.target = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
        this.color = buffer.readInt();
        this.width = buffer.readFloat();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketFXArcBolt message, MessageContext ctx) {
        Color c = new Color(message.color);
        FXDispatcher.INSTANCE.arcLightning(message.source.x, message.source.y, message.source.z, message.target.x, message.target.y, message.target.z, c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, message.width);
        return null;
    }
}

