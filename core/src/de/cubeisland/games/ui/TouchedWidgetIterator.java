package de.cubeisland.games.ui;

import java.util.Iterator;

final class TouchedWidgetIterator implements Iterator<Widget> {

    private final RootWidget root;
    private final int x;
    private final int y;

    private Widget current = null;
    private Widget next = null;

    TouchedWidgetIterator(RootWidget root, int x, int y) {
        this.root = root;
        this.x = x;
        this.y = y;
    }

    private Widget nextInnerWidget() {
        if (this.current == null) {
            this.current = this.root;
            return this.current;
        }
        for (Widget child : this.current.getChildren()) {
            if (child.isActive() && child.containsPoint(x, y)) {
                this.current = child;
                return child;
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (this.next == null) {
            this.next = nextInnerWidget();
        }
        return this.next != null;
    }

    @Override
    public Widget next() {
        Widget next = this.next;
        if (next == null) {
            next = nextInnerWidget();
        }
        if (next == null) {
            throw new RuntimeException("No more touched widgets...");
        }
        this.next = null;
        return next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("You can't remove them here!");
    }
}
