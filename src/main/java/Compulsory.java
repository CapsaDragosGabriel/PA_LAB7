import scrabble.Game;
import scrabble.Player;

public class Compulsory {
    public static void main(String[] args) {
        Game game = new Game();
        game.addDictionary();
        game.addPlayer(new Player("Player1", game));
        game.addPlayer(new Player("Player2", game));
        game.addPlayer(new Player("Player3", game));
        System.out.println(game.getBag());

        game.play();
        for (Thread thread : game.getThreads()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // System.out.println(game.getBag());
        int maxPoints = 0;
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + " " + player.getScore());
            if (player.getScore() > maxPoints) maxPoints = player.getScore();
        }
        System.out.println("The winner(s) is(are):");
        for (Player player : game.getPlayers()) {
            if (player.getScore() == maxPoints)
                System.out.println(player.getName());
            ;
        }
        game.getTimekeeper().setEnd(System.nanoTime());
        System.out.println(game.getTimekeeper().getDuration());
        System.out.println(game.getBoard());
    }
}
