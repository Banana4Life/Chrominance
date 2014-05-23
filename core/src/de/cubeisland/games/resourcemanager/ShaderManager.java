package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.lang.reflect.Field;
import java.util.Map;

public class ShaderManager extends ResourceManager {
    public ShaderProgram saturation;

    public ShaderManager() {
        super("./shaders");
    }

    @Override
    protected void makeResource(Field field, Map<String, FileHandle> fileMap) {
        try {
            ShaderProgram shader = new ShaderProgram(fileMap.get(field.getName() + ".vertex"), fileMap.get(field.getName() + ".fragment"));
            if (shader.isCompiled()) {
                field.set(this, shader);
            } else {
                Gdx.app.log("Error compiling shader", shader.getLog());
            }
        } catch (IllegalAccessException e) {
            Gdx.app.log("Error loading shaders", e.getMessage());
        }
    }
}
