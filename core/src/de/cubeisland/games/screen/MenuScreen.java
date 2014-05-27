package de.cubeisland.games.screen;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.ui.Sizing;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.menu.MainMenu;
import de.cubeisland.games.ui.widgets.Container;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.YELLOW;
import static de.cubeisland.games.ui.Sizing.STATIC;

public class MenuScreen extends AbstractMenuScreen<Chrominance> {

    public MenuScreen(Chrominance game) {
        super(game, new MainMenu());

        Widget rt = this.getRootWidget();

        Widget c1 = new Container().setBackgroundColor(GREEN ).setContentDimensions(100, 100).setMargin(10).setSizing(STATIC);
        Widget c2 = new Container().setBackgroundColor(RED   ).setContentDimensions( 70,  70).setMargin(10).setSizing(STATIC);
        Widget c3 = new Container().setBackgroundColor(YELLOW).setContentHeight(300).setMargin(10).setSizing(STATIC);

        rt.addChild(c1);
        c1.addChild(c2);
        c2.addChild(c3);
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

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