package Validation.Exception;

public class InvalidDrawException extends RuntimeException{
    public InvalidDrawException(){
        super("Cant Draw");
    }
}
