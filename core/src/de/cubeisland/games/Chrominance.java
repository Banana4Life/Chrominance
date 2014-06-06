package de.cubeisland.games;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.engine.reflect.codec.YamlCodec;
import de.cubeisland.games.resourcemanager.*;
import de.cubeisland.games.screen.MainMenuScreen;
import de.cubeisland.games.util.ColorConverter;
import de.cubeisland.games.util.Vector2Converter;

public class Chrominance extends Base2DGame {

    private final Reflector reflector;

    public TextureManager textures;
    public ShaderManager shaders;
    public MapManager maps;
    public TowerManager towers;
    public SoundManager sounds;
    public FontManager fonts;

    public Chrominance() {
        this.reflector = new Reflector();
        reflector.getCodecManager().registerCodec(new YamlCodec());
        reflector.getDefaultConverterManager().registerConverter(Vector2.class, new Vector2Converter());
        reflector.getDefaultConverterManager().registerConverter(Color.class, new ColorConverter());
    }

    @Override
    public void create() {
        super.create();

        this.textures = new TextureManager(this);
        this.shaders = new ShaderManager(this);
        this.towers = new TowerManager(this);
        this.maps = new MapManager(this);
        this.sounds = new SoundManager(this);
        this.fonts = new FontManager(this);

        this.setScreen(new MainMenuScreen(this));
    }

    public Reflector getReflector() {
        return reflector;
    }

}
