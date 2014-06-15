package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.FingerInput;
import de.cubeisland.games.component.Require;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.entity.FingerInputDetector.EntityTouchedEvent;

@Require(FingerInputDetector.class)
public class Focus extends Component<Entity> {

    private static Focus focused = null;

    @Override
    public void update(float delta) {
    }

    protected void handle(FingerInputDetector sender, EntityTouchedEvent event) {
        setFocused(!isFocused());
    }

    public boolean isFocused() {
        return focused == this;
    }

    public Focus setFocused(boolean focus) {
        if (focus) {
            focus(this);
        } else {
            unfocus(this);
        }
        return this;
    }

    public static void focus(Focus component) {
        if (focused != component) {
            focused = component;
        }
    }

    public static void unfocus(Focus component) {
        if (focused == component) {
            focused = null;
        }
    }
}
