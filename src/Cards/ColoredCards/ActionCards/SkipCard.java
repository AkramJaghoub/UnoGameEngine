package Cards.ColoredCards.ActionCards;

import Cards.ColoredCards.Color;
import Player.PlayerQueue;
import Command.GameCommand;

public class SkipCard extends Action{
    public SkipCard(ActionType actionType, Color color) {
        super(actionType, color);
    }
    @Override
    public void performAction(GameCommand gameCommand) {
        PlayerQueue players = gameCommand.getPlayerQueue();
          players.getNextPlayer();
          players.getNextPlayer();
    }
    @Override
    public String getCardName() {
        return "Skip";
    }
}