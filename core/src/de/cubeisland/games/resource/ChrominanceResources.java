package de.cubeisland.games.resource;

import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.games.resource.bag.*;

public class ChrominanceResources extends Resources {
    public final Fonts fonts;
    public final Maps maps;
    public final Shaders shaders;
    public final Sounds sounds;
    public final Textures textures;
    public final Towers towers;

    public ChrominanceResources(Reflector reflector) {
        this.fonts = new Fonts();
        this.shaders = new Shaders();
        this.sounds = new Sounds();
        this.textures = new Textures();
        this.towers = new Towers(reflector);
        this.maps = new Maps(this.towers);
    }
}
