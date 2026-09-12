
/**
 * Implements a game similar to Lights Out, called LightsOff.
 * More info about the game: https://en.wikipedia.org/wiki/Lights_Out_(game)
 */
public class Project1Main {

    /**
     * Main method to show how to run the game.
     * 
     * @param args Command-line arguments not used in this project
     */
    public static void main(String[] args) {
        // 5 different examples of running the game of life
        // uncomment one and let it run

        Project1 game = new Project1("o o o | o x o | o o o |");
        System.out.println("\n\nShall we play a game?");
        System.out.println(game);
        System.out.println("Playing at 1,1");
        game.play(1, 1);
        System.out.println(game);
        System.out.println("number of moves: " + game.numMoves());
        System.out.println("number of wins: " + game.numWins());
        System.out.println("game over? " + game.isGameOver());

        System.out.println("\n\nLet's play another game");
        game.initialize(5);
        System.out.println(game);
        System.out.println("Playing at 1,1");
        game.play(1, 1);
        if (game.isGameOver())
            System.out.println("You won!");
        System.out.println(game);

        System.out.println("number of moves: " + game.numMoves());
        System.out.println("number of wins: " + game.numWins());
        System.out.println("game over? " + game.isGameOver());
    }
}