package scrabble;

import java.util.List;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running;
    public Player(String name) { this.name = name; }
    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty()) {
            return false;
        }
     //   create a word with all the extracted tiles;
        String word="";
        for (Tile tile : extracted)
        {
            word=word+tile.getLetter();
        }

        game.getBoard().addWord(this, word);

        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Sleep error");
        }
        return true;
    }
   // implement the run method;

    public void setGame(Game game) {
        this.game=game;
    }

    @Override
    public void run() {

    }

    public String getName() {
        return name;
    }
}