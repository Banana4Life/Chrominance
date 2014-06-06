package de.cubeisland.games.screen;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.screen.menu.MainMenu;

public class MainMenuScreen extends AbstractMenuScreen<Chrominance> {

    public MainMenuScreen(Chrominance game) {
        super(game);
    }

    @Override
    public void onShow() {
        pushMenu(new MainMenu(getGame().resources.fonts.menuFont));
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {
    }
}