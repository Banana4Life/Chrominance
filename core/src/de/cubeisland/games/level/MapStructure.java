package de.cubeisland.games.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.type.Tower;
import de.cubeisland.games.level.tile.TileType;
import de.cubeisland.games.wave.PredefinedWaveGenerator;
import de.cubeisland.games.wave.Wave;
import de.cubeisland.games.wave.WaveGenerator;
import de.cubeisland.games.wave.WaveStructure;

import java.util.*;

public class MapStructure
{
    private final int number;
    private final TileType[][] mapData;
    private final List<Path> paths;
    private Vector2 palettePosition;
    private final Map<Vector2, Tower> towerLocations;
    private final float width;
    private final float height;
    private final Random random;
    private final Map<Integer, Tower> colorTowerLookup;
    private WaveGenerator generator;

    public MapStructure(List<Tower> towers, FileHandle fileHandle, WaveStructure waveStructure) {
        paths = new ArrayList<>();
        towerLocations = new HashMap<>();

        Pixmap rawMap = new Pixmap(fileHandle);
        this.width = rawMap.getWidth();
        this.height = rawMap.getHeight();

        this.generator = new PredefinedWaveGenerator(waveStructure);

        // build tower lookup table
        this.colorTowerLookup = new HashMap<>();
        for (Tower t : towers) {
            this.colorTowerLookup.put(Color.rgba8888(t.getIdColor()), t);
        }

        mapData = loadMap(rawMap);
        random = new Random();

        this.number = Integer.parseInt(fileHandle.nameWithoutExtension().replaceAll("[^\\d]", "").replaceAll("^0*", ""));
    }

    public int getNumber() {
        return this.number;
    }

    private TileType[][] loadMap(Pixmap rawMap) {
        TileType[][] tileMap = new TileType[rawMap.getWidth()][rawMap.getHeight()];

        for (int x = 0; x < rawMap.getWidth(); x++) {
            for (int y = 0; y < rawMap.getHeight(); y++) {
                final int pixelColor = rawMap.getPixel(x, y);
                TileType tileType = TileType.getByColorValue(pixelColor);
                tileMap[x][y] = tileType;
                if (tileType == TileType.NONE && colorTowerLookup.get(pixelColor) != null) {
                    this.towerLocations.put(new Vector2(x + 0.5f, rawMap.getHeight() - 0.5f - y), colorTowerLookup.get(rawMap.getPixel(x, y)));
                }
                if (tileType == TileType.PALETTE_POSITION) {
                    if (this.palettePosition != null) {
                        throw new IllegalArgumentException("The map has multiple palette positions! First: " + this.palettePosition + ", second: " + new Vector2(x, y));
                    }
                    this.palettePosition = new Vector2(x, y);
                }
            }
        }

        if (this.palettePosition == null) {
            throw new IllegalArgumentException("The map has no palette position!");
        }

        for (int x = 0; x < tileMap.length; x++) {
            for (int y = 0; y < tileMap[0].length; y++) {
                if (tileMap[x][y] == TileType.END_PATH) {
                    getPath(tileMap, x, y);
                }
            }
        }

        return tileMap;
    }

    private void getPath(TileType[][] map, int x, int y) {
        getPath(map, x, y, new Vector2(), new Vector2(), new ArrayList<Node>());
    }

    private void getPath(TileType[][] map, int x, int y, Vector2 currMove, Vector2 lastMove, List<Node> nodeList) {
        if (currMove.hasOppositeDirection(lastMove)) {
            return;
        }

        if (!(new Rectangle(0, 0, map.length - 1, map[0].length - 1).contains(x, y))) {
            return;
        }

        if ((map[x][y] == TileType.PATH || map[x][y] == TileType.BEGIN_PATH) && !currMove.hasSameDirection(lastMove)) {
            nodeList.add(new Node(new Vector2(x + 0.5f + currMove.cpy().rotate(180f).x, getHeight() - (y + 0.5f) + currMove.y)));
        }
        if (map[x][y] == TileType.BEGIN_PATH) {
            nodeList.add(new Node(new Vector2(x + 0.5f, getHeight() - (y + 0.5f))));

            Collections.reverse(nodeList);
            paths.add(new Path(this, nodeList));

            return;
        } else if (map[x][y] == TileType.END_PATH) {
            nodeList.add(new Node(new Vector2(x + 0.5f, map[0].length - (y + 0.5f))));
        }

        if (map[x][y] == TileType.PATH) {
            getPath(map, x + 1, y, new Vector2(1, 0), currMove, new ArrayList<>(nodeList));
            getPath(map, x - 1, y, new Vector2(-1, 0), currMove, new ArrayList<>(nodeList));
            getPath(map, x, y + 1, new Vector2(0, 1), currMove, new ArrayList<>(nodeList));
            getPath(map, x, y - 1, new Vector2(0, -1), currMove, new ArrayList<>(nodeList));
        } else if (map[x][y] == TileType.END_PATH) {
            getPath(map, x + 1, y, new Vector2(1, 0), new Vector2(1, 0), new ArrayList<>(nodeList));
            getPath(map, x - 1, y, new Vector2(-1, 0), new Vector2(-1, 0), new ArrayList<>(nodeList));
            getPath(map, x, y + 1, new Vector2(0, 1), new Vector2(0, 1), new ArrayList<>(nodeList));
            getPath(map, x, y - 1, new Vector2(0, -1), new Vector2(0, -1), new ArrayList<>(nodeList));
        }
    }

    public TileType getTypeAt(int x, int y) {
        return mapData[x][y];
    }

    public List<Path> getPaths() {
        return paths;
    }

    public Vector2 getPalettePosition() {
        return this.palettePosition.cpy();
    }

    public Map<Vector2, Tower> getTowerLocations() {
        return towerLocations;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getScale() {
        return Math.min(Gdx.graphics.getWidth() / this.width, Gdx.graphics.getHeight() / this.height);
    }

    public Vector2 getOffset() {
        float widthScale = Gdx.graphics.getWidth() / this.width;
        float heightScale = Gdx.graphics.getHeight() / this.height;

        if (widthScale > heightScale) {
            return new Vector2((widthScale - heightScale) * this.width / 2, 0);
        } else if (widthScale < heightScale) {
            return new Vector2(0, (heightScale - widthScale) * this.height / 2);
        } else {
            return new Vector2(0, 0);
        }
    }

    public Vector2 scale(Vector2 in) {
        return in.cpy().scl(getScale());
    }
    public Vector2 offset(Vector2 in) {
        return in.cpy().add(getOffset());
    }

    public Path getRandomPath() {
        return this.paths.get(this.random.nextInt(this.paths.size()));
    }

    public WaveGenerator getGenerator() {
        return generator;
    }
}
