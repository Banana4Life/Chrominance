package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.ui.*;
import de.cubeisland.games.ui.menu.Menu;

public class GameScreen extends AbstractGameScreen<Chrominance> {

    private final ShaderProgram shader;
    private Level level;
    private Menu pauseMenu;
    private Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));

    public GameScreen(final Chrominance game) {
        super(game);

        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(Gdx.files.internal("shaders/saturation.vertex.glsl"), Gdx.files.internal("shaders/saturation.fragment.glsl"));
        System.out.println(shader.isCompiled() ? "Shader compiled successfully." : shader.getLog());

        game.getBatch().setShader(shader);

        this.level = new Level(getGame(), Gdx.files.internal("map.bmp"));
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {
        game.getCamera().update();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        shader.begin();
        shader.setUniformf("Saturation", level.getSaturation());

        game.getBatch().begin();
        game.getBatch().draw(texture, 500, 100, 100, 100);
        game.getBatch().end();

        this.level.update(delta);

        shader.end();
    }
}