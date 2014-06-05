package de.cubeisland.games.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.ui.widgets.menu.MenuAction;

public class GameScreen extends AbstractGameScreen<Chrominance> {

    public static final MenuAction CLOSE = new CloseMenuAction();

    private Level level;

    public GameScreen(final Chrominance game) {
        super(game);

        ShaderProgram.pedantic = false;

        game.getBatch().setShader(game.shaderManager.saturation);

        this.level = new Level(this, game.mapManager.map1);
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

        game.getBatch().begin();
        game.getBatch().pauseShader();
        game.getBatch().draw(game.textureManager.badlogic, 400, 100, 100, 100);
        game.getBatch().continueShader();
        game.shaderManager.saturation.setUniformf("Saturation", level.getSaturation());
        game.getBatch().draw(game.textureManager.badlogic, 500, 100, 100, 100);
        game.getBatch().end();

        this.level.update(delta);

        game.shaderManager.saturation.end();
    }


    private static class CloseMenuAction implements MenuAction {
        @Override
        public void go(Screen screen) {
            if (screen instanceof GameScreen) {
                
            }
        }
    }

    public void won() {
        //TODO: Implement things that happen when you win
        System.out.println("You won!");
    }
    public void lost() {
        //TODO: Implement things that happen when you lose
        System.out.println("You lose!");
    }
}