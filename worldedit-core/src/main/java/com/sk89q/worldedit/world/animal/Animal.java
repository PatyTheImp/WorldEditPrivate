package com.sk89q.worldedit.world.animal;

import com.sk89q.worldedit.entity.Entity;

public interface Animal extends Entity {
    void setBaby(boolean baby);
    boolean isBaby();
}
