package Game;

import Cards.Piles.DiscardPile;
import Cards.Piles.DrawPile;
import Command.GameCommand;
import Player.PlayerQueue;
import Util.Validate;

public abstract class Game {

    protected PlayerQueue players;
    protected DiscardPile discardPile;
    protected DrawPile drawPile;
    protected GameCommand gameCommand;

    public void runGame(){
        preGameSetUp();
        boolean hasGameEnded = false;
        while (!hasGameEnded) {
            runRound();
            hasGameEnded = !playAgain();
        }
        displayGameWinner();
    }

    public void preGameSetUp(){  //as all variations will have the same way of entering players
        String [] playerNames = Validate.handlePlayerNamesInput();
        players.createPlayers(playerNames);
        dealInitialCards();
    }

    protected abstract void play();
    protected abstract void dealInitialCards();
    protected abstract void runRound();
    protected abstract boolean checkRoundWinner();
    protected abstract void displayGameWinner();
    protected abstract boolean playAgain();
    protected abstract void resetGame();
}