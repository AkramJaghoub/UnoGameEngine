package Cards.WildCards;

import Cards.Card;
import Cards.Piles.DrawPile;
import Player.Player;
import Player.PlayerQueue;
import Command.GameCommand;

public class WildDrawFourCard extends Wild {

    public WildDrawFourCard(WildType wildType) {
        super(wildType);
    }

    @Override
    public void performAction(GameCommand gameCommand) {
        PlayerQueue players = gameCommand.getPlayerQueue();
        DrawPile drawPile = gameCommand.getDrawPile();
        Player nextPlayer = players.getNextPlayer();
        determineChosenColor();
        int drawCount = 4;
        for (int i = 0; i < drawCount; i++) {
            Card drawnCard = drawPile.drawCard();
            nextPlayer.addCardToPlayerHand(drawnCard);
        }
        players.getNextPlayer(); //blocks the next player who had drawn the 2 cards and moves to the person after them
    }

    @Override
    public String getCardName() {
        return "Draw four";
    }
}