package Cards;

import Cards.ColoredCards.ActionCards.Action;
import Cards.WildCards.Wild;
import Game.Deck;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DrawPile {
    private static DrawPile instance;
    private final Stack<Card> drawPileCards;

    private DrawPile() {
        drawPileCards = new Stack<>();
        initializeDrawPile();
    }

    public static DrawPile getInstance(){
        if(instance == null)
            instance = new DrawPile();
        return instance;
    }

    private void initializeDrawPile() {
        List<Card> cards = Deck.getInstance().getDeckCards();
        Collections.shuffle(cards);
        addCardsToDrawPile(cards);
    }

    public void addCardsToDrawPile(List<Card> cards) {
        drawPileCards.addAll(cards);
    }

    public Card getTop() {
        return drawPileCards.peek();
    }

    public Card getCard() {
        return drawPileCards.pop();
    }

    public boolean isEmpty() {
        return drawPileCards.isEmpty();
    }

    public Stack<Card> getDrawPileCards() {
        return drawPileCards;
    }

    public int size() {
        return drawPileCards.size();
    }

    public void makeNewDrawPile(Stack<Card> discardedCards) {
        Card peekCard = discardedCards.pop();
        Collections.shuffle(discardedCards);
        drawPileCards.clear();
        addCardsToDrawPile(discardedCards);
        discardedCards.clear();
        discardedCards.push(peekCard);
    }

    public boolean isSpecialCard(Card card) {
        return card instanceof Wild || card instanceof Action;
    }
}