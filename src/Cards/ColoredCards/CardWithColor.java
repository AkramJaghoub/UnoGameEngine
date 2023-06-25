package Cards.ColoredCards;

import Cards.Card;
public abstract class CardWithColor extends Card {
    private final Color color;

    protected CardWithColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "CardWithColor{" +
                "color=" + color +
                '}';
    }
}
