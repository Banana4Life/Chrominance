package de.cubeisland.games.resource.bag;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import de.cubeisland.games.resource.ResourceBag;

import java.lang.reflect.Field;

public class Shaders extends ResourceBag<ShaderProgram> {
    public ShaderProgram saturation;

    @Override
    protected ShaderProgram load(FileHandle basedir, Field field) {

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
