package main;

import pieces.*;

public class Board {
    Piece[][] table;
    private boolean end = false;


    public Board(){
        this.table = new Piece[8][8];
    }

    public void init(){
        for (int i = 0; i < 8 ; i++){
            Location white_location = new Location(1,i);
            Pawn white_pawn = new Pawn(Color.WHITE,white_location,this);
            table[1][i] = white_pawn ;

            Location black_location = new Location(6,i);
            Pawn black_pawn = new Pawn(Color.BLACK,black_location,this);
            table[6][i] = black_pawn;

            if (i == 0 || i == 7){
                Location white_loc_rook = new Location(0,i);
                Rook white_rook = new Rook(Color.WHITE,white_loc_rook,this);
                table[0][i] = white_rook;

                Location black_loc_rook = new Location(7,i);
                Rook black_rook = new Rook(Color.BLACK,black_loc_rook,this);
                table[7][i] = black_rook;
            } else if (i == 2 || i == 5){
                Location white_loc_bishop = new Location(0,i);
                Bishop white_bishop = new Bishop(Color.WHITE,white_loc_bishop,this);
                table[0][i] = white_bishop;

                Location black_loc_bishop = new Location(7,i);
                Bishop black_bishop = new Bishop(Color.BLACK,black_loc_bishop,this);
                table[7][i] = black_bishop;
            } else if (i == 1 || i == 6){
                Location white_loc_knight = new Location(0,i);
                Knight white_knight = new Knight(Color.WHITE,white_loc_knight,this);
                table[0][i] =  white_knight;

                Location black_loc_knight = new Location(7,i);
                Knight black_knight = new Knight(Color.BLACK,black_loc_knight,this);
                table[7][i] = black_knight;
            }

            Location white_queen_loc = new Location(0,3);
            Queen white_queen = new Queen(Color.WHITE,white_queen_loc,this);
            table[0][3] = white_queen;

            Location white_king_loc = new Location(0,4);
            King white_king = new King(Color.WHITE,white_king_loc,this);
            table[0][4] = white_king;

            Location black_queen_loc = new Location(7,3);
            Queen black_queen = new Queen(Color.BLACK,black_queen_loc,this);
            table[7][3]= black_queen;

            Location black_king_loc = new Location(7,4);
            King black_king = new King(Color.BLACK,black_king_loc,this);
            table[7][4]=black_king;
        }


    }

    public Piece getPieceAt(Location loc){
        if (table[loc.getRow()][loc.getCol()] != null){
            return table[loc.getRow()][loc.getCol()];
        }
        return null;
    }

    public void movePiece(Location from, Location to) {
        Piece piece = table[from.getRow()][from.getCol()];
        if (piece != null){
            table[from.getRow()][from.getCol()] = null;
            table[to.getRow()][to.getCol()] = piece;
        }
    }

    public void movePieceCapturing(Location from,Location to)  {
        Piece piece = table[from.getRow()][from.getCol()];
        Piece piece_captured = table[to.getRow()][to.getCol()];
        if(piece != null){
            if (piece_captured.getClass().equals(King.class)){
                setEnd(true);
                System.out.println("Game is over " + piece.getColor() + " Won!");
            }
            table[from.getRow()][from.getCol()] = null;
            table[to.getRow()][to.getCol()] = piece;
        }
    }


    public boolean freeDiagonalPath(Location from, Location to){

        boolean clear_path = true;
        int diff = Math.abs(from.getCol() - to.getCol());
        if ((from.getCol() < to.getCol()) && (from.getRow() < to.getRow())){
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()+i][from.getCol()+i] != null) {
                    clear_path = false;
                }
            }
        }
        else {
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()-i][from.getCol()-i] != null){
                    clear_path = false;
                }
            }
        }
        return clear_path;
    }

    public boolean freeAntidiagonalPath(Location from,Location to){
        boolean clear_path = true;
        int diff = Math.abs(from.getCol() - to.getCol());


        if ((from.getCol() < to.getCol()) && (from.getRow() > to.getRow())){
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()-i][from.getCol()+i] != null){
                    clear_path = false;
                }
            }
        } else {
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()+i][from.getCol()-i] != null){
                    clear_path = false;
                }
            }
        }
        return clear_path;
    }


    public boolean freeVerticalPath(Location from, Location to){
        boolean clear_path = true;
        int diff = Math.abs(to.getRow()-from.getRow());

        if(from.getRow() < to.getRow()){
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()+i][from.getCol()] != null){
                    clear_path = false;
                }
            }
        } else {
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()-i][from.getCol()] != null){
                    clear_path = false;
                }
            }
        }
        return clear_path;
    }


    public boolean freeHorizontalPath(Location from,Location to){
        boolean clear_path = true;
        int diff = Math.abs(to.getCol()-from.getCol());

        if (from.getCol()< to.getCol()){
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()][from.getCol()+i] != null){
                    clear_path = false;
                }
            }
        } else {
            for (int i = 1; i < diff-1; i++){
                if (table[from.getRow()][from.getCol() - i] != null){
                    clear_path = false;
                }
            }
        }
        return clear_path;
    }

    @Override
    public String toString(){
        String board = new String();
        board += ' '+ "abcdefgh" + ' ' + "\n";
        for (int z = 7; z >= 0; z--){
            board += z+1;
            for(int i = 0; i <8; i++){
                Piece piece = table[z][i];
                if (piece != null){
                    board += piece.toString();
                } else{
                    board += " ";
                }
            }
            board += z+1 + "\n";
        }
        board += ' '+ "abcdefgh" + ' ' + '\n';
        return board;
    }

    public Piece[][] getTable() {
        return table;
    }


    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
