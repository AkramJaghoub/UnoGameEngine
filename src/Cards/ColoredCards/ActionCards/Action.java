package Cards.ColoredCards.ActionCards;

import Cards.Card;
import Cards.ColoredCards.CardWithColor;
import Cards.ColoredCards.Color;
import Cards.ColoredCards.NumberedCard.CardWithNumber;
import Cards.WildCards.Wild;
import Game.GameCommand;

public abstract class Action extends CardWithColor {
    private final ActionType actionType;
    protected Action(ActionType actionType, Color color){
        super(color);
        this.actionType = actionType;
    }

    public ActionType getActionCard(){
        return actionType;
    }

    public abstract void performAction(GameCommand gameCommand);

    @Override
    public Boolean isValid(Card otherCard) {
        if(otherCard instanceof CardWithNumber){
            CardWithNumber card2 = (CardWithNumber) otherCard;
            return (getColor() == card2.getColor());
        }
        if(otherCard instanceof Action){
            Action card2 = (Action) otherCard;
            return (getColor() == card2.getColor() || getCardName().equals(card2.getCardName()));
        }
        if(otherCard instanceof Wild){
            Wild card2 = (Wild) otherCard;
            return getColor() == card2.getChosenColor();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Card{" +
                "actionType = " + actionType +
                ", color = " + getColor() +
                '}';
    }
}
