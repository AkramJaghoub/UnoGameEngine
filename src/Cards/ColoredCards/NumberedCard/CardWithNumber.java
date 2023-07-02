package Cards.ColoredCards.NumberedCard;

import Cards.Card;
import Cards.ColoredCards.ActionCards.Action;
import Cards.ColoredCards.CardWithColor;
import Cards.ColoredCards.Color;
import Cards.WildCards.Wild;
import Validation.Exception.CardNotFoundException;

import java.util.Objects;

public class CardWithNumber extends CardWithColor {
    private final int number;
    public CardWithNumber(int number, Color color){
        super(color);
        this.number = number;
    }
    public int getNumber(){
        return number;
    }

    @Override
    public String getCardName() {
        return "Numbered";
    }

    @Override
    public boolean canPlayOn(Card topDiscardCard) {
        if(topDiscardCard instanceof CardWithNumber top){
            return (Objects.equals(getNumber(), top.getNumber()) || getColor() == top.getColor());
        }
        if(topDiscardCard instanceof Action top){
            return getColor() == top.getColor();
        }
        if(topDiscardCard instanceof Wild top){
            return getColor() == top.getChosenColor();
        }
        throw new CardNotFoundException();
    }

    @Override
    public int cardScore() {
        return this.number;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number = " + number +
                ", color = " + getColor() +
                '}';
    }
}