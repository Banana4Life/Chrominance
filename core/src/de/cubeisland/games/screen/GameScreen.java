package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ColorDefense;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.ui.Menu;
import de.cubeisland.games.ui.MenuItem;
import de.cubeisland.games.ui.MenuItemSelectListener;
import de.cubeisland.games.ui.MenuOptions;

public class GameScreen extends ScreenAdapter {

    private final ColorDefense game;
    private Level level;
    private OrthographicCamera camera;
    private boolean paused;
    private Menu pauseMenu;

    public GameScreen(final ColorDefense game) {
        this.game = game;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);

        paused = false;
        pauseMenu = new Menu.Builder().options(new MenuOptions.Builder().padding(new Vector2(20, 10)).alignment(MenuOptions.Alignment.CENTER).build()).build();
        pauseMenu.setTitle("Pause");
        pauseMenu.add(pauseMenu.createItem("Continue", new MenuItemSelectListener() {
            @Override
            public void onItemSelected(MenuItem item, Vector2 touchPoint) {
                unpause();
            }
        }));
        pauseMenu.add(pauseMenu.createItem("Options", new MenuItemSelectListener() {
            @Override
            public void onItemSelected(MenuItem item, Vector2 touchPoint) {
                System.out.println("Something different happened...");
            }
        }));
        pauseMenu.add(pauseMenu.createItem("Exit to main menu", new MenuItemSelectListener() {
            @Override
            public void onItemSelected(MenuItem item, Vector2 touchPoint) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        }));
        // Center it
        Vector2 centerPos = new Vector2((Gdx.graphics.getWidth() / 2) - (pauseMenu.getMaxWidth() / 2), (Gdx.graphics.getHeight() / 2) - (pauseMenu.getMaxHeight() / 2));
        pauseMenu.moveTo(centerPos);

        this.level = new Level(Gdx.files.internal("map.bmp"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        if (isPaused()) {
            game.batch.begin();
            pauseMenu.render(game);
            game.batch.end();
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

    public void unpause() {
        this.paused = false;
    }

    public void pause() {
        this.paused = true;
    }
}