package de.cubeisland.games.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.level.tile.TileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Map
{
    private final TileType[][] mapData;
    private final List<Path> paths;
    private final List<Vector2> towerLocations;
    private final float width;
    private final float height;
    private final Random random;

    private Map(FileHandle fileHandle) {
        paths = new ArrayList<>();
        towerLocations = new ArrayList<>();

        Pixmap rawMap = new Pixmap(fileHandle);
        this.width = rawMap.getWidth();
        this.height = rawMap.getHeight();

        mapData = loadMap(rawMap);
        random = new Random();
    }

    private TileType[][] loadMap(Pixmap rawMap) {
        TileType[][] tileMap = new TileType[rawMap.getWidth()][rawMap.getHeight()];

        for (int x = 0; x < rawMap.getWidth(); x++) {
            for (int y = 0; y < rawMap.getHeight(); y++) {
                TileType tileType = TileType.getByColorValue(rawMap.getPixel(x, y));
                tileMap[x][y] = tileType;
                if (tileType == TileType.TOWER_SLOT) {
                    this.towerLocations.add(new Vector2(x + 0.5f, rawMap.getHeight() - 0.5f - y));
                }
            }
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

        if (map[x][y] == TileType.PATH && !currMove.hasSameDirection(lastMove)) {
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

    public static Map load(FileHandle fileHandle) {

        return new Map(fileHandle);
    }

    public List<Path> getPaths() {
        return paths;
    }

    public List<Vector2> getTowerLocations() {
        return towerLocations;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getHorizontalScale() {
        return Gdx.graphics.getWidth() / this.getWidth();
    }

    public float getVerticalScale() {
        return Gdx.graphics.getHeight() / this.getHeight();
    }

    public Vector2 scale(Vector2 in) {
        Vector2 out = in.cpy();

        out.x *= getHorizontalScale();
        out.y *= getVerticalScale();

        return out;
    }

    public Path getRandomPath() {
        return this.paths.get(this.random.nextInt(this.paths.size()));
    }
}
