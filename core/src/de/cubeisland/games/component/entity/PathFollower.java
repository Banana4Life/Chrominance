package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Before;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Path;

@Before(Move.class)
public class PathFollower extends Component<Entity>
{
    private Path path;

    public Path getPath()
    {
        return path;
    }

    public void setPath(Path path)
    {
        this.path = path;
    }

    @Override
    public void update(float delta) {
        if (this.path != null) {

        }
    }
}
