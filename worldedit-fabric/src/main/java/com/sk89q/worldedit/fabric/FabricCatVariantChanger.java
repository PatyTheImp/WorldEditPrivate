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
        if (variant.equalsIgnoreCase("jellie"))
            catV = catVariantRegistry.getOrThrow(CatVariant.JELLIE);
        else if (variant.equalsIgnoreCase("tabby"))
            catV = catVariantRegistry.getOrThrow(CatVariant.TABBY);
        else if (variant.equalsIgnoreCase("black"))
            catV = catVariantRegistry.getOrThrow(CatVariant.BLACK);
        else throw new java.lang.IllegalArgumentException();
        cat.setVariant(Holder.direct(catV));
    }
}
