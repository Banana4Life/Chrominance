package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.widgets.menu.MainMenu;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Image;
import de.cubeisland.games.ui.widgets.Label;

import static com.badlogic.gdx.graphics.Color.*;
import static de.cubeisland.games.ui.Sizing.FILL_PARENT;

public class MenuScreen extends AbstractMenuScreen<Chrominance> {

    private final Label label;

    public MenuScreen(Chrominance game) {
        super(game, new MainMenu());

        Widget rt = this.getRootWidget();

        Widget c1 = new Container().setBackgroundColor(GREEN ).setContentDimensions(100, 100).setMargin(10).setVerticalSizing(FILL_PARENT);
        Widget c2 = new Container().setBackgroundColor(RED   ).setContentDimensions( 70,  70).setMargin(10).setVerticalSizing(FILL_PARENT);
        Widget c3 = new Container().setBackgroundTexture(new Texture(Gdx.files.internal("map.bmp"))).setBackgroundColor(YELLOW).setContentHeight(300).setMargin(10).setVerticalSizing(FILL_PARENT).setPadding(3);

        label = new Label();
        label.setFont(new Font(new FreeTypeFontGenerator(Gdx.files.internal("fonts/neou/Neou-Bold.ttf")), c3, .2f))
             .setForegroundColor(BLACK);
        c3.addChild(label);

        Image img = new Image(new Texture(Gdx.files.internal("badlogic.jpg"))).setScale(2.5f);
        c3.addChild(img);

        rt.addChild(c1);
        c1.addChild(c2);
        c2.addChild(c3);
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

        label.setText("FPS: " + game.getFPS());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}