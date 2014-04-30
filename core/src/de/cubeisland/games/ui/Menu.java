package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.cubeisland.games.ColorDefense;

import java.util.ArrayList;

/**
 * Created by Malte on 29.04.2014.
 */
public class Menu {
    private final Vector2 position;
    private final ArrayList<MenuItem> items;
    private final MenuOptions options;
    private final Vector3 touchPoint = Vector3.Zero;
    private boolean inputLock = false;

    public Menu(Vector2 position, ArrayList<MenuItem> items, MenuOptions options) {
        this.position = position;
        this.items = items;
        this.options = options;
    }

    public void render(ColorDefense game) {
        renderItems(game);

        if (Gdx.input.justTouched()) {
            game.camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0)); // Translate the touched point to coordinates
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                Vector2 pos = getPosOfItem(item, i);
                Rectangle hitbox;
                if (getOptions().getPaddingHit()) {
                    hitbox = new Rectangle(pos.x, pos.y - item.getHeight(), item.getWidth(), item.getHeight());
                } else {
                    // Maybe difference between horizontal and vertical hitting...
                    Vector2 padding = getOptions().getPadding();
                    hitbox = new Rectangle(pos.x + padding.x, pos.y - item.getHeight() + padding.y, item.getContentWidth(), item.getContentHeight());
                }
                if (hitbox.contains(touchPoint.x, touchPoint.y) && !inputLock) {
                    // Lock input
                    inputLock = true;
                    item.listener.onItemSelected(item, new Vector2(touchPoint.x, touchPoint.y));
                }
            }
            inputLock = false;
        }
    }

    public void renderItems(ColorDefense game) {
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            Vector2 pos = getPosOfItem(item, i);
            game.batch.end();
            /* DEBUG */
            ShapeRenderer shapes = new ShapeRenderer();
            shapes.begin(ShapeRenderer.ShapeType.Line);
            shapes.setProjectionMatrix(game.camera.combined);
            shapes.setColor(new Color(0, 1, 0, 0.5f));
            shapes.rect(pos.x, pos.y - item.getHeight(), item.getWidth(), item.getHeight());
            shapes.setColor(new Color(1,0,0,0.5f));
            shapes.rect(pos.x, pos.y - item.getHeight() + getOptions().getPadding().y, item.getWidth(), item.getHeight() - 2 * getOptions().getPadding().y);
            shapes.end();
            game.batch.begin();
            item.render(game, pos.x, pos.y);
        }
    }

    private Vector2 getPosOfItem(MenuItem item, int i) {
        float x = 0, y = position.y + getHeight();
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
        for (int h = 0; h < i; h++) {
            y -= items.get(h).getHeight();
        }
        return new Vector2(x, y);
    }

    public MenuOptions getOptions() {
        return options;
    }

    public MenuItem createItem(String text) {
        return new MenuItem(this, text);
    }

    public MenuItem createItem(String text, MenuItemSelectListener listener) {
        return new MenuItem(this, text, listener);
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

    public float getWidth() {
        return getMaxWidth();
    }

    public float getHeight() {
        float height = 0;
        for (MenuItem item: items) {
            height += item.getHeight();
        }
        return height;
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

    public static class Builder {
        private Vector2 position = Vector2.Zero;
        private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        private MenuOptions options = new MenuOptions.Builder().build();

        public Builder() {
        }
        public Builder position(Vector2 position) {
            this.position = position;
            return this;
        }
        public Builder items(ArrayList<MenuItem> items) {
            this.items = items;
            return this;
        }
        public Builder options(MenuOptions options) {
            this.options = options;
            return this;
        }
        public Menu build() {
            return new Menu(position, items, options);
        }
    }
}
