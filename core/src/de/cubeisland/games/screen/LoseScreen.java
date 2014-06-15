package de.cubeisland.games.screen;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.event.ReflectedEventHandler;
import de.cubeisland.games.ui.HorizontalAlignment;
import de.cubeisland.games.ui.RootWidget;
import de.cubeisland.games.ui.VerticalAlignment;
import de.cubeisland.games.ui.event.TouchUpEvent;
import de.cubeisland.games.ui.widgets.Label;

public class LoseScreen extends AbstractGameScreen<Chrominance> {

    public LoseScreen(Chrominance game) {
        super(game);
    }

    @Override
    public void onShow() {
        super.onShow();

        Label l = new Label().setText("You lost!").setFont(getGame().resources.fonts.menuFont);
        getRootWidget().registerEventHandler(new ReflectedEventHandler<RootWidget<Chrominance>, TouchUpEvent>() {
            @Override
            public void handle(RootWidget<Chrominance> sender, TouchUpEvent event) {
                System.out.println("hi!");
            }
        });

        getRootWidget().addChild(l.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE));
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

    }
}
