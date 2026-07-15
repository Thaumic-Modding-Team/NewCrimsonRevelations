# New Crimson Revelations Changelog
## 1.6.0
### Changed
- The smash attack from the Boots of the Meteor will now scale depending on how far you fall down with them
- Crimson Archers now fire Primal Arrows instead of normal arrows (the aspect type they fire will be random per spawn)
- Restored original Primal Arrow particles from Thaumcraft 4 and 5
- Crimson Fabric and Crimson Plates are now dropped more plentifully from cultists
### Fixed
- Fixed odd behavior with the Primordial Scribing Tools
- Fixed Boots of the Meteor activating the smash attack when climbing stairs
- Fixed Boots of the Meteor sometimes not displaying particles when the smash attack is activated
- Fixed Boots of the Meteor not protecting from fall damage
- Fixed buggy Primal Arrow physics
- Fixed cult armor sets bugging out on armor stands when a player with the same armor sneaks in third person
### Removed
- Removed the Gold Nugget drop from the cultist loot table
---
## 1.5.3
### Update Notes
This update cleans up and reworks a lot of code in the mod to prepare for the next major update. [ThaumicAPI](https://www.curseforge.com/minecraft/mc-mods/thaumicapi) was also integrated and is now required going forward.
### Added
- Added default Tropicraft Legacy support for Beheading
- Added Botania and Magiculture Integrations compat with Mana Pods
### Changed
- Refactored the entire mod's code and cleaned up several classes
- Updated `ru_ru.lang` courtesy of Quarkrus
### Fixed
- Fixed most cult sets not being properly dyeable
- Fixed Beheading causing cultists to drop zombie heads instead of the steve head
### Removed
- Removed the Furious Zombie Spawning tweak, this was moved over to ThaumicTweaker
- Removed the unused Void Goggles
---
## 1.5.2
### Fixed
- Fixed Beheading II not being applied when crafting the Axe of Execution via Infusion
---
## 1.5.1
### Fixed
- Fixed Thaumic Augmentation still being required for the Overgrown Taintacle to be rendered
- Hopefully fixed Vis Attunement causing a crash with Thaumic Wands and Thaumic Staff
---
## 1.5.0
### Added
- Added Ancient Crimson Armor, pieces are dropped from Shambling Husks or found in Eldritch Spires (Thaumic Augmentation)
- Added Boots of the Comet, the boots will temporarily freeze water into Frosted Ice when beneath the user
- Added Crimson Paladin Armor, pieces are found in Eldritch Spires (Thaumic Augmentation)
- Added Crimson Ranger Armor, pieces are found in Eldritch Spires (Thaumic Augmentation)
- Added the Chameleon infusion enchantment for tools, this enchantment allows the tool to store multiple enchantment sets on the fly. Pressing V hotkey by default will switch enchantment sets on the enchanted tool.
- Added the Vis Attunement infusion enchantment for armor, each level of this enchantment will apply a +1% vis discount, upwards to +3%
- Added some new loot to treasure bags and Eldritch Spires (Thaumic Augmentation)
- Added many new config options including a configurable mob list for the Beheading infusion enchantment!
### Changed
- Overgrown Taintacle and Boots of the Meteor no longer require Thaumic Augmentation to be installed to access them
- Overgrown Taintacle is now immune to most projectiles (configurable) and all effects
- Added new flame particles when jumping or running with the Boots of the Meteor
- Renamed Pickaxe of Distortion to Pickaxe of Warped Distortion and gave it an updated texture
- Pickaxe of Warped Distortion now plays sounds and emits particles depending on the block you're breaking
- Renamed Ring of Nutrition to Ring of Nutriment and gave it an updated texture
- Ring of Nutriment now makes you immune to starvation damage
- Shovel of the Purifier now emits particles when digging tainted blocks with it
- Digging taint blocks with the Shovel of the Purifier has a chance to drop Vitium vis crystals
- Sneaking and right-clicking with the Shovel of the Purifier will now cleanse you of all taint effects
- Mana beans now display their aspect on their name
- Added warp to dark study research
- Primordial Scribing Tools is now significantly harder to craft
- Updated several recipes to have more ore dictionary support
- The new cult sets introduced by the mod can now be dyed!
- Fully reworked the config file and reworked some settings
- Several tweaks to various research pages
- Several recipe changes and tweaks
### Fixed
- Fixed Thaumcraft Fix still being required when eating mana beans when this shouldn't be required anymore
- Fixed primal arrows changing on world reset
- Fixed Ring of Protection lacking runic shielding when found as loot
---
## 1.4.6
### Changed
- All scribing tools are now available regardless if Thaumcraft Fix is installed or not
- Reworked Runic Amulet of Emergency Shielding to give Absorption II (8 hearts) rather than 8 points of Runic Shielding when breached
- Restored the original ability of the Charged Runic Ring of Shielding from older versions, it will now boost Runic Shielding recharge speed by 50% when worn
### Fixed
- Fixed a check with Runic Shielding baubles
---
## 1.4.5
### Changed
- Thaumcraft Fix dependency is now completely optional. Scribing Tools of Knowledge and Primordial Scribing Tools will both be disabled if it isn't installed.
---
## 1.4.4
### Fixed
- Crimson portal particles now fully work on multiplayer again
### Removed
- Removed old Thaumometer scanning tweak, this feature was moved to ThaumicTweaker
---
## 1.4.3
### Fixed
- Fixed a possible Lesser Crimson Portal crash on servers
---
## 1.4.2
### Fixed
- Fixed a networking error
---
## 1.4.1
### Update Notes
In order to ensure config options work properly with mixins, ConfigAnytime is now a required dependency!
### Fixed
- Fixed Boots of the Meteor still consuming charges on creative mode
- Fixed Ethereal Bloom dropping itself twice when the block below is mined
- Fixed Conflicting Content Removal Check not working as intended
- Fixed Old Thaumometer Scanning not being able to be disabled
---
## 1.4.0
### Added
- Added Technomancer's Scribing Tools, a new set of scribing tools that have a Vis capacity
- Added Thaumic Litmus Paper, a one-use item that tells you all exact warp amounts. Inspired by a similar item in Warp Theory. (configurable)
- Added Axe of Execution, it sets mobs on fires, smelts harvested blocks, and has a chance to cause certain mobs to drop their own head or other items
- Added Beheading Infusion Enchantment, now you can apply a beheading effect to any tool you want!
- Added Punch Focus Effect, does zero damage but greatly knocks everything away!
- Added Hex Focus Effect, the original Curse effect from older Thaumcraft versions is now back
- Added Crimson Archer, a new cultist mob that can spawn from lesser portals!
- Added Crimson Archer bestiary entry
- Added back the original Thaumometer scanning functionality from 1.7.10 (configurable)
- Added Ethereal Bloom, another returning feature from old Thaumcraft versions!
- Restored original portal particles from TC5 (1.8.9) when cultists spawn
### Changed
- Changed color of Overgrown Taintacle boss bar from pink to purple
- Adjusted durability of all scribing tools
- Reduced sanity cleanse effects from Sanitation Scribing Tools
- Updated Crimson Cleric bestiary lore text
- Moved Ancient Baubles to Fundamentals
- Moved Runic Baubles, Specialized Runic Baubles, and Verdant Rings to Infusion
- Organized research and translation entries in their files
- Increased Crimson Rites drop chance from portals (25% + 10% per Looting level)
- Reworked Shovel of the Purifier's zap particles
- Reduced Shovel of the Purifier's overall particle amount
- Updated Shovel of the Purifier texture
- Cultist projectiles (arrows, fireballs, and orbs) will no longer harm other cultists!
- Items originating from other addons are now automatically disabled by default when their respective addon is also installed. This can be completely disabled in the config file.
### Fixed
- Fixed custom names not displaying on the Overgrown Taintacle's boss bar properly after rejoining a world
- Fixed Boots of the Meteor infinitely working at 1 charge
- Fixed yet another crash caused by the Overgrown Taintacle's death animation
### Removed
- Removed Erythurgy, Entropic Processing, and Ordered Deconstruction. These have been moved to Thaumic Wonders Unofficial.
- Removed Quartz Purification
---
## 1.3.3
### Fixed
- Fixed a server side crash
---
## 1.3.2
### Added
- Added Pickaxe of Distortion
- Added Ring of Nutrition
- Added Shovel of the Purifier
### Changed
- Improved Bow of Bone's first person zoom
- Improved Bow of Bone's automatic firing
### Fixed
- Fixed Mana Pods not always dropping Mana Beans in certain circumstances due to a deprecated method
- Fixed Aer Arrows not applying extra knockback when the Punch enchantment is active or anything else that affects knockback from bows
- Fixed primal arrows not firing from dispensers
---
## 1.3.1
### Added
- Added config options to control how frequently Mana Beans generate in Magical Forests and to control Pechs dropping primal aspect Mana Beans
### Changed
- Pechs now drop primal aspect Mana Beans like in 1.7.10!
### Fixed
- Fixed the mod crashing without Thaumic Augmentation installed despite being an optional dependency
- Fixed Mana Beans not spawning in the Magical Forest outside of dev environments
- Fixed mixin refmap paths being incorrect causing most mixins to not work
---
## 1.3.0
### Update Notes
Thaumcraft Fix is now a required dependency.
### Added
- Mana Beans are now back from previous Thaumcraft versions! Farm them on magical biomes and use them to melt them down into essentia or eat them for randomized effects (configurable list!) and research points! You can also find them generating naturally in Magical Forest biomes.
- Verdant Heart Band, a returning bauble from Thaumcraft 5. It acts exactly the same as the Verdant Heart Charm but its usage in the ring slot allows both infusion enchants to be used at once. Once unlocked, it can either be crafted from scratch or you can convert a charm into its ring counterpart using an Arcane Workbench at the cost of vis and vice versa.
- A few new config options to modify Mana Beans
### Changed
- Scribing Tools of Knowledge and Primordial Scribing Tools are now filtered on what research categories they give research points to and will never give research on unused categories from other addons with the help of Thaumcraft Fix
### Fixed
- A few typo fixes
---
## 1.2.4
### Fixed
- Fully fixed the spam right-click issue on Primordial Scribing Tools while playing in Survival multiplayer
---
## 1.2.3
### Added
- Added a batch of new textures and sounds for upcoming updates (stay tuned!)
### Changed
- Updated Crimson Archer Helmet texture
### Fixed
- Fixed particles from custom scribing tools not appearing in multiplayer
- Stereo sounds are now converted to mono to prevent issues
- Hopefully fixed a very bad issue with custom scribing tools where they could be spam right-clicked to endlessly gain research points in multiplayer
---
## 1.2.2
### Changed
- Updated and improved several existing textures (thanks fakeginkgo!)
---
## 1.2.1
### Changed
- Fully updated Simplified Chinese translation (thanks fakeginkgo)!
---
## 1.2.0
### Added
- Added Runic Baubles, a set of returning baubles from Thaumcraft 4 and 5 that grants runic shielding. These will immediately be available upon learning Runic Shielding.
- Added Specialized Runic Baubles, an upgraded set of runic baubles returning from Thaumcraft 4 and 5. Thanks to keletu for originally porting these into the now discontinued Thaumisc mod, which this mod has used a bit of the code from as a base before improving them heavily and adding back the Charged Ring of Runic Shielding with new mechanics.
- The Ring of Protection returns from Thaumcraft 4 and 5 with a new texture. You'll be able to find it in certain loot chests.
- Several new scribing tools were added to further help with making research less frustrating. This includes the Scribing Tools of Knowledge which provides theories, observations, and curios, the Scribing Tools of Sanitation which helps remove warp, and the Primordial Scribing Tools which has infinite uses and retains the benefits of the other two scribing tools.
- Introduced the Boots of the Meteor, a returning item from Thaumcraft 2. The functionality is quite identical to the original, you will also need Thaumic Augmentation installed for them to be enabled.
- Some extra lore filled pages were added
- Furious Zombies are no longer unused in Thaumcraft 6 and are now able to spawn naturally on the surface. Config options for disabling their spawns, spawn weight, and adding undergrown spawns were also added.
### Changed
- Some balancing changes were done to already existing research entries and recipes
- Updated some text of various research entries
- Magic Tallow Blocks now have CTM support! (Courtesy of PessiMysterio)
- The textures of Embellished Crimson Fabric and Dark Auromancy research were updated (Courtesy of fakeginkgo)
- Updated credits
Other miscellaneous tweaks that were forgotten.
### Fixed
-  Fixed an odd Multiplayer crash
---
## 1.1.0
### Added
- Added Embellished Crimson Fabric, this new item is used for several recipes
- Added Blinding Flash Focus Effect, this focus effect is stronger against undead but weaker against non-undead. It also blinds and blurs targets (or provides weakness if not a player).
- Added Primal Arrows, returning from Thaumcraft 4 and 5 but with slight differences
- Added Crimson Archer Armor, currently this is mainly only obtainable from crafting (credits to Damien Hazard, this model was also featured in Thaumic Dyes)
- Added various lore entries to further expand the Crimson Cult's lore including the new bestiary. Scan clerics or knights to learn more about them!
- Added Magic Tallow Blocks, another returning item from Thaumcraft 4. This is mainly used to create tallow golems.
- Added three new golem materials to utilize, Crimson Plate, Flesh, and Tallow
- Added JER support for Crimson Clerics and Crimson Knights, this will disable itself if Just Enough Mariculture is also installed
### Changed
- Several research keys were renamed to have better compatibility with other addons
- Reworked how the mod injects into existing loot tables, no longer requiring additional loot tables to do so
- Erythurgy's icon is now a deep red color
- Renamed Crimson Cult Apparel to Basic Crimson Cult Apparel
- Renamed Crimson Cult Sword to Basic Crimson Cult Arsenal
- Crimson Cult armor recipes have been slightly tweaked
- Vis cost of Crimson Cult armor is now dependent on the piece with boots being the cheapest (15) and chestplates being the most expensive (50)
- Reduced default Overgrown Taintacle spawn weight from 2 to 1
- Overgrown Taintacle's loot has been tweaked, a pearl will now only have a 1/5 chance to drop and the drop variety has been spiced up a bit
- Buffed Overgrown Taintacle's health (175 > 200) and armor (8 > 16)
- Lesser Crimson Portal's loot has been tweaked, a curiosity drop is now guaranteed, it will also drop more items per kill
- Several entries are now marked as hidden and won't show up until you are close to unlocking them
- Updated how the mod is structured and did additional cleaning up
- Updated credits and added a logo
### Fixed
- Fixed Infusion Stabilization being a requirement to start the Revelations category. This was leftover from the original Crimson Revelations mod.
---
## 1.0.2
### Additions
- Added the Bow of Bone, it should work similarly to how it does in previous Thaumcraft versions (have fun!)
### Changed
- Focus effect textures should be less blurry now (thanks PessiMysterio!)
- Cleaned up lang files
### Fixed
- Fixed the Overgrown Taintacle causing a crash when it is killed in a server
---
## 1.0.1
### Fixed
- Fixed a dedicated server crash (sorry!)