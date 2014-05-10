package de.cubeisland.games.component;

/**
 *
 * @param <T> the type of the component holder for type safety
 */
public abstract class Component<T extends ComponentHolder<T>> implements Comparable<Component<?>> {
    private T owner;
    private Class<? extends Component<?>> before;
    private Class<? extends Component<?>> after;

    protected final void init(T owner) {
        this.owner = owner;
        this.onInit();

        Before before = this.getClass().getAnnotation(Before.class);
        if (before != null) {
            this.before = before.value();
        }
        After after = this.getClass().getAnnotation(After.class);
        if (after != null) {
            this.after = after.value();
        }
        if (this.after != null && this.before != null && this.before == this.after) {
            throw new IllegalStateException("The before and after relations may not reference the same class!");
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
}
