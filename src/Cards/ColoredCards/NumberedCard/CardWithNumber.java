package Cards.ColoredCards.NumberedCard;

import Cards.Card;
import Cards.ColoredCards.ActionCards.Action;
import Cards.ColoredCards.CardWithColor;
import Cards.ColoredCards.Color;
import Cards.WildCards.Wild;

import java.util.Objects;

public class CardWithNumber extends CardWithColor {
    private final Number number;
    public CardWithNumber(Number number, Color color){
        super(color);
        this.number = number;
    }
    public Number getNumber(){
        return number;
    }

    @Override
    public String getCardName() {
        return "Numbered";
    }

    @Override
    public Boolean isValid(Card otherCard) {
        if(otherCard instanceof Action){
            Action card2 = (Action) otherCard;
            return getColor() == card2.getColor();
        }
        if(otherCard instanceof Wild){
            Wild card2 = (Wild) otherCard;
            return getColor() == card2.getChosenColor();
        }
        CardWithNumber card2 = (CardWithNumber) otherCard;
        return (Objects.equals(getNumber(), card2.getNumber()) || getColor() == card2.getColor());
    }

    @Override
    public String toString() {
        return "Card{" +
                "number = " + number +
                ", color = " + getColor() +
                '}';
    }
}
