package com.sk89q.worldedit.world.animal;

import com.sk89q.worldedit.entity.Entity;

/**
 * Represents an animal in the minecraft world
 */
public interface Animal extends Entity {
    /**
     * Makes the animal a baby
     * @param baby - if it's a baby or not
     */
    void setBaby(boolean baby);

    /**
     * Gets the variant of the animal
     * @return - the id of the variant (or null if the animal doesn't have any)
     */
    String getVariant();

    /**
     * Sets the variant of the animal to the given one
     * @param variant - id of the variant
     */
    void setVariant(String variant);

    /**
     * Sets the age of the animal to the given one
     * @param age - the age
     */
    void setAge(int age);

    /**
     * Gets the age of the animal
     * @return - the age
     */
    int getAge();
}
