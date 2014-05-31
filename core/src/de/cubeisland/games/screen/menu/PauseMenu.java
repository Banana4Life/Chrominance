package de.cubeisland.games.screen.menu;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.screen.EndScreen;
import de.cubeisland.games.screen.GameScreen;
import de.cubeisland.games.screen.MainMenuScreen;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.widgets.menu.Entry;
import de.cubeisland.games.ui.widgets.menu.Menu;
import de.cubeisland.games.ui.widgets.menu.MenuAction;

public class PauseMenu extends Menu<Chrominance> {

    protected PauseMenu(Font font) {
        super(font);
    }

    @Entry(label = "Continue", order = 0)
    public MenuAction continueGame() {
        return GameScreen.CLOSE;
    }

    @Entry(label = "Back to main menu", order = 10)
    public MainMenuScreen mainMenu() {
        return new MainMenuScreen(getGame());
    }

    @Entry(label = "Quit", order = 20)
    public EndScreen end() {
        return new EndScreen(getGame());
    }
}
