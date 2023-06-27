package Player;

import Cards.Card;
import Util.CardFormat;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cardsInHand;
    private int roundPoints;

    public Player(String name) {
        this.name = name;
        cardsInHand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void clearCardsInHand(){
        cardsInHand.clear();
    }

    public int getRoundPoints(){
        return roundPoints;
    }


    public void addCardToPlayerHand(Card card) {
        cardsInHand.add(card);
    }

    public void displayPlayerCards() {
        System.out.println(getName() + " Cards:");
        StringBuilder[] cardLines = new StringBuilder[6];
        for (int i = 0; i < cardsInHand.size(); ++i) {
            Card card = cardsInHand.get(i);
            String cardString = CardFormat.formatCard(card, i);
            String[] lines = cardString.split("\n");
            for (int j = 0; j < lines.length; j++) {
                if (cardLines[j] == null) {
                    cardLines[j] = new StringBuilder();
                }
                cardLines[j].append(lines[j]);
                cardLines[j].append("  "); // Add two spaces between each card
            }
        }
        for (StringBuilder line : cardLines) {
            System.out.println(line);
        }
        System.out.println();
    }

    public Card getChosenCard(int pos) {
        return cardsInHand.get(pos);
    }

    public Card playCard(int cardPos) {
        return cardsInHand.remove(cardPos);
    }

    public void addToScore(int roundPoints){
        this.roundPoints += roundPoints;
    }
}
