package com.sk89q.worldedit.fabric.animal;

import com.sk89q.worldedit.fabric.FabricWorldEdit;
import com.sk89q.worldedit.fabric.internal.FabricEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Variant;

/**
 * Represents an animal entity on the Fabric platform
 */
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
            case Frog frog -> frog.setVariant(getVariant(variantID, FabricWorldEdit.getRegistry(Registries.FROG_VARIANT)));
            case Cat cat ->  cat.setVariant(getVariant(variantID, FabricWorldEdit.getRegistry(Registries.CAT_VARIANT)));
            default -> throw new IllegalArgumentException();
        }
        this.variant = variantID;
    }

    @Override
    public void setAge(int age) {
        animal.setAge(age);

    }

    @Override
    public int getAge() {
        return animal.getAge();
    }

    /**
     * Gets a variant from the registries
     * @param variantID - the id of the variant
     * @param registry - the registry key for the variant type
     * @param <T> - the type of the variant
     * @return - the holder of the variant
     */
    private <T> Holder<T> getVariant(String variantID, Registry<T> registry) {
        T variant = registry.get(ResourceLocation.parse("minecraft:" + variantID));
        if (variant == null)
            throw new IllegalArgumentException();
        return Holder.direct(variant);
    }
}
