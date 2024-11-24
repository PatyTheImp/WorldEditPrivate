package com.sk89q.worldedit.fabric;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;

public class FabricFrogVariantChanger {
    private FabricFrogVariantChanger() {

    }

    public static void setVariant(Frog frog, String variant) {
        Registry<FrogVariant> frogVariantRegistry = FabricWorldEdit.getRegistry(Registries.FROG_VARIANT);
        FrogVariant frogV;

        if (variant.equalsIgnoreCase("temperate"))
            frogV = frogVariantRegistry.getOrThrow(FrogVariant.TEMPERATE);
        else if (variant.equalsIgnoreCase("warm"))
            frogV = frogVariantRegistry.getOrThrow(FrogVariant.WARM);
        else if (variant.equalsIgnoreCase("cold"))
            frogV = frogVariantRegistry.getOrThrow(FrogVariant.COLD);
        else throw new java.lang.IllegalArgumentException();
        frog.setVariant(Holder.direct(frogV));
    }
}
