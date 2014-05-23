package de.cubeisland.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.cubeisland.games.resourcemanager.MapManager;
import de.cubeisland.games.resourcemanager.ShaderManager;
import de.cubeisland.games.resourcemanager.TextureManager;
import de.cubeisland.games.screen.MenuScreen;

public class Chrominance extends Game {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    public TextureManager textureManager;
    public ShaderManager shaderManager;
    public MapManager mapManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.textureManager = new TextureManager();
        this.shaderManager = new ShaderManager();
        this.mapManager = new MapManager();
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.setToOrtho(false, width, height);
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
