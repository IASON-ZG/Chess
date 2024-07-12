import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;
import pieces.*;

import java.util.*;

public class Game {

    Board board;
    Color turn;
    Map<String, List<String>> games = new HashMap<String, List<String>>();
    List<String> moves;
    boolean end_game;

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    public static void main(String[] args) throws InvalidMoveException {
        new Game().play();
    }


    public void play() throws InvalidMoveException {
        List<String> moves = new ArrayList<>();
        setMoves(moves);
        board = new Board();
        setTurn(Color.WHITE);
        end_game = false;

        board.init();
        System.out.println(board.toString());
        Scanner myObj = new Scanner(System.in);


        while (end_game == false){
            System.out.print(turn + " plays "  + "enter move :");
            String move = myObj.nextLine();
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
                if (move.length() != 4){
                    System.out.println('\n' +"Wrong syntax in the move!" + '\n');
                }
                else if (move.charAt(0) > 'h' || move.charAt(1) > '8' || move.charAt(1) < '1' || move.charAt(2) > 'h' || move.charAt(3) > '8' || move.charAt(3) < '1'){
                    System.out.println('\n' +"Move outside of range!" + '\n');

                } else {
                    handleMove(move);
                }
                System.out.println(board.toString());
            }
        }
        System.out.print("Want to play again ? y-yes n-no ");
        String again = myObj.nextLine();
        if (again.charAt(0) == 'y'){
            this.play();
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
                        if (board.isEnd()){
                            end_game = true;
                        }
                    } catch (InvalidMoveException e){
                        if (piece.getClass().equals(Pawn.class)){
                            System.out.println('\n' +"Pawn only moves vertical and can move 2 positions forward only the first time!" + '\n');
                        }
                        else if (piece.getClass().equals(Bishop.class)){
                            System.out.println('\n' +"Bishop only moves horizontally and not on top of other pieces!" + '\n');
                        }
                        else if (piece.getClass().equals(King.class)){
                            System.out.println('\n' +"King only moves one position and not on top of other pieces!" + '\n');
                        }
                        else if (piece.getClass().equals(Knight.class)){
                            System.out.println('\n' +"Knight can move 2 paces horizontally and 1 vertically or the other way around!" + '\n');
                        }
                        else if (piece.getClass().equals(Queen.class)){
                            System.out.println('\n' +"Queen cannot move on top of other pieces" + '\n');
                        }
                        else if (piece.getClass().equals(Rook.class)){
                            System.out.println('\n' +"Rook can move either horizontally and vertically as many positions but not on top of other pieces!" + '\n');
                        }
                    }
                }
                else {
                    System.out.println('\n' + "Wrong color piece moved !" + '\n');
                }
            }
            else {
                System.out.println('\n' +"There is no piece to move!" + '\n');
            }
        }
    }

    private void saveGame(){
        Scanner myObj = new Scanner(System.in);
        System.out.print("What would you like to name your game ? ");
        String name = myObj.nextLine();
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