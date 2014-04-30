package de.cubeisland.games.entity;

import de.cubeisland.games.component.Component;

public class EntityFactory {
    public Entity createEntity(EntityType type) {
        Entity entity = new Entity(type);

        for (Class<? extends Component<Entity>> entry : type.getComponents()) {
            entity.attach(entry);
        }

        return entity;
    }
}
