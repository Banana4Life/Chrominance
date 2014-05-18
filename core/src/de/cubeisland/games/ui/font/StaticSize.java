package de.cubeisland.games.ui.font;

public class StaticSize implements SizeDefinition {
    private final int size;

    public StaticSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
