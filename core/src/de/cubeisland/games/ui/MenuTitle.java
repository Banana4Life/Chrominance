package de.cubeisland.games.ui;

import de.cubeisland.games.ColorDefense;


public class MenuTitle extends MenuItem {

    public MenuTitle(Menu parent, String text) {
        super(parent, text);
    }

    public MenuTitle(Menu parent, String text, OnClickListener listener) {
        super(parent, text, listener);
    }

    public void render(ColorDefense game, float delta) {
        options.getTitleFont().draw(game.batch, getText(), Math.round(getPosition().x + options.getPadding().x), Math.round(getPosition().y - options.getPadding().y));
    }

    public float getWidth() {
        return text.isEmpty() ? 0 : getContentWidth() + 2 * options.getPadding().x;
    }

    public float getContentWidth() {
        return text.isEmpty() ? 0 : options.getTitleFont().getBounds(getText()).width;
    }

    public float getHeight() {
        return text.isEmpty() ? 0 : getContentHeight() + 2 * options.getPadding().y;
    }

    public float getContentHeight() {
        return text.isEmpty() ? 0 : options.getTitleFont().getBounds(getText()).height;
    }
}
