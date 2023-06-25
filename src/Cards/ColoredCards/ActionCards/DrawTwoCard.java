package Cards.ColoredCards.ActionCards;

import Cards.*;
import Cards.ColoredCards.Color;
import Player.Player;
import Player.PlayerQueue;
import Cards.DiscardPile;
import Cards.DrawPile;
import Game.GameCommand;


public class

DrawTwoCard extends Action {
    public DrawTwoCard(ActionType actionType, Color color) {
        super(actionType, color);
    }
    @Override
    public void performAction(GameCommand gameCommand) {//dis draw player
        PlayerQueue players = gameCommand.getPlayerQueue();
        DrawPile drawPile = gameCommand.getDrawPile();
        DiscardPile discardPile = gameCommand.getDiscardPile();
        Player nextPlayer = players.getNextPlayer();
        int drawCount = 2;
        if (drawPile.size() < drawCount) {
            if (drawPile.size() == 1) {
                Card drawnCard = drawPile.getCard();
                nextPlayer.addCardToPlayerHand(drawnCard);
                drawCount--;
                drawPile.makeNewDrawPile(discardPile.getDiscardPileCards());
            } else if (drawPile.isEmpty()) {
                drawPile.makeNewDrawPile(discardPile.getDiscardPileCards());
            }
        }
        for (int i = 0; i < drawCount; i++) {
            Card drawnCard = drawPile.getCard();
            nextPlayer.addCardToPlayerHand(drawnCard);
        }
        players.getNextPlayer(); //blocks the next player who had drawn the 2 cards and moves to the person after them
    }
    @Override
    public String getCardName() {
        return "Draw two";
    }
}