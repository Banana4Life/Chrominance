package de.cubeisland.games.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
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
        BitmapFont font = parent.getOptions().getFont();
        Vector2 padding = parent.getOptions().getPadding();
        font.draw(game.batch, getText(), x + padding.x, y - padding.y - font.getBounds(getText()).height);
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
