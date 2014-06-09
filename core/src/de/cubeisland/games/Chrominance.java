package de.cubeisland.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.logging.Log;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.engine.reflect.codec.YamlCodec;
import de.cubeisland.games.resource.ChrominanceResources;
import de.cubeisland.games.screen.MainMenuScreen;
import de.cubeisland.games.util.converter.ColorConverter;
import de.cubeisland.games.util.converter.Vector2Converter;

public class Chrominance extends Base2DGame {

    private final Reflector reflector;
    public final ChrominanceResources resources;

    public Chrominance(Log logger) {
        super(logger);
        this.reflector = new Reflector();
        reflector.getCodecManager().registerCodec(new YamlCodec());
        reflector.getDefaultConverterManager().registerConverter(Vector2.class, new Vector2Converter());
        reflector.getDefaultConverterManager().registerConverter(Color.class, new ColorConverter());

        this.resources = new ChrominanceResources(this.reflector);
    }

    @Override
    public void create() {
        super.create();
        this.resources.build();

        Pixmap cursorImage = new Pixmap(Gdx.files.internal("textures/crosshair.png"));
        Gdx.input.setCursorImage(cursorImage, Math.round(cursorImage.getWidth() / 2f), Math.round(cursorImage.getHeight() / 2f));

        this.resources.songs.main.setVolume(.05f);
        this.resources.songs.main.play();

        this.setScreen(new MainMenuScreen(this));
    }

    public Reflector getReflector() {
        return this.reflector;
    }

    @Override
    public void dispose() {
        super.dispose();
        this.resources.dispose();
        getLog().shutdown();
    }
}
