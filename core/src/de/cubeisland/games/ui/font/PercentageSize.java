package de.cubeisland.games.ui.font;

import de.cubeisland.games.ui.Widget;

public class PercentageSize implements SizeDefinition {
    private final Widget container;
    private final float percentage;

    public PercentageSize(Widget container, float percentage) {
        this.container = container;
        this.percentage = percentage;
    }

    @Override
    public int getSize() {
        return Math.round(container.getContentHeight() * percentage);
    }
}
