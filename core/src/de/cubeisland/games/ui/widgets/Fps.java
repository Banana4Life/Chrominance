package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.ui.DrawContext;
import de.cubeisland.games.ui.Sizing;
import de.cubeisland.games.ui.Widget;

public class Fps extends Widget {

    private final Label label;

    public Fps() {
        this.label = new Label();
        addChild(this.label);
        setBackgroundColor(new Color(0, 0, 0, .5f));
        setHorizontalSizing(Sizing.FIT_CONTENT);
        setPadding(3);
        setMargin(2);
    }

    @Override
    protected void draw(DrawContext context) {
        this.label.setText(String.valueOf(Gdx.graphics.getFramesPerSecond()));
        super.draw(context);
    }
}
