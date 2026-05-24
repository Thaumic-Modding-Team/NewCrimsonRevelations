package mod.icarus.crimsonrevelations.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thaumcraft.client.fx.FXDispatcher;

public class PacketFXCultistPortal implements IMessage, IMessageHandler<PacketFXCultistPortal, IMessage> {
    private double posX;
    private double posY;
    private double posZ;
    private double width;
    private double height;

    public PacketFXCultistPortal() {
    }

    public PacketFXCultistPortal(double posX, double posY, double posZ, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.width = width;
        this.height = height;
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeDouble(posX);
        buffer.writeDouble(posY);
        buffer.writeDouble(posZ);
        buffer.writeDouble(width);
        buffer.writeDouble(height);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.posX = buffer.readDouble();
        this.posY = buffer.readDouble();
        this.posZ = buffer.readDouble();
        this.width = buffer.readDouble();
        this.height = buffer.readDouble();
    }

    @Override
    public IMessage onMessage(PacketFXCultistPortal message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            for (int i = 0; i < 20; ++i) {
                double d0 = Minecraft.getMinecraft().world.rand.nextGaussian() * 0.05;
                double d2 = Minecraft.getMinecraft().world.rand.nextGaussian() * 0.05;
                double d3 = Minecraft.getMinecraft().world.rand.nextGaussian() * 0.05;
                double d4 = 2.0;

                FXDispatcher.GenPart pp = new FXDispatcher.GenPart();
                pp.age = 10 + Minecraft.getMinecraft().world.rand.nextInt(10);
                pp.alpha = new float[]{0.8F, 0.8F};
                pp.grid = 32;
                pp.layer = 1;
                pp.partInc = 1;
                pp.partNum = 5;
                pp.partStart = 337;
                float s = (float) (3.0F + Minecraft.getMinecraft().world.rand.nextGaussian() * 2.0F);
                pp.scale = new float[]{s, s};
                pp.redEnd = 0.6F;
                pp.greenEnd = 0.0F;
                pp.blueEnd = 0.0F;

                FXDispatcher.INSTANCE.drawGenericParticles(message.posX + Minecraft.getMinecraft().world.rand.nextFloat() * message.width * 2.0f - message.width + d0 * d4,
                        message.posY + Minecraft.getMinecraft().world.rand.nextFloat() * message.height + d2 * d4,
                        message.posZ + Minecraft.getMinecraft().world.rand.nextFloat() * message.width * 2.0f - message.width + d3 * d4, d0, d2, d3, pp);
            }
        });
        return null;
    }
}
