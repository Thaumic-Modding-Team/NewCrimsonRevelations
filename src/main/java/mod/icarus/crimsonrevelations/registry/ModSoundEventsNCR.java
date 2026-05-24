package mod.icarus.crimsonrevelations.registry;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class ModSoundEventsNCR {
    public static final SoundEvent FOCUS_BLINDING_LIGHT_HIT = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "focus.blinding_light.hit"));
    public static final SoundEvent FOCUS_BLINDING_LIGHT_SHOOT = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "focus.blinding_light.shoot"));
    public static final SoundEvent FOCUS_PUNCH_HIT = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "focus.punch.hit"));
    public static final SoundEvent FOCUS_PUNCH_SHOOT = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "focus.punch.shoot"));
    public static final SoundEvent MISC_DISTORTION_PICKAXE_CLANK = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "misc.distortion_pickaxe.clank"));
    public static final SoundEvent MISC_PURIFYING_SHOVEL_PURIFY = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "misc.purifying_shovel.purify"));
    public static final SoundEvent MISC_ROOTS = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "misc.roots"));
    public static final SoundEvent RUNIC_BAUBLE_CHARGE = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "bauble.runic.charge"));
    public static final SoundEvent RUNIC_BAUBLE_EMERGENCY = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "bauble.runic.emergency"));
    public static final SoundEvent RUNIC_BAUBLE_KINETIC = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "bauble.runic.kinetic"));
    public static final SoundEvent RUNIC_BAUBLE_REGEN = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "bauble.runic.regen"));
}
