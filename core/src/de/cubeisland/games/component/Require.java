package de.cubeisland.games.component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Require {
    Class<? extends Component<?>> value();
}
