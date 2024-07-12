package main;

public enum Color {
    WHITE,BLACK;

    public Color nextColor(){
        switch(this) {
            case WHITE:
                return BLACK;
            case BLACK:
                return WHITE;
            default:
                return null;
        }
    }
}
