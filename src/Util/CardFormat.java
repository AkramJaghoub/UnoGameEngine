package Util;

import Cards.Card;
import Cards.ColoredCards.CardWithColor;
import Cards.ColoredCards.Color;
import Cards.ColoredCards.NumberedCard.CardWithNumber;
import Cards.WildCards.Wild;

public final class CardFormat {

    private CardFormat() {}

    public static String formatCard(Card card, int index) {
        String cardString = formatCard(card);
        return cardString.isEmpty() ? "" : String.format("               %d.        \n%s", index + 1, cardString);
    }

    public static String formatCard(Card card) {
        String colorCode;
        String cardValue;

        if (card == null) {
            return "";
        }

        if (card instanceof Wild wildCard) {
            colorCode = wildCard.getChosenColor() != null ? checkColor(wildCard.getChosenColor()) : "\u001B[37m"; //if color wasn't chosen color it to white
            cardValue = " " + wildCard.getCardName() + " ";
        } else {
            CardWithColor coloredCard = (CardWithColor) card;
            colorCode = checkColor(coloredCard.getColor());
            cardValue = card instanceof CardWithNumber ? String.valueOf(((CardWithNumber) card).getNumber()) : card.getCardName();
        }
        int cardWidth = Math.max(cardValue.length() + 2, 16);
        String horizontalLine = colorCode + "─".repeat(cardWidth) + "\u001B[0m";
        String emptyLine = colorCode + " ".repeat(cardWidth) + "\u001B[0m";
        String[] lines = {
                "       " + colorCode + "┌" + horizontalLine + colorCode + "┐\u001B[0m",
                "       " + colorCode + "│" + emptyLine + "│\u001B[0m",
                "       " + colorCode + "│" + padCenter(cardValue, cardWidth) + "│\u001B[0m",
                "       " + colorCode + "│" + emptyLine + "│\u001B[0m",
                "       " + colorCode + "└" + horizontalLine + colorCode + "┘\u001B[0m"
        };
        return String.join("\n", lines).replace("│", colorCode + "│");
    }

    private static String checkColor(Color color) {
        return switch (color) {
            case RED -> "\u001B[31m";
            case GREEN -> "\u001B[32m";
            case YELLOW -> "\u001B[33m";
            case BLUE -> "\u001B[34m";
        };
    }

    private static String padCenter(String str, int width) {
        int totalPadding = width - str.length();
        int leftPad = Math.max(0, totalPadding / 2);
        int rightPad = Math.max(0, totalPadding - leftPad);
        return " ".repeat(leftPad) + str + " ".repeat(rightPad);
    }
}