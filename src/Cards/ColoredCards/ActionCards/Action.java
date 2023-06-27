package Cards.ColoredCards.ActionCards;

import Cards.Card;
import Cards.ColoredCards.CardWithColor;
import Cards.ColoredCards.Color;
import Cards.ColoredCards.NumberedCard.CardWithNumber;
import Cards.WildCards.Wild;
import Command.GameCommand;
import Validation.Exception.CardNotFoundException;

public abstract class Action extends CardWithColor {
    private final ActionType actionType;
    protected Action(ActionType actionType, Color color){
        super(color);
        this.actionType = actionType;
    }

    public ActionType getActionType(){
        return actionType;
    }

    public abstract void performAction(GameCommand gameCommand);

    @Override
    public boolean canPlayOn(Card topDiscardCard) {
        if(topDiscardCard instanceof CardWithNumber top){
            return getColor() == top.getColor();
        }
        if(topDiscardCard instanceof Action top){
            return getColor() == top.getColor() || getCardName().equals(top.getCardName());
        }
        if(topDiscardCard instanceof Wild top){
            return getColor() == top.getChosenColor();
        }
        throw new CardNotFoundException();
    }

    @Override
    public int cardScore(){
        return 20;
    }

    @Override
    public String toString() {
        return "Card{" +
                "actionType = " + actionType +
                ", color = " + getColor() +
                '}';
    }
}
