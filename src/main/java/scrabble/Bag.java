package scrabble;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> letters=new ArrayList<>() ;
    public Bag() {
        for (char c = 'a'; c < 'z'; c++) {
            int points=(int) (Math.random()*10);
            if (points==0) points++;
            for (int index=1; index<=10;index++)
            letters.add(new Tile(c, points));
        }
    }
    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (letters.isEmpty()) {
                break;
            }
            Random rand = new Random();
            int maxLetters=letters.size();
            int int_random=rand.nextInt(maxLetters);

            extracted.add(letters.get(int_random));
        }
        return extracted;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "letters=" + letters +
                '}';
    }
}