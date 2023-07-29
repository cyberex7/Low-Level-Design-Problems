package lld_game_design_primer.sudoku.Approach;

import java.util.Arrays;

public class Sudoku {

    private static Sudoku instance = null; // lazy loading

    private boolean finished = false;

    private char[][] board = new char[9][9];

    private Sudoku() {}

    public static Sudoku getInstance() {
        if (instance == null) {
            instance = new Sudoku();
        }
        return instance;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void solve() {
        backtrack(board, 0, -1); // it is important to understand why we are passing 0 as row
                                 // and -1 as column. For this, please see how we are
                                 // calculating currentRow and currentCol in backtrack( ... ) method
    }


    public void backtrack(char[][] board, int row, int col){
        if (row == 8 && col == 8 && board[row][col] == '0') {
            finished = true;
            return;
        }
        // constructing current candidates has two parts:
        // (1) identifying current cell
        // (2) identifying all suitable candidates for the current cell
        //
        // construct current cell
        int currentRow = col == 8 ? row + 1 : row; // row of current cell
        // we go to next row only when col has reached 8, otherwise row remains same
        int currentCol = col == 8 ? 0 : col + 1; // column of current cell
        // new col resets to 0 if previous col has reached 8, otherwise to get new col we just shift one cell towards right
        // which is same as incrementing the previous col by 1

        if (currentRow == 9) { // nextRow == 9 means we have already processed cell [8, 8] which marks the end of Sudoku game
            finished = true;
            return;
        }
        if(board[currentRow][currentCol] == '0'){ // if current cell contains '0', solve for the current cell
            boolean[] suitableCandidatesForCurrentCell = constructCandidates(board, currentRow, currentCol);
            for (int k = 1; k <= 9; k++) {
                if (suitableCandidatesForCurrentCell[k - 1]) {
                    fillBoard(currentRow, currentCol, (char)(k + '0')); //makeMove

                    backtrack(board, currentRow, currentCol); // recursively call backtrack(...)

                    if (finished) { // early termination
                        // the moment we get one valid solution for the Sudoku we
                        // do not bother to compute any more even the Sudoku may have more than one solutions.
                        //
                        // Also notice that, specifically fo rthis solution, this step
                        // also avoids the undoMove() step when we are returning back to the calling function (during recursion)
                        // an avoiding undoMove is necessary to keep the solution persisted in the board[][] array.
                        return;
                    }
                    undoFillBoard(currentRow, currentCol); // undoMove
                }
            }
        } else { // if current cell doe NOT contain '0',
            // do NOT solve for the current cell, recursively
            // call backtrack(...) to directly move on to the next cell, if any.
            backtrack(board, currentRow, currentCol);
        }
    }

    private boolean[] constructCandidates(char[][] board, int row, int col) {
        boolean[] candidates = new boolean[9];
        Arrays.fill(candidates, true);
        // column check
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '0') {
                candidates[board[i][col] - '0' - 1] = false;
            }
        }
        // row check
        for (int j = 0; j < 9; j++) {
            if (board[row][j] != '0') {
                candidates[board[row][j] - '0' - 1] = false;
            }
        }
        // sub-box check
        int topLeftRow = (row / 3) * 3;
        int topLeftCol = (col / 3) * 3;
        // once we have computed the row and col of the top left point of the sub-box
        // we know that the sub-box would be a square matrix
        // with right bottom point =  [topLeftRow + 3, topLeftCol + 3]
        for (int i = topLeftRow; i < topLeftRow + 3; i++) {
            for (int j = topLeftCol; j < topLeftCol + 3; j++) {
                if (board[i][j] != '0') {
                    candidates[board[i][j] - 1 - '0'] = false;
                }
            }
        }
        return candidates;
    }

    private void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private void fillBoard(int x, int y, char value) {
        board[x][y] = value;
    }

    private void undoFillBoard(int x, int y) {
        board[x][y] = '0';
    }


    public static void main(String[] args) {
        Sudoku sudoku = Sudoku.getInstance();
        char[][] board =
                {{'3', '0', '6', '5', '0', '8', '4', '0', '0'},
                {'5', '2', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '8', '7', '0', '0', '0', '0', '3', '1'},
                {'0', '0', '3', '0', '1', '0', '0', '8', '0'},
                {'9', '0', '0', '8', '6', '3', '0', '0', '5'},
                {'0', '5', '0', '0', '9', '0', '6', '0', '0'},
                {'1', '3', '0', '0', '0', '0', '2', '5', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '7', '4'},
                {'0', '0', '5', '2', '0', '6', '3', '0', '0'}};
        sudoku.setBoard(board);
        sudoku.solve();
        sudoku.printBoard();
    }
}

/*
Output:
3  1  6  5  7  8  4  9  2
5  2  9  1  3  4  7  6  8
4  8  7  6  2  9  5  3  1
2  6  3  4  1  5  9  8  7
9  7  4  8  6  3  1  2  5
8  5  1  7  9  2  6  4  3
1  3  8  9  4  7  2  5  6
6  9  2  3  5  1  8  7  4
7  4  5  2  8  6  3  1  9
*/