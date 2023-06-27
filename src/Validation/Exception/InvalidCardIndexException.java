package Validation.Exception;

public class InvalidCardIndexException extends RuntimeException {
    public InvalidCardIndexException(){
        super("Invalid card index");
    }
}
