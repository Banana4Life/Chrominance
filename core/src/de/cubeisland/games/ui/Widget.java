package de.cubeisland.games.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Widget implements Invalidatable, Disposable {

    private static final WidgetDepthComparator BY_DEPTH = new WidgetDepthComparator();
    private static final AtomicInteger WIDGET_COUNTER = new AtomicInteger(0);
    private final int id;
    private Widget parent = null;
    private final List<Widget> children = new ArrayList<>();

    //region Positioning fields
    private Positioning         positioning         = Positioning.RELATIVE_ALIGNED;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private VerticalAlignment   verticalAlignment   = VerticalAlignment.TOP;

    private Sizing horizontalSizing = Sizing.FILL_PARENT;
    private Sizing verticalSizing   = Sizing.FIT_CONTENT;

    private int   depth         = 0;
    private float x             = 0;
    private float y             = 0;
    private float absX          = 0;
    private float absY          = 0;

    private float contentWidth  = 0;
    private float contentHeight = 0;

    private float paddingTop    = 0;
    private float paddingRight  = 0;
    private float paddingBottom = 0;
    private float paddingLeft   = 0;

    private float marginTop     = 0;
    private float marginRight   = 0;
    private float marginBottom  = 0;
    private float marginLeft    = 0;
    //endregion

    protected Widget() {
        id = WIDGET_COUNTER.getAndIncrement();
    }

    //region Getters and Setters
    public int getId() {
        return id;
    }

    public Widget getParent() {
        return parent;
    }

    public RootWidget getRoot() {
        return getParent().getRoot();
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Positioning getPositioning() {
        return positioning;
    }

    public void setPositioning(Positioning positioning) {
        this.positioning = positioning;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    public Widget setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public VerticalAlignment getVerticalAlignment() {
        return this.verticalAlignment;
    }

    public Widget setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    public Widget setAlignment(VerticalAlignment alignment) {
        this.setVerticalAlignment(alignment);
        return this;
    }

    public Widget setAlignment(HorizontalAlignment alignment) {
        this.setHorizontalAlignment(alignment);
        return this;
    }

    public Widget setAlignment(HorizontalAlignment horizontal, VerticalAlignment vertical) {
        this.setAlignment(horizontal);
        this.setAlignment(vertical);
        return this;
    }

    public float getX() {
        return x;
    }

    public Widget setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }

    public Widget setY(float y) {
        this.y = y;
        return this;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    public float getAbsoluteX() {
        return this.absX;
    }

    public float getAbsoluteY() {
        return this.absY;
    }

    public Vector2 getAbsolutePosition() {
        return new Vector2(absX, absY);
    }

    public Widget setPosition(Vector2 position) {
        return this.setPosition(position.x, position.y);
    }

    public Widget setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public float getPaddingTop() {
        return paddingTop;
    }

    public float getPaddingRight() {
        return paddingRight;
    }

    public float getPaddingBottom() {
        return paddingBottom;
    }

    public float getPaddingLeft() {
        return paddingLeft;
    }

    public Widget setPaddingTop(float paddingTop) {
        this.paddingTop = paddingTop;
        return this;
    }

    public Widget setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
        return this;
    }

    public Widget setPaddingBottom(float paddingBottom) {
        this.paddingBottom = paddingBottom;
        return this;
    }

    public Widget setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
        return this;
    }

    public Widget setPadding(float top, float right, float bottom, float left) {
        this.setPaddingTop(top);
        this.setPaddingRight(right);
        this.setPaddingBottom(bottom);
        this.setPaddingLeft(left);
        return this;
    }

    public Widget setPadding(float vertical, float horizontal) {
        return this.setPadding(vertical, horizontal, vertical, horizontal);
    }

    public Widget setPadding(float padding) {
        return this.setPadding(padding, padding);
    }

    public float getMarginTop() {
        return marginTop;
    }

    public Widget setMarginTop(float marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public Widget setMarginRight(float marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public Widget setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public float getMarginLeft() {
        return marginLeft;
    }

    public Widget setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public Widget setMargin(float top, float right, float bottom, float left) {
        this.setMarginTop(top);
        this.setMarginRight(right);
        this.setMarginBottom(bottom);
        this.setMarginLeft(left);
        return this;
    }

    public Widget setMargin(float horizontal, float vertical) {
        return this.setMargin(vertical, horizontal, vertical, horizontal);
    }

    public Widget setMargin(float value) {
        return this.setMargin(value, value);
    }

    public Widget setContentDimensions(float width, float height) {
        setContentWidth(width);
        setContentHeight(height);
        return this;
    }

    public float getWidth() {
        return getPaddingLeft() + getContentWidth() + getPaddingRight();
    }

    public float getContentWidth() {
        return contentWidth;
    }

    public Widget setContentWidth(float contentWidth) {
        this.contentWidth = contentWidth;
        return this;
    }

    public float getHeight() {
        return getPaddingTop() + getContentHeight() + getPaddingBottom();
    }

    public float getContentHeight() {
        return contentHeight;
    }

    public Sizing getHorizontalSizing() {
        return horizontalSizing;
    }

    public void setHorizontalSizing(Sizing horizontalSizing) {
        this.horizontalSizing = horizontalSizing;
    }

    public Sizing getVerticalSizing() {
        return verticalSizing;
    }

    public Widget setVerticalSizing(Sizing verticalSizing) {
        this.verticalSizing = verticalSizing;
        return this;
    }

    public Widget setSizing(Sizing sizing) {
        this.setHorizontalSizing(sizing);
        this.setVerticalSizing(sizing);
        return this;
    }

    public Widget setContentHeight(float contentHeight) {
        this.contentHeight = contentHeight;
        return this;
    }

    public Widget addChild(Widget widget) {
        if (widget == this) {
            throw new IllegalArgumentException("You can't add the widget as a child of itself!");
        }
        if (this.children.contains(widget) || this == widget.parent) {
            throw new IllegalArgumentException("The given widget is already a child!");
        }
        this.children.add(widget);
        widget.parent = this;
        Collections.sort(this.children, BY_DEPTH);
        widget.invalidate();
        return this;
    }

    public Widget removeChild(Widget widget) {
        if (widget.parent == this) {
            this.children.remove(widget);
            widget.parent = null;
        }

        return this;
    }

    public List<Widget> getChildren() {
        return new ArrayList<>(this.children);
    }
    //endregion

    @Override
    public final void invalidate() {
        this.recalculate();
        for (Widget child : this.children) {
            child.invalidate();
        }
    }

    @Override
    public void dispose() {
        Iterator<Widget> it = this.children.iterator();
        while (it.hasNext()) {
            it.next().dispose();
            it.remove();
        }
    }

    protected void recalculate() {

        if (getParent().getVerticalSizing() == Sizing.FIT_CONTENT && getVerticalSizing() == Sizing.FILL_PARENT) {
            throw new IllegalStateException("Circular vertical sizing dependency: parent has FIT_CONTENT and child has FILL_PARENT. Parent: " + getParent() + ", Child: " + this);
        }
        if (getParent().getHorizontalSizing() == Sizing.FIT_CONTENT && getHorizontalSizing() == Sizing.FILL_PARENT) {
            throw new IllegalStateException("Circular horizontal sizing dependency: parent has FIT_CONTENT and child has FILL_PARENT. Parent: " + getParent() + ", Child: " + this);
        }

        this.x = calculateX();
        this.y = calculateY();

        this.absX = calculateAbsoluteX();
        this.absY = calculateAbsoluteY();

        this.contentWidth = calculateInnerWidth();
        this.contentHeight = calculateInnerHeight();
    }

    protected float calculateAbsoluteX() {

        float x = this.x;

        if (this.positioning != Positioning.ABSOLUTE) {
            Widget w = this;
            while (w.getParent() != null) {
                x += w.getX();
                w = w.getParent();
            }
        }

        return x;
    }

    protected float calculateAbsoluteY() {

        float y = this.y;

        if (this.positioning != Positioning.ABSOLUTE) {
            Widget w = this;
            while (w.getParent() != null) {
                y += w.getY();
                w = w.getParent();
            }
        }

        return getRoot().getHeight() - y;
    }

    protected float calculateX() {
        switch (positioning) {
            case RELATIVE_ALIGNED:
                return this.calculateAlignedPositionX();
            case RELATIVE:
            case ABSOLUTE:
            default:
                return this.x;
        }
    }

    protected float calculateY() {
        switch (positioning) {
            case RELATIVE_ALIGNED:
                return this.calculateAlignedPositionY();
            case RELATIVE:
            case ABSOLUTE:
            default:
                return this.y;
        }
    }

    protected float calculateAlignedPositionX() {
        switch (horizontalAlignment) {
            case CENTER:
                return getParent().getContentWidth() / 2f - getWidth() / 2f;
            case RIGHT:
                return getParent().getContentWidth() - getWidth() - getMarginRight();
            case LEFT:
            default:
                return getMarginLeft();
        }
    }

    protected float calculateAlignedPositionY() {
        switch (verticalAlignment) {
            case MIDDLE:
                return getParent().getContentHeight() / 2f - getHeight() / 2f;
            case BOTTOM:
                return getContentHeight() - getHeight() - getMarginBottom();
            case TOP:
            default:
                return this.getMarginTop();
        }
    }

    protected float calculateInnerWidth() {
        switch (horizontalSizing) {
            case FIT_CONTENT:
                return calculatedChildrenWidth();
            case FILL_PARENT:
                return getParent().getContentWidth() - getMarginLeft() - getMarginRight() - getPaddingLeft() - getPaddingRight();
            case STATIC:
            default:
                return getContentWidth();
        }
    }

    protected float calculateInnerHeight() {
        switch (verticalSizing) {
            case FIT_CONTENT:
                return calculatedChildrenHeight();
            case FILL_PARENT:
                return getParent().getContentHeight() - getMarginBottom() - getMarginTop() - getPaddingBottom() - getPaddingTop();
            case STATIC:
            default:
                return getContentHeight();
        }
    }

    protected float calculatedChildrenWidth() {
        float width = 0;

        for (Widget child : this.children) {
            width = Math.max(width, child.getX() + child.calculateInnerWidth() + child.getMarginLeft() + child.getMarginRight());
        }

        return width;
    }

    protected float calculatedChildrenHeight() {
        float height = 0;

        for (Widget child : this.children) {
            height = Math.max(height, child.getY() + child.calculateInnerHeight() + child.getMarginTop() + child.getMarginBottom());
        }

        return height;
    }

    public final void render(DrawContext context) {
        this.draw(context);
        for (Widget child : this.children) {
            child.render(context);
        }
    }

    protected void draw(DrawContext context) {
    }

    //region Object overrides
    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Widget)) {
            return false;
        }
        return ((Widget)obj).id == this.id;
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + this.id;
    }

    private static final class WidgetDepthComparator implements Comparator<Widget> {
        @Override
        public int compare(Widget w1, Widget w2) {
            if (w1.depth > w2.depth) {
                return -1;
            } else if (w1.depth < w2.depth) {
                return 1;
            }
            return 0;
        }
    }
    //endregion
}
