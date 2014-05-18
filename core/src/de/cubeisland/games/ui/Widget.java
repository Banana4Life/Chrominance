package de.cubeisland.games.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class Widget implements Invalidatable, Comparable<Widget>, Disposable {

    private Widget parent = null;
    private final List<Widget> children = new ArrayList<>();

    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private VerticalAlignment verticalAlignment     = VerticalAlignment.TOP;

    private int   zIndex        = 0;
    private float positionX     = 0;
    private float positionY     = 0;

    private float contentWidth  = 0;
    private float contentHeight = 0;

    private float paddingTop    = 0;
    private float paddingRight  = 0;
    private float paddingBottom = 0;
    private float paddingLeft   = 0;

    public Widget getParent() {
        return parent;
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

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
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

    public Widget setPadding(float vertical, float horizontal) {
        this.setPaddingTop(vertical);
        this.setPaddingBottom(vertical);
        this.setPaddingLeft(horizontal);
        this.setPaddingRight(horizontal);
        return this;
    }

    public Widget setPadding(float padding) {
        this.setPadding(padding, padding);
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
        if (this.children.contains(widget)) {
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
        if (this.zIndex > o.zIndex) {
            return 1;
        } else if (this.zIndex < o.zIndex) {
            return -1;
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

    }

    public final void render() {
        for (Widget child : this.children) {
            child.render();
        }
        this.draw();
    }

    protected void draw() {
    }
}
