package de.cubeisland.games.ui.widgets.menu;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Entry {
    String label();
    int order();
}
