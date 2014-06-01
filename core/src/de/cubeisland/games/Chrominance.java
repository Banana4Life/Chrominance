package de.cubeisland.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.engine.reflect.codec.YamlCodec;
import de.cubeisland.games.resourcemanager.*;
import de.cubeisland.games.screen.MenuScreen;
import de.cubeisland.games.util.BetterBatch;
import de.cubeisland.games.util.Vector2Converter;

public class Chrominance extends Game {

    private BetterBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    private final Reflector reflector;

    public TextureManager textureManager;
    public ShaderManager shaderManager;
    public MapManager mapManager;
    public TowerManager towerManager;
    public SoundManager soundManager;

    public Chrominance() {
        this.reflector = new Reflector();
    }

    @Override
    public void create() {
        reflector.getCodecManager().registerCodec(new YamlCodec());
        reflector.getDefaultConverterManager().registerConverter(Vector2.class, new Vector2Converter());

        batch = new BetterBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.textureManager = new TextureManager(this);
        this.shaderManager = new ShaderManager(this);
        this.mapManager = new MapManager(this);
        this.towerManager = new TowerManager(this);
        this.soundManager = new SoundManager(this);
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

    public BetterBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Reflector getReflector() {
        return reflector;
    }

}
