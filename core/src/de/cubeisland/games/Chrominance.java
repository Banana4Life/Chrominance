package de.cubeisland.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.cubeisland.games.screen.GameScreen;
import de.cubeisland.games.screen.MenuScreen;

public class Chrominance extends BaseGame {

    @Override
    public void create() {
        super.create();
        this.setScreen(new MenuScreen(this));
    }
}
