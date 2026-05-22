package mod.icarus.crimsonrevelations.enchants;

import com.invadermonky.thaumicapi.api.ThaumicAPI;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class CREnchantments {
    public static EnumInfusionEnchantment BEHEADING = ThaumicAPI.registerInfusionEnchantment(
            "BEHEADING", 5, "CR_BEHEADING_INFUSION", "weapon");

    // Two levels might be a bit much since that gives 3 enchant slots. Also, you may want to add
    // a tooltip or message when the chameleon slot changes. Just toss it into the CycleChameleon
    // message handler.
    public static EnumInfusionEnchantment CHAMELEON = ThaumicAPI.registerInfusionEnchantment(
            "CHAMELEON", 2, "CR_CHAMELEON_INFUSION", "axe", "pickaxe", "shovel", "weapon");

    // Keeping the infusion level to 3 so it is on par with the Goggles of Revealing, but does not
    // make the higher tier TA armors obsolete.
    public static EnumInfusionEnchantment VIS_ATTUNEMENT = ThaumicAPI.registerInfusionEnchantment(
            "VIS_ATTUNEMENT", 3, "CR_VIS_ATTUNEMENT_INFUSION", "armor");
}
