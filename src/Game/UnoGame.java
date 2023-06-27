package Game;

import Cards.Card;
import Cards.Piles.DiscardPile;
import Cards.Piles.DrawPile;
import Command.GameCommand;
import Player.*;
import Util.CardFormat;
import Command.*;
import Util.Validate;

import java.util.Scanner;

public class UnoGame implements Game {
    private final PlayerQueue players;
    private final DiscardPile discardPile;
    private final DrawPile drawPile;
    GameCommand gameCommand;

    public UnoGame() {
        players = PlayerQueue.getInstance();
        this.discardPile = DiscardPile.getInstance();
        this.drawPile = DrawPile.getInstance();
        gameCommand = new GameCommand();
    }

    @Override
    public void play() {
        preGameSetUp();
        boolean playGame = true;
        while (playGame) {
            boolean hasEnded = false;
            while (!hasEnded) {
                gameCommand.getPlayerTurn();
                System.out.println();
                players.getCurrentPlayer().displayPlayerCards();
                if (gameCommand.isPlayable()) {
                    System.out.println("Current Top Card: \n" + CardFormat.formatCard(discardPile.getTop()));
                    System.out.print("NOW CHOOSE A CARD THAT MATCHES THE TOP OF THE DISCARD:");
                    int selectedCardIndex = Validate.handleCardsInput();
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
                    System.out.println("You don't have a playable card, drawing one card...");
                    Card card = drawPile.drawCard();
                    if (card.canPlayOn(discardPile.getTop())) {
                        System.out.println("HEY, YOU HAVE DRAWN A MATCHED CARD!\n" + CardFormat.formatCard(card));
                        System.out.println("\n");
                        if (drawPile.isSpecialCard(card)) {
                            new CardCommand(card, gameCommand).execute(); //check if the drawn card is an action card to perform an action
                        } else {
                            players.getNextPlayer();
                        }
                        discardPile.addCardToDiscardPile(card);
                    } else {
                        System.out.println("Sorry, the drawn card\n" + CardFormat.formatCard(card) + " \ndoesn't match, adding it to your collection.");
                        players.getCurrentPlayer().addCardToPlayerHand(card);
                        players.getNextPlayer(); //proceed to the next player if they didn't draw a matched card
                    }
                }
                hasEnded = players.checkForWinner();
            }
            playGame = playAgain(); //yes plays another round no ends the game
            if(playGame)
                resetGame();
        }
    }
    private void preGameSetUp(){
        String [] playerNames = Validate.handlePlayerNamesInput();
        players.createPlayers(playerNames);
        dealInitialCards();
    }

    private void dealInitialCards() {
        for (int i = 0; i < 7; ++i) {
            for (Player player : players) {
                Card card = drawPile.drawCard();
                player.addCardToPlayerHand(card);
            }
        }
    }

    private boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to play again? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    private void resetGame(){
        players.resetPlayerCards();
        DrawPile.getInstance().initializeDrawPile();
        DiscardPile.getInstance().initializeDiscardPile();
        dealInitialCards();
    }
}