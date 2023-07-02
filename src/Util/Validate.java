package Util;

import Player.PlayerQueue;
import Validation.Exception.IllegalNumberOfPlayersException;
import Validation.Exception.InvalidCardIndexException;
import Validation.Exception.InvalidColorIndexException;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Validate {
    private Validate(){
    }
    public static int handleCardsInput() {
        PlayerQueue players = PlayerQueue.getInstance();
        int selectedCardIndex = -1;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                Scanner input = new Scanner(System.in);
                selectedCardIndex = input.nextInt() - 1;
                if (selectedCardIndex < 0 || selectedCardIndex >= players.getCurrentPlayer().getCardsInHand().size()) {
                    throw new InvalidCardIndexException();   //if player enters the wrong index
                }
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a numeric value: ");
            } catch (InvalidCardIndexException e) {
                System.out.print(e.getMessage() + ", please enter a valid card index: ");
            }
        }
        return selectedCardIndex;
    }

    public static String[] handlePlayerNamesInput() {
        Scanner input = new Scanner(System.in);
        String[] playerNames = null;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.print("Enter player names (separated by space): ");
                String playerNamesInput = input.nextLine().trim();
                playerNames = playerNamesInput.split(" ");
                if (playerNames.length < 2 || playerNames.length > 10) {
                    throw new IllegalNumberOfPlayersException();
                }
                isValidInput = true;
            } catch (IllegalNumberOfPlayersException e) {
                System.out.println(e.getMessage() + " please enter 2 to 10 player names");
            }
        }
        return playerNames;
    }

    public static int handleWildColorsInput(){
        int colorIndex = 0;
        boolean isValidInput = false;
        while(!isValidInput) {
            try {
                System.out.println("Choose one of these colors:\t ");
                System.out.print("1. Red\t  2. Blue\t  3. Green\t  4. Yellow\n");
                Scanner input = new Scanner(System.in);
                colorIndex = input.nextInt();
                if(colorIndex < 1 || colorIndex > 4) {
                    throw new InvalidColorIndexException();  //if player enters the wrong index
                }
                isValidInput = true;
            }catch(InputMismatchException e){
                System.out.print("Please enter a numeric value: ");
            }catch(InvalidColorIndexException e){
                System.out.println(e.getMessage() + ", please enter a valid card color");
            }
        }
        return colorIndex;
    }
}