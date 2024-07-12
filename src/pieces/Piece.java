package pieces;

import main.Board;
import main.Color;
import main.InvalidMoveException;
import main.Location;

public abstract class Piece {

    private Color color;
    private Location location;
    private Board board;

    public Piece(Color color, Location location, Board board) {
        this.color = color;
        this.location = location;
        this.board = board;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public Board getBoard() {
        return board;
    }

    public abstract void moveTo(Location newLoc) throws InvalidMoveException;
}
