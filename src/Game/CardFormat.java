package Game;

import Cards.Card;
import Cards.ColoredCards.ActionCards.Action;
import Cards.ColoredCards.CardWithColor;
import Cards.ColoredCards.NumberedCard.CardWithNumber;
import Cards.WildCards.Wild;

public class CardFormat {
    public static String formatCard(Card card) {
        if (card == null) {
            return "";
        }
        String colorCode;
        String cardValue;

        if (card instanceof Wild) {
            colorCode = "\u001B[37m"; // White color for Wild cards
            cardValue = " " + ((Wild) card).getCardName() + " ";
        } else {
            colorCode = switch (((CardWithColor) card).getColor()) {
                case RED -> "\u001B[31m";
                case GREEN -> "\u001B[32m";
                case YELLOW -> "\u001B[33m";
                case BLUE -> "\u001B[34m";
            };

            if (card instanceof CardWithNumber) {
                cardValue = String.valueOf(((CardWithNumber) card).getNumber());
            } else if (card instanceof Action) {
                cardValue = ((Action) card).getCardName();
            } else {
                cardValue = ((CardWithColor) card).getColor().toString();
            }
        }

        int cardWidth = Math.max(cardValue.length() + 2, 16); // Adjust the minimum width as needed

        String horizontalLine = colorCode + "─".repeat(cardWidth) + "\u001B[0m";
        String emptyLine = colorCode + " ".repeat(cardWidth) + "\u001B[0m";

        String[] lines = {
                "       " + colorCode + "┌" + horizontalLine + colorCode + "┐\u001B[0m",
                "       " + colorCode + "│" + emptyLine + "│\u001B[0m",
                "       " + colorCode + "│" + padCenter(cardValue, cardWidth) + "│\u001B[0m",
                "       " + colorCode + "│" + emptyLine + "│\u001B[0m",
                "       " + colorCode + "└" + horizontalLine + colorCode + "┘\u001B[0m"
        };

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line.replace("│", colorCode + "│")).append("\n");
        }
        return sb.toString();
    }

    public static String formatCard(Card card, int index) {
        String cardString = formatCard(card);
        if (!cardString.isEmpty()) {
            cardString = String.format("               " + (index + 1) + ".        \n") + cardString;
        }
        return cardString;
    }

    private static String padCenter(String str, int width) {
        int totalPadding = width - str.length();
        int leftPad = Math.max(0, totalPadding / 2);
        int rightPad = Math.max(0, totalPadding - leftPad);
        return " ".repeat(leftPad) + str + " ".repeat(rightPad);
    }
}
