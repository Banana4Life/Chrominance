package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import de.cubeisland.games.event.EventProcessor;
import de.cubeisland.games.ui.layout.Layout;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public abstract class Widget extends EventProcessor implements Disposable {

    private static final WidgetDepthComparator BY_DEPTH = new WidgetDepthComparator();
    private static final AtomicInteger WIDGET_COUNTER = new AtomicInteger(0);
    private final int id;
    private Widget parent = null;
    private final List<Widget> children = new ArrayList<>();
    private final List<Widget> childrenOrderedByDepth = new ArrayList<>();

    private boolean verticalPositionValid = false;
    private boolean horizontalPositionValid = false;
    private boolean widthValid = false;
    private boolean heightValid = false;

    //region Positioning fields
    private Positioning         positioning         = null;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private VerticalAlignment   verticalAlignment   = VerticalAlignment.TOP;

    private Layout layout           = null;
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

    //region visual properties
    private boolean active        = true;
    private boolean visible       = true;
    private Color foregroundColor = null;
    private Color backgroundColor = Color.CLEAR.cpy();
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

    public final boolean isRoot() {
        return this instanceof RootWidget;
    }

    public int getDepth() {
        return this.depth;
    }

    public Widget setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public Positioning getPositioning() {
        return this.positioning;
    }

    public Widget setPositioning(Positioning positioning) {
        this.positioning = positioning;
        invalidatePosition();
        return this;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    public Widget setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        invalidatePosition();
        return this;
    }

    public VerticalAlignment getVerticalAlignment() {
        return this.verticalAlignment;
    }

    public Widget setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        invalidatePosition();
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

    public Layout getLayout() {
        return layout;
    }

    public Widget setLayout(Layout layout) {
        this.layout = layout;
        invalidate();
        return this;
    }

    public float getX() {
        calculateX();
        return x;
    }

    public Widget setX(float x) {
        this.x = x;
        invalidate();
        return this;
    }

    public float getY() {
        calculateY();
        return y;
    }

    public Widget setY(float y) {
        this.y = y;
        invalidate();
        return this;
    }

    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    public Widget setPosition(Vector2 position) {
        return this.setPosition(position.x, position.y);
    }

    public Widget setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        invalidatePosition();
        return this;
    }

    public float getAbsoluteX() {
        calculateX();
        return this.absX;
    }

    public float getAbsoluteY() {
        calculateY();
        return this.absY;
    }

    public Vector2 getAbsolutePosition() {
        return new Vector2(getAbsoluteX(), getAbsoluteY());
    }

    public float getPaddingTop() {
        return this.paddingTop;
    }

    public float getPaddingRight() {
        return this.paddingRight;
    }

    public float getPaddingBottom() {
        return this.paddingBottom;
    }

    public float getPaddingLeft() {
        return this.paddingLeft;
    }

    public Widget setPaddingTop(float paddingTop) {
        this.paddingTop = paddingTop;
        invalidate();
        return this;
    }

    public Widget setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
        invalidate();
        return this;
    }

    public Widget setPaddingBottom(float paddingBottom) {
        this.paddingBottom = paddingBottom;
        invalidate();
        return this;
    }

    public Widget setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
        invalidate();
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
        invalidate();
        return this;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public Widget setMarginRight(float marginRight) {
        this.marginRight = marginRight;
        invalidate();
        return this;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public Widget setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
        invalidate();
        return this;
    }

    public float getMarginLeft() {
        return marginLeft;
    }

    public Widget setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
        invalidate();
        return this;
    }

    public Widget setMargin(float top, float right, float bottom, float left) {
        this.setMarginTop(top);
        this.setMarginRight(right);
        this.setMarginBottom(bottom);
        this.setMarginLeft(left);
        return this;
    }

    public Widget setMargin(float vertical, float horizontal) {
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
        calculateContentWidth();
        return contentWidth;
    }

    public Widget setContentWidth(float contentWidth) {
        this.contentWidth = contentWidth;
        invalidate();
        return this;
    }

    public float getOuterWidth() {
        return getWidth() + getMarginLeft() + getMarginRight();
    }

    public float getHeight() {
        return getPaddingTop() + getContentHeight() + getPaddingBottom();
    }

    public float getContentHeight() {
        calculateContentHeight();
        return contentHeight;
    }

    public Widget setContentHeight(float contentHeight) {
        this.contentHeight = contentHeight;
        invalidate();
        return this;
    }

    public float getOuterHeight() {
        return getHeight() + getMarginTop() + getMarginBottom();
    }

    public Sizing getHorizontalSizing() {
        return horizontalSizing;
    }

    public Widget setHorizontalSizing(Sizing horizontalSizing) {
        this.horizontalSizing = horizontalSizing;
        invalidate();
        return this;
    }

    public Sizing getVerticalSizing() {
        return verticalSizing;
    }

    public Widget setVerticalSizing(Sizing verticalSizing) {
        this.verticalSizing = verticalSizing;
        invalidate();
        return this;
    }

    public Widget setSizing(Sizing sizing) {
        this.setHorizontalSizing(sizing);
        this.setVerticalSizing(sizing);
        return this;
    }

    public Color getForegroundColor() {
        if (this.foregroundColor != null) {
            return foregroundColor.cpy();
        }
        Widget parent = getParent();
        if (parent != null) {
            return parent.getForegroundColor();
        }
        return null;
    }

    public Widget setForegroundColor(Color foregroundColor) {
        if (foregroundColor == null) {
            this.foregroundColor = null;
        } else {
            this.foregroundColor = foregroundColor.cpy();
        }
        return this;
    }

    public Color getBackgroundColor() {
        return backgroundColor.cpy();
    }

    public Widget setBackgroundColor(Color color) {
        this.backgroundColor = color.cpy();
        return this;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Widget setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Widget setActive(boolean active) {
        this.active = active;
        if (active) {
            invalidate();
        }
        return this;
    }

    public boolean isFocused() {
        return getRoot().isFocused(this);
    }

    public Widget addChild(Widget widget) {
        if (widget == null) {
            throw new IllegalArgumentException("The child widget may not be null!");
        }
        if (widget == this) {
            throw new IllegalArgumentException("You can't add the widget as a child of itself!");
        }
        if (widget.parent != null) {
            throw new IllegalArgumentException("The given widget is already a child of a different widget!");
        }
        if (this.children.contains(widget) || this == widget.parent) {
            throw new IllegalArgumentException("The given widget is already a child!");
        }
        this.children.add(widget);
        this.childrenOrderedByDepth.add(widget);
        Collections.sort(this.childrenOrderedByDepth, BY_DEPTH);
        widget.parent = this;
        invalidate();

        widget.onAdded();

        if (getParent() != null) {
            applyLayout();
        }

        return this;
    }

    public Widget removeChild(Widget widget) {
        if (widget.parent == this) {
            this.children.remove(widget);
            this.childrenOrderedByDepth.remove(widget);
            widget.parent = null;
            invalidate();
        }

        return this;
    }

    protected void onAdded() {
        applyLayout();
    }

    public List<Widget> getChildren() {
        return new ArrayList<>(this.children);
    }
    //endregion

    @Override
    public void dispose() {
        Iterator<Widget> it = this.children.iterator();
        while (it.hasNext()) {
            it.next().dispose();
            it.remove();
        }
        getRoot().unfocus(this);
    }

    public void invalidate() {
        this.invalidatePosition();
        this.invalidateDimensions();
    }

    protected void invalidatePosition() {
        this.verticalPositionValid = false;
        this.horizontalPositionValid = false;

        for (Widget child : this.children) {
            child.invalidatePosition();
        }
    }

    protected void invalidateDimensions() {
        this.widthValid = false;
        this.heightValid = false;

        for (Widget child : this.children) {
            child.invalidateDimensions();
        }
    }

    protected void calculateX() {
        calculateRelativeX();
        calculateAbsoluteX();
    }

    protected void calculateRelativeX() {
        if (this.horizontalPositionValid) {
            return;
        }
        if (getParent().getLayout() == null) {
            this.x = calculateAlignedPositionX();
        }
    }

    protected void calculateAbsoluteX() {
        if (this.horizontalPositionValid) {
            return;
        }

        Widget w = getParent();
        Widget p;

        float x = this.x + w.getPaddingLeft() + getMarginLeft();

        while (w.getParent() != null) {
            p = w.getParent();
            x += w.getX() + p.getPaddingLeft() + w.getMarginLeft();
            w = p;
        }

        this.absX = x;
        this.horizontalPositionValid = true;
    }

    protected void calculateY() {
        calculateRelativeY();
        calculateAbsoluteY();
    }

    protected void calculateRelativeY() {
        if (this.verticalPositionValid) {
            return;
        }
        if (getPositioning() == null && getParent().getLayout() == null) {
            this.y = calculateAlignedPositionY();
        }
    }

    protected void calculateAbsoluteY() {
        if (this.verticalPositionValid) {
            return;
        }

        Widget w = getParent();
        Widget p;

        float y = this.y + w.getPaddingTop() + getMarginTop();

        while (w.getParent() != null) {
            p = w.getParent();
            y += w.getY() + p.getPaddingTop() + w.getMarginTop();
            w = p;
        }

        this.absY = y;
        this.verticalPositionValid = true;
    }

    protected float calculateAlignedPositionX() {
        switch (horizontalAlignment) {
            case CENTER:
                return getParent().getContentWidth() / 2f - getWidth() / 2f;
            case RIGHT:
                return getParent().getContentWidth() - getWidth() - getMarginRight();
            case LEFT:
            default:
//                return getMarginLeft();
                return 0;
        }
    }

    protected float calculateAlignedPositionY() {
        switch (verticalAlignment) {
            case MIDDLE:
                return getParent().getContentHeight() / 2f - getHeight() / 2f;
            case BOTTOM:
                return getParent().getContentHeight() - getHeight() - getMarginBottom();
            case TOP:
            default:
//                return getMarginTop();
                return 0;
        }
    }

    protected void calculateContentWidth() {
        if (this.widthValid) {
            return;
        }
        if (getParent().getHorizontalSizing() == Sizing.FIT_CONTENT && getHorizontalSizing() == Sizing.FILL_PARENT) {
            throw new IllegalStateException("Circular horizontal sizing dependency: parent has FIT_CONTENT and child has FILL_PARENT. Parent: " + getParent() + ", Child: " + this);
        }
        final float width;
        switch (horizontalSizing) {
            case FIT_CONTENT:
                width = calculatedChildrenWidth();
                break;
            case FILL_PARENT:
                width = getParent().getContentWidth() - getMarginLeft() - getMarginRight() - getPaddingLeft() - getPaddingRight();
                break;
            case STATIC:
            default:
                width = this.contentWidth;
        }

        this.contentWidth = width;
        this.widthValid = true;
    }

    protected void calculateContentHeight() {
        if (this.heightValid) {
            return;
        }
        if (getParent().getVerticalSizing() == Sizing.FIT_CONTENT && getVerticalSizing() == Sizing.FILL_PARENT) {
            throw new IllegalStateException("Circular vertical sizing dependency: parent has FIT_CONTENT and child has FILL_PARENT. Parent: " + getParent() + ", Child: " + this);
        }
        final float height;
        switch (verticalSizing) {
            case FIT_CONTENT:
                height = calculatedChildrenHeight();
                break;
            case FILL_PARENT:
                height = getParent().getContentHeight() - getMarginBottom() - getMarginTop() - getPaddingBottom() - getPaddingTop();
                break;
            case STATIC:
            default:
                height = this.contentHeight;
        }
        this.contentHeight = height;
        this.heightValid = true;
    }

    protected float calculatedChildrenWidth() {
        float width = 0;

        for (Widget child : this.children) {
            width = Math.max(width, child.getX() + child.getOuterWidth());
        }

        return width;
    }

    protected float calculatedChildrenHeight() {
        float height = 0;

        for (Widget child : this.children) {
            height = Math.max(height, child.getY() + child.getOuterHeight());
        }

        return height;
    }

    protected void applyLayout() {
        Layout l = getLayout();
        if (l != null) {
            l.positionWidgets(this.children);
        }
    }

    public final void render(DrawContext context) {
        if (!isVisible()) {
            return;
        }
        this.draw(context);
        for (Widget child : this.childrenOrderedByDepth) {
            if (child.isActive()) {
                child.render(context);
            }
        }
    }

    protected void draw(DrawContext context) {
        Vector2 pos = this.getAbsolutePosition();

        final ShapeRenderer r = context.getShapeRenderer();

        if (context.getGame().isDebug()) {
            r.begin(Filled);
            r.setColor(Color.BLACK);
            r.circle(pos.x, pos.y, 5);
            r.setColor(Color.MAGENTA);
            r.circle(pos.x + getWidth(), pos.y + getHeight(), 5);
            r.end();

            r.begin(Line);
            r.setColor(Color.ORANGE);
            r.rect(pos.x, pos.y, getWidth(), getHeight());
            r.end();
        }

        if (this.backgroundColor.a > 0f) {
            if (this.backgroundColor.a < 1) {
                Gdx.gl.glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            }
            r.begin(Filled);
            r.setColor(this.backgroundColor);
            r.rect(pos.x, pos.y, getWidth(), getHeight());
            r.end();
            if (this.backgroundColor.a < 1) {
                Gdx.gl.glDisable(GL20.GL_BLEND);
            }
        }

        if (context.getGame().isDebug()) {
            if (getPaddingTop() + getPaddingBottom() + getPaddingLeft() + getPaddingRight() > 0) {
                r.begin(Line);
                r.setColor(Color.BLACK);
                r.rect(pos.x + getPaddingLeft(), pos.y + getPaddingTop(), getContentWidth(), getContentHeight());
                r.end();
            }

            if (getMarginTop() + getMarginBottom() + getMarginLeft() + getMarginRight() > 0) {
                r.begin(Line);
                r.setColor(Color.PINK);
                r.rect(pos.x - getMarginLeft(), pos.y - getMarginTop(), getOuterWidth(), getOuterHeight());
                r.end();
            }
        }
    }

    protected boolean containsPoint(float x, float y) {
        final float minX = getAbsoluteX();
        final float maxX = minX + getWidth();
        final float minY = getAbsoluteY();
        final float maxY = minY + getHeight();

        return (x >= minX && x < maxX && y >= minY && y < maxY);
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
                return 1;
            } else if (w1.depth < w2.depth) {
                return -1;
            }
            return 0;
        }
    }
    //endregion
}
