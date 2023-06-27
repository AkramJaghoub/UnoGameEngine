package Validation.Exception;

public class IllegalNumberOfPlayersException extends RuntimeException{
    public IllegalNumberOfPlayersException(){
        super("Invalid Number Of Players");
    }
}
