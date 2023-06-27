package Command;

import Cards.Card;
import Cards.ColoredCards.ActionCards.Action;
import Cards.WildCards.Wild;
public class CardCommand implements Command {
    private final Card card;
    private final GameCommand gameCommand;
    public CardCommand(Card card, GameCommand gameCommand) {
        this.card = card;
        this.gameCommand = gameCommand;
    }
    public void execute() {
        if (card instanceof Action actionCard) {
            actionCard.performAction(gameCommand);
        } else if (card instanceof Wild wildCard) {
            wildCard.performAction(gameCommand);
        }else{
            gameCommand.getPlayerQueue().getNextPlayer();
        }
    }
}