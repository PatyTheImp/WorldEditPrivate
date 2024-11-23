package com.sk89q.worldedit.fabric;

import com.sk89q.worldedit.fabric.internal.FabricEntity;
import net.minecraft.world.entity.animal.Animal;

public class FabricAnimal extends FabricEntity implements com.sk89q.worldedit.world.animal.Animal {
    private final Animal animal;

    public FabricAnimal(Animal animal) {
        super(animal);
        this.animal = animal;
    }

    @Override
    public void setBaby(boolean baby) {
        animal.setBaby(baby);
    }

    @Override
    public boolean isBaby() {
        return animal.isBaby();
    }
}
