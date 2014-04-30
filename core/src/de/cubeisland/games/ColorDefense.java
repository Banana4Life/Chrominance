package de.cubeisland.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.cubeisland.games.screen.MenuScreen;

public class ColorDefense extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;

        public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.setScreen(new MenuScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void resize(int width, int height) {
        super.resize(width, height);
        camera.setToOrtho(false, width, height);
    }



}
