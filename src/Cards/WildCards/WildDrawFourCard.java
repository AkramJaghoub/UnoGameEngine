package Cards.WildCards;

import Cards.Card;
import Cards.DiscardPile;
import Cards.DrawPile;
import Player.Player;
import Player.PlayerQueue;
import Game.GameCommand;

public class WildDrawFourCard extends Wild {

    public WildDrawFourCard(WildType wildType) {
        super(wildType);
    }

    @Override
    public void performAction(GameCommand gameCommand) {
        PlayerQueue players = gameCommand.getPlayerQueue();
        DrawPile drawPile = gameCommand.getDrawPile();
        DiscardPile discardPile = gameCommand.getDiscardPile();
        Player nextPlayer = players.getNextPlayer();
        determineChosenColor();
        int drawCount = 4;
        if (drawPile.size() < drawCount) {
            if (drawPile.size() <= 3) {
                int size = drawCount - drawPile.size();
                for(int i = 0; i < size; i++) {
                    Card drawnCard = drawPile.getCard();
                    nextPlayer.addCardToPlayerHand(drawnCard);
                    drawCount--;
                    drawPile.makeNewDrawPile(discardPile.getDiscardPileCards());
                }
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
        return "Draw four";
    }
}
