package pieces;

import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;

public class King extends Piece {

    public King(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public void moveTo(Location newLoc) throws InvalidMoveException {
        if ( newLoc.getRow() >= 0 && newLoc.getRow() <= 7 && newLoc.getCol()>= 0 && newLoc.getCol() <= 7) {
            if (Math.abs(newLoc.getCol()-getLocation().getCol()) > 1 || Math.abs(newLoc.getRow()-getLocation().getRow()) > 1 ){
                throw new InvalidMoveException();
            }
            else {
                if (getBoard().getTable()[newLoc.getRow()][newLoc.getCol()] == null) {
                    getBoard().movePiece(getLocation(), newLoc);
                    setLocation(newLoc);
                } else {
                    Piece piece = getBoard().getPieceAt(newLoc);
                    if (piece.getColor().equals(getColor())) {
                        throw new InvalidMoveException();
                    } else {
                        getBoard().movePieceCapturing(getLocation(), newLoc);
                        setLocation(newLoc);
                    }
                }
            }

        }
    }

    public String toString(){
        if (getColor() == Color.WHITE){
            return "K";
        } else {
            return "k";
        }
    }

}
