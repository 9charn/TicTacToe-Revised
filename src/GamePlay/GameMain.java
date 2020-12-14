package GamePlay;

import Results.GameState;
import Results.Seed;

import java.util.Scanner;

/**
 * @author Netpichit Kongkasai 63130500073
 * 
 * The main class for the Tic-Tac-Toe.
 * It acts as the overall controller of the game.
 */
public class GameMain {
    private Board board;            // the game board
    private GameState currentState; // the current state of the game (of enum GameState)
    private Seed currentPlayer;     // the current player (of enum Seed)

    private static Scanner in = new Scanner(System.in); // input Scanner

    /** Constructor to setup the game */
    public GameMain() {
        board = new Board(); // allocate game-board

        // Initialize the game-board and current status
        initGame();
        // Play the game once. Players CROSS and NOUGHT move alternately.
        do {
            playerMove(currentPlayer);  // update the content, currentRow and currentCol
            board.paint();              // ask the board to paint itself
            updateGame(currentPlayer);  // update currentState
            
            switch (currentState) {     // Print message if game-over
                case CROSS_WON -> System.out.println("\n'X' is winner!");
                case NOUGHT_WON -> System.out.println("\n'O' is winner!");
                case DRAW -> System.out.println("\nIt's draw!");
            }

            // Switch player
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING); // repeat until game-over
    }

    /** Initialize the game-board contents and the current states */
    public void initGame() {
        board.init();                     // clear the board contents
        currentPlayer = Seed.CROSS;       // CROSS plays first
        currentState = GameState.PLAYING; // ready to play
    }

    /**
     * The player with "theSeed" makes one move, with input validation.
     * @param theSeed Update Cell's content, Board's currentRow and currentCol.
     */
    public void playerMove(Seed theSeed) {
        boolean validInput = false; // for validating input
        do {
            if (theSeed == Seed.CROSS) {
                System.out.println("\nPlayer 'X', enter your move (row[1-3] column[1-3]):");
            } else {
                System.out.println("\nPlayer 'O', enter your move (row[1-3] column[1-3]):");
            }

            System.out.print("Enter number on the row: ");
            int row = in.nextInt() - 1; // input number start at 1 (1-2-3) but not 0 (0-1-2)
            System.out.print("Enter number on the column: ");
            int col = in.nextInt() - 1; // input number start at 1 (1-2-3) but not 0 (0-1-2)
         
            // Seed of Row or Column should be "MORE THAN 0" or "LESS THAN 3"
            if (row >= 0 && row < Board.ROWS
             && col >= 0 && col < Board.COLS
             && board.cells[row][col].content == Seed.EMPTY) {
                board.cells[row][col].content = theSeed;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true; // input okay, exit loop
            } else {
                System.out.println("\nThis move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
            }
        } while (validInput != true); // repeat until input is valid
    }

    /**
     * @param theSeed Update the currentState after the player with "theSeed" has moved
     */
    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) { // check for win
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) { // check for draw
            currentState = GameState.DRAW;
        }
        // Otherwise, no change to current state (still GameState.PLAYING).
    }

    /**
     * @param args The entry main() method
     */
    public static void main(String[] args) {
        GameMain gameMain = new GameMain(); // Let the constructor do the job
    }
}
