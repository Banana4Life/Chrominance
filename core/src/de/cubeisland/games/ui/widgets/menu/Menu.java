package de.cubeisland.games.ui.widgets.menu;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ui.HorizontalAlignment;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.layout.ListLayout;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Label;
import de.cubeisland.games.util.Pair;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static de.cubeisland.games.ui.HorizontalAlignment.CENTER;
import static de.cubeisland.games.ui.Sizing.FILL_PARENT;

public abstract class Menu<T extends Base2DGame> extends Container {

    private static final OrderComparator BY_ORDER = new OrderComparator();

    private final String title;
    private Font font;

    protected Menu(String title, Font font) {
        this.title = title;
        this.font = font;
        setVerticalSizing(FILL_PARENT);
        setBackgroundColor(Color.WHITE);
        setMargin(30, 100);
        setLayout(new ListLayout());

        addChild(new Label().setFont(getFont().setSize(getFont().getSize() * 2)).setText(getTitle()).setMargin(10, 20).setAlignment(CENTER));

        Widget container = new Container().setLayout(new ListLayout());
        parseEntries(container, this);

        addChild(container);
    }

    public String getTitle() {
        return title;
    }

    public Font getFont() {
        return font.copy();
    }

    public void setFont(Font font) {
        this.font = font;
    }

    private static void parseEntries(Widget w, Menu menu) {
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
            w.addChild(entry.getRight());
        }
    }

    @SuppressWarnings("unchecked")
    public T getGame() {
        return (T) getRoot().getScreen().getGame();
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
