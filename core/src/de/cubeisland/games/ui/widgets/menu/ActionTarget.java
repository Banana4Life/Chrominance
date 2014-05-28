package de.cubeisland.games.ui.widgets.menu;

public class ActionTarget implements MenuTarget {
    private final Runnable runnable;

    public ActionTarget(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void go() {
        this.runnable.run();
    }
}
