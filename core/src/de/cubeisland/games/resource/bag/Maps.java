package de.cubeisland.games.resource.bag;

import com.badlogic.gdx.files.FileHandle;
import de.cubeisland.engine.reflect.ReflectedYaml;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.level.MapStructure;
import de.cubeisland.games.resource.ResourceBag;
import de.cubeisland.games.wave.WaveStructure;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Maps extends ResourceBag<MapStructure> {
    public MapStructure map01;
    public MapStructure map02;
    public MapStructure map03;
    public MapStructure map05;
    public MapStructure map06;
    public MapStructure map07;
    public MapStructure map08;
    public MapStructure map09;
    public MapStructure map10;

    private final Towers towers;
    private final Reflector reflector;

    public Maps(Reflector reflector, Towers towers) {
        this.towers = towers;
        this.reflector = reflector;
    }

    @Override
    public void build() {
        this.towers.build();
        super.build();
        Collections.sort(this.resources, new Comparator<MapStructure>() {
            @Override
            public int compare(MapStructure o1, MapStructure o2) {
                return o1.getNumber() - o2.getNumber();
            }
        });
    }

    public MapStructure getFirst() {
        return this.resources.get(0);
    }

    public MapStructure getNext(MapStructure map) {
        int index = this.resources.indexOf(map);
        if (index == -1 || index == this.resources.size() - 1) {
            return null;
        }
        return this.resources.get(index + 1);
    }

    @Override
    protected MapStructure load(FileHandle basedir, Field field) {
        FileHandle mapdir = basedir.child(fieldToPath(field));

        WaveConfig config = this.reflector.load(WaveConfig.class, mapdir.child("config.yml").read());
        int number = Integer.parseInt(mapdir.nameWithoutExtension().replaceAll("[^\\d]", "").replaceAll("^0*", ""));

        return new MapStructure(this.towers.getResources(), mapdir.child("map.bmp"), number, new WaveStructure(config.waves));
    }

    public static class WaveConfig extends ReflectedYaml {
        public List<List<List<EntityType>>> waves;
    }
}
