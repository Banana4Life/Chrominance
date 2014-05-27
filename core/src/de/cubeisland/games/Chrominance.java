package de.cubeisland.games;

import de.cubeisland.games.screen.MenuScreen;

public class Chrominance extends Base2DGame {

    @Override
    public void create() {
        super.create();
        this.setScreen(new MenuScreen(this));
    }
}
