package Command;

import Cards.*;
import Cards.Piles.DiscardPile;
import Cards.Piles.DrawPile;
import Player.Player;
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

    public boolean isCardAvailable() {
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

    public void playCard(Card chosenCard) {
        Command playCommand;
        Player currentPlayer = players.getCurrentPlayer();
        Card card = currentPlayer.playCard(currentPlayer.getCardsInHand().indexOf(chosenCard));
        System.out.println(currentPlayer.getName() + " played: \n" + CardFormat.formatCard(card));
        discardPile.addCardToDiscardPile(card);
        playCommand = new CardCommand(card, this);
        playCommand.execute();
    }

    public void playMatchedCard(Card matchedCard){
        Command playCommand;
        Player currentPlayer = players.getCurrentPlayer();
        System.out.println(currentPlayer.getName() + " played: \n" + CardFormat.formatCard(matchedCard));
        discardPile.addCardToDiscardPile(matchedCard);
        playCommand = new CardCommand(matchedCard, this);
        playCommand.execute();
    }
}