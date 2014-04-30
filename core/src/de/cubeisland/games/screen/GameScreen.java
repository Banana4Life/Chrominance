package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import de.cubeisland.games.ColorDefense;
import de.cubeisland.games.level.Level;

/**
 * Created by Malte on 29.04.2014.
 */

public class GameScreen extends ScreenAdapter {

    private final ColorDefense game;
    private Level level;
    private OrthographicCamera camera;

    public GameScreen(final ColorDefense game) {
        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);

        this.level = new Level(Gdx.files.internal("map.bmp"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);

        this.level.tick(Math.round(delta * 1000));

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            this.game.setScreen(new MenuScreen(this.game));
            dispose();
        }
    }
}