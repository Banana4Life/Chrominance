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
    public final MenuItemSelectListener listener;

    public MenuItem(Menu parent, String text) {
        this(parent, text, new MenuItemSelectListener() {
            @Override
            public void onItemSelected(MenuItem item, Vector2 touchPoint) {
                System.out.println("MenuItem \"" + item.getText() + "\" was clicked");
            }
        });
    }
    public MenuItem(Menu parent, String text, MenuItemSelectListener listener) {
        this.parent = parent;
        this.text = text;
        this.listener = listener;
    }

    public void render(ColorDefense game, float x, float y) {
        BitmapFont font = parent.getOptions().getFont();
        Vector2 padding = parent.getOptions().getPadding();
        font.draw(game.batch, getText(), x + padding.x, y - padding.y);
    }

    public Menu getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public float getWidth() {
        return getContentWidth() + 2 * parent.getOptions().getPadding().x;
    }

    public float getContentWidth() {
        return parent.getOptions().getFont().getBounds(getText()).width;
    }

    public float getHeight() {
        return getContentHeight() + 2 * parent.getOptions().getPadding().y;
    }

    public float getContentHeight() {
        return parent.getOptions().getFont().getBounds(getText()).height;
    }

}
