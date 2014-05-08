package de.cubeisland.games.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.ComponentFactory;

public class EntityFactory {

    private final ComponentFactory componentFactory;

    public EntityFactory(ComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
    }

    public Entity createEntity(EntityType type) {
        Entity entity = new Entity(type);

        for (Class<? extends Component<Entity>> entry : type.getComponents()) {
            entity.attach(componentFactory.createComponent(entity, entry));
        }

        return entity;
    }
}
