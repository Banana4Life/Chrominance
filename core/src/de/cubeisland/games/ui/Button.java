package de.cubeisland.games.ui;


public class Button extends Clickable {
    public Button(String text) {
        super(new LayoutOptions.Builder().build(), text);
    }

    public Button(LayoutOptions options, String text) {
        super(options, text);
    }

    public Button(ElementType type, LayoutOptions options, String text, OnClickListener listener) {
        super(type, options, text, listener);
    }
}
