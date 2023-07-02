package Cards.Piles;

import Cards.Card;
import Cards.ColoredCards.ActionCards.Action;
import Cards.WildCards.Wild;
import Game.Deck;
import Validation.Exception.InvalidDrawException;
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

    public void initializeDrawPile() {
        drawPileCards.clear();
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

    public Card drawCard() {
        if(isEmpty())
            makeNewDrawPile();
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

    public void makeNewDrawPile() {
        drawPileCards.clear();
        Stack<Card> discardedPileCards = DiscardPile.getInstance().getDiscardPileCards();
        Card peekCard = discardedPileCards.pop();
        while(!discardedPileCards.isEmpty()){
            drawPileCards.push(discardedPileCards.pop());
        }
        Collections.shuffle(drawPileCards);
        discardedPileCards.clear();
        discardedPileCards.push(peekCard);
        if(discardedPileCards.isEmpty())
            throw new InvalidDrawException();  //because it can't create a new draw pile
    }

    public boolean isSpecialCard(Card card) {
        return card instanceof Wild || card instanceof Action;
    }
}