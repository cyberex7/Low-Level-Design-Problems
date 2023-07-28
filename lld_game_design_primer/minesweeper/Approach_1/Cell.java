package lld_game_design_primer.minesweeper.Approach_1;

public class Cell {
    private int row;
    private int column;
    private boolean isBomb;
    private int number;
    private boolean isExposed;
    private boolean isFlagged;

    public Cell(int r, int c) {
        row = r;
        column = c;
    }

    public void setRowAndColumn(int r, int c) {
        row = r;
        column = c;
    }

    public void setBomb() {
        isBomb = true;
        number = -1;
    }

    public void flag() {
        isFlagged = true;
    }

    public void unflag() {
        isFlagged = false;
    }

    public boolean isBlank() {
        return number == 0;
    }

    public boolean isNumber() {
        return number > 0;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void expose() {
        isExposed = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isBomb() {
        return isBomb;
    }
}

