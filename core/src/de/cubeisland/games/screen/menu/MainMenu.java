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

    @Entry("Start")
    public Screen start() {
        return new GameScreen(getGame());
    }

    @Entry("Options")
    public GameScreen options() {
        return new OptionsScreen(getGame());
    }

    @Entry("Credits")
    public GameScreen credits() {
        return new CreditsScreen(getGame());
    }

    @Entry("Exit")
    public GameScreen end() {
        return new EndScreen(getGame());
    }

}
