package Game;

import Cards.Card;
import Cards.ColoredCards.ActionCards.Action;
import Cards.ColoredCards.CardWithColor;
import Cards.ColoredCards.NumberedCard.CardWithNumber;
import Cards.DiscardPile;
import Cards.DrawPile;
import Cards.WildCards.Wild;
import Player.*;

import java.util.Arrays;
import java.util.Scanner;


public class UnoGame implements Game {
    private final PlayerQueue players;
    private final Deck deck;
    private DiscardPile discardPile;
    private DrawPile drawPile;
    GameCommand gameCommand;
    Scanner input = new Scanner(System.in);

    public UnoGame() {
        players = new PlayerQueue();
        this.deck = Deck.getInstance();
        this.discardPile = DiscardPile.getInstance();
        this.drawPile = DrawPile.getInstance();
    }

    @Override
    public void play() {
        createPlayers();
        dealInitialCards();
        boolean hasEnded = false;
        System.out.println("\tNUMBER OF PLAYERS IN THE GAME: " + players.getNumberOfPlayers());
        gameCommand = new GameCommand(drawPile, discardPile, players);
        for (Player player : players) {
            player.displayPlayerCards();
        }
        System.out.println();
        while (!hasEnded) {
            gameCommand.getPlayerTurn();
            System.out.println();
            if (gameCommand.isPlayable()) {
                players.getCurrentPlayer().displayPlayerCards();
                System.out.println("**********************************************");
                System.out.println("Current Top Card: \n" + CardFormat.formatCard(discardPile.getTop()));
                System.out.println("**********************************************");
                System.out.print("NOW CHOOSE A CARD THAT MATCHES THE TOP OF THE DISCARD:");
                int selectedCardIndex = input.nextInt() - 1;
                if (gameCommand.isPlayableCard(selectedCardIndex)) {
                    Card card = gameCommand.playCard(selectedCardIndex);
                    System.out.println("**********************************************");
                    discardPile.addCardToDiscardPile(card);
                } else {
                    System.out.println("\n**********************************************");
                    System.out.println("HEY " + players.getCurrentPlayer().getName() + ", CHOOSE A CARD THAT MATCHES THE TOP.");
                    System.out.println("**********************************************");
                    continue;
                }
            } else {
                System.out.println("**********************************************");
                System.out.println("You don't have a playable card, drawing one card...");
                Card card = drawPile.getCard();
                if (card.isValid(discardPile.getTop())) {
                    System.out.println("**********************************************");
                    System.out.println("HEY, YOU HAVE DRAWN A MATCHED CARD! " + CardFormat.formatCard(drawPile.getTop()));
                    System.out.println("**********************************************");
                    discardPile.addCardToDiscardPile(card);
                } else {
                    System.out.println("**********************************************");
                    System.out.println("Sorry, the drawn card" + CardFormat.formatCard(drawPile.getTop()) + " doesn't match, adding it to your collection.");
                    System.out.println("**********************************************");
                    players.getCurrentPlayer().addCardToPlayerHand(card);
                }
                players.getNextPlayer(); //proceed to the next player whether they had drawn a matched card or not
            }
            gameCommand.updateGameStatus(drawPile, discardPile, players);
            hasEnded = checkForWinner();
        }
    }

    public void createPlayers() {
        System.out.println("ENTER PLAYERS NAMES");
        String playerNamesInput = input.nextLine().toUpperCase();
        String[] playersNames = playerNamesInput.split(" ");
        System.out.println();
        for (String playerName : playersNames) {
            Player newPlayer = new Player(playerName);
            players.addPlayer(newPlayer);
        }
    }

    public void dealInitialCards() {
        this.drawPile = DrawPile.getInstance();
        for (int i = 0; i < 7; ++i) {
            for (Player player : players) {
                Card card = drawPile.getCard();
                player.addCardToPlayerHand(card);
            }
        }
        this.discardPile = DiscardPile.getInstance();
    }

    public boolean checkForWinner() {
        for (Player player : players) {
            if (player.getCardsInHand().size() == 0) {
                System.out.println(player.getName() + " HAS WON CONGRATULATIONS!!");
                return true;
            }
        }
        return false;
    }
}