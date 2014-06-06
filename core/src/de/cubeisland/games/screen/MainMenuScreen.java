package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.screen.menu.MainMenu;
import de.cubeisland.games.ui.font.Font;

public class MainMenuScreen extends AbstractMenuScreen<Chrominance> {

    public MainMenuScreen(Chrominance game) {
        super(game);
    }

    @Override
    public void onShow() {
        final Font font = new Font(new FreeTypeFontGenerator(Gdx.files.internal("fonts/neou/neouBold.ttf")), 30);

        pushMenu(new MainMenu(getGame().fonts.menuFont));
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {
    }
}