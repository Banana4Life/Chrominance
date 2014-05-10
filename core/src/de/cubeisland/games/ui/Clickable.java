package de.cubeisland.games.ui;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;

public class Clickable extends Element {

    protected final LayoutOptions options;
    protected final String text;
    protected Rectangle hitbox;
    public final OnClickListener listener;


    public Clickable(LayoutOptions parent, String text) {
        this(ElementType.BUTTON, parent, text, new OnClickListener() {
            @Override
            public void onItemClicked(MenuItem item, Vector2 touchPoint) {
                System.out.println("MenuItem \"" + item.getText() + "\" was clicked");
            }
        });
    }

    public Clickable(ElementType type, LayoutOptions options, String text, OnClickListener listener) {
        super(type);
        this.options = options;
        this.text = text;
        this.listener = listener;
    }

    public void render(ColorDefense game, float delta) {
        options.getFont().draw(game.getBatch(), getText(), Math.round(getPosition().x + options.getPadding().x), Math.round(getPosition().y - options.getPadding().y));
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

    public String getText() {
        return text;
    }

    public float getWidth() {
        return getContentWidth() + 2 * options.getPadding().x;
    }

    public float getContentWidth() {
        return options.getFont().getBounds(getText()).width;
    }

    public float getHeight() {
        return getContentHeight() + 2 * options.getPadding().y;
    }

    public float getContentHeight() {
        return options.getFont().getBounds(getText()).height;
    }

}
