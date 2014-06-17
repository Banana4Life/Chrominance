package de.cubeisland.games.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ReflectedEventHandler<S extends EventSender, E extends Event> implements EventHandler<S, E> {

    private final Class<S> applicableSender;
    private final Class<E> applicableEvent;

    protected ReflectedEventHandler() {
        Type t = getClass().getGenericSuperclass();
        if (t != ReflectedEventHandler.class) {
            //throw new IllegalArgumentException("You have to directly extend " + ReflectedEventHandler.class.getName() + " !");
        }
        Type[] typeArgs = ((ParameterizedType) t).getActualTypeArguments();
        this.applicableSender = getClassFromType(typeArgs[0]);
        this.applicableEvent = getClassFromType(typeArgs[1]);
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getClassFromType(Type t) {
        if (t instanceof Class) {
            return (Class<T>) t;
        } else if (t instanceof ParameterizedType) {
            return getClassFromType(((ParameterizedType) t).getRawType());
        } else {
            throw new IllegalArgumentException("Unsupported type!");
        }
    }

    @Override
    public Class<S> getApplicableSender() {
        return this.applicableSender;
    }

    @Override
    public Class<E> getApplicableEvent() {
        return this.applicableEvent;
    }
}
