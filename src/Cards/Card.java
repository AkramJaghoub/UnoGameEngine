package Cards;

public abstract class Card {
    public abstract String getCardName();
    public abstract Boolean isValid(Card otherCard);
}