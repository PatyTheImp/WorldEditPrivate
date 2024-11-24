package com.sk89q.worldedit.fabric;

import com.sk89q.worldedit.fabric.internal.FabricEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Variant;

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
        if (variant == null)
            return;
        switch (animal) {
            case Parrot parrot -> parrot.setVariant(Parrot.Variant.valueOf(variant.toUpperCase()));
            case Axolotl axolotl -> axolotl.setVariant(Axolotl.Variant.valueOf(variant.toUpperCase()));
            case Fox fox -> fox.setVariant(Fox.Type.valueOf(variant.toUpperCase()));
            case Horse horse -> horse.setVariant(Variant.valueOf(variant.toUpperCase()));
            case Llama llama -> llama.setVariant(Llama.Variant.valueOf(variant.toUpperCase()));
            case MushroomCow mushroomCow ->
                    mushroomCow.setVariant(MushroomCow.MushroomType.valueOf(variant.toUpperCase()));
            case Rabbit rabbit -> rabbit.setVariant(Rabbit.Variant.valueOf(variant.toUpperCase()));
            case Frog frog -> FabricFrogVariantChanger.setVariant(frog, variant);
            case Cat cat -> FabricCatVariantChanger.setVariant(cat, variant);
            default -> throw new IllegalArgumentException();
        }
        this.variant = variant;
    }
}
