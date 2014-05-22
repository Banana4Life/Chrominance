package de.cubeisland.games.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Widget implements Invalidatable, Comparable<Widget>, Disposable {

    private static final AtomicInteger WIDGET_COUNTER = new AtomicInteger(0);
    private final int id;
    private Widget parent = null;
    private final List<Widget> children = new ArrayList<>();

    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private VerticalAlignment verticalAlignment     = VerticalAlignment.TOP;

    private Sizing horizontalSizing = Sizing.FILL_PARENT;
    private Sizing verticalSizing   = Sizing.FIT_CONTENT;

    private int   depth         = 0;
    private float positionX     = 0;
    private float positionY     = 0;

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

    protected Widget() {
        id = WIDGET_COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public Widget getParent() {
        return parent;
    }

    public RootWidget getRoot() {
        return getParent().getRoot();
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

    public Widget setHorizontalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Vector2 getAbsolutePosition() {
        Vector2 pos = getPosition();
        Widget parent = getParent();
        if (parent != null) {
            Vector2 parentPos = parent.getAbsolutePosition();
            pos.set(parentPos.x + pos.x, parentPos.y - pos.y);
        }
        return pos;
    }

    public Vector2 getPosition() {
        return new Vector2(positionX, positionY);
    }

    public Widget setPosition(Vector2 position) {
        return this.setPosition(position.x, position.y);
    }

    public Widget setPosition(float x, float y) {
        this.positionX = x;
        this.positionY = y;
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
        Collections.sort(this.children);
        return this;
    }

    public Widget removeChild(Widget widget) {
        if (widget.parent == this) {
            this.children.remove(widget);
            widget.parent = null;
        }

        return this;
    }

    @Override
    public final void invalidate() {
        this.recalculate();
        for (Widget child : this.children) {
            child.invalidate();
        }
    }

    @Override
    public final int compareTo(Widget o) {
        if (this.depth > o.depth) {
            return -1;
        } else if (this.depth < o.depth) {
            return 1;
        }
        return 0;
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
        this.positionX = this.calculatePositionX();
        this.positionY = this.calculatePositionY();

        this.contentWidth = this.getContentWidth();
        this.contentHeight = this.getContentHeight();
    }

    protected float calculatePositionX() {
        switch (horizontalAlignment) {
            case CENTER:
                return getParent().getContentWidth() / 2f - this.calculateInnerWidth() / 2f;
            case RIGHT:
                return getParent().getContentWidth() - this.calculateInnerWidth();
            case LEFT:
            default:
                return this.marginLeft;
        }
    }

    protected float calculatePositionY() {
        switch (verticalAlignment) {
            case MIDDLE:
                return getParent().getContentHeight() / 2f - this.calculateInnerHeight() / 2f;
            case BOTTOM:
                return this.calculateInnerWidth() + this.marginBottom;
            case TOP:
            default:
                return getParent().getHeight() - this.marginTop;
        }
    }

    protected float calculateInnerWidth() {
        switch (horizontalSizing) {
            case FIT_CONTENT:
                return this.calculatedChildrenWidth();
            case FILL_PARENT:
                return getParent().getContentWidth() - getMarginLeft() - getMarginRight() - getPaddingLeft() - getPaddingRight();
            case STATIC:
            default:
                return this.contentWidth;
        }
    }

    protected float calculatedChildrenWidth() {
        return 0;
    }

    protected float calculateInnerHeight() {
        switch (verticalSizing) {
            case FIT_CONTENT:
                return this.calculatedChildrenHeight();
            case FILL_PARENT:
                return getParent().getContentHeight() - getMarginBottom() - getMarginTop() - getPaddingBottom() - getPaddingTop();
            case STATIC:
            default:
                return this.contentHeight;
        }
    }

    protected float calculatedChildrenHeight() {
        return 0;
    }

    public final void render() {
        this.draw();
        for (Widget child : this.children) {
            child.render();
        }
    }

    protected void draw() {
    }

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
}
