package de.cubeisland.games.component;

/**
 * Created by Jonas on 28.04.14.
 */
public abstract class Component<T> {
    private T owner;

    protected final void init(T owner) {
        this.owner = owner;
        this.onInit();
    }

    public T getOwner() {
        return this.owner;
    }

    protected void onInit() {
    }

    public void onAttach() {
    }

    public void onDetach() {
    }

    public abstract void update(float delta);
}
