public class TicTacToeBoard {

    /** 3x3 board for the tictactoe game */
    public char[][] board;

    /**
     * Default constructor. It should instantiate a
     * 3x3 board by calling new and then do a nested
     * loop to initialize the positions in the board
     * to ' ' (space)
     */
    public TicTacToeBoard() {
        // allocate a 3 x 3 array and store it in 'board'

        // and initialize the values to ' ' (spaces)
        // do a nested loop over all the rows/cols to store ' '

        board = new char[3][3]; // allocates 3x3 to board
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = ' '; // fills each with blanks
            }
        }
    }

    /**
     * This returns true if the board is full.
     * The board is full if every position has
     * a value of x or o. If there is at least one ' '
     * on the board, it is not considered full.
     * 
     * @return true if the board is full, false otherwise
     */
    public boolean boardIsFull() {
        // Write a nested loop here to check if all positions
        // have either an x or an o, that is if all positions
        // full. Return true if they are, false if they are not.

        for (int i = 0; i < board.length; i++) {
            for (int e = 0; e < board[i].length; e++) {
                if (board[i][e] != 'o' || board[i][e] != 'x') {
                    return false; // checker for x and o
                }
            }
        }
        return true;
    }

    /**
     * Returns true if 'player' has won the game. It
     * checks to see if the player has 3-in-a-row over rows, cols,
     * or diagonals... if it has won, then return true.
     * Otherwise, return false.
     * 
     * @param player either x or o
     * @return a boolean value on which player won
     */
    public boolean hasPlayerWon(char player) {
        // A player has won if and only if:
        // a) there are three of the player's tokens' in a row,
        // b) there are three of the player's tokens' in a col,
        // c) there are three of the player's tokens in the \ diagonal, or
        // d) there are three of the player's tokens in the / diagonal.

        for (int i = 0; i < board.length; i++) { // for loop reads the first char of each row
            if (board[i][0] == player) { // row checker
                if (board[i][1] == player && board[i][2] == player) {
                    return true;
                }
            }
        }
        for (int i = 0; i < board.length; i++) { // for loop reads the first char of each col
            if (board[0][i] == player) { // col checker
                if (board[1][i] == player && board[2][i] == player) {
                    return true;
                }
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            // diagonal down checker
            return true;
        }
        if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
            // diagonal up checker
            return true;
        }

        return false;
    }

    /**
     * Play made by a player (x or o) at (row,col). If
     * that position not empty, this does nothing. But
     * if (row,col) is empty then place the player (x or o)
     * in that position.
     * 
     * @param player either x or o
     * @param row    row to check
     * @param col    col to check
     */
    public void play(char player, int row, int col) {
        boolean emp = positionIsEmpty(row, col);
        if (emp) {
            board[row][col] = player;
        }
    }

    /**
     * Return the token (x or o) stored at row, col.
     * 
     * @param row row of the cell to return
     * @param col col of the cell to return
     * @return returns the char at row,col
     */
    public char getToken(int row, int col) {
        return board[row][col];
    }

    /**
     * Returns true if the game is over. Game is over
     * if there are 3 in a row in either a row, col, or
     * one of the two diagonals, or the board is full.
     * 
     * @return true if games is over, false otherwise
     */
    public boolean gameIsOver() {
        // return true if game is over
        // game is over if: a) board is full, or
        // b) one player has 3 in a row
        return boardIsFull() || hasPlayerWon('o') || hasPlayerWon('x');
    }

    /**
     * Returns true if the game is a tie. A game
     * is a tie if the board is full and there
     * are no 3-in-a-row cases.
     * The game is not a tie if the game is not over
     * so this should return false.
     * 
     * @return true if game is a tie, false otherwise
     */
    public boolean gameIsATie() {

        // A game is a tie if the board is full AND
        // nobody won. You can use boardIsFull() and
        // gameIsOver() to build this routine.

        // return true if the game is tie
        return getWinner() == '-';
    }

    /**
     * Is position (row,col) empty? An empty position
     * has a ' ' (space). You can assume that
     * row,col are valid values.
     * 
     * @param row row to check
     * @param col col to check
     * @return true if the position (row,col) is a space
     */
    public boolean positionIsEmpty(int row, int col) {
        // position row,col is empty if it has a ' ' (space)
        return board[row][col] == ' ';
    }

    /**
     * Returns the winner of the current game.
     * Note that it is possible that this board is tie, in
     * which case it returns a '-';
     * If the game is not over yet (that is, neither has x nor
     * o won) and the game board is not full yet, then game
     * is not over and this routine should return a space (' ')
     * 
     * @return character representing the player(x/o) or a
     *         space representing a game not over yet, or a '-' for
     *         a tied game.
     */
    public char getWinner()
    // preconditions: game is over and it is not a tie
    {
        if (hasPlayerWon('x')) {
            return 'x';
        } else if (hasPlayerWon('o')) {
            return 'o';
        } else if (boardIsFull()) {// board is full and no one won, a tie
            return '-';
        } else {
            return ' ';
        }
    }
}