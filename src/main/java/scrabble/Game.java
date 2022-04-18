package scrabble;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new MockDictionary();
    private final List<Player> players = new ArrayList<>();
    private Player currPlayer;
    private int playerIndex = 0;
    private Timekeeper timekeeper = new Timekeeper(this, System.nanoTime());
    private boolean gameOver = false;
    private List<Thread> threads = new ArrayList<>();

    public void addDictionary() {
        try {
            dictionary.readWordsFromFile("E:\\AN2\\ProiectePA\\PA_LAB7\\words.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Timekeeper getTimekeeper() {
        return timekeeper;
    }

    public void setTimekeeper(Timekeeper timekeeper) {
        this.timekeeper = timekeeper;
    }

    synchronized public boolean isGameOver() {
        return gameOver;
    }

    synchronized public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    synchronized public int getScore(String word) {
        int score = 0;
        for (int index = 0; index < word.length(); index++)
            score += bag.getTileByLetter(word.charAt(index)).getPoints();
        return score;
    }

    public void addPlayer(Player player) {
        players.add(player);

    }

    public void play() {
        currPlayer = players.get(playerIndex);
        Thread threadKeeper = new Thread(timekeeper);
        threadKeeper.setDaemon(true);
        threadKeeper.start();

        for (Player player : players) {
            //start a new Thread representing the player;

            Thread thread = new Thread(player);
            threads.add(thread);
            thread.start();

        }
    }

    synchronized public Player getCurrPlayer() {
        return currPlayer;
    }

    public void nextPlayer() {
        playerIndex++;
        if (playerIndex == players.size()) playerIndex = 0;
        currPlayer = players.get(playerIndex);
    }

    synchronized public Dictionary getDictionary() {
        return dictionary;
    }

    synchronized public List<Player> getPlayers() {
        return players;
    }

    synchronized public Board getBoard() {
        return board;
    }

    synchronized public Bag getBag() {
        return bag;
    }

    public List<Thread> getThreads() {
        return threads;
    }

}