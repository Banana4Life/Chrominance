package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Spawner extends Component<Entity> {

    private Entity spawner;

    @Override
    public void update(float delta) {

    }

    public Entity get() {
        return this.spawner;
    }

    public Spawner set(Entity e) {
        this.spawner = e;
        return this;
    }
}
