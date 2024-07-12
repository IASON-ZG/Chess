package pieces;

import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;

public class Bishop extends Piece{
    public Bishop(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public void moveTo(Location newLoc) throws InvalidMoveException {

        if ( newLoc.getRow() >= 0 && newLoc.getRow() <= 7 && newLoc.getCol()>= 0 && newLoc.getCol() <= 7){
            if (Math.abs(newLoc.getCol() - getLocation().getCol()) == Math.abs(newLoc.getRow() - getLocation().getRow())) {
                if ((newLoc.getCol() > getLocation().getCol() && newLoc.getRow() > getLocation().getRow()) || (newLoc.getCol() < getLocation().getCol() && newLoc.getRow() < getLocation().getRow())){
                    if (getBoard().freeDiagonalPath(getLocation(),newLoc)){
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
                    else {
                        throw new InvalidMoveException();
                    }
                }
                else {
                    if (getBoard().freeAntidiagonalPath(getLocation(),newLoc)){
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
                    else {
                        throw new InvalidMoveException();
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





//        if ( newLoc.getRow() >= 0 && newLoc.getRow() <= 7 && newLoc.getCol()>= 0 && newLoc.getCol() <= 7) {
//            if (newLoc.getCol() > getLocation().getCol() && (Math.abs(newLoc.getCol())-getLocation().getCol() == Math.abs(newLoc.getRow())-getLocation().getCol())) {
//                if (newLoc.getRow() > getLocation().getRow()) {
//                    if (getBoard().freeDiagonalPath(getLocation(), newLoc) == true) {
//                        if (getBoard().getTable()[newLoc.getRow()][newLoc.getCol()] == null){
//                            getBoard().movePiece(getLocation(),newLoc);
//                            setLocation(newLoc);
//
//                        }
//                        else {
//                            Piece piece = getBoard().getPieceAt(newLoc);
//                            if (piece.getColor().equals(getColor())){
//                                throw new InvalidMoveException();
//                            }
//                            else {
//                                getBoard().movePieceCapturing(getLocation(), newLoc);
//                                setLocation(newLoc);
//                            }
//                        }
//                    }
//                    else {
//                        throw new InvalidMoveException();
//                    }
//                }
//                else {
//                    if (getBoard().freeAntidiagonalPath(getLocation(), newLoc) == true) {
//                        if (getBoard().getTable()[newLoc.getRow()][newLoc.getCol()] == null){
//                            getBoard().movePiece(getLocation(),newLoc);
//                            setLocation(newLoc);
//                        }
//                        else {
//                            Piece piece = getBoard().getPieceAt(newLoc);
//                            if (piece.getColor().equals(getColor())){
//                                throw new InvalidMoveException();
//                            }
//                            else {
//                                getBoard().movePieceCapturing(getLocation(), newLoc);
//                                setLocation(newLoc);
//                            }
//                        }
//                    } else {
//                        throw new InvalidMoveException();
//                    }
//                }
//            }
//            else if (newLoc.getCol() < getLocation().getCol() && (Math.abs(newLoc.getCol())-getLocation().getCol() == Math.abs(newLoc.getRow())-getLocation().getCol())){
//                if (newLoc.getRow() < getLocation().getRow()) {
//                    if (getBoard().freeDiagonalPath(getLocation(), newLoc) == true) {
//                        if (getBoard().getTable()[newLoc.getRow()][newLoc.getCol()] == null){
//                            getBoard().movePiece(getLocation(),newLoc);
//                            setLocation(newLoc);
//                        }
//                        else {
//                            Piece piece = getBoard().getPieceAt(newLoc);
//                            if (piece.getColor().equals(getColor())){
//                                throw new InvalidMoveException();
//                            }
//                            else {
//                                getBoard().movePieceCapturing(getLocation(), newLoc);
//                                setLocation(newLoc);
//                            }
//                        }
//                    } else {
//                        throw new InvalidMoveException();
//                    }
//                }
//                else {
//                    if (getBoard().freeAntidiagonalPath(getLocation(), newLoc) == true) {
//                        if (getBoard().getTable()[newLoc.getRow()][newLoc.getCol()] == null){
//                            getBoard().movePiece(getLocation(),newLoc);
//                            setLocation(newLoc);
//                        }
//                        else {
//                            Piece piece = getBoard().getPieceAt(newLoc);
//                            if (piece.getColor().equals(getColor())){
//                                throw new InvalidMoveException();
//                            }
//                            else {
//                                getBoard().movePieceCapturing(getLocation(), newLoc);
//                                setLocation(newLoc);
//                            }
//                        }
//                    } else {
//                        throw new InvalidMoveException();
//                    }
//                }
//            }
//        }
//        else {
//            throw new InvalidMoveException();
//        }
    }

    public String toString(){
        if (getColor() == Color.WHITE){
            return "B";
        } else {
            return "b";
        }
    }
}
