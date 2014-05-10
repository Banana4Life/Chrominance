package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.ui.Element;
import de.cubeisland.games.ui.Menu;
import de.cubeisland.games.ui.MenuItem;
import de.cubeisland.games.ui.OnClickListener;

public class GameScreen extends ScreenAdapter {

    private final ColorDefense game;
    private final ShapeRenderer shapes = new ShapeRenderer();
    private Level level;
    private boolean paused;
    private Menu pauseMenu;

    public GameScreen(final ColorDefense game) {
        this.game = game;

        paused = false;
        pauseMenu =  new Menu.Builder().alignment(Element.Alignment.CENTER).padding(new Vector2(20, 10)).build();
        pauseMenu.setTitle("Pause");
        pauseMenu.add(pauseMenu.createItem("Continue", new OnClickListener() {
            @Override
            public void onItemClicked(MenuItem item, Vector2 touchPoint) {
                unpauseGame();
            }
        }));
        pauseMenu.add(pauseMenu.createItem("Options", new OnClickListener() {
            @Override
            public void onItemClicked(MenuItem item, Vector2 touchPoint) {
                System.out.println("Something different happened...");
            }
        }));
        pauseMenu.add(pauseMenu.createItem("Exit to main menu", new OnClickListener() {
            @Override
            public void onItemClicked(MenuItem item, Vector2 touchPoint) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        }));
        // Center it
        Vector2 centerPos = new Vector2((Gdx.graphics.getWidth() / 2f) - (pauseMenu.getMaxWidth() / 2f), (Gdx.graphics.getHeight() / 2f) - (pauseMenu.getHeight() / 2f));
        pauseMenu.moveTo(centerPos);

        this.level = new Level(Gdx.files.internal("map.bmp"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getCamera().update();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        if (isPaused()) {
            // Render it but with delta zero
            this.level.update(0);

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapes.begin(ShapeRenderer.ShapeType.Filled);
            shapes.setProjectionMatrix(game.getCamera().combined);
            shapes.setColor(new Color(0, 0, 0, 0.4f));
            shapes.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapes.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            game.getBatch().begin();
            pauseMenu.render(game, delta);
            game.getBatch().end();
        } else {
            this.level.update(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            paused = true;
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void unpauseGame() {
        this.paused = false;
    }

    public void pauseGame() {
        this.paused = true;
    }
}