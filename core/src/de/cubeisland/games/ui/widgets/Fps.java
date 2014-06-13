package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.ui.DrawContext;
import de.cubeisland.games.ui.Sizing;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;

public class Fps extends Widget {

    private final Label label;

    public Fps(Font font) {
        this.label = new Label().setFont(font);
        addChild(this.label);
        setBackgroundColor(new Color(0, 0, 0, .5f));
        setHorizontalSizing(Sizing.FIT_CONTENT);
    }

    @Override
    protected void draw(DrawContext context) {
        this.label.setText(String.valueOf(Gdx.graphics.getFramesPerSecond()));
        super.draw(context);
    }
}
