package de.cubeisland.games.event;

public class BrokenEventHandlerException extends RuntimeException {
    public BrokenEventHandlerException(String s, ReflectiveOperationException e) {
        super(s, e);
    }
}
