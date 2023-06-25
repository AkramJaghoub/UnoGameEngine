package Cards.WildCards;

import Cards.Card;
import Cards.ColoredCards.Color;
import Game.GameCommand;
import java.util.Scanner;

public abstract class Wild extends Card {
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

    public void determineChosenColor(){
        System.out.println("Choose one of the colors below for the Wild Card");
        System.out.println("1. Red\t  2. Blue\t  3. Green\t  4. Yellow");
        Scanner input = new Scanner(System.in);
        int colorChoice = input.nextInt();
        this.chosenColor = switch (colorChoice) {
            case 1 -> Color.RED;
            case 2 -> Color.BLUE;
            case 3 -> Color.GREEN;
            case 4 -> Color.YELLOW;
            default -> throw new IllegalArgumentException("COLOR DOESN'T EXIST");
        };
    }

    public abstract void performAction(GameCommand gameCommand);

    @Override
    public Boolean isValid(Card otherCard) {
        return true;
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
