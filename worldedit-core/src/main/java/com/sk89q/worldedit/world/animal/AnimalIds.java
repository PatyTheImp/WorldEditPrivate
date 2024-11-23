package com.sk89q.worldedit.world.animal;

public enum AnimalIds {
    ARMADILLO("minecraft:armadillo"),
    AXOLOTL("minecraft:axolotl"),
    BAT("minecraft:bat"),
    BEE("minecraft:bee"),
    CAMEL("minecraft:camel"),
    CAT("minecraft:cat");

    private final String id;

    AnimalIds(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
