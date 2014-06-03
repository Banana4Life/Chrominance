package de.cubeisland.games.component;

import de.cubeisland.games.event.Event;
import de.cubeisland.games.event.EventHandler;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.event.MethodEventHandler;

/**
 *
 * @param <T> the type of the component holder for type safety
 */
public abstract class Component<T extends ComponentHolder<T>> implements EventSender {
    private T owner;
    private final Class<? extends Component<?>> before;
    private final Class<? extends Component<?>> after;
    private final TickPhase tickPhase;

    protected Component() {
        Before beforeAnnotation = this.getClass().getAnnotation(Before.class);
        if (beforeAnnotation != null) {
            this.before = beforeAnnotation.value();
        } else {
            this.before = null;
        }
        After afterAnnotation = this.getClass().getAnnotation(After.class);
        if (afterAnnotation != null) {
            this.after = afterAnnotation.value();
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

        for (EventHandler<Event, EventSender> handler : MethodEventHandler.parseHandlers(this)) {
            this.owner.registerEventHandler(handler);
        }
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Component component = (Component) o;

        if (after != null ? !after.equals(component.after) : component.after != null) {
            return false;
        }
        if (before != null ? !before.equals(component.before) : component.before != null) {
            return false;
        }
        if (!owner.equals(component.owner)) {
            return false;
        }
        if (tickPhase != component.tickPhase) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + (before != null ? before.hashCode() : 0);
        result = 31 * result + (after != null ? after.hashCode() : 0);
        result = 31 * result + tickPhase.hashCode();
        return result;
    }

    public final boolean shouldTick(TickPhase currentPhase) {
        return this.tickPhase == currentPhase;
    }

    public Class<? extends Component<?>> getBefore() {
        return before;
    }

    public Class<? extends Component<?>> getAfter() {
        return after;
    }

    @Override
    public void trigger(EventSender sender, Event event) {
        this.owner.trigger(sender, event);
    }
}
