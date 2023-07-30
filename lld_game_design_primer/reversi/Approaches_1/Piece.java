package lld_game_design_primer.reversi.Approaches_1;
public class Piece {
    public enum Color {
        White, Black
    }
    private Color color;

    public Piece(Color c) {
        color = c;
    }

    public void flip() {
        if (color == Color.Black) {
            color = Color.White;
        } else {
            color = Color.Black;
        }
    }

    public Color getColor() {
        return color;
    }
}