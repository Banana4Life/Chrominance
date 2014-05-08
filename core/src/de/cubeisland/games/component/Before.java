package de.cubeisland.games.component;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Before {
    Class<? extends Component<?>> value();
}
