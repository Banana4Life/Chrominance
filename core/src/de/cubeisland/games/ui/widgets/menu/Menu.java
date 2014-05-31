package de.cubeisland.games.ui.widgets.menu;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.layout.ListLayout;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.util.Pair;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static de.cubeisland.games.ui.HorizontalAlignment.CENTER;
import static de.cubeisland.games.ui.Sizing.FILL_PARENT;
import static de.cubeisland.games.ui.Sizing.STATIC;

public abstract class Menu<T extends Base2DGame> extends Container {

    private static final OrderComparator BY_ORDER = new OrderComparator();

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
        List<Pair<Integer, MenuEntry>> entries = new ArrayList<>();
        for (Method m : menu.getClass().getDeclaredMethods()) {
            if (!Modifier.isPublic(m.getModifiers())) {
                continue;
            }
            Entry entry = m.getAnnotation(Entry.class);
            if (entry == null) {
                continue;
            }

            entries.add(new Pair<>(entry.order(), new MenuEntry(entry.label(), menu.getFont())));
        }

        Collections.sort(entries, BY_ORDER);

        for (Pair<Integer, MenuEntry> entry : entries) {
            menu.addChild(entry.getRight());
        }
    }

    @SuppressWarnings("unchecked")
    public T getGame() {
        return (T) getRoot().getScreen().getGame();
    }

    @Override
    protected void recalculate() {
        this.setContentWidth(getParent().getContentWidth() * .75f);
        super.recalculate();
    }

    private static final class OrderComparator implements Comparator<Pair<Integer, MenuEntry>> {
        @Override
        public int compare(Pair<Integer, MenuEntry> pair1, Pair<Integer, MenuEntry> pair2) {
            if (pair1.getLeft() > pair2.getLeft()) {
                return 1;
            } else if (pair1.getLeft() < pair2.getLeft()) {
                return -1;
            }
            return 0;
        }
    }
}
