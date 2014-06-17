package de.cubeisland.games.resource;

import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.games.resource.bag.*;

public class ChrominanceResources extends Resources {
    public final Fonts fonts;
    public final Maps maps;
    public final Shaders shaders;
    public final Sounds sounds;
    public final Textures textures;
    public final Projectiles projectiles;
    public final Towers towers;
    public final Songs songs;

    public ChrominanceResources(Reflector reflector) {
        this.fonts = new Fonts();
        this.shaders = new Shaders();
        this.sounds = new Sounds();
        this.textures = new Textures();
        this.projectiles = new Projectiles(reflector);
        this.towers = new Towers(reflector, projectiles);
        this.maps = new Maps(reflector, this.towers);
        this.songs = new Songs();
    }
}
