package pieces;

import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;

public class Pawn extends Piece{

    private boolean first_move ;
    public Pawn(Color color, Location location, Board board) {
        super(color, location, board);
        this.first_move = true;
    }

    @Override
    public void moveTo(Location newLoc) throws InvalidMoveException {
        int checker = 1;
        if (getColor().equals(Color.BLACK)){
            checker = -1 ;
        }
        int new_row = newLoc.getRow();
        int current_row = getLocation().getRow();

        if ( newLoc.getRow() >= 0 && newLoc.getRow() <= 7 && newLoc.getCol()>= 0 && newLoc.getCol() <= 7) {
            if (newLoc.getCol() != getLocation().getCol()) {
                if (((new_row - getLocation().getRow() * checker) == 1) && (getBoard().getTable()[new_row][newLoc.getCol()] != null)) {
                    Piece piece = getBoard().getPieceAt(newLoc);
                    if (piece.getColor().equals(getColor())){
                        throw new InvalidMoveException();
                    }
                    else{
                        getBoard().movePieceCapturing(getLocation(), newLoc);
                        setLocation(newLoc);
                    }
                } else {
                    throw new InvalidMoveException();
                }
            }
            else {
                if (getBoard().getTable()[new_row][newLoc.getCol()] == null) {
                    if ((new_row - current_row) * checker == 1) {
                        getBoard().movePiece(getLocation(), newLoc);
                        setLocation(newLoc);
                    } else if ((new_row - current_row) * checker == 2 && first_move == true) {
                        getBoard().movePiece(getLocation(), newLoc);
                        setLocation(newLoc);
                        first_move = false;
                    } else {
                        throw new InvalidMoveException();
                    }
                }
                else {
                    throw new InvalidMoveException();
                }
            }
        }
        else {
            throw new InvalidMoveException();
        }
    }

    public String toString(){
        if (getColor() == Color.WHITE){
            return "P";
        } else {
            return "p";
        }
    }
}
