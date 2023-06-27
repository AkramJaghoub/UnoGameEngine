package Cards.Piles;

import Cards.Card;

import java.util.Stack;

public class DiscardPile {
    private final Stack<Card> discardPileCards;
    private static DiscardPile instance;

    private DiscardPile() {
        discardPileCards = new Stack<>();
        initializeDiscardPile();
    }

    public static DiscardPile getInstance(){
        if(instance == null)
            instance = new DiscardPile();
        return instance;
    }

    public void initializeDiscardPile(){
        discardPileCards.clear();
        DrawPile drawPile = DrawPile.getInstance();
        while (drawPile.isSpecialCard(drawPile.getTop())) {
            Card removedFromDraw = drawPile.drawCard();
            addCardToDiscardPile(removedFromDraw);
        }
        addCardToDiscardPile(drawPile.drawCard());
    }

    public void addCardToDiscardPile(Card card) {
        discardPileCards.push(card);
    }

    public Stack<Card> getDiscardPileCards() {
        return discardPileCards;
    }

    public Card getTop() {
        return discardPileCards.peek();
    }
}