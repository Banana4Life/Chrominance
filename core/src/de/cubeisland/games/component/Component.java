package de.cubeisland.games.component;

import de.cubeisland.games.component.event.Event;
import de.cubeisland.games.component.event.EventHandler;
import de.cubeisland.games.component.event.MethodEventHandler;

/**
 *
 * @param <T> the type of the component holder for type safety
 */
public abstract class Component<T extends ComponentHolder<T>> implements Comparable<Component<?>> {
    private T owner;
    private final Class<? extends Component<?>> before;
    private final Class<? extends Component<?>> after;
    private final TickPhase tickPhase;

    {
        Before before = this.getClass().getAnnotation(Before.class);
        if (before != null) {
            this.before = before.value();
        } else {
            this.before = null;
        }
        After after = this.getClass().getAnnotation(After.class);
        if (after != null) {
            this.after = after.value();
        } else {
            this.after = null;
        }

        if (this.after != null && this.before != null && this.before == this.after) {
            throw new IllegalStateException("The before and after relations may not reference the same class!");
        }

        Phase phase = this.getClass().getAnnotation(Phase.class);
        if (phase != null) {
            this.tickPhase = phase.value();
        } else {
            this.tickPhase = TickPhase.BEGIN;
        }
    }

    protected final void init(T owner) {
        this.owner = owner;
        this.onInit();

        for (EventHandler<Event> handler : MethodEventHandler.parseHandlers(this)) {
            this.owner.registerEventHandler(handler);
        }
    }

    protected final void emit(Event event) {
        this.owner.emit(this, event);
    }

    public T getOwner() {
        return this.owner;
    }

    protected void onInit() {
    }

    public void onAttach() {
    }

    public void onDetach() {
    }

    public abstract void update(float delta);

    @Override
    public int compareTo(Component<?> o) {
        if (this.before == o.getClass() || o.after == this.getClass())
        {
            return -1;
        }
        else if (this.after == o.getClass() || o.before == this.getClass())
        {
            return 1;
        }
        return 0;
    }

    public final boolean shouldTick(TickPhase currentPhase) {
        return this.tickPhase == currentPhase;
    }
}
