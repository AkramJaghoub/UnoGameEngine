package Cards;

public interface Card {
    String getCardName();
    boolean canPlayOn(Card topDiscardCard);
    int cardScore();
}