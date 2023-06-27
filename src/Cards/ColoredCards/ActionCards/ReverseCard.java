package Cards.ColoredCards.ActionCards;

import Cards.ColoredCards.Color;
import Player.PlayerQueue;
import Command.GameCommand;

public class ReverseCard extends Action {

    public ReverseCard(ActionType actionType, Color color) {
        super(actionType, color);
    }

    @Override
    public void performAction(GameCommand gameCommand) {
        PlayerQueue players = gameCommand.getPlayerQueue();
        players.reversePlayerOrder();
        if(players.getNumberOfPlayers() > 2)
            players.getNextPlayer();
    }

    @Override
    public String getCardName() {
        return "Reverse";
    }
}
