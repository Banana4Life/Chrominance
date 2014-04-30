package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;
import de.cubeisland.games.ui.Menu;
import de.cubeisland.games.ui.MenuOptions;

/**
 * Created by Malte on 29.04.2014.
 */
public class MenuScreen extends ScreenAdapter {

    final ColorDefense game;
    final Menu menu;

    Vector2 pos;

    public MenuScreen(final ColorDefense game) {
        this.game = game;
        menu = new Menu.Builder().options(new MenuOptions.Builder().padding(new Vector2(20, 10)).alignment(MenuOptions.Alignment.CENTER).build()).build();
        menu.add(menu.createItem("ABCDEFGHIJKLM"));
        menu.add(menu.createItem("DEF"));
        menu.add(menu.createItem("GHI"));
        // Center it
        pos = new Vector2((Gdx.graphics.getWidth() / 2) - (menu.getMaxWidth() / 2), (Gdx.graphics.getHeight() / 2) - (menu.getMaxHeight() / 2));
        menu.moveTo(pos);
        //pos = new Vector2(Gdx.graphics.getWidth() / 2 );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        menu.render(game);
        game.batch.end();

//        if (Gdx.input.isTouched()) {
//            game.setScreen(new GameScreen(game));
//            dispose();
//        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
super.show();
    }

    @Override
    public void hide() {
super.hide();
    }

    @Override
    public void pause() {
super.pause();
    }

    @Override
    public void resume() {
super.resume();
    }

    @Override
    public void dispose() {
super.dispose();
    }

}