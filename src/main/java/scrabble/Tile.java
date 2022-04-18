package scrabble;

public class Tile {
    private final char letter;
    private final int points;

    public Tile(char letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    public char getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }
    //constructor
//getters
//toString

    @Override
    public String toString() {

        return "" + letter;
    }
    /*public String toString() {
        return "Tile{" +
                "letter=" + letter +
                ", points=" + points +
                '}';
    }*/
}