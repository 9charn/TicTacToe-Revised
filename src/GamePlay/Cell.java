package GamePlay;

import Results.Seed;

/**
 * @author Panus Onmonkong 63130500080
 * 
 * The Cell class models each individual cell of the game board.
 */
public class Cell {
    // package access
    Seed content; // content of this cell of type Seed.
                  // take a value of Seed.EMPTY, Seed.CROSS, or Seed.NOUGHT
    int row, col; // row and column of this cell, not used in this program

    /** 
     * Constructor to initialize this cell
     */
    public Cell(int row, int col) {
       this.row = row;
       this.col = col;
       clear(); // clear content
    }

    /** Clear the cell content to EMPTY */
    public void clear() {
       content = Seed.EMPTY;
    }

    /** Paint itself */
    public void paint() {
        switch (content) {
            case CROSS -> System.out.print(" X ");
            case NOUGHT -> System.out.print(" O ");
            case EMPTY -> System.out.print("   ");
        }
    }
}
