package de.cubeisland.games.ui.menu;

/**
 * Created by Phillip on 18.05.2014.
 */
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
