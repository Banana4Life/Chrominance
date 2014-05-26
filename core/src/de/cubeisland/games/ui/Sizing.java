package de.cubeisland.games.ui;

public enum Sizing {
    /**
     * Static sizing uses the specified width and height
     */
    STATIC,

    /**
     * Calculates the minimum required size based on the child elements
     */
    FIT_CONTENT,

    /**
     * Tries to fill the parent widget with this widget
     */
    FILL_PARENT,
}
