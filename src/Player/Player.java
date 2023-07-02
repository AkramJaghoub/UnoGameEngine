package Player;

import Cards.Card;
import Util.CardFormat;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private final List<Card> cardsInHand;
    private int roundPoints;

    public Player(String name) {
        this.name = name;
        cardsInHand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String player){
        name = player;
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

    public void getPlayerTurn() {
        System.out.println("It's " + getName() + "'s turn");
        displayPlayerCards();
    }

    public void displayPlayerCards() {
        System.out.println(getName() + " Cards:");
        StringBuilder[] cardLines = new StringBuilder[6];
        int cardCount = 0; //counter to tracking the number of cards per line
        for (int i = 0; i < cardsInHand.size(); ++i) {
            Card card = cardsInHand.get(i);
            String cardString = CardFormat.formatCard(card, i);
            String[] lines = cardString.split("\n");
            for (int j = 0; j < lines.length; j++) {
                cardLines[j] = cardLines[j] == null ? new StringBuilder() : cardLines[j];
                cardLines[j].append(lines[j]).append("  ");
            }
            cardCount++;
            if (cardCount == 7 || i == cardsInHand.size() - 1) {
                for (StringBuilder line : cardLines) {
                    if (line != null) {
                        System.out.println(line);
                    }
                }
                System.out.println();
                cardLines = new StringBuilder[6];
                cardCount = 0;
            }
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