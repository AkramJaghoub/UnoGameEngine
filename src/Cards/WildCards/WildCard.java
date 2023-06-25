package Cards.WildCards;

import Game.Game;
import Game.GameCommand;

public class WildCard extends Wild {

    public WildCard(WildType wildType) {
        super(wildType);
    }

    @Override
    public void performAction(GameCommand gameCommand) {
        determineChosenColor();
        gameCommand.getPlayerQueue().getNextPlayer();
    }

    @Override
    public String getCardName() {
        return "Wild";
    }
}