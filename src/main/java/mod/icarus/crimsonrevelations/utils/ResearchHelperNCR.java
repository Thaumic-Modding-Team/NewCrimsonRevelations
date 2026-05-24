package mod.icarus.crimsonrevelations.utils;

import net.minecraftforge.fml.common.Loader;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thecodex6824.thaumcraftfix.api.research.ResearchCategoryTheorycraftFilter;

import java.util.HashSet;
import java.util.Set;

public class ResearchHelperNCR {
    public static final boolean isThaumcraftFixLoaded = Loader.isModLoaded("thaumcraftfix");

    public static ResearchCategory[] getResearchCategories() {
        // Use Thaumcraft Fix's filter if it's also installed.
        if (isThaumcraftFixLoaded) {
            return ResearchCategoryTheorycraftFilter.getAllowedTheorycraftCategories().toArray(new ResearchCategory[0]);
        } else {
            Set<ResearchCategory> categories = new HashSet<>();
            categories.add(ResearchCategories.getResearchCategory("ALCHEMY"));
            categories.add(ResearchCategories.getResearchCategory("ARTIFICE"));
            categories.add(ResearchCategories.getResearchCategory("AUROMANCY"));
            categories.add(ResearchCategories.getResearchCategory("BASICS"));
            categories.add(ResearchCategories.getResearchCategory("GOLEMANCY"));
            categories.add(ResearchCategories.getResearchCategory("INFUSION"));
            categories.add(ResearchCategories.getResearchCategory("ELDRITCH"));
            return categories.toArray(new ResearchCategory[0]);
        }
    }
}
