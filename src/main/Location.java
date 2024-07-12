package main;

public class Location {
    private int r;
    private int c;

    public Location(int r,int c){
        this.r = r;
        this.c = c;
    }

    public Location(String loc){
        char letter = Character.toLowerCase(loc.charAt(0));
        this.c = letter - 'a'  ;
        this.r = Character.getNumericValue(loc.charAt(1)) - 1;
    }

    public int getCol() {
        return c;
    }

    public int getRow() {
        return r;
    }

    @Override
    public String toString() {
        return c +" "+ r ;
    }

}
