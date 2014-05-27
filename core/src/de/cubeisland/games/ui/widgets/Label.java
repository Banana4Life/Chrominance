package de.cubeisland.games.ui.widgets;

import de.cubeisland.games.ui.DrawContext;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;

public class Label extends Widget {
    private String text;
    private Font font;

    public Font getFont() {
        return font;
    }

    public Label setFont(Font font) {
        this.font = font;
        return this;
    }

    public String getText() {
        return text;
    }

    public Label setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    protected void draw(DrawContext context) {
        super.draw(context);
    }
}
