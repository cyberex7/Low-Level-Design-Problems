package lld_game_design_primer.minesweeper.Approach_1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Board {
    public enum GameState { INPROGRESS, WON, LOST}
    private int rows;
    private int columns;
    private Cell[][] cells;
    private int unexposedCellsRemaining; // determines if a player wins


    public Board(int rows, int columns, int numberOfBombsToBePlanted) {
        this.rows = rows;
        this.columns = columns;

        Cell[] bombs = new Cell[numberOfBombsToBePlanted];

        initializeBoard();
        plantBombsAtRandomPositions(numberOfBombsToBePlanted, bombs);
        setNumberedCells(bombs);

        unexposedCellsRemaining = rows * columns - numberOfBombsToBePlanted;
    }

    private void initializeBoard() {
        cells = new Cell[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }
    }

   private void plantBombsAtRandomPositions(int numberOfBombsToBePlanted, Cell[] bombs) {
        plantBombsSequentially(numberOfBombsToBePlanted, bombs);
        shuffleBombs();
    }
    private void plantBombsSequentially(int numberOfBombs, Cell[] bombs) {
        for (int i = 0; i < numberOfBombs; i++) {
            int r = i / columns;
            int c = (i - r * columns) % columns;
            cells[r][c].setBomb();
            bombs[i] = cells[r][c];
        }
    }

    private void shuffleBombs() {
        int nCells = rows * columns;
        Random random = new Random();

        for (int index1 = 0; index1 < nCells; index1++) {

            int index2 = index1 + random.nextInt(nCells - index1);

            if (index1 != index2) {

				// compute row and column index from index1 value
                int row1 = index1 / columns;
                int column1 = (index1 - row1 * columns) % columns;

                Cell cell1 = cells[row1][column1];

				// compute row and column index corresponsing to index2
                int row2 = index2 / columns;
                int column2 = (index2 - row2 * columns) % columns;

                Cell cell2 = cells[row2][column2];

				// swap
                cells[row1][column1] = cell2;
                cell2.setRowAndColumn(row1, column1);
                cells[row2][column2] = cell1;
                cell1.setRowAndColumn(row2, column2);
            }
        }
    }

    // makes a move as per player's instruction
    // and returns the state of the game: game lost, or, game still in progress, or, player won
    public GameState exposeCell(int row, int col) {
        Cell cell = cells[row][col];
        if (!cell.isExposed()) {
            cell.expose();
            if (cell.isBlank()) {
                expandBlank(cell);
            }
            unexposedCellsRemaining--;
            if (unexposedCellsRemaining == 0) {
                return GameState.WON;
            }
            if (cell.isBomb()) {
                return GameState.LOST;
            }
        }
        return GameState.INPROGRESS;
    }

   public void expandBlank(Cell cell) {
    int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
    };

    Queue<Cell> toExplore = new LinkedList<>();
    toExplore.add(cell);

    while (!toExplore.isEmpty()) {
        Cell current = toExplore.remove();

        for (int[] direction : directions) {
            int r = current.getRow() + direction[0];
            int c = current.getColumn() + direction[1];

            if (r >= 0 && r < rows && c >= 0 && c < columns) {
                Cell neighbor = cells[r][c];

                neighbor.expose(); // we do not check if this is a bomb cell since a blank cell can never be surrounded by a bomb cell

                // we expose both blank and numbered cell but expand on only blank cells
                if (neighbor.isBlank()) {
                    toExplore.add(neighbor);
                }
            }
        }
    }
}

    public void printBoard() {
        // print the board
    }

    private void setNumberedCells(Cell[] bombs) {
        // Offsets of 8 surrounding cells
        int[][] directions = { {-1, -1}, {-1, 0}, {-1, 1},{ 0, -1},{ 0, 1},{ 1, -1}, { 1, 0}, { 1, 1} };
        for (Cell bomb : bombs) {
            int row = bomb.getRow();
            int col = bomb.getColumn();
            for (int[] direction : directions) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r >= 0 && r < rows && c >= 0 && c < columns) {
                    cells[r][c].setNumber(cells[r][c].getNumber() + 1);
                }
            }
        }
    
    }
}
