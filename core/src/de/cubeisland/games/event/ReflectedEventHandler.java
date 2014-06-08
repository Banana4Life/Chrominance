package de.cubeisland.games.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ReflectedEventHandler<E extends Event, S extends EventSender> implements EventHandler<S, E> {

    private final Class<E> applicableEvent;
    private final Class<S> applicableSender;

    @SuppressWarnings("unchecked")
    protected ReflectedEventHandler() {
        Type t = getClass().getGenericSuperclass();
        if (t != ReflectedEventHandler.class) {
            //throw new IllegalArgumentException("You have to directly extend " + ReflectedEventHandler.class.getName() + " !");
        }
        Type[] typeArgs = ((ParameterizedType) t).getActualTypeArguments();
        this.applicableEvent = (Class<E>)typeArgs[0];
        this.applicableSender = (Class<S>)typeArgs[1];
    }

    @Override
    public Class<E> getApplicableEvent() {
        return this.applicableEvent;
    }

    @Override
    public Class<S> getApplicableSender() {
        return this.applicableSender;
    }
}
