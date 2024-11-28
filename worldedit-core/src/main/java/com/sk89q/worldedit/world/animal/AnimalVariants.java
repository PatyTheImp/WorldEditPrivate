package com.sk89q.worldedit.world.animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalVariants {
    private static final AnimalVariants INSTANCE = new AnimalVariants();
    private final Map<String, List<String>> variants = new HashMap<>();

    private AnimalVariants() {
    }

    public static AnimalVariants getInstance() {
        return INSTANCE;
    }

    public void register(String animalID, String variantID) {
        variants.computeIfAbsent(animalID, k -> new ArrayList<>()).add(variantID);
    }

    public void register(String animalID, List<String> variantsList) {
        variants.put(animalID, variantsList);
    }

    public List<String> getVariantsFor(String animalID) {
        return variants.get(animalID);
    }
}

