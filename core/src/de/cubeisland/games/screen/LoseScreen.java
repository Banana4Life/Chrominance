package de.cubeisland.games.screen;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.event.ReflectedEventHandler;
import de.cubeisland.games.level.MapStructure;
import de.cubeisland.games.ui.RootWidget;
import de.cubeisland.games.ui.event.TouchUpEvent;
import de.cubeisland.games.ui.layout.ListLayout;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Label;

public class LoseScreen extends AbstractGameScreen<Chrominance> {

    private final MapStructure map;

    public LoseScreen(Chrominance game, MapStructure map) {
        super(game);
        this.map = map;
    }

    @Override
    public void onShow() {
        super.onShow();

        Container c = new Container();
        c.setLayout(new ListLayout())
         .setFont(getGame().resources.fonts.menuFont)
         .setPadding(120, 130);

        Label loser = new Label().setText("You lost!");
        Label click = new Label().setText("Click to restart");

        c.addChild(loser.setMargin(10, 0));
        c.addChild(click.setMargin(10, 0));

        getRootWidget()
                .addChild(c)
                .registerEventHandler(new ReflectedEventHandler<RootWidget<Chrominance>, TouchUpEvent>() {
                    @Override
                    public void handle(RootWidget<Chrominance> sender, TouchUpEvent event) {
                        getGame().setScreen(new GameScreen(getGame(), map));
                    }
                });
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

    }
}
