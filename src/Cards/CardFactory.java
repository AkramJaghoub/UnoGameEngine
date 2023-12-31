package Cards;

import Cards.ColoredCards.ActionCards.*;
import Cards.ColoredCards.Color;
import Cards.ColoredCards.NumberedCard.CardWithNumber;
import Cards.WildCards.WildCard;
import Cards.WildCards.WildDrawFourCard;
import Cards.WildCards.WildType;
import Validation.Exception.CardNotFoundException;

public class CardFactory {
    public Card createCard(ActionType actionType, Color color){
        return switch(actionType){
            case DRAW_TWO -> new DrawTwoCard(actionType, color);
            case SKIP -> new SkipCard(actionType, color);
            case REVERSE -> new ReverseCard(actionType, color);
            case default -> throw new CardNotFoundException();
        };
    }

    public Card createCard(WildType wildType){
        return switch(wildType) {
            case WILD_CARD -> new WildCard(wildType);
            case WILD_DRAW_FOUR -> new WildDrawFourCard(wildType);
            case default -> throw new CardNotFoundException();
        };
    }

    public Card createCard(int number, Color color){
        return new CardWithNumber(number, color);
    }
}