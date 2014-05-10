package de.cubeisland.games.component.event;

public class BrokenEventHandlerException extends RuntimeException {
    public BrokenEventHandlerException(String s, ReflectiveOperationException e) {
        super(s, e);
    }
}
