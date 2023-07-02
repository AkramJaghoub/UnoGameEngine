package Player;

import Game.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PlayerQueue implements Iterable<Player> {
    private final List<Player> players;
    private final List<String> originalPlayers;
    private int currentIndex;
    private static PlayerQueue instance;

    private PlayerQueue() {
        players = new ArrayList<>();
        originalPlayers = new ArrayList<>();
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
            originalPlayers.add(playerName);
        }
    }

    public void resetPlayers(){
        for(int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            String originalName = originalPlayers.get(i);
            currentPlayer.clearCardsInHand();
            currentPlayer.setName(originalName);
        }
        currentIndex = 0;
    }

    public Player getRoundWinner(){
        for(Player player : players){
            if(player.getCardsInHand().isEmpty()) {
                return player;
            }
        }
        return null;
    }

    public Player getGameWinner(){
        Player winner = null;
        int maxPoints = Integer.MIN_VALUE;
        for(Player player : players){
            int playerPoints = player.getRoundPoints();
            if(playerPoints > maxPoints) {
                maxPoints = playerPoints;
                winner = player;
            }
        }
        return winner;
    }


    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}