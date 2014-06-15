package de.cubeisland.games.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.MapStructure;
import de.cubeisland.games.screen.menu.PauseMenu;
import de.cubeisland.games.ui.widgets.Fps;
import de.cubeisland.games.ui.widgets.menu.Menu;
import de.cubeisland.games.ui.widgets.menu.MenuAction;

public class GameScreen extends AbstractGameScreen<Chrominance> {

    public static final MenuAction CLOSE = new CloseMenuAction();

    private boolean paused = false;
    private Menu pauseMenu;
    private GameScreenInputProcessor inputProcessor;
    private Level level;

    public GameScreen(final Chrominance game, MapStructure map) {
        super(game);
        getRootWidget().setFont(game.resources.fonts.menuFont);

        ShaderProgram.pedantic = false;
        game.getBatch().setShader(game.resources.shaders.saturation);

        this.level = new Level(this, map);
        this.pauseMenu = new PauseMenu(game.resources.fonts.menuFont);
    }

    @Override
    public void onShow() {
        this.inputProcessor = new GameScreenInputProcessor(this);
        getGame().getInput().addProcessor(this.inputProcessor);
        this.getRootWidget().addChild(pauseMenu);
        pauseMenu.setActive(false);

        getRootWidget().addChild(new Fps());
    }

    @Override
    public void onHide() {
        if (this.inputProcessor != null) {
            getGame().getInput().removeProcessor(this.inputProcessor);
            this.inputProcessor = null;
        }
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

        if (isPaused()) {
            delta = 0;
        }

        game.getBatch().begin();
        game.getBatch().pauseShader();
        game.getBatch().draw(game.resources.textures.badlogic, 400, 100, 100, 100);
        game.getBatch().continueShader();
        game.resources.shaders.saturation.setUniformf("Saturation", level.getSaturation());
        game.getBatch().draw(game.resources.textures.badlogic, 500, 100, 100, 100);
        game.getBatch().end();

        this.level.update(delta);

        game.resources.shaders.saturation.end();
    }

    @Override
    public void pause() {
        if (!getGame().isDebug()) {
            setPaused(true);
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        this.pauseMenu.setActive(paused);
    }

    public boolean isPaused() {
        return this.paused;
    }

    @Override
    public void resume() {
    }

    public Level getLevel() {
        return level;
    }

    private static class CloseMenuAction implements MenuAction {
        @Override
        public void go(Screen screen) {
            if (screen instanceof GameScreen) {
                ((GameScreen)screen).setPaused(false);
            }
        }
    }

    public void won() {
        //TODO: Implement things that happen when you win
        System.out.println("You won!");
    }
    public void lost() {
        getGame().setScreen(new LoseScreen(getGame(), getLevel().getMap()));
    }
}