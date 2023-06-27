package Cards.WildCards;

import Cards.Card;
import Cards.ColoredCards.Color;
import Command.GameCommand;
import Util.Validate;

public abstract class Wild implements Card{
    private final WildType wildType;
    private Color chosenColor;

    protected Wild(WildType wildType){
        this.wildType = wildType;
    }

    public WildType getWildType(){
        return wildType;
    }

    public Color getChosenColor(){
        return chosenColor;
    }

    public abstract void performAction(GameCommand gameCommand);

    public void determineChosenColor(){
        int colorChoice = Validate.handleWildColorsInput();
        this.chosenColor = switch (colorChoice) {
            case 1 -> Color.RED;
            case 2 -> Color.BLUE;
            case 3 -> Color.GREEN;
            case 4 -> Color.YELLOW;
            default -> throw new IllegalStateException("Unexpected value: " + colorChoice);
        };
    }

    @Override
    public boolean canPlayOn(Card topDiscardCard) {
        return true;
    }

    @Override
    public int cardScore() {
        return 50;
    }

    @Override
    public String toString() {
        String color = getChosenColor() == null ? "" : ", Color = " + getChosenColor();
        return "Card{" +
                "wildType = " + wildType +
                 color +
                '}';
    }
}
