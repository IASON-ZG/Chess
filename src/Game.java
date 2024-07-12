import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;
import pieces.Piece;

import java.util.*;

public class Game {

    Board board = new Board();
    Color turn = Color.WHITE;
    Map<String, List<String>> games = new HashMap<String, List<String>>();
    List<String> moves = new ArrayList<>();
    boolean end_game = false;


    public static void main(String[] args) throws InvalidMoveException {
        new Game().play();
    }


    public void play() throws InvalidMoveException {
        board.init();
        System.out.println(board.toString());
        Scanner myObj = new Scanner(System.in);
        System.out.print(turn + " plays "  + "enter move :");
        String move = myObj.nextLine();

        while (end_game == false){
            if (move.charAt(0) == ':'){
                if(move.charAt(1) == 'h'){
                    printHelp();
                }
                else if (move.charAt(1) == 's'){
                    saveGame();
                }
                else if (move.charAt(1) == 'o'){
                    openGame();
                }
                else if (move.charAt(1) == 'x'){
                    exitGame();
                    return;
                }
                System.out.println(board.toString());
            }
            else {
                handleMove(move);
                System.out.println(board.toString());
            }
            System.out.print(turn + " plays "  + "enter move :");
            move = myObj.nextLine();
        }
    }


    private void handleMove(String moveString) throws InvalidMoveException {
        if (moveString.length() != 4){
            throw new InvalidMoveException();
        }
        else {
            Location location_piece = new Location(moveString.substring(0,2));
            Piece piece = board.getPieceAt(location_piece);
            if (piece != null) {
                if (piece.getColor().equals(isTurn())) {
                    Location location_to = new Location(moveString.substring(2));
                    try {
                        piece.moveTo(location_to);
                        setTurn(isTurn().nextColor());
                        moves.add(moveString);
                    } catch (InvalidMoveException e){
                        System.out.println("Please enter a valid move !" + '\n');
                    }
                }
                else {
                    System.out.println("Please enter a valid move!");
                }
            }
            else {
                System.out.println("Please enter a valid move!");
            }
        }
    }

    private void saveGame(){
        Scanner myObj = new Scanner(System.in);
        System.out.print("What would you like to name your game ? ");
        String name = myObj.nextLine();
//        game[0] = board;
        List<String> moves_save = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++){
            moves_save.add(moves.get(i));
        }
        games.put(name,moves_save);
    }

    private void openGame() throws InvalidMoveException {
        Scanner myObj = new Scanner(System.in);
        System.out.print("What game would you like to continue ? ");
        String name = myObj.nextLine();
        List<String> game_picked = games.get(name);
        moves = new ArrayList<>();
        if( game_picked != null ){
            setTurn(Color.WHITE);
            board = new Board();
            board.init();
            int size = game_picked.size();
            for (int i = 0 ; i < size; i++){
                handleMove(game_picked.get(0));
                game_picked.remove(0);
            }
        }
        else {
            System.out.println("Please pick a game that exists!");
        }

    }

    private void exitGame(){
        end_game = true;
    }

    private void printHelp(){
        System.out.print("The user has the ability to either type in his move in this form (a2a4) or choose from a variety of options" + '\n' +
                "such as :h - help  :s - save :o -open save :x -exit" + '\n' + '\n');

    }


    public Color isTurn() {
        return turn;
    }

    public void setTurn(Color turn) {
        this.turn = turn;
    }
}