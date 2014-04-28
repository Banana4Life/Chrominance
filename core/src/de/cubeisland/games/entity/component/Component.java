package de.cubeisland.games.entity.component;

import de.cubeisland.games.entity.Entity;

/**
 * Created by Jonas on 28.04.14.
 */
public abstract class Component {
    private Entity owner;

    protected final void init(Entity owner) {
        this.owner = owner;
        this.onInit();
    }

    public Entity getOwner() {
        return this.owner;
    }

    protected void onInit() {
    }

    public void onAttach() {
    }

    public void onDetach() {
    }

    public void update(int delta) {
    }
}
