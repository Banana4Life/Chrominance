package de.cubeisland.games.ui;


import de.cubeisland.games.ColorDefense;

public class Button extends Clickable {

    protected final String text;


    public Button(float width, float height, String text) {
        this(new LayoutOptions.Builder().build(), width, height, text);
    }

    public Button(LayoutOptions options, float width, float height, String text) {
        super(options, width, height);
        this.text = text;
    }

    public Button(ElementType type, LayoutOptions options, float width, float height, String text) {
        super(type, options, width, height);
        System.out.println(text + ": " + (options.getFont() == null));
        //options.getFont().getBounds(text).width, options.getFont().getBounds(text).height
        this.text = text;
    }

    public Button(ElementType type, LayoutOptions options, float width, float height, String text, OnClickListener listener) {
        super(type, options, width, height, listener);
        System.out.println(text + ": " + (options.getFont() == null));
        //options.getFont().getBounds(text).width, options.getFont().getBounds(text).height
        this.text = text;
    }

    public void render(ColorDefense game, float delta) {
        options.getFont().draw(game.getBatch(), getText(), Math.round(getPosition().x + options.getPadding().x), Math.round(getPosition().y - options.getPadding().y));
    }

    public String getText() {
        return text;
    }

}
