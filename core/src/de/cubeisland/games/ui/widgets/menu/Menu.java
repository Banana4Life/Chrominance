package de.cubeisland.games.ui.widgets.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.event.Event;
import de.cubeisland.games.event.EventHandler;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.event.MethodEventHandler;
import de.cubeisland.games.screen.AbstractMenuScreen;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.event.MouseEnterEvent;
import de.cubeisland.games.ui.event.MouseLeaveEvent;
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
    private Color hoverColor;

    protected Menu(String title, Font font) {
        this.title = title;
        setFont(font);
        setVerticalSizing(FIT_CONTENT);
        //setAlignment(MIDDLE);
        setMargin(30, 100);
        setLayout(new ListLayout());
        ;

        Widget titleContainer = new Container().setFont(getFont().setSize(getFont().getSize() * 3)).setMargin(20, 0);
        titleContainer.addChild(new Label().setText(getTitle()).setAlignment(CENTER));

        Widget entryContainer = new Container().setLayout(new ListLayout());
        parseEntries(entryContainer, this);

        addChild(titleContainer);
        addChild(entryContainer);

    }

    @Override
    protected void onAdded() {
        super.onAdded();
        if (this.hoverColor == null) {
            this.hoverColor = getForegroundColor();
        }
    }

    public Color getHoverColor() {
        return this.hoverColor.cpy();
    }

    public Menu<T> setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor.cpy();
        return this;
    }

    public String getTitle() {
        return title;
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

            MenuEntry menuEntry = new MenuEntry(entry.label());

            for (EventHandler<EventSender, Event> handler : MethodEventHandler.parseHandlers(menu.new EntryEventHandlers(m))) {
                menuEntry.registerEventHandler(handler);
            }

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

    private final class EntryEventHandlers {
        private final Method method;

        public EntryEventHandlers(Method method) {
            this.method = method;
        }

        @SuppressWarnings("unchecked")
        public void handle(EventSender sender, TouchDownEvent event) {
            try {
                Object result = method.invoke(Menu.this);
                if (result instanceof Screen) {
                    Menu.this.getGame().setScreen((Screen) result);
                } else if (result instanceof MenuAction) {
                    ((MenuAction) result).go(Menu.this.getGame().getScreen());
                } else if (result instanceof Menu) {
                    Screen screen = Menu.this.getGame().getScreen();
                    if (screen instanceof AbstractMenuScreen) {
                        ((AbstractMenuScreen) screen).pushMenu((Menu) result);
                    }
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }

        public void handle(EventSender sender, MouseEnterEvent event) {
            Widget w = event.getWidget();

            event.getWidget().setForegroundColor(getHoverColor());
        }

        public void handle(EventSender sender, MouseLeaveEvent event) {
            event.getWidget().setForegroundColor(null);
        }
    }
}
