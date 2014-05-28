package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.entity.type.Tower;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TowerManager extends ResourceManager {
    public Tower towerSlow;
    public Tower towerPower;
    public Tower towerHighFreq;
    public Tower towerRange;
    public Tower towerPoison;

    public TowerManager() {
        super("./tower");
    }

    @Override
    protected void makeResource(Field field, Map<String, FileHandle> fileMap) {
        final String fieldName = field.getName();
        final Texture baseTexture;
        final FileHandle baseFile = fileMap.get(fieldName + "Base");
        if (baseFile != null) {
            baseTexture = new Texture(baseFile);
        } else {
            baseTexture = new Texture(fileMap.get("towerBase"));
        }
        final Texture turretTexture = new Texture(fileMap.get(fieldName + "Turret"));
        List<Vector2> muzzleOffset = new ArrayList<>();
        muzzleOffset.add(new Vector2(0, 0));
        try {
            field.set(this, new Tower()
                                .setCenterOffset(new Vector2(10, 0))
                                .setMuzzleOffset(muzzleOffset)
                                .setTurretTexture(turretTexture)
                                .setBaseTexture(baseTexture));
        } catch (IllegalAccessException e) {
            Gdx.app.log("Error loading towers", e.getMessage());
        }
    }
}
