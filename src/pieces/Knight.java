package pieces;

import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;

public class Knight extends Piece{
    public Knight(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public void moveTo(Location newLoc) throws InvalidMoveException {
        if ( newLoc.getRow() >= 0 && newLoc.getRow() <= 7 && newLoc.getCol()>= 0 && newLoc.getCol() <= 7) {
            int diff_row = Math.abs(newLoc.getRow()- getLocation().getRow());
            int diff_col = Math.abs(newLoc.getCol()- getLocation().getCol());
            if ( (diff_row == 1 && diff_col == 2) || (diff_row == 2 && diff_col ==1 ) ){
                if (getBoard().getTable()[newLoc.getRow()][newLoc.getCol()] == null){
                    getBoard().movePiece(getLocation(),newLoc);
                    setLocation(newLoc);
                }
                else {
                    Piece piece = getBoard().getPieceAt(newLoc);
                    if (piece.getColor().equals(getColor())){
                        throw new InvalidMoveException();
                    }
                    else {
                        getBoard().movePieceCapturing(getLocation(), newLoc);
                        setLocation(newLoc);
                    }
                }
            }
            else {
                throw new InvalidMoveException();
            }

        }
        else {
            throw new InvalidMoveException();
        }
    }

    public String toString(){
        if (getColor() == Color.WHITE){
            return "N";
        } else {
            return "n";
        }
    }
}
