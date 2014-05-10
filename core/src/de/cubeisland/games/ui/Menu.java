package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.cubeisland.games.ColorDefense;

import java.util.ArrayList;
import java.util.List;

import static de.cubeisland.games.util.VectorUtil.zero;

public class Menu extends Container {

    private MenuTitle title;
    private final List<MenuItem> items;
    private final Vector3 touchPoint = Vector3.Zero;
    private final BitmapFont font, titleFont;

    public Menu(MenuTitle title, Vector2 position, List<MenuItem> items, Alignment alignment, Vector2 padding, BitmapFont font, BitmapFont titleFont) {
        super(ElementType.MENU, alignment, padding);
        setPosition(position);
        this.items = items;
        this.title = title == null ? new MenuTitle(this, "") : title;
        this.font = font;
        this.titleFont = titleFont;
        initialize();
    }

    public void initialize() {
        MenuItem item;
        Vector2 pos;

        pos = getPosOfItem(title, 0);
        title.setPosition(new Vector2(pos.x, position.y + getHeight()));

        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            pos = getPosOfItem(item, i);
            item.setPosition(pos);
        }
    }

    public void render(ColorDefense game, float delta) {
        // Render Title
        title.render(game, delta);
        // Render Items
        for (MenuItem item : items) {
            item.render(game, delta);
            /* DEBUG */
            //game.batch.end();
            //ShapeRenderer shapes = new ShapeRenderer();
            //shapes.begin(ShapeRenderer.ShapeType.Line);
            //shapes.setProjectionMatrix(game.camera.combined);
            //shapes.setColor(new Color(0, 1, 0, 0.5f));
            //shapes.rect(title.getPosition().x, title.getPosition().y - title.getHeight(), title.getWidth(), title.getHeight());
            //shapes.setColor(new Color(1,0,0,0.5f));
            //shapes.rect(title.getPosition().x, title.getPosition().y - title.getHeight() + getPadding().y, title.getWidth(), title.getHeight() - 2 * getPadding().y);
            //shapes.end();
            //game.batch.begin();
        }

        //FPS-Counter: options.getFont().draw(game.batch, Integer.toString(Gdx.graphics.getFramesPerSecond()), 10, 10);

        if (Gdx.input.justTouched()) {
            // Translate the touched point to coordinates
            game.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            for (MenuItem item : items) {
                if (item.getHitbox().contains(touchPoint.x, touchPoint.y)) {
                    item.listener.onItemClicked(item, new Vector2(touchPoint.x, touchPoint.y));
                    break;
                }
            }
        }
    }

    private Vector2 getPosOfItem(MenuItem item, int i) {
        float x;
        float y = getPosition().y + getHeight() - title.getHeight();

        switch (getAlignment()) {
            case CENTER:
                x = getPosition().x + (getMaxWidth() / 2 - item.getWidth() / 2);
                break;
            case RIGHT:
                x = getPosition().x + getMaxWidth() - item.getWidth();
                break;
            case LEFT:
            default:
                x = getPosition().x;
                break;
        }
        for (int h = 0; h < i; h++) {
            y -= items.get(h).getHeight();
        }
        return new Vector2(x, y);
    }

    public MenuItem createItem(String text) {
        return new MenuItem(this, text);
    }

    public MenuItem createItem(String text, OnClickListener listener) {
        return new MenuItem(this, text, listener);
    }

    public void setTitle(String text) {
        this.title = new MenuTitle(this, text);
        initialize();
    }

    public void setTitle(String text, OnClickListener listener) {
        this.title = new MenuTitle(this, text, listener);
        initialize();
    }

    public float getMaxWidth() {
        float maxWidth = 0;
        if (title.getWidth() > maxWidth) {
            maxWidth = title.getWidth();
        }
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
            height += title.getHeight();
        for (MenuItem item: items) {
            height += item.getHeight();
        }
        return height;
    }

    public void add(MenuItem item) {
        items.add(item);
        initialize();
    }

    public void moveTo(Vector2 position) {
        if (position.x + getMaxWidth() > Gdx.graphics.getWidth()) {
            position.set(Gdx.graphics.getWidth() - getMaxWidth(), position.y);
        }
        if (position.y + getMaxHeight() > Gdx.graphics.getHeight()) {
            position.set(position.x, Gdx.graphics.getHeight() - getMaxHeight());
        }
        this.position.set(position);
        initialize();
    }

    public MenuTitle getTitle() {
        return title;
    }

    public BitmapFont getFont() {
        return this.font;
    }

    public BitmapFont getTitleFont() {
        return this.titleFont;
    }

    public static class Builder {
        private MenuTitle title = null;
        private Vector2 position = zero();
        private List<MenuItem> items = new ArrayList<>();
        private Alignment alignment = Alignment.CENTER;
        private Vector2 padding = zero();
        private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/neou/Neou-Bold.ttf"));
        private BitmapFont font = generator.generateFont(40);
        private BitmapFont titleFont = generator.generateFont(60);

        public Builder() {
            generator.dispose();
            font.setColor(0.7f, 0.7f, 0.7f, 1);
            titleFont.setColor(0.7f, 0.7f, 0.7f, 1);
        }
        public Builder title(MenuTitle title) {
            this.title = title;
            return this;
        }
        public Builder position(Vector2 position) {
            this.position = position;
            return this;
        }
        public Builder items(ArrayList<MenuItem> items) {
            this.items = items;
            return this;
        }
        public Builder alignment(Alignment alignment) {
            this.alignment = alignment;
            return this;
        }
        public Builder padding(Vector2 padding) {
            this.padding = padding;
            return this;
        }
        public Builder font(BitmapFont font) {
            this.font = font;
            return this;
        }
        public Builder titleFont(BitmapFont titleFont) {
            this.titleFont = titleFont;
            return this;
        }
        public Menu build() {
            return new Menu(title, position, items, alignment, padding, font, titleFont);
        }
    }
}
