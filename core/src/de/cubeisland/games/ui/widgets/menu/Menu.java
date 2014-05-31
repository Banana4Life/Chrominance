package de.cubeisland.games.ui.widgets.menu;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ui.HorizontalAlignment;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.layout.ListLayout;
import de.cubeisland.games.ui.widgets.Container;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static de.cubeisland.games.ui.HorizontalAlignment.CENTER;
import static de.cubeisland.games.ui.Sizing.FILL_PARENT;
import static de.cubeisland.games.ui.Sizing.STATIC;

public abstract class Menu<T extends Base2DGame> extends Container {

    private Font font;

    protected Menu(Font font) {
        this.font = font;
        setHorizontalSizing(STATIC);
        setVerticalSizing(FILL_PARENT);
        setHorizontalAlignment(CENTER);
        setBackgroundColor(Color.WHITE);
        setMargin(30);
        setLayout(new ListLayout());
        parseEntries(this);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    private static void parseEntries(Menu menu) {
        for (Method m : menu.getClass().getMethods()) {
            if (!Modifier.isPublic(m.getModifiers())) {
                continue;
            }
            Entry entry = m.getAnnotation(Entry.class);
            if (entry == null) {
                continue;
            }

            menu.addChild(new MenuEntry(entry.value(), menu.getFont()));
        }
    }

    @SuppressWarnings("unchecked")
    public T getGame() {
        return (T) getRoot().getScreen().getGame();
    }

    @Override
    protected void recalculate() {
        super.recalculate();
        this.setContentWidth(getParent().getContentWidth() * .75f);
    }
}
