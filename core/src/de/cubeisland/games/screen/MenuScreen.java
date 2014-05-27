package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.ui.Sizing;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.menu.MainMenu;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Label;

import static com.badlogic.gdx.graphics.Color.*;
import static de.cubeisland.games.ui.Sizing.FILL_PARENT;
import static de.cubeisland.games.ui.Sizing.STATIC;

public class MenuScreen extends AbstractMenuScreen<Chrominance> {

    private final Label label;

    public MenuScreen(Chrominance game) {
        super(game, new MainMenu());

        Widget rt = this.getRootWidget();

        Widget c1 = new Container().setBackgroundColor(GREEN ).setContentDimensions(100, 100).setMargin(10).setVerticalSizing(FILL_PARENT);
        Widget c2 = new Container().setBackgroundColor(RED   ).setContentDimensions( 70,  70).setMargin(10).setVerticalSizing(FILL_PARENT);
        Widget c3 = new Container().setBackgroundColor(YELLOW).setContentHeight(300).setMargin(10).setVerticalSizing(FILL_PARENT).setPadding(3);

        label = new Label();
        label.setFont(new Font(new FreeTypeFontGenerator(Gdx.files.internal("fonts/neou/Neou-Bold.ttf")), c3, .5f))
             .setForegroundColor(BLACK);
        c3.addChild(label);

        rt.addChild(c1);
        c1.addChild(c2);
        c2.addChild(c3);
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

        label.setText("FPS: " + game.getFPS());

//        game.getBatch().setProjectionMatrix(game.getCamera().combined);
//        game.getBatch().begin();
//        menu.draw(game, delta);
//        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}