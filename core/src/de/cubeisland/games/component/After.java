package de.cubeisland.games.component;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface After {
    Class<? extends Component<?>> value();
}
