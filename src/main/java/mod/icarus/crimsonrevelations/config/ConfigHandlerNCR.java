package mod.icarus.crimsonrevelations.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = NewCrimsonRevelations.MODID, name = NewCrimsonRevelations.NAME)
public class ConfigHandlerNCR {
    @Config.Comment("Boots of the Comet")
    public static CometBootsSettings comet_boots = new CometBootsSettings();

    @Config.Comment("Pickaxe of Warped Distortion")
    public static DistortionPickaxeSettings distortion_pickaxe = new DistortionPickaxeSettings();

    @Config.Comment("Ethereal Bloom")
    public static EtherealBloomSettings ethereal_bloom = new EtherealBloomSettings();

    @Config.Comment("Infusion Enchantments")
    public static InfusionEnchantmentSettings infusion_enchants = new InfusionEnchantmentSettings();

    @Config.Comment("Scribing Tools of Knowledge")
    public static KnowledgeToolsSettings knowledge_tools = new KnowledgeToolsSettings();

    @Config.Comment("Mana Beans")
    public static ManaBeanSettings mana_beans = new ManaBeanSettings();

    @Config.Comment("Mod Integration")
    public static ModIntegrationSettings mod_integration_settings = new ModIntegrationSettings();

    @Config.Comment("Boots of the Meteor")
    public static MeteorBootsSettings meteor_boots = new MeteorBootsSettings();

    @Config.Comment("Ring of Nutriment")
    public static NutritionRingSettings nutrition_ring = new NutritionRingSettings();

    @Config.Comment("Overgrown Taintacle")
    public static OvergrownTaintacleSettings overgrown_taintacle = new OvergrownTaintacleSettings();

    @Config.Comment("Scribing Tools of Knowledge")
    public static PrimordialToolsSettings primordial_tools = new PrimordialToolsSettings();

    @Config.Comment("Shovel of the Purifier")
    public static PurifyingShovelSettings purifying_shovel = new PurifyingShovelSettings();

    @Config.Comment("Scribing Tools of Sanitation")
    public static SanitationToolsSettings sanitation_tools = new SanitationToolsSettings();

    @Config.Comment("Thaumic Augmentation")
    public static TAIntegrationSettings thaumic_augmentation_settings = new TAIntegrationSettings();

    @Config.Comment("Thaumic Litmus Paper")
    public static ThaumicLitmusPaperSettings thaumic_litmus_paper = new ThaumicLitmusPaperSettings();

    public static class CometBootsSettings {
        @Config.Name("Jump Boost")
        @Config.Comment("The boost applied when the wearer jumps, this is added to the base jump height of the player. [default: 0.3]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double jumpBoost = 0.3D;

        @Config.Name("Jump Factor")
        @Config.Comment("The boost applied to player movement while in the air. Note that sprinting's jump modifier uses this value as well. [default: 0.02]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double jumpFactor = 0.02D;

        @Config.Name("Land Speed Boost")
        @Config.Comment("The boost applied while the wearer is on the ground, this is added to the base movement of the player per tick. [default: 0.06]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double landSpeed = 0.06D;

        @Config.Name("Sneak Reduction")
        @Config.Comment("Any speed boosts (not jump) will be divided by this value while sneaking. [default: 4.0]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double sneakReduction = 4.0D;

        @Config.Name("Step Height")
        @Config.Comment("The boost applied to the player's step height (while not sneaking), this is added to the vanilla default value of 0.6. [default: 0.67]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double stepHeight = 0.67D;

        @Config.Name("Water Speed Boost")
        @Config.Comment("The boost applied while the wearer is in water, this is added to the base movement of the player per tick. [default: 0.03]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double waterSpeedBoost = 0.03D;
    }

    public static class DistortionPickaxeSettings {
        @Config.RequiresMcRestart
        @Config.Name("Enable Warped Pickaxe of Distortion")
        @Config.Comment("Enables the Pickaxe of Warped Distortion. [default: true]")
        public boolean enableDistortionPickaxe = true;

        @Config.Name("Mining Sounds")
        @Config.Comment("The Pickaxe of Warped Distortion will play its own unique sounds when mining blocks. [default: true]")
        public boolean miningSounds = true;
    }

    public static class EtherealBloomSettings {
        @Config.RequiresMcRestart
        @Config.Name("Enable Ethereal Bloom")
        @Config.Comment("Enables the Ethereal Bloom. [default: true]")
        public boolean enableEtherealBloom = true;
    }

    public static class InfusionEnchantmentSettings {
        @Config.Name("Beheading Mob Head Drops")
        @Config.Comment({"List of mob head drops in the format 'modid:entity;subtypes;modid:item[:metadata][;max_quantity]'.", "Example: 'minecraft:skeleton;true;minecraft:skull:0' or 'minecraft:chicken;false;minecraft:feather;2'"})
        public String[] beheadingMobHeadDrops = new String[]{
                "minecraft:skeleton;true;minecraft:skull:0",
                "minecraft:stray;true;minecraft:skull:0",
                "minecraft:wither_skeleton;true;minecraft:skull:1",
                "minecraft:zombie;true;minecraft:skull:2",
                "minecraft:player;false;minecraft:skull:3",
                "minecraft:creeper;true;minecraft:skull:4",
                "minecraft:ender_dragon;true;minecraft:skull:5",
                "minecraft:snowman;false;minecraft:pumpkin",
                "minecraft:villager_golem;false;minecraft:pumpkin",
                "mod_lavacow:boneworm;false;minecraft:skull:0",
                "mod_lavacow:forsaken;true;minecraft:skull:0",
                "mod_lavacow:skeletonking;false;minecraft:skull:0",
                "mod_lavacow:soulworm;false;minecraft:skull:1",
                "mod_lavacow:scarecrow;false;mod_lavacow:scarecrowhead_common",
                "techguns:armysoldier;false;minecraft:skull:3",
                "techguns:bandit;false;minecraft:skull:3",
                "techguns:commando;false;minecraft:skull:3",
                "techguns:dictatordave;false;minecraft:skull:3",
                "techguns:psychosteve;false;minecraft:skull:3",
                "techguns:stormtrooper;false;minecraft:skull:3",
                "techguns:zombiefarmer;true;minecraft:skull:2",
                "techguns:zombieminer;true;minecraft:skull:2",
                "techguns:zombiepoliceman;true;minecraft:skull:2",
                "techguns:zombiesoldier;true;minecraft:skull:2",
                "thaumcraft:CultistCleric;false;minecraft:skull:3",
                "thaumcraft:CultistKnight;false;minecraft:skull:3",
                "thaumcraft:CultistLeader;false;minecraft:skull:3",
                "tropicraft:tropiskeleton;false;minecraft:skull:0",
                "tropicraft:tropicreeper;false;minecraft:skull:4"
        };

        @Config.Name("Chameleon Blacklist")
        @Config.Comment("List of tools that should never be compatible with the Chameleon infusion enchantment.")
        public String[] chameleonBlacklist = new String[]{
                "forbiddenmagicre:morph_axe",
                "forbiddenmagicre:morph_pick",
                "forbiddenmagicre:morph_shovel",
                "forbiddenmagicre:morph_sword"
        };
    }

    public static class KnowledgeToolsSettings {
        @Config.Name("Curiosity Chance")
        @Config.Comment("The percentage chance for a curiosity to be obtained from the Scribing Tools of Knowledge. [default: 0.2]")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.RequiresMcRestart
        public double curiosityChance = 0.2D;
    }

    public static class ManaBeanSettings {
        @Config.Name("Aspect Count")
        @Config.Comment("Mana Bean contained aspect count. [default: 5]")
        @Config.RangeInt(min = 1, max = 128)
        @Config.RequiresMcRestart
        public int aspectCount = 5;

        @Config.Name("Effect List")
        @Config.Comment("Configurable list of possible effects that eaten Mana Beans can apply.")
        public String[] effectList = new String[]{
                "minecraft:absorption",
                "minecraft:blindness",
                "minecraft:fire_resistance",
                "minecraft:haste",
                "minecraft:health_boost",
                "minecraft:hunger",
                "minecraft:invisibility",
                "minecraft:jump_boost",
                "minecraft:levitation",
                "minecraft:luck",
                "minecraft:mining_fatigue",
                "minecraft:nausea",
                "minecraft:night_vision",
                "minecraft:poison",
                "minecraft:regeneration",
                "minecraft:resistance",
                "minecraft:slowness",
                "minecraft:speed",
                "minecraft:strength",
                "minecraft:unluck",
                "minecraft:water_breathing",
                "minecraft:weakness",
                "minecraft:wither",
                "thaumcraft:blurredvision",
                "thaumcraft:deathgaze",
                "thaumcraft:fluxtaint",
                "thaumcraft:infectiousvisexhaust",
                "thaumcraft:sunscorned",
                "thaumcraft:thaumarhia",
                "thaumcraft:unnaturalhunger",
                "thaumcraft:visexhaust",
                "thaumcraft:warpward"
        };

        @Config.Name("Generation Frequency")
        @Config.Comment("The amount of iterations over block positions to generate Mana Pods. [default: 10]")
        @Config.RangeInt(min = 0, max = 100)
        public int generationFrequency = 10;

        @Config.Name("Pech Loot")
        @Config.Comment("Enables Primal Mana Beans to drop as loot from Pechs. [default: true]")
        public boolean pechLoot = true;


        @Config.Name("Research Chance")
        @Config.Comment("The percentage chance for an eaten Mana Bean to grant observations and theories for research. [default: 0.33]")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.RequiresMcRestart
        public double researchChance = 0.33D;
    }

    public static class MeteorBootsSettings {
        @Config.Name("Jump Boost")
        @Config.Comment("The boost applied when the wearer jumps, this is added to the base jump height of the player. [default: 0.3]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double jumpBoost = 0.3D;

        @Config.Name("Jump Factor")
        @Config.Comment("The boost applied to player movement while in the air. Note that sprinting's jump modifier uses this value as well. [default: 0.02]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double jumpFactor = 0.02D;

        @Config.Name("Land Speed Boost")
        @Config.Comment("The boost applied while the wearer is on the ground, this is added to the base movement of the player per tick. [default: 0.06]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double landSpeed = 0.06D;

        @Config.Name("Sneak Reduction")
        @Config.Comment("Any speed boosts (not jump) will be divided by this value while sneaking. [default: 4.0]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double sneakReduction = 4.0D;

        @Config.Name("Step Height")
        @Config.Comment("The boost applied to the player's step height (while not sneaking), this is added to the vanilla default value of 0.6. [default: 0.67]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double stepHeight = 0.67D;

        @Config.Name("Water Speed Boost")
        @Config.Comment("The boost applied while the wearer is in water, this is added to the base movement of the player per tick. [default: 0.03]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double waterSpeedBoost = 0.03D;
    }

    public static class ModIntegrationSettings {
        @Config.Name("Just Enough Resources Integration")
        @Config.Comment("Enables Just Enough Resources integration. [default: true]")
        @Config.RequiresMcRestart
        public boolean enableJERIntegration = true;

        @Config.Name("Thaumic Augmentation Integration")
        @Config.Comment("Enables Thaumic Augmentation integration. [default: true]")
        @Config.RequiresMcRestart
        public boolean enableTAIntegration = true;
    }

    public static class NutritionRingSettings {
        @Config.RequiresMcRestart
        @Config.Name("Enable Ring of Nutriment")
        @Config.Comment("Enables the Ring of Nutriment. [default: true]")
        public boolean enableNutritionRing = true;
    }

    public static class OvergrownTaintacleSettings {
        @Config.Name("Overgrown Taintacle Boss Bar")
        @Config.Comment("Enables the Overgrown Taintacle's boss bar. [default: false]")
        @Config.RequiresWorldRestart
        public boolean bossBar = false;

        @Config.Name("Overgrown Taintacle Projectile Immunity Threshold")
        @Config.Comment("When the percentage of the Overgrown Taintacle's health is this low, it will become immune to projectiles (1.0 to always make it immune). [default: 1.0]")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.RequiresMcRestart
        public double projectileImmunityThreshold = 1.0D;

        @Config.Name("Overgrown Taintacle Spawn Weight")
        @Config.Comment("Default spawn weight of Overgrown Taintacles in the Emptiness' Tainted Lands. [default: 1]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int spawnWeight = 1;
    }

    public static class PrimordialToolsSettings {
        @Config.Name("Curiosity Chance")
        @Config.Comment("The percentage chance for a curiosity to be obtained from the Primordial Scribing Tools. [default: 0.3]")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.RequiresMcRestart
        public double curiosityChance = 0.3D;

        @Config.Name("Warp Ward Duration")
        @Config.Comment("The amount of time in minutes that Warp Ward lasts. [default: 60]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int warpWardDuration = 60;
    }

    public static class PurifyingShovelSettings {
        @Config.RequiresMcRestart
        @Config.Name("Enable Shovel of the Purifier")
        @Config.Comment("Enables the Shovel of the Purifier. [default: true]")
        public boolean enablePurifyingShovel = true;

        @Config.Name("Shovel of the Purifier Special Cost")
        @Config.Comment("Enables the Shovel of the Purifier's special ability when right-clicking and sneaking. [default: true]")
        @Config.RequiresMcRestart
        public boolean enableSpecial = true;

        @Config.Name("Shovel of the Purifier Cleanse Max Cost")
        @Config.Comment("The maximum amount of durability that is consumed when the Shovel of the Purifier removes nearby Flux Goo. [default: 15]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int cleanseMaxCost = 15;

        @Config.Name("Shovel of the Purifier Special Cost")
        @Config.Comment("The amount of durability that is consumed when the Shovel of the Purifier removes taint effects from its user. [default: 100]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int specialCost = 100;
    }

    public static class SanitationToolsSettings {
        @Config.Name("Warp Ward Duration")
        @Config.Comment("The amount of time in minutes that Warp Ward lasts. [default: 20]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int warpWardDuration = 20;
    }

    public static class TAIntegrationSettings {
        @Config.Name("Taint Seed Spawn Weight")
        @Config.Comment("Default spawn weight of Taint Seeds in the Emptiness' Tainted Lands. [default: 20]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int taintSeedSpawnWeight = 20;

        @Config.Name("Taint Swarm Spawn Weight")
        @Config.Comment("Default spawn weight of Taint Swarms in the Emptiness' Tainted Lands. [default: 20]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int taintSwarmSpawnWeight = 20;
    }

    public static class ThaumicLitmusPaperSettings {
        @Config.RequiresMcRestart
        @Config.Name("Enable Thaumic Litmus Paper")
        @Config.Comment("Enables the Thaumic Litmus Paper. [default: true]")
        public boolean enableThaumicLitmusPaper = true;
    }

    // ConfigAnytime is needed to ensure mixins can be toggleable with config settings
    static {
        ConfigAnytime.register(ConfigHandlerNCR.class);
    }

    @Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(NewCrimsonRevelations.MODID)) {
                ConfigManager.sync(NewCrimsonRevelations.MODID, Config.Type.INSTANCE);
                ConfigLists.initLists();
            }
        }
    }
}
