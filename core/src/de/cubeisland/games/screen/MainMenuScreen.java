package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.screen.menu.MainMenu;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;

public class MainMenuScreen extends AbstractMenuScreen<Chrominance> {

    private Widget c1;

    public MainMenuScreen(Chrominance game) {
        super(game);

        final Font font = new Font(new FreeTypeFontGenerator(Gdx.files.internal("fonts/neou/Neou-Bold.ttf")), 30);

        pushMenu(new MainMenu(font));

//        RootWidget<Chrominance> rt = getRootWidget();
//
//        c1 = new Container().setX(20).setContentDimensions(100, 200).setSizing(Sizing.STATIC).setBackgroundColor(Color.GRAY).setMargin(10).setPadding(5);
//        Widget c2 = new Container().setContentDimensions(50, 100).setSizing(Sizing.STATIC).setBackgroundColor(Color.BLUE).setMargin(2).setHorizontalAlignment(HorizontalAlignment.CENTER);
//        Widget c3 = new Container().setContentDimensions(25, 50).setSizing(Sizing.STATIC).setBackgroundColor(Color.ORANGE).setMargin(2).setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//        rt.addChild(c1);
//        c1.addChild(c2);
//        c2.addChild(c3);
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {
        //c1.setX(c1.getX() + delta * 100);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}