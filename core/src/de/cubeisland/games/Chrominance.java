package de.cubeisland.games;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.engine.reflect.codec.YamlCodec;
import de.cubeisland.games.resourcemanager.*;
import de.cubeisland.games.screen.GameScreen;
import de.cubeisland.games.screen.MainMenuScreen;
import de.cubeisland.games.util.Vector2Converter;

public class Chrominance extends Base2DGame {

    private final Reflector reflector;

    public TextureManager textureManager;
    public ShaderManager shaderManager;
    public MapManager mapManager;
    public TowerManager towerManager;
    public SoundManager soundManager;

    public Chrominance() {
        this.reflector = new Reflector();
        reflector.getCodecManager().registerCodec(new YamlCodec());
        reflector.getDefaultConverterManager().registerConverter(Vector2.class, new Vector2Converter());
    }

    @Override
    public void create() {
        super.create();

        this.textureManager = new TextureManager(this);
        this.shaderManager = new ShaderManager(this);
        this.mapManager = new MapManager(this);
        this.towerManager = new TowerManager(this);
        this.soundManager = new SoundManager(this);

        this.setScreen(new MainMenuScreen(this));
//        this.setScreen(new GameScreen(this));
    }

    public Reflector getReflector() {
        return reflector;
    }

}
