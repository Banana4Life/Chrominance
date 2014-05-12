package de.cubeisland.games.ui;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;

public class Clickable extends Element {

    protected final LayoutOptions options;
    protected Rectangle hitbox;
    public final OnClickListener listener;
    private final float width, height;


    public Clickable(LayoutOptions options, float width, float height) {
        this(ElementType.BUTTON, options, width, height, new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                // TODO Remove default code
                System.out.println("No InputListener was defined");
            }
        });
    }
    public Clickable(ElementType type, LayoutOptions options, float width, float height) {
        this(type, options, width, height, new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                // TODO Remove default code
                System.out.println("No InputListener was defined");
            }
        });
    }

    public Clickable(ElementType type, LayoutOptions options, float width, float height, OnClickListener listener) {
        super(type);
        this.options = options;
        this.width = width;
        this.height = height;
        this.listener = listener;
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);
        //TODO Change to padding of item
        this.hitbox = new Rectangle(position.x + options.getPadding().x, position.y - getHeight() + options.getPadding().y, getContentWidth(), getContentHeight());
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    }

    public LayoutOptions getOptions() {
        return options;
    }

    public float getWidth() {
        return getContentWidth() + 2 * options.getPadding().x;
    }

    public float getContentWidth() {
        return this.width;
    }

    public float getHeight() {
        return getContentHeight() + 2 * options.getPadding().y;
    }

    public float getContentHeight() {
        return this.height;
    }

}
