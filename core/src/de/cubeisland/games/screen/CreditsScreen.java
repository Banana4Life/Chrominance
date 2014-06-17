package de.cubeisland.games.screen;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.event.ReflectedEventHandler;
import de.cubeisland.games.ui.event.TouchUpEvent;
import de.cubeisland.games.ui.layout.ListLayout;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Label;

import static de.cubeisland.games.ui.HorizontalAlignment.CENTER;
import static de.cubeisland.games.ui.HorizontalAlignment.RIGHT;
import static de.cubeisland.games.ui.VerticalAlignment.BOTTOM;

public class CreditsScreen extends AbstractGameScreen<Chrominance> {

    public CreditsScreen(Chrominance game) {
        super(game);
    }

    @Override
    public void onShow() {
        super.onShow();

        getRootWidget().setFont(getGame().resources.fonts.menuFont);

        Container container = new Container();
        getRootWidget().addChild(container);
        container.setLayout(new ListLayout());

        Container headline = new Container();
        headline.addChild(label("Credits").setAlignment(CENTER));
        headline.setFont(getRootWidget().getFont().setSize(50))
                .setMargin(25, 0);
        container.addChild(headline);

        Container names = new Container();
        names.setLayout(new ListLayout())
                .setMargin(0, 140);

        names.addChild(headline("Code"));
        names.addChild(label("    Phillip Schichtel"));
        names.addChild(label("    Jonas Dann"));
        names.addChild(label("    Malte Heinzelmann"));
        names.addChild(headline("Sound Effects"));
        names.addChild(label("    Katharina Spinner"));
        names.addChild(headline("Graphical Effects"));
        names.addChild(label("    Jonas Dann"));
        names.addChild(label("    Marco Dörfler"));
        names.addChild(label("    Ralf Oberer"));
        names.addChild(label("    Katharina Spinner"));

        container.addChild(names);

        Label back = new Label().setText("Back");
        getRootWidget().addChild(back);
        back.setAlignment(RIGHT, BOTTOM)
            .setPadding(3)
            .registerEventHandler(new ReflectedEventHandler<EventSender, TouchUpEvent>() {
                @Override
                public void handle(EventSender sender, TouchUpEvent event) {
                    getGame().setScreen(new MainMenuScreen(getGame()));
                }
            });

    }

    private static Label label(String text) {
        Label l = new Label().setText(text);
        l.setMargin(5, 0);

        return l;
    }

    private static Label headline(String text) {
        Label l = label(text + ":");
        l.setForegroundColor(Color.RED);
        l.setMarginTop(l.getMarginTop() + 3);

        return l;
    }

    @Override
    public void renderScreen(Chrominance game, float delta) {

    }
}
