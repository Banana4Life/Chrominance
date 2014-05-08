package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;
import de.cubeisland.games.ui.Menu;
import de.cubeisland.games.ui.MenuItem;
import de.cubeisland.games.ui.MenuItemSelectListener;
import de.cubeisland.games.ui.MenuOptions;

public class MenuScreen extends ScreenAdapter {

    final ColorDefense game;
    final Menu menu;

    Vector2 pos;

    public MenuScreen(final ColorDefense game) {
        this.game = game;
        menu = new Menu.Builder().options(new MenuOptions.Builder().padding(new Vector2(20, 10)).alignment(MenuOptions.Alignment.LEFT).build()).build();
        menu.setTitle("Color Defense");
        menu.add(menu.createItem("Start", new MenuItemSelectListener() {
            @Override
            public void onItemSelected(MenuItem item, Vector2 touchPoint) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }));
        menu.add(menu.createItem("Options"));
        menu.add(menu.createItem("Credits"));

        menu.add(menu.createItem("Exit", new MenuItemSelectListener() {
            @Override
            public void onItemSelected(MenuItem item, Vector2 touchPoint) {
                Gdx.app.exit();
            }
        }));
        // Center it
        pos = new Vector2((Gdx.graphics.getWidth() / 2) - (menu.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (menu.getHeight() / 2));
        menu.moveTo(pos);

        //pos = new Vector2(Gdx.graphics.getWidth() / 2 );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        menu.render(game);
       // menu.getOptions().getTitleFont().draw(game.batch, "COLOR", 0, 100);
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

}