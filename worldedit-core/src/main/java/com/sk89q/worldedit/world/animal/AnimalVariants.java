package com.sk89q.worldedit.world.animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Keeps a registry of the variants of each animal (the ones that have any)
 */
public class AnimalVariants {
    private static final AnimalVariants INSTANCE = new AnimalVariants();
    private final Map<String, List<String>> variants = new HashMap<>();

    private AnimalVariants() {
    }

    public static AnimalVariants getInstance() {
        return INSTANCE;
    }

    /**
     * Registers a single variant of the given animal
     * @param animalID - the id from the animal
     * @param variantID - the id from the variant
     */
    public void register(String animalID, String variantID) {
        variants.computeIfAbsent(animalID, k -> new ArrayList<>()).add(variantID);
    }

    /**
     * Registers all the variants of the given animal
     * @param animalID - the id from the animal
     * @param variantsList - a list of the variants
     */
    public void register(String animalID, List<String> variantsList) {
        variants.put(animalID, variantsList);
    }

    /**
     * Gets the list of variants of the given animal
     * @param animalID - the id from the animal
     * @return the list of variants of the given animal
     */
    public List<String> getVariantsFor(String animalID) {
        return variants.get(animalID);
    }
}

