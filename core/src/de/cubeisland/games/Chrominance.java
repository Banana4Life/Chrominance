package de.cubeisland.games;

import de.cubeisland.games.screen.MainMenuScreen;

public class Chrominance extends Base2DGame {

    @Override
    public void create() {
        super.create();
        this.setScreen(new MainMenuScreen(this));
    }
}
