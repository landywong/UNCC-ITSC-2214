public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, ITSC2214!");
        TicTacToeBoard board = new TicTacToeBoard();
        board.play('o', 0, 0);
        board.play('x', 0, 1);
        board.play('o', 0, 2);

        board.play('x', 1, 0);
        board.play('x', 1, 1);
        board.play('o', 1, 2);

        board.play('o', 2, 1);
        board.play('x', 2, 0);
        board.play('o', 2, 2);

        System.out.println("Game over? " + board.gameIsOver());
        System.out.println("Game tied? " + board.gameIsATie());
        System.out.println("Winner is  " + board.getWinner());
    }
}