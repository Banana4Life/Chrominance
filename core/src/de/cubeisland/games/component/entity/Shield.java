package de.cubeisland.games.component.entity;

import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.component.Component;

public class Shield extends ColorContainer {
    private float additionalRadius = 4;
    private boolean addedRadius = false;

    @Override
    public void onAttach() {
        super.onAttach();

        setAmount(5);
    }

    @Override
    public void update(float delta) {
        if (!addedRadius) {
            getOwner().get(Collidable.class).setVolume(new Circle(((Circle) getOwner().get(Collidable.class).getVolume()).getRadius() + additionalRadius));
            addedRadius = true;
        }
    }

    public void handle(Component sender, ColorContainerEmptyEvent event) {
        if (sender.getOwner() == getOwner()) {
            getOwner().get(Collidable.class).setVolume(new Circle(((Circle)getOwner().get(Collidable.class).getVolume()).getRadius() - additionalRadius));
            getOwner().detach(Shield.class);
        }
    }

    public float getAdditionalRadius() {
        return additionalRadius;
    }
}
