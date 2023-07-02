package Game;

import Cards.Card;
import Cards.Piles.DiscardPile;
import Cards.Piles.DrawPile;
import Command.GameCommand;
import Player.*;
import Util.CardFormat;
import Util.Validate;
import java.util.Scanner;

public class UnoGame extends Game {

    public UnoGame() {
        players = PlayerQueue.getInstance();
        this.discardPile = DiscardPile.getInstance();
        this.drawPile = DrawPile.getInstance();
        gameCommand = new GameCommand();
    }

    @Override
    protected void play() {
        runGame();
    }

    @Override
    protected void dealInitialCards() {
        for (int i = 0; i < 7; ++i) {
            for (Player player : players) {
                Card card = drawPile.drawCard();
                player.addCardToPlayerHand(card);
            }
        }
    }

    @Override
    protected void runRound() {
        boolean hasRoundEnded = false;
        while (!hasRoundEnded) {
            Player currentPlayer = players.getCurrentPlayer();
            currentPlayer.getPlayerTurn();
            if (gameCommand.isCardAvailable()) {
                System.out.println("Current Top Card: \n" + CardFormat.formatCard(discardPile.getTop()));
                System.out.print("NOW CHOOSE A CARD THAT MATCHES THE TOP OF THE DISCARD: ");
                int selectedCardIndex = Validate.handleCardsInput();
                if (gameCommand.isPlayableCard(selectedCardIndex)) {
                    Card card = currentPlayer.getCardsInHand().get(selectedCardIndex);
                    gameCommand.playCard(card);
                    System.out.println("**********************************************");
                } else {
                    System.out.println("\n**********************************************");
                    System.out.println("HEY " + currentPlayer.getName() + ", CHOOSE A CARD THAT MATCHES THE TOP.");
                    System.out.println("**********************************************");
                    continue;
                }
            } else {
                System.out.println("You don't have a playable card, drawing one card...");
                Card card = drawPile.drawCard();
                if (card.canPlayOn(discardPile.getTop())) {
                    System.out.println("HEY, YOU HAVE DRAWN A MATCHED CARD!\n" + CardFormat.formatCard(card));
                    System.out.println("\n");
                    gameCommand.playMatchedCard(card);
                } else {
                    System.out.println("Sorry, the drawn card\n" + CardFormat.formatCard(card) + " \ndoesn't match, adding it to your collection.");
                    currentPlayer.addCardToPlayerHand(card);
                    players.getNextPlayer();
                }
            }
            hasRoundEnded = checkRoundWinner();
        }
    }

    @Override
    protected boolean checkRoundWinner() {
        Player roundWinner = players.getRoundWinner();
        if (roundWinner == null)
            return false;
        Round round = new Round();
        round.calculateRemainingPlayersPoints(roundWinner);
        System.out.println("Congratulations " + roundWinner.getName() + " you have won the round with a score of: " + roundWinner.getRoundPoints());
        round.displayScoreboard();
        return true;
    }

    @Override
    protected void displayGameWinner(){
        Player gameWinner = players.getGameWinner();
        System.out.println("Congratulations " + gameWinner.getName() + " you have won the game!!!!!");
    }

    @Override
    protected boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you wish to play again? (yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                resetGame();
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.print("Invalid input, please enter 'yes' or 'no': ");
            }
        }
    }

    @Override
    protected void resetGame(){
        players.resetPlayers();
        DrawPile.getInstance().initializeDrawPile();
        DiscardPile.getInstance().initializeDiscardPile();
        dealInitialCards();
    }
}