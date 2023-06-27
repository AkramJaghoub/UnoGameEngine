package Player;

import Game.Round;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PlayerQueue implements Iterable<Player> {
    private final List<Player> players;
    private int currentIndex;
    private static PlayerQueue instance;

    private PlayerQueue() {
        players = new ArrayList<>();
        currentIndex = 0;
    }

    public static PlayerQueue getInstance(){
        if(instance == null)
            instance = new PlayerQueue();
        return instance;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(currentIndex);
    }

    public void reversePlayerOrder() {
        Collections.reverse(players);
        currentIndex = players.size() - 1 - currentIndex; //return to the player which played the card
    }

    public Player getNextPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        currentIndex = (currentIndex + 1) % players.size(); //to make it a circular queue
        return players.get(currentIndex);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public void createPlayers(String[] playerNames) {
        for (String playerName : playerNames) {
            Player newPlayer = new Player(playerName);
            addPlayer(newPlayer);
        }
    }

    public void resetPlayerCards(){
        for(Player player : players)
            player.clearCardsInHand();
        currentIndex = 0;
    }

    public boolean checkForWinner(){
        Round round = new Round();
        for(Player player : players){
            if(player.getCardsInHand().isEmpty()) {
                round.calculateRemainingPlayersPoints(player);
                System.out.println("Congratulations " + player.getName() + " you have won the round with a score of: " + player.getRoundPoints());
                displayScoreboard();
                return true;
            }
        }
        return false;
    }

    public void displayScoreboard(){
        System.out.println("----------------------------------");
        System.out.println("PLAYERS SCOREBOARD");
        for(Player player : players){
            System.out.println(player.getName() + ": " + player.getRoundPoints());
        }
        System.out.println("----------------------------------");
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}