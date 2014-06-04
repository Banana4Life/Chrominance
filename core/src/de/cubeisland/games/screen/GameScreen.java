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
        shader = game.shaderManager.saturation;

        game.getBatch().setShader(shader);

        paused = false;
        pauseMenu =  new Menu.Builder().alignment(Element.Alignment.CENTER).padding(new Vector2(20, 10)).build();
        pauseMenu.setTitle("Pause");
        pauseMenu.add(pauseMenu.createItem("Continue", new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                unpauseGame();
            }
        }));
        pauseMenu.add(pauseMenu.createItem("Options", new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                System.out.println("Something different happened...");
            }
        }));
        pauseMenu.add(pauseMenu.createItem("Exit to main menu", new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        }));
        // Center it
        Vector2 centerPos = new Vector2((Gdx.graphics.getWidth() / 2f) - (pauseMenu.getMaxWidth() / 2f), (Gdx.graphics.getHeight() / 2f) - (pauseMenu.getHeight() / 2f));
        pauseMenu.moveTo(centerPos);

        this.level = new Level(this, this.game.mapManager.map2);
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