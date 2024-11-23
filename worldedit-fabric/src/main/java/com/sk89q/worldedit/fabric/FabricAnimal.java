package com.sk89q.worldedit.fabric;

import com.sk89q.worldedit.fabric.internal.FabricEntity;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Parrot;

public class FabricAnimal extends FabricEntity implements com.sk89q.worldedit.world.animal.Animal {
    private final Animal animal;
    private String variant;

    public FabricAnimal(Animal animal) {
        super(animal);
        this.animal = animal;
        variant = null;
    }

    @Override
    public void setBaby(boolean baby) {
        animal.setBaby(baby);
    }

    @Override
    public boolean isBaby() {
        return animal.isBaby();
    }

    @Override
    public String getVariant() {
        return variant;
    }

    @Override
    public void setVariant(String variant) {
        if (!(animal instanceof VariantHolder<?>))
            return;
        if (animal instanceof Parrot)
            ((Parrot)animal).setVariant(Parrot.Variant.valueOf(variant.toUpperCase()));
        if (animal instanceof Cat)
            FabricCatVariantChanger.setVariant((Cat)animal, variant);
        this.variant = variant;
    }
}
