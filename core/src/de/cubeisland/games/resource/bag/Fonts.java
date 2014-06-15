package de.cubeisland.games.resource.bag;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.resource.ResourceBag;
import de.cubeisland.games.ui.font.Font;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Fonts extends ResourceBag<Font> {

    @Def(font = "neou/bold", size = 30, flipped = true)
    public Font menuFont;

    @Override
    protected Font load(FileHandle basedir, Field field) {
        Def def = field.getAnnotation(Def.class);
        if (def == null) {
            throw new IllegalArgumentException("Missing @Def annotation!");
        }

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(basedir.child(def.font().replace('/', File.separatorChar) + ".ttf"));

        return new Font(gen, def.flipped(), def.size());
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Def {
        String font();
        int size();
        boolean flipped() default false;
    }
}
