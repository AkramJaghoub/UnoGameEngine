package Cards.ColoredCards.ActionCards;

import Cards.*;
import Cards.ColoredCards.Color;
import Player.Player;
import Player.PlayerQueue;
import Cards.Piles.DrawPile;
import Command.GameCommand;

public class DrawTwoCard extends Action {
    public DrawTwoCard(ActionType actionType, Color color) {
        super(actionType, color);
    }
    @Override
    public void performAction(GameCommand gameCommand) {
        PlayerQueue players = gameCommand.getPlayerQueue();
        DrawPile drawPile = gameCommand.getDrawPile();
        Player nextPlayer = players.getNextPlayer();
        int drawCount = 2;
        for (int i = 0; i < drawCount; i++) {
            Card drawnCard = drawPile.drawCard();
            nextPlayer.addCardToPlayerHand(drawnCard);
        }
        players.getNextPlayer(); //blocks the next player who had drawn the 2 cards and moves to the person after them
    }
    @Override
    public String getCardName() {
        return "Draw two";
    }
}