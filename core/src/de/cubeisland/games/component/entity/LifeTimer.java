package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class LifeTimer extends Component<Entity> {
    private long lifeTime = 0;
    private long currentLifeTime = 0;

    @Override
    public void update(float delta) {
        currentLifeTime += (long) (delta * 1000);
        if (currentLifeTime > lifeTime) {
            getOwner().die();
        }
    }

    public void setLifetime(long timeInMillis) {
        this.lifeTime = timeInMillis;
    }
}
