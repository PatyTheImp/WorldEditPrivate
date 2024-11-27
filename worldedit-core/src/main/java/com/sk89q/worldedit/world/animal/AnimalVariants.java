package com.sk89q.worldedit.world.animal;

import java.util.List;
import java.util.Map;

public record AnimalVariants(Map<AnimalType, List<String>> variants) {

    // Static factory method to create an instance of AnimalVariants
    public static AnimalVariants create() {
        // Initialize the variants map
        Map<AnimalType, List<String>> variants = Map.of(
                new AnimalType("cat"), List.of(
                        "tabby",
                        "black",
                        "red",
                        "siamese",
                        "british_shorthair",
                        "calico",
                        "persian",
                        "ragdoll",
                        "white",
                        "jellie",
                        "all_black"
                ),
                new AnimalType("parrot"), List.of(
                        "red_blue",
                        "blue",
                        "green",
                        "yellow_blue",
                        "gray"
                ),
                new AnimalType("axolotl"), List.of(
                        "lucy",
                        "wild",
                        "gold",
                        "cyan",
                        "blue"
                ),
                new AnimalType("fox"), List.of(
                        "red",
                        "snow"
                ),
                new AnimalType("frog"), List.of(
                        "temperate",
                        "warm",
                        "cold"
                ),
                new AnimalType("horse"), List.of(
                        "white",
                        "creamy",
                        "chestnut",
                        "brown",
                        "black",
                        "gray",
                        "dark_brown"
                ),
                new AnimalType("llama"), List.of(
                        "creamy",
                        "white",
                        "brown",
                        "gray"
                ),
                new AnimalType("trader_llama"), List.of(
                        "creamy",
                        "white",
                        "brown",
                        "gray"
                ),
                new AnimalType("mooshroom"), List.of(
                        "red",
                        "brown"
                ),
                new AnimalType("rabbit"), List.of(
                        "white",
                        "white_splotched",
                        "gold",
                        "brown",
                        "black",
                        "salt",
                        "evil"
                )
        );

        return new AnimalVariants(variants);
    }

    // Method to retrieve the list of variants for a given AnimalType
    public List<String> getVariantsFor(AnimalType type) {
        return variants.getOrDefault(type, List.of()); // Return an empty list if the type is not found
    }
}

