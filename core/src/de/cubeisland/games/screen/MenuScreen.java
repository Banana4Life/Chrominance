package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;
import de.cubeisland.games.ui.Element;
import de.cubeisland.games.ui.Menu;
import de.cubeisland.games.ui.MenuItem;
import de.cubeisland.games.ui.OnClickListener;

public class MenuScreen extends ScreenAdapter {

    final ColorDefense game;
    final Menu menu;

    public MenuScreen(final ColorDefense game) {
        this.game = game;
        menu = new Menu.Builder().alignment(Element.Alignment.CENTER).padding(new Vector2(20, 10)).build();
        menu.setTitle("Color Defense");
        menu.add(menu.createItem("Start", new OnClickListener() {
            @Override
            public void onItemClicked(MenuItem item, Vector2 touchPoint) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }));
        menu.add(menu.createItem("Options"));
        menu.add(menu.createItem("Credits"));

        menu.add(menu.createItem("Exit", new OnClickListener() {
            @Override
            public void onItemClicked(MenuItem item, Vector2 touchPoint) {
                Gdx.app.exit();
            }
        }));
        // Center it
        Vector2 centerPos = new Vector2((Gdx.graphics.getWidth() / 2) - (menu.getMaxWidth() / 2), (Gdx.graphics.getHeight() / 2) - (menu.getHeight() / 2));
        menu.moveTo(centerPos);

        //pos = new Vector2(Gdx.graphics.getWidth() / 2 );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        menu.render(game, delta);
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