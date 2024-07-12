package main;

public class InvalidLocationException extends Exception{

    public InvalidLocationException(){
        super("Please insert a valid location");
    }
}
