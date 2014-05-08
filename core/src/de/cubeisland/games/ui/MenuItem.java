package de.cubeisland.games.ui;

import com.badlogic.gdx.math.Vector2;


public class MenuItem extends Clickable {

    public MenuItem(Menu parent, String text) {
        this(parent, text, new OnClickListener() {
            @Override
            public void onItemClicked(MenuItem item, Vector2 touchPoint) {
                System.out.println("MenuItem \"" + item.getText() + "\" was clicked");
            }
        });
    }
    public MenuItem(Menu parent, String text, OnClickListener listener) {
        super(ElementType.MENUITEM, new LayoutOptions.Builder().padding(parent.getPadding()).font(parent.getFont()).titleFont(parent.getTitleFont()).build(), text, listener);
    }
}