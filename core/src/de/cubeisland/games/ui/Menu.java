package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.cubeisland.games.ColorDefense;

import java.util.ArrayList;

/**
 * Created by Malte on 29.04.2014.
 */
public class Menu {

    private final static Vector2 DEFAULT_position = Vector2.Zero;
    private final static ArrayList<MenuItem> DEFAULT_items = new ArrayList<MenuItem>();
    private final static MenuItemSelectListener DEFAULT_listener = new MenuItemSelectListener() {
        @Override
        public void onItemSelected(MenuItem item, Vector2 touchPoint) {
            System.out.println("MenuItem \"" + item.getText() + "\" was clicked");
        }
    };
    private final static MenuOptions DEFAULT_options = new MenuOptions();

    private final Vector2 position;
    private final ArrayList<MenuItem> items;
    private final MenuItemSelectListener listener;
    private final MenuOptions options;
    private final Vector3 touchPoint = Vector3.Zero;

    public Menu() {
        this(DEFAULT_position, DEFAULT_items, DEFAULT_listener, DEFAULT_options);
    }

    public Menu(Vector2 position) {
        this(position, DEFAULT_items, DEFAULT_listener, DEFAULT_options);
    }

    public Menu(Vector2 position, ArrayList<MenuItem> items) {
        this(position, items, DEFAULT_listener, DEFAULT_options);
    }

    public Menu(Vector2 position, ArrayList<MenuItem> items, MenuItemSelectListener listener) {
        this(position, items, listener, DEFAULT_options);
    }

    public Menu(Vector2 position, ArrayList<MenuItem> items, MenuOptions options) {
        this(position, items, DEFAULT_listener, options);
    }

    public Menu(Vector2 position, MenuItemSelectListener listener) {
        this(position, DEFAULT_items, listener, DEFAULT_options);
    }

    public Menu(Vector2 position, MenuItemSelectListener listener, MenuOptions options) {
        this(position, DEFAULT_items, listener, options);
    }

    public Menu(Vector2 position, MenuOptions options) {
        this(position, DEFAULT_items, DEFAULT_listener, options);
    }

    public Menu(ArrayList<MenuItem> items) {
        this(DEFAULT_position, items, DEFAULT_listener, DEFAULT_options);
    }

    public Menu(ArrayList<MenuItem> items, MenuItemSelectListener listener) {
        this(DEFAULT_position, items, listener, DEFAULT_options);
    }

    public Menu(ArrayList<MenuItem> items, MenuOptions options) {
        this(DEFAULT_position, items, DEFAULT_listener, options);
    }

    public Menu(ArrayList<MenuItem> items, MenuItemSelectListener listener, MenuOptions options) {
        this(DEFAULT_position, items, listener, options);
    }

    public Menu(MenuItemSelectListener listener) {
        this(DEFAULT_position, DEFAULT_items, listener, DEFAULT_options);
    }

    public Menu(MenuItemSelectListener listener, MenuOptions options) {
        this(DEFAULT_position, DEFAULT_items, listener, options);
    }

    public Menu(MenuOptions options) {
        this(DEFAULT_position, DEFAULT_items, DEFAULT_listener, options);
    }

    public Menu(Vector2 position, ArrayList<MenuItem> items, MenuItemSelectListener listener, MenuOptions options) {
        this.position = position;
        this.items = items;
        this.listener = listener;
        this.options = options;
    }

    public void render(ColorDefense game) {
        int maxWidth = 0;
        int maxHeight = 0;
        for (MenuItem item : items) {
            float width = getOptions().getFont().getBounds(item.getText()).width;
            float height = getOptions().getFont().getBounds(item.getText()).height;
        }
        renderItems(game);
        if (Gdx.input.justTouched()) {
            game.camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0)); // Translate the touched point to coordinates
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                Vector2 pos = getPosOfItem(item, i);
                if ((new Rectangle(pos.x, pos.y - item.getHeight(), item.getWidth(), item.getHeight())).contains(touchPoint.x, touchPoint.y)) {
                    listener.onItemSelected(item, new Vector2(touchPoint.x, touchPoint.y));
                }
            }
        }
    }

    public void renderItems(ColorDefense game) {
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            Vector2 pos = getPosOfItem(item, i);
            item.render(game, pos.x, pos.y);
        }
    }

    private Vector2 getPosOfItem(MenuItem item) {
        return getPosOfItem(item, 0);
    }

    private Vector2 getPosOfItem(MenuItem item, int i) {
        float x = 0, y = position.y;
        switch (options.getAlignment()) {
            case LEFT:
                x = position.x;
                break;
            case CENTER:
                x = position.x + (getMaxWidth() / 2 - item.getWidth() / 2);
                break;
            case RIGHT:
                x = position.x + getMaxWidth() - item.getWidth();
                break;
        }
        for (int h = 0; h <= i; h++) {
            y += items.get(h).getHeight();
        }
        return new Vector2(x, y);
    }

    public MenuOptions getOptions() {
        return options;
    }

    public MenuItem createItem(String text) {
        return new MenuItem(this, text);
    }

    public float getMaxWidth() {
        float maxWidth = 0;
        for (MenuItem item : items) {
            float width = item.getWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    public float getMaxHeight() {
        float maxHeight = 0;
        for (MenuItem item : items) {
            float height = item.getHeight();
            if (height > maxHeight) {
                maxHeight = height;
            }
        }
        return maxHeight;
    }

    public void add(MenuItem item) {
        items.add(item);
    }

    public void moveTo(Vector2 position) {
        if (position.x + getMaxWidth() > Gdx.graphics.getWidth()) {
            position.set(Gdx.graphics.getWidth() - getMaxWidth(), position.y);
        }
        if (position.y + getMaxHeight() > Gdx.graphics.getHeight()) {
            position.set(position.x, Gdx.graphics.getHeight() - getMaxHeight());
        }
        this.position.set(position);
    }
}
