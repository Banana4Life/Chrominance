package de.cubeisland.games.ui;

public interface Invalidatable {
    /**
     * This object can be invalidated. When an object gets invalidated, it is supposed to flush its caches and
     * recalculate its values as demanded.
     */
    void invalidate();
}
