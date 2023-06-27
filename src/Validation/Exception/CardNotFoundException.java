package Validation.Exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Card Doesn't Exist");
    }
}
