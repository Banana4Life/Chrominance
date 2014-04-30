package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import de.cubeisland.games.ColorDefense;

/**
 * Created by Malte on 29.04.2014.
 */
public class MenuScreen extends ScreenAdapter {

    private final ColorDefense game;

    private OrthographicCamera camera;

    public MenuScreen(final ColorDefense game) {
        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);

        this.game.batch.begin();
        this.game.font.draw(this.game.batch, "Welcome to Drop!!! ", 100, 150);
        this.game.font.draw(this.game.batch, "Tap anywhere to begin!", 100, 100);
        this.game.batch.end();

        if (Gdx.input.isTouched()) {
            this.game.setScreen(new GameScreen(this.game));
            dispose();
        }
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

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }


    //...Rest of class omitted for succinctness.

}