package com.sk89q.worldedit.fabric;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.CatVariant;

public class FabricCatVariantChanger {
    private FabricCatVariantChanger() {

    }

    public static void setVariant(Cat cat, String variant) {
        Registry<CatVariant> catVariantRegistry = FabricWorldEdit.getRegistry(Registries.CAT_VARIANT);
        CatVariant catV;

        if (variant.equalsIgnoreCase("tabby"))
            catV = catVariantRegistry.getOrThrow(CatVariant.TABBY);
        else if (variant.equalsIgnoreCase("black"))
            catV = catVariantRegistry.getOrThrow(CatVariant.BLACK);
        else if (variant.equalsIgnoreCase("red"))
            catV = catVariantRegistry.getOrThrow(CatVariant.RED);
        else if (variant.equalsIgnoreCase("siamese"))
            catV = catVariantRegistry.getOrThrow(CatVariant.SIAMESE);
        else if (variant.equalsIgnoreCase("british_shorthair"))
            catV = catVariantRegistry.getOrThrow(CatVariant.BRITISH_SHORTHAIR);
        else if (variant.equalsIgnoreCase("calico"))
            catV = catVariantRegistry.getOrThrow(CatVariant.CALICO);
        else if (variant.equalsIgnoreCase("persian"))
            catV = catVariantRegistry.getOrThrow(CatVariant.PERSIAN);
        else if (variant.equalsIgnoreCase("ragdoll"))
            catV = catVariantRegistry.getOrThrow(CatVariant.RAGDOLL);
        else if (variant.equalsIgnoreCase("white"))
            catV = catVariantRegistry.getOrThrow(CatVariant.WHITE);
        else if (variant.equalsIgnoreCase("all_black"))
            catV = catVariantRegistry.getOrThrow(CatVariant.ALL_BLACK);
        else if (variant.equalsIgnoreCase("jellie"))
            catV = catVariantRegistry.getOrThrow(CatVariant.JELLIE);
        else throw new java.lang.IllegalArgumentException();
        cat.setVariant(Holder.direct(catV));
    }
}
