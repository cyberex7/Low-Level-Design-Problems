package lld_game_design_primer.reversi.Approaches_1;

public class Reversi {
    private static Reversi instance;
    private Board board;
    private final int ROWS = 10;
    private final int COLUMNS = 10;

    private Reversi() {
        board = new Board(ROWS, COLUMNS);
    }

    public static Reversi getInstance() { // singleton
        if (instance == null) {
            instance = new Reversi();
        }
        return instance;
    }

    public int getScore(Piece.Color player) {
        return board.getScoreForColor(player);
    }

    public boolean play(int row, int column, Piece.Color player) {
        return board.makeMove(row, column, player);
    }
}