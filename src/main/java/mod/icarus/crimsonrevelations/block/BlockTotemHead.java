package mod.icarus.crimsonrevelations.block;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class BlockTotemHead extends BlockMaterial {
    String unlocName;
    public BlockTotemHead(String unlocName) {
        super(unlocName, Material.ROCK, MapColor.OBSIDIAN, 50.0F, 4000.0F, SoundType.STONE);
        this.setTranslationKey(NewCrimsonRevelations.MODID + ":eldritch_totem_head");
        this.unlocName = unlocName;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flag) {
        tooltip.add(1, I18n.format("tooltip." + NewCrimsonRevelations.MODID + "." + unlocName));
    }
}
