package scrabble;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> letters = new ArrayList<>();
    private final List<Tile> tilePoints = new ArrayList<>();

    public Bag() {
        String aeioulnstr = "aeioulnstr";
        String dg = "dg";
        String bcmp = "bcmp";
        String fhvwy = "fhvwy";
        String jx = "jx";
        String qz = "qz";
        int points;
        int noTiles = 0;

        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'a' || c == 'i') noTiles = 9;
            else if (c == 'e') noTiles = 12;
            else if (c == 'o') noTiles = 8;
            else if (c == 'b' || c == 'c' || c == 'f' || c == 'h' || c == 'm' || c == 'p' || c == 'v' || c == 'w' || c == 'y')
                noTiles = 2;
            else if (c == 'g') noTiles = 3;
            else if (c == 'd' || c == 'l' || c == 's' || c == 'u') noTiles = 4;
            else if (c == 'n' || c == 'r' || c == 't') noTiles = 6;
            else noTiles = 1;


            if (aeioulnstr.indexOf(c) > -1) {
                points = 1;

                for (int index = 1; index <= noTiles; index++)
                    letters.add(new Tile(c, points));
            } else if (dg.indexOf(c) > -1) {
                points = 2;

                for (int index = 1; index <= noTiles; index++)
                    letters.add(new Tile(c, points));
            } else if (bcmp.indexOf(c) > -1) {
                points = 3;

                for (int index = 1; index <= noTiles; index++)
                    letters.add(new Tile(c, points));
            } else if (fhvwy.indexOf(c) > -1) {
                points = 4;

                for (int index = 1; index <= noTiles; index++)
                    letters.add(new Tile(c, points));
            } else if (c == 'k') {
                points = 5;

                for (int index = 1; index <= noTiles; index++)
                    letters.add(new Tile(c, points));
            } else if (jx.indexOf(c) > -1) {
                points = 8;

                for (int index = 1; index <= noTiles; index++)
                    letters.add(new Tile(c, points));
            } else {
                points = 10;

                for (int index = 1; index <= noTiles; index++)
                    letters.add(new Tile(c, points));

            }
        }
        tilePoints.add(new Tile('a', 1));
        tilePoints.add(new Tile('b', 3));
        tilePoints.add(new Tile('c', 3));
        tilePoints.add(new Tile('d', 2));
        tilePoints.add(new Tile('e', 1));
        tilePoints.add(new Tile('f', 4));
        tilePoints.add(new Tile('g', 2));
        tilePoints.add(new Tile('h', 4));
        tilePoints.add(new Tile('i', 1));
        tilePoints.add(new Tile('j', 8));
        tilePoints.add(new Tile('k', 5));
        tilePoints.add(new Tile('l', 1));
        tilePoints.add(new Tile('m', 3));
        tilePoints.add(new Tile('n', 1));
        tilePoints.add(new Tile('o', 1));
        tilePoints.add(new Tile('p', 3));
        tilePoints.add(new Tile('q', 10));
        tilePoints.add(new Tile('r', 1));
        tilePoints.add(new Tile('s', 1));
        tilePoints.add(new Tile('t', 1));
        tilePoints.add(new Tile('u', 1));
        tilePoints.add(new Tile('v', 4));
        tilePoints.add(new Tile('w', 4));
        tilePoints.add(new Tile('x', 8));
        tilePoints.add(new Tile('y', 4));
        tilePoints.add(new Tile('z', 10));
    }

    public Tile getTileByLetter(char ch) {
        /**
         *merge prin tilePoints pana gaseste tile-ul care are caracterul ch
         */
        return tilePoints.stream().filter(tile -> ch == tile.getLetter()).findFirst().orElse(null);

    }

    synchronized public  List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (letters.isEmpty()) {
                break;
            }
            Random rand = new Random();
            int maxLetters = letters.size();
            int intRandom = rand.nextInt(0, maxLetters);

            extracted.add(letters.get(intRandom));
        }

        letters.removeAll(extracted);

        return extracted;
    }

    synchronized public List<Tile> getLetters() {
        return letters;
    }

    synchronized public void removeAllTiles(Collection<Tile> list) {
        letters.removeAll(list);
    }

    @Override
    public String toString() {
        return "Bag{" + "letters=" + letters + '}';
    }

    synchronized public void returnTiles(List<Tile> extracted) {
        letters.addAll(extracted);
    }
}