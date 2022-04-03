package scrabble;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
  //  private final Dictionary dictionary = (Dictionary) new scrabble.MockDictionary();
    private final List<Player> players = new ArrayList<>();
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }
    public void play() {
        for (Player player : players) {
            //start a new Thread representing the player;
        }
    }
    public static void main(String args[]) {
        Game game = new Game();
        game.addPlayer(new Player("scrabble.Player 1"));
        game.addPlayer(new Player("scrabble.Player 2"));
        game.addPlayer(new Player("scrabble.Player 3"));
        game.play();
    }

    public Board getBoard() {
        return board;
    }

    public Bag getBag() {
        return bag;
    }
}