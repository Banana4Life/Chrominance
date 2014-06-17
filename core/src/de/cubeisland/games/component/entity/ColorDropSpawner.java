package de.cubeisland.games.component.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityTypes;
import de.cubeisland.games.event.EventSender;

import static de.cubeisland.games.component.TickPhase.BEGIN;
import static de.cubeisland.games.component.entity.FingerInputDetector.EntityBeginTouchEvent;

@Phase(BEGIN)
public class ColorDropSpawner extends Component<Entity> {

    private Entity spawnedDrop = null;

    @Override
    public void update(float delta) {
        if (this.spawnedDrop != null && !getOwner().get(FingerInputDetector.class).isTouchDown()) {
            this.spawnedDrop.die();
        }
    }


    public void handle(EventSender sender, EntityBeginTouchEvent event) {
        this.spawnedDrop = getOwner().getLevel().spawn(EntityTypes.COLOR_DROP, new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        this.spawnedDrop.get(Spawner.class).set(getOwner());
        this.spawnedDrop.get(ColorDropRenderer.class).setColor(getOwner().get(ColorContainer.class).getColor());
    }
}
