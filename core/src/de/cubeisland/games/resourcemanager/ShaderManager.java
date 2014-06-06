package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;

public class ShaderManager extends ResourceManager<ShaderProgram> {
    public ShaderProgram saturation;

    public ShaderManager() {
        super("shaders");
    }

    @Override
    protected ShaderProgram makeResource(FileHandle basedir, Field field) {

        String path = fieldToPath(field);
        final FileHandle vertex = basedir.child(path + ".vertex.glsl");
        final FileHandle fragment = basedir.child(path + ".fragment.glsl");

        ShaderProgram shader = new ShaderProgram(vertex, fragment);
        if (shader.isCompiled()) {
            return shader;
        } else {
            throw new RuntimeException("Error compiling shader\n" +  shader.getLog());
        }
    }
}
