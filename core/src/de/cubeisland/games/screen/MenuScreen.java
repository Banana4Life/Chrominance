package de.cubeisland.games.screen;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.ui.menu.MainMenu;

public class MenuScreen extends AbstractMenuScreen<Chrominance> {

    public MenuScreen(Chrominance game) {
        super(game, new MainMenu());
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

//        game.getCamera().update();
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