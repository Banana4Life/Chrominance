package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;
import de.cubeisland.games.ui.*;

public class MenuScreen extends ScreenAdapter {

    final ColorDefense game;
    final Menu menu;

    public MenuScreen(final ColorDefense game) {
        this.game = game;
        menu = new Menu.Builder().alignment(Element.Alignment.CENTER).padding(new Vector2(20, 10)).build();
        menu.setTitle("Color Defense");
        menu.add(menu.createItem("Start", new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }));
        menu.add(menu.createItem("Options"));
        menu.add(menu.createItem("Credits"));

        menu.add(menu.createItem("Exit", new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                Gdx.app.exit();
            }
        }));
        // Center it
        Vector2 centerPos = new Vector2((Gdx.graphics.getWidth() / 2f) - (menu.getMaxWidth() / 2f), (Gdx.graphics.getHeight() / 2f) - (menu.getHeight() / 2f));
        menu.moveTo(centerPos);

        //pos = new Vector2(Gdx.graphics.getWidth() / 2 );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getCamera().update();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().begin();
        menu.render(game, delta);
        game.getBatch().end();

//        if (Gdx.input.isTouched()) {
//            game.setScreen(new GameScreen(game));
//            dispose();
//        }
    }
}