package de.cubeisland.games.ui;

import de.cubeisland.games.ColorDefense;


public class MenuTitle extends MenuItem {

    public MenuTitle(Menu parent, String text) {
        this(parent, new LayoutOptions.Builder().padding(parent.getPadding()).font(parent.getFont()).titleFont(parent.getTitleFont()).build(), text);
    }

    public MenuTitle(Menu parent, LayoutOptions options, String text) {
        super(parent, (text != null && options.getFont() != null) ? options.getTitleFont().getBounds(text).width : 0, (text != null && options.getFont() != null) ? options.getTitleFont().getBounds(text).height : 0, text);
    }

    public MenuTitle(Menu parent, String text, OnClickListener listener) {
        this(parent, new LayoutOptions.Builder().padding(parent.getPadding()).font(parent.getFont()).titleFont(parent.getTitleFont()).build(), text, listener);
    }

    public MenuTitle(Menu parent, LayoutOptions options, String text, OnClickListener listener) {
        super(parent, options.getTitleFont().getBounds(text).width, options.getTitleFont().getBounds(text).height, text, listener);
    }

    public void render(ColorDefense game, float delta) {
        options.getTitleFont().draw(game.getBatch(), getText(), Math.round(getPosition().x + options.getPadding().x), Math.round(getPosition().y - options.getPadding().y));
    }

}
