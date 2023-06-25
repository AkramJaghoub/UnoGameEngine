package Game;

import Cards.ColoredCards.ActionCards.ActionType;
import Cards.ColoredCards.Color;
import Cards.*;
import Cards.WildCards.WildType;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static Deck instance;
    private final List<Card> deckCards;
    private final CardFactory cardFactory = new CardFactory();

    private Deck(){
        deckCards = new ArrayList<>();
        createCards();
    }

    public static Deck getInstance(){
        if(instance == null)
            instance = new Deck();
        return instance;
    }

    public List<Card> getDeckCards(){
        return deckCards;
    }

    private void createCards() {
        for (Color color : Color.values()) {
            createNumberedCards(color);
            createActionCards(color);
        }
        createWildCards();
    }

    private void createNumberedCards(Color color) {
        for (int i = 0; i <= 9; i++) {
            int limit = (i == 0) ? 1 : 2;
            for (int j = 0; j < limit; j++) {
                Card card = cardFactory.createCard(i, color);
                deckCards.add(card);
            }
        }
    }

    private void createActionCards(Color color) {
        for (int i = 0; i < 2; i++) {
            for (ActionType actionType : ActionType.values()) {
                Card card = cardFactory.createCard(actionType, color);
                deckCards.add(card);
            }
        }
    }

    private void createWildCards() {
        for (int i = 0; i < 4; i++) {
            for (WildType wildType : WildType.values()) {
                Card card = cardFactory.createCard(wildType);
                deckCards.add(card);
            }
        }
    }
}