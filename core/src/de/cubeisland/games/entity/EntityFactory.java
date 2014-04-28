package de.cubeisland.games.entity;

import de.cubeisland.games.component.Component;

/**
 * Created by phill_000 on 28.04.2014.
 */
public class EntityFactory {
    public Entity createEntity(EntityType type) {
        Entity entity = new Entity(type);

        for (Class<Class<? extends Component<Entity>> entry : type.getComponents()) {
            entity.attach(entry);
        }

        return entity;
    }
}
