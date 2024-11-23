package com.sk89q.worldedit.world.animal;

public enum AnimalIds {
    ARMADILLO("minecraft:armadillo"),
    AXOLOTL("minecraft:axolotl"),
    //BAT("minecraft:bat"), // doesn't work
    BEE("minecraft:bee"),
    CAMEL("minecraft:camel"),
    CAT("minecraft:cat"),
    CHICKEN("minecraft:chicken"),
    //COD("minecraft:cod"), // doesn't work
    COW("minecraft:cow"),
    //DOLPHIN("minecraft:dolphin"), // doesn't work
    DONKEY("minecraft:donkey"),
    FOX("minecraft:fox"),
    FROG("minecraft:frog"),
    GOAT("minecraft:goat"),
    HORSE("minecraft:horse"),
    LLAMA("minecraft:llama"),
    MOOSHROOM("minecraft:mooshroom"),
    MULE("minecraft:mule"),
    OCELOT("minecraft:ocelot"),
    PANDA("minecraft:panda"),
    PARROT("minecraft:parrot"),
    PIG("minecraft:pig"),
    POLAR_BEAR("minecraft:polar_bear"),
    //PUFFERFISH("minecraft:pufferfish"), // doesn't work
    RABBIT("minecraft:rabbit"),
    //SALMON("minecraft:salmon"), // doesn't work
    SHEEP("minecraft:sheep"),
    //SQUID("minecraft:squid"),  // doesn't work
    //TADPOLE("minecraft:tadpole"), // doesn't work
    TRADER_LLAMA("minecraft:trader_llama"),
    //TROPICAL_FISH("minecraft:tropical_fish"), // doesn't work
    TURTLE("minecraft:turtle"),
    WOLF("minecraft:wolf");

    private final String id;

    AnimalIds(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
