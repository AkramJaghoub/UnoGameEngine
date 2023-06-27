package Game;

import Cards.Card;
import Player.PlayerQueue;
import Player.Player;

public class Round {
    private final PlayerQueue players;
    public Round(){
        players = PlayerQueue.getInstance();
    }
    public void calculateRemainingPlayersPoints(Player roundWinner) {
        int roundPoints = 0;
        for (Player player : players) {
            if (player != roundWinner) {
                roundPoints += calculateHandScore(player);
                player.getCardsInHand().clear();
            }
        }
        roundWinner.addToScore(roundPoints);
    }

    public int calculateHandScore(Player player) {
        int handScore = 0;
        for (Card card : player.getCardsInHand()) {
            handScore += card.cardScore();
        }
        return handScore;
    }
}
