package main;

public class InvalidMoveException extends Exception{

    public InvalidMoveException(){
        super("Please insert a valid Move");
    }
}
