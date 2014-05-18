package de.cubeisland.games.ui.widgets;

public class Button extends Container {

    private final Label label = new Label();

    public Button(String text) {
        this.addChild(this.label.setText(text));
    }

    public String getText() {
        return this.label.getText();
    }

    public Button setText(String text) {
        this.label.setText(text);
        return this;
    }

    public Label getLabel() {
        return label;
    }
}
