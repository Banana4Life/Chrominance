package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;

public class ShaderManager extends ResourceManager<ShaderProgram> {
    public ShaderProgram saturation;

    public ShaderManager(Chrominance game) {
        super(game, "./shaders");
    }

    @Override
    protected ShaderProgram makeResource(Field field, FileHandles fileMap) {
        ShaderProgram shader = new ShaderProgram(fileMap.get(field.getName() + ".vertex"), fileMap.get(field.getName() + ".fragment"));
        if (shader.isCompiled()) {
            return shader;
        } else {
            throw new RuntimeException("Error compiling shader\n" +  shader.getLog());
        }
    }
}
