package de.cubeisland.games.ui.widgets.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ReflectedEventHandler;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.event.TouchDownEvent;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.layout.ListLayout;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Label;
import de.cubeisland.games.util.Pair;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static de.cubeisland.games.ui.HorizontalAlignment.CENTER;
import static de.cubeisland.games.ui.Sizing.FIT_CONTENT;

public abstract class Menu<T extends Base2DGame> extends Container {

    private static final OrderComparator BY_ORDER = new OrderComparator();

    private final String title;
    private Font font;

    protected Menu(String title, Font font) {
        this.title = title;
        this.font = font;
        setVerticalSizing(FIT_CONTENT);
        //setAlignment(MIDDLE);
        setBackgroundColor(Color.WHITE);
        setMargin(30, 100);
        setLayout(new ListLayout());

        Widget titleContainer = new Container().setMargin(20, 0);
        titleContainer.addChild(new Label().setFont(getFont().setSize(getFont().getSize() * 3)).setText(getTitle()).setAlignment(CENTER));

        Widget entryContainer = new Container().setLayout(new ListLayout());
        parseEntries(entryContainer, this);

        addChild(titleContainer);
        addChild(entryContainer);
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

            MenuEntry menuEntry = new MenuEntry(entry.label(), menu.getFont());
            menuEntry.registerEventHandler(new ClickHandler(menu, m));

            entries.add(new Pair<>(entry.order(), menuEntry));
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

    private static final class ClickHandler extends ReflectedEventHandler<TouchDownEvent, EventSender> {
        private final Menu menu;
        private final Method method;

        public ClickHandler(Menu menu, Method method) {
            this.menu = menu;
            this.method = method;
        }

        @Override
        public void handle(EventSender sender, TouchDownEvent event) {
            System.out.println("Clicked!");
            try {
                Object result = method.invoke(this.menu);
                if (result instanceof Screen) {
                    menu.getGame().setScreen((Screen) result);
                } else if (result instanceof MenuAction) {
                    ((MenuAction) result).go(menu.getGame().getScreen());
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }
}
