package mod.icarus.crimsonrevelations.network.packets;

import io.netty.buffer.ByteBuf;
import mod.icarus.crimsonrevelations.enchants.InfusionEnchantments;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class CRPacketCycleChameleon implements IMessage, IMessageHandler<CRPacketCycleChameleon, IMessage> {
    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

    @Override
    public IMessage onMessage(CRPacketCycleChameleon message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
            EntityPlayer player = ctx.getServerHandler().player;
            ItemStack heldStack = player.getHeldItemMainhand();
            int level = EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldStack, InfusionEnchantments.CHAMELEON);
            if(!heldStack.isEmpty() && level > 0) {
                NBTTagCompound chameleonTag = this.getChameleonTag(heldStack);

                int currSlot = chameleonTag.getInteger("currSlot");
                NBTTagList curEnchants = this.stripEnchants(heldStack);
                chameleonTag.setTag("slot" + currSlot, curEnchants);

                int nextSlot = (currSlot + 1) % (level + 1);
                NBTTagList nextEnchants = chameleonTag.getTagList("slot" + nextSlot, 10);
                this.setEnchants(heldStack, nextEnchants);

                chameleonTag.setInteger("currSlot", nextSlot);
                heldStack.setTagInfo("chameleon", chameleonTag);
            }
        });
        return null;
    }

    private NBTTagCompound getChameleonTag(ItemStack stack) {
        return stack.getTagCompound() != null ? stack.getTagCompound().getCompoundTag("chameleon") : new NBTTagCompound();
    }

    private NBTTagList stripEnchants(ItemStack stack) {
        NBTTagList enchants = stack.getEnchantmentTagList();
        if(stack.getTagCompound() != null) {
            stack.getTagCompound().removeTag("ench");
        }
        return enchants;
    }

    private void setEnchants(ItemStack stack, NBTTagList enchants) {
        if(!enchants.isEmpty()) {
            stack.setTagInfo("ench", enchants);
        }
    }
}
