package lld_game_design_primer.reversi.Approaches_1;

public class Board {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN
    }

    private int blackCount = 0;
    private int whiteCount = 0;
    private Piece[][] board;

    public Board(int rows, int columns) {
        board = new Piece[rows][columns];
    }

    public void initialize() {
		/* initialize board by placing white (W) and black (B) pieces in the center like below:
		 *     W B
		 *     B W
		 */
        int middleRow = board.length / 2;
        int middleColumn = board[middleRow].length / 2;

        board[middleRow][middleColumn] = new Piece(Piece.Color.White);
        board[middleRow + 1][middleColumn] = new Piece(Piece.Color.Black);
        board[middleRow + 1][middleColumn + 1] = new Piece(Piece.Color.White);
        board[middleRow][middleColumn + 1] = new Piece(Piece.Color.Black);

        // update score
        blackCount = 2;
        whiteCount = 2;
    }

    public boolean makeMove(int row, int column, Piece.Color color) {
        if (board[row][column] != null) {
            return false;
        }

		/*
		attempt to flip in all 8 directions:
		up,
		down,
		left,
		right,
		diagonal (2 directions: left-up, right-down),
		anti-diagonal (2 directions: left-down, right-up)
		*/
        int[] results = new int[8];

        // search in all 8 directions to see if in any direction the player has made any conquer.
        // keep in mind some moves can result in conquers in multiple directions.
        results[0] = flipSection(row - 1, column, color, Direction.UP);
        results[1] = flipSection(row + 1, column, color, Direction.DOWN);
        results[2] = flipSection(row, column + 1, color, Direction.RIGHT);
        results[3] = flipSection(row, column - 1, color, Direction.LEFT);
        results[0] = flipSection(row - 1, column - 1, color, Direction.LEFT_UP);
        results[1] = flipSection(row + 1, column + 1, color, Direction.RIGHT_DOWN);
        results[2] = flipSection(row - 1, column + 1, color, Direction.RIGHT_UP);
        results[3] = flipSection(row + 1, column - 1, color, Direction.LEFT_DOWN);

		/* compute how many pieces were flipped */
        int flipped = 0;
        for (int result : results) {
            if (result > 0) {
                flipped += result;
            }
        }

		/* if nothing was flipped, then it's an invalid move */
        if (flipped < 0) {
            return false;
        }

		/* flip the piece, and update the score */
        board[row][column] = new Piece(color);

        updateScore(color, flipped + 1);

        return true;
    }

    // return number of pieces flipped. Return -1 if no conquer was made.
    private int flipSection(int row, int column, Piece.Color color, Direction d) {

        int rowMovement = 0;
        int columnMovement = 0;

        switch (d) {
            case UP:
                rowMovement = -1;
                break;

            case DOWN:
                rowMovement = 1;
                break;

            case LEFT:
                columnMovement = -1;
                break;

            case RIGHT:
                columnMovement = 1;
                break;

            case LEFT_UP:
                rowMovement = -1;
                columnMovement = -1;
                break;

            case RIGHT_DOWN:
                rowMovement = 1;
                columnMovement = 1;
                break;

            case RIGHT_UP:
                rowMovement = -1;
                columnMovement = 1;
                break;

            case LEFT_DOWN:
                rowMovement = 1;
                columnMovement = -1;
                break;
        }
        
        // if we go out of boundary or reach a cell with no piece in it,
        // that means we cannot make any conquer in the given direction
        if (row < 0 || row >= board.length || column < 0 || column >= board[row].length // [row, column] is outside of the board
                || board[row][column] == null // there is no piece placed in this cell
                ) {
            return -1; // return -1 to indicate there is nothing to flip
        }

		// the cell at [row, column] cannot be flipped since this piece is 
        // of the same color as the given color, so we return 0.
        // we do not explore any further in the given direction since we
        // we got this piece which is of the same color of the given color, so this 
        // ends the search in the given direction. Basically this piece becomes one end (end1),
        // and the other end (end2) is the piece (of the same color as the given color) from
        // where we started the searching or exploration. We would
        // be flipping all the pieces in between the two ends (end1 and end2).
        if (board[row][column].getColor() == color) {
            return 0; // return 0 since there is nothing to flip
        }
        
        int numberOfOtherCellsFlippedInTheDirection = flipSection(row + rowMovement, column + columnMovement, color, d);
        
        // while exploring if we went out of boundary without getting a piece with the same color as the given color, that
        // would mean that there is no piece of the same color on the other side, so return -1.
        // it takes two pieces of the same color on both sides to flip all the cells in between.
        if (numberOfOtherCellsFlippedInTheDirection == -1) {
            // in this case what happened is: after making the move
            // we started searching in a direction and hit the wall
            // but did not find any piece of the same color in the direction we were searching
            return -1;
        }
        
        // we have reached this line of code means that we have gotten another piece of the given color
        // in the given direction. This means we have two pieces of same color as the given color on two ends
        // and all the pieces in between two ends would be flip. 
        // This piece happens to be in between those two ends. So flip it.
        board[row][column].flip();
        
        return numberOfOtherCellsFlippedInTheDirection + 1;
    }

    private void updateScore(Piece.Color newColor, int newPieces) {
        
        if (newColor == Piece.Color.Black) {
            whiteCount -= newPieces - 1;
            blackCount += newPieces;
        } 
        else {
            blackCount -= newPieces - 1;
            whiteCount += newPieces;
        }
    }

    public int getScoreForColor(Piece.Color c) {
        if (c == Piece.Color.Black) {
            return blackCount;
        } else {
            return whiteCount;
        }
    }
}
    
