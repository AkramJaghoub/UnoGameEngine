package Command;

import Cards.*;
import Cards.Piles.DiscardPile;
import Cards.Piles.DrawPile;
import Player.PlayerQueue;
import Util.CardFormat;

public class GameCommand {
    private final DrawPile drawPile;
    private final DiscardPile discardPile;
    private final PlayerQueue players;

    public GameCommand() {
        this.drawPile = DrawPile.getInstance();
        this.discardPile = DiscardPile.getInstance();
        this.players = PlayerQueue.getInstance();
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public PlayerQueue getPlayerQueue() {
        return players;
    }

    public void getPlayerTurn() {
        System.out.println("It's " + players.getCurrentPlayer().getName() + "'s turn");
    }

    public boolean isPlayable() {
        for (Card card : players.getCurrentPlayer().getCardsInHand()) {
            if (card.canPlayOn(discardPile.getTop())) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlayableCard(int pos) {
        return players.getCurrentPlayer().getChosenCard(pos).canPlayOn(discardPile.getTop());
    }

    public Card playCard(int pos) {
        Command playCommand;
        Card card = players.getCurrentPlayer().playCard(pos);
        System.out.println(players.getCurrentPlayer().getName() + " played: \n" + CardFormat.formatCard(card));
        playCommand = new CardCommand(card, this);
        playCommand.execute();
        return card;
    }
}