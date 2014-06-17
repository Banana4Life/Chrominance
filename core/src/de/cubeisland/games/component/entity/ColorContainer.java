package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.event.Event;

public class ColorContainer extends Component<Entity> {
    private double amount = 0;
    private double maxAmount = 10;
    private Color color = Color.BLUE;

    @Override
    public void update(float delta) {
    }

    public Color getColor() {
        return color.cpy();
    }
    public ColorContainer setColor(Color color) {
        this.color = color.cpy();
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public ColorContainer setAmount(double amount) {

        this.amount = Math.max(0, Math.min(this.maxAmount, amount));
        if (amount == 0) {
            trigger(new ColorContainerEmptyEvent());
        }

        return this;
    }

    public ColorContainer subAmount(double amount) {
        return setAmount(this.amount - amount);
    }

    public ColorContainer addAmount(double amount) {
        return setAmount(this.amount + amount);
    }

    public ColorContainer setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
        return this;
    }
    public double getMaxAmount() {
        return maxAmount;
    }

    public ColorContainer refill() {
        return this.setAmount(maxAmount);
    }

    public static class ColorContainerEmptyEvent extends Event {
        public ColorContainerEmptyEvent() {
        }
    }
}