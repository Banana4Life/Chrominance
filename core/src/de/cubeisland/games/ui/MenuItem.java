package de.cubeisland.games.ui;

import com.badlogic.gdx.math.Vector2;


public class MenuItem extends Button {

    public MenuItem(Menu parent, String text) {
        this(parent, text, new OnClickListener() {
            @Override
            public void onItemClicked(Clickable item, Vector2 touchPoint) {
                System.out.println("MenuItem \"" + ((Button) item).getText() + "\" was clicked");
            }
        });
    }

    public MenuItem(Menu parent, String text, OnClickListener listener) {
        this(new LayoutOptions.Builder().padding(parent.getPadding()).font(parent.getFont()).titleFont(parent.getTitleFont()).build(), text, listener);
    }

    public MenuItem(LayoutOptions options, String text) {
        super(ElementType.MENUITEM, options, (text != null && options.getFont() != null) ? options.getFont().getBounds(text).width : 0, (text != null && options.getFont() != null) ? options.getFont().getBounds(text).height : 0, text);
        //System.out.println(text + ": " + (options.getFont() == null));
    }

    public MenuItem(Menu parent, float width, float height, String text) {
        super(ElementType.MENUITEM, new LayoutOptions.Builder().padding(parent.getPadding()).font(parent.getFont()).titleFont(parent.getTitleFont()).build(), width, height, text);
    }

    public MenuItem(LayoutOptions options, String text, OnClickListener listener) {
        super(ElementType.MENUITEM, options, (text != null && options.getFont() != null) ? options.getFont().getBounds(text).width : 0, (text != null && options.getFont() != null) ? options.getFont().getBounds(text).height : 0, text, listener);
    }

    public MenuItem(Menu parent, float width, float height, String text, OnClickListener listener) {
        super(ElementType.MENUITEM, new LayoutOptions.Builder().padding(parent.getPadding()).font(parent.getFont()).titleFont(parent.getTitleFont()).build(), width, height, text, listener);
    }
}