package de.cubeisland.games.screen.menu;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.screen.GameScreen;
import de.cubeisland.games.screen.MainMenuScreen;
import de.cubeisland.games.ui.Sizing;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.widgets.menu.Entry;
import de.cubeisland.games.ui.widgets.menu.Menu;
import de.cubeisland.games.ui.widgets.menu.MenuAction;

public class PauseMenu extends Menu<Chrominance> {

    public PauseMenu(Font font) {
        super("Pause", font);
        setSizing(Sizing.FILL_PARENT);
        setBackgroundColor(new Color(0, 0, 0, .5f));
        setMargin(0);
        setForegroundColor(Color.WHITE);
        setHoverColor(Color.WHITE.cpy().mul(.8f));
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
    public void end() {
        getGame().exit();
    }
}
