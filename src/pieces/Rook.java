package pieces;

import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;

public class Rook extends Piece{
    public Rook(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public void moveTo(Location newLoc) throws InvalidMoveException {
        if ( newLoc.getRow() >= 0 && newLoc.getRow() <= 7 && newLoc.getCol()>= 0 && newLoc.getCol() <= 7) {
            if (newLoc.getCol() == getLocation().getCol()){
                if (getBoard().freeVerticalPath(getLocation(),newLoc)){
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
            else if ( newLoc.getRow() == getLocation().getRow()){
                if (getBoard().freeHorizontalPath(getLocation(),newLoc)){
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
        else {
            throw new InvalidMoveException();
        }
    }
    public String toString(){
        if (getColor() == Color.WHITE){
            return "R";
        } else {
            return "r";
        }
    }

}
