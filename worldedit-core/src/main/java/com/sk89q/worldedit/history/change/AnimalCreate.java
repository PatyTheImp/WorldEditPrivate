package com.sk89q.worldedit.history.change;

import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.entity.BaseEntity;
import com.sk89q.worldedit.history.UndoContext;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.world.animal.Animal;

import static com.google.common.base.Preconditions.checkNotNull;

public class AnimalCreate implements Change {
    private final Location location;
    private final BaseEntity state;
    private Animal animal;
    private boolean isBaby;

    /**
     * Create a new instance.
     *
     * @param location the location
     * @param state the state of the created animal
     * @param animal the animal that was created
     */
    public AnimalCreate(Location location, BaseEntity state, Animal animal) {
        checkNotNull(location);
        checkNotNull(state);
        checkNotNull(animal);
        this.location = location;
        this.state = state;
        this.animal = animal;
        isBaby = animal.isBaby();
    }

    @Override
    public void undo(UndoContext context) throws WorldEditException {
        if (animal != null) {
            isBaby = animal.isBaby();
            animal.remove();
            animal = null;
        }
    }

    @Override
    public void redo(UndoContext context) throws WorldEditException {
        animal = checkNotNull(context.getExtent()).createAnimal(location, state);
        if (animal != null)
            animal.setBaby(isBaby);
    }
}
