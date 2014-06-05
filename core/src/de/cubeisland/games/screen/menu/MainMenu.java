package de.cubeisland.games.screen.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.screen.CreditsScreen;
import de.cubeisland.games.screen.EndScreen;
import de.cubeisland.games.screen.GameScreen;
import de.cubeisland.games.screen.OptionsScreen;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.widgets.menu.Entry;
import de.cubeisland.games.ui.widgets.menu.Menu;

public class MainMenu extends Menu<Chrominance> {

    public MainMenu(Font font) {
        super("Main Menu", font);
        setForegroundColor(new Color(0.7f, 0.7f, 0.7f, 1));
    }

    @Entry(label = "Start", order = 0)
    public Screen start() {
        return new GameScreen(getGame());
    }

    @Entry(label = "Options", order = 10)
    public GameScreen options() {
        return new OptionsScreen(getGame());
    }

    @Entry(label = "Credits", order = 20)
    public GameScreen credits() {
        return new CreditsScreen(getGame());
    }

    @Entry(label = "Exit", order = 30)
    public GameScreen end() {
        return new EndScreen(getGame());
    }
}
