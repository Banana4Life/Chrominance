package de.cubeisland.games.screen.menu;

import com.badlogic.gdx.Screen;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.screen.*;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.widgets.menu.*;

public class MainMenu extends Menu<Chrominance> {

    public MainMenu(Font font) {
        super(font);
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
