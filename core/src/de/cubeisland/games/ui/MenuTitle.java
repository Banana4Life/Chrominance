package de.cubeisland.games.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;


public class MenuTitle extends MenuItem {

    public MenuTitle(Menu parent, String text) {
        super(parent, text);
    }

    public MenuTitle(Menu parent, String text, MenuItemSelectListener listener) {
        super(parent, text, listener);
    }

    public void render(ColorDefense game, float x, float y) {
        BitmapFont font = parent.getOptions().getTitleFont();
        Vector2 padding = parent.getOptions().getPadding();
        font.draw(game.batch, getText(), Math.round(x + padding.x), Math.round(y - padding.y));
    }

    public float getWidth() {
        return text.isEmpty() ? 0 : getContentWidth() + 2 * parent.getOptions().getPadding().x;
    }

    public float getContentWidth() {
        return text.isEmpty() ? 0 : parent.getOptions().getTitleFont().getBounds(getText()).width;
    }

    public float getHeight() {
        return text.isEmpty() ? 0 : getContentHeight() + 2 * parent.getOptions().getPadding().y;
    }

    public float getContentHeight() {
        return text.isEmpty() ? 0 : parent.getOptions().getTitleFont().getBounds(getText()).height;
    }
}
