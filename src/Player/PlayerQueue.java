package Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PlayerQueue implements Iterable<Player> {
    private final List<Player> players;
    private int currentIndex;

    public PlayerQueue() {
        players = new ArrayList<>();
        currentIndex = 0;
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
        currentIndex = players.size() - 1 - currentIndex;
    }

    public Player getNextPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        currentIndex = (currentIndex + 1) % players.size();
        return players.get(currentIndex);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}