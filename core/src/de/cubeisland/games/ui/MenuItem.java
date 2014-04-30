package de.cubeisland.games.ui;

import de.cubeisland.games.ColorDefense;

/**
 * Created by Malte on 29.04.2014.
 */
public class MenuItem {

    private final Menu parent;
    private final String text;

    public MenuItem(Menu parent, String text) {
        this.parent = parent;
        this.text = text;
    }

    public void render(ColorDefense game, float x, float y) {
        parent.getOptions().getFont().draw(game.batch, getText(), x - parent.getOptions().getPadding().x, y - parent.getOptions().getPadding().y - parent.getOptions().getFont().getBounds(getText()).height);
    }

    public Menu getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public float getWidth() {
        return parent.getOptions().getFont().getBounds(getText()).width + 2 * parent.getOptions().getPadding().x;
    }

    public float getHeight() {
        return parent.getOptions().getFont().getBounds(getText()).height + 2 * parent.getOptions().getPadding().y;
    }

}
