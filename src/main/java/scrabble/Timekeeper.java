package scrabble;

import static java.lang.Thread.sleep;

/**
 * timekeeper
 * system.nanotime(start , end, scadere) - durata totala
 * elapsed cst de 500
 * sleep(cst)
 * gameOver=true;
 * synchronized(game.getBoard)
 * {
 * game.getBoard().notifyAll();
 * }
 * thread create
 * thread.setDaemon(true);
 */
public class Timekeeper implements Runnable {
    private long start;
    private long end;
    private int MAX_DURATION = 20000;
    private Game game;

    public Timekeeper(Game game, long start) {
        this.start = start;
        this.game = game;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getMAX_DURATION() {
        return MAX_DURATION;
    }

    public void setMAX_DURATION(int MAX_DURATION) {
        this.MAX_DURATION = MAX_DURATION;
    }

    public double getDuration() {
        return ((double) end - start) / 1_000_000;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(MAX_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The time is up!");
        synchronized (game.getBoard()) {
            game.setGameOver(true);
            game.getBoard().notifyAll();
        }
    }
}
