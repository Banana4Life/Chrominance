package de.cubeisland.games.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.level.Level;

import java.util.UUID;

public class EntityFactory {
    private final Level level;

    public EntityFactory(Level level) {
        this.level = level;
    }

    public Entity createEntity(EntityType type) {
        Entity entity = new Entity(UUID.randomUUID(), type, level);

        for (Class<? extends Component<Entity>> entry : type.getComponents()) {
            entity.attach(entry);
        }

        return entity;
    }
}
