package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.ui.font.Font;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class FontManager extends ResourceManager<Font> {

    @Def(font = "neouBold", size = 30)
    public Font menuFont;

    public FontManager() {
        super("fonts");
    }

    @Override
    protected Font makeResource(FileHandle basedir, Field field) {
        Def def = field.getAnnotation(Def.class);
        if (def == null) {
            throw new IllegalArgumentException("Missing @Def annotation!");
        }

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(basedir.child(fieldToPath(field) + ".ttf"));

        return new Font(gen, def.size());
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Def {
        String font();
        int size();
    }
}
