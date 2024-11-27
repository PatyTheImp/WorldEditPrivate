package com.sk89q.worldedit.fabric;

import com.sk89q.worldedit.fabric.internal.FabricEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
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
    public void setVariant(String variantID) {
        if (variantID == null)
            return;
        switch (animal) {
            case Parrot parrot -> parrot.setVariant(Parrot.Variant.valueOf(variantID.toUpperCase()));
            case Axolotl axolotl -> axolotl.setVariant(Axolotl.Variant.valueOf(variantID.toUpperCase()));
            case Fox fox -> fox.setVariant(Fox.Type.valueOf(variantID.toUpperCase()));
            case Horse horse -> horse.setVariant(Variant.valueOf(variantID.toUpperCase()));
            case Llama llama -> llama.setVariant(Llama.Variant.valueOf(variantID.toUpperCase()));
            case MushroomCow mushroomCow ->
                    mushroomCow.setVariant(MushroomCow.MushroomType.valueOf(variantID.toUpperCase()));
            case Rabbit rabbit -> rabbit.setVariant(Rabbit.Variant.valueOf(variantID.toUpperCase()));
            case Frog frog -> frog.setVariant(getFrogVariant(variantID));
            case Cat cat -> cat.setVariant(getCatVariant(variantID));
            default -> throw new IllegalArgumentException();
        }
        this.variant = variantID;
    }

    private Holder<CatVariant> getCatVariant(String variantID) {
        Registry<CatVariant> catVariantRegistry = FabricWorldEdit.getRegistry(Registries.CAT_VARIANT);
        CatVariant catV = catVariantRegistry.get(ResourceLocation.parse("minecraft:" + variantID));
        if (catV == null)
            throw new IllegalArgumentException();
        return Holder.direct(catV);
    }

    private Holder<FrogVariant> getFrogVariant(String variantID) {
        Registry<FrogVariant> frogVariantRegistry = FabricWorldEdit.getRegistry(Registries.FROG_VARIANT);
        FrogVariant frogV = frogVariantRegistry.get(ResourceLocation.parse("minecraft:" + variantID));
        if (frogV == null)
            throw new IllegalArgumentException();
        return Holder.direct(frogV);
    }
}
