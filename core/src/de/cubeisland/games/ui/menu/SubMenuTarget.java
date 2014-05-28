package de.cubeisland.games.ui.menu;

public class SubMenuTarget implements MenuTarget {
    private final Menu menu;

    public SubMenuTarget(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void go() {
        // show menu
    }
}
