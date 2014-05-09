package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Health extends Component<Entity>
{

    private double health = 0;

    @Override
    public void update(float delta) {
        if (health <= 0) {
            getOwner().die();
        }
    }

    public double getHealth() {
        return health;
    }

    public Health setHealth(double health) {
        this.health = health;
        return this;
    }

    public Health damage(double damage) {
        this.setHealth(this.getHealth() - damage);
        return this;
    }

    public Health heal(double heal) {
        this.setHealth(this.getHealth() + heal);
        return this;
    }
}
