package scrabble;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running;
    private int toExtract = 7;
    private int score = 0;

    List<Tile> currLetters = new ArrayList<>();

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(toExtract);
        currLetters.addAll(extracted);
        if (game.isGameOver()) return false;
        synchronized (game.getBoard()) {
            if (!game.getCurrPlayer().equals(this) && !game.isGameOver()) {
                try {
                    game.getBoard().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (currLetters.size() < 7) {
            System.out.println("extracted: " + extracted);
            System.out.println("Current letters" + currLetters);

            System.out.println("Not enough letters in the bag. The game is over.");
            synchronized (game.getBoard()) {
                game.setGameOver(true);
                game.getBoard().notifyAll();

            }
            return false;
        }
        //   create a word with all the extracted tiles;

        int wordSize = 7;
        boolean foundWord = false;
        List<Tile> subWord = new ArrayList<>();
        //System.out.println(name+" "+extracted);
        /**
         * cauta cuvinte de lungime cat mai mare
         * face cate un subcuvant cu literele pe care le are in mana
         * pana gaseste unul valid
         * sau pana cand ajunge la cuvinte mai mici de 2 litere
         */
        for (wordSize = 7; wordSize >= 2 && !foundWord; wordSize--) {
            List<Tile> currentCopy = new ArrayList<>(currLetters);
            subWord.clear();
            for (int currSize = 0; currSize < wordSize; currSize++) {
                Random random = new Random();
                int index = random.nextInt(0, currentCopy.size());
                subWord.add(currentCopy.get(index));
                currentCopy.remove(currentCopy.get(index));
            }

            StringBuilder wordBuilder = new StringBuilder();
            for (Tile tile : subWord) {
                wordBuilder.append(tile.getLetter());
            }
            String word = wordBuilder.toString();
            if (game.getDictionary().isWord(word) && !game.isGameOver()) {
                game.getBoard().addWord(this, word);
                foundWord = true;

                currLetters.removeAll(subWord);
                //game.getBag().removeAllTiles(extracted);
                toExtract = 7 - currLetters.size();
                score += game.getScore(word);

                synchronized (game.getBoard()) {
                    game.nextPlayer();
                    game.getBoard().notify();

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println(name+" "+extracted);
        /**
         * daca nu poate face un cuvant cedeaza tura altui jucator
         * si returneaza piesele
         */
        if (!foundWord && !game.isGameOver()) {
            System.out.println(this.name + " couldn t make a word");
            game.getBag().returnTiles(currLetters);
            currLetters.clear();
            toExtract = 7;
            synchronized (game.getBoard()) {
                game.nextPlayer();
                game.getBoard().notify();

            }
        }
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Sleep error");
        }
        return true;
    }
    // implement the run method;

    public int getScore() {
        return score;
    }


    @Override
    public void run() {
        boolean submit = true;

        while (submit)
            submit = submitWord();

    }

    public String getName() {
        return name;
    }
}