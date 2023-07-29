package lld_game_design_primer.jigsaw_puzzle.Approach_1;

public class Edge {
    public enum Shape {
        CONCAVE, CONVEX, FLAT;
        public Shape getOpposite() {
            switch (this) {
                case CONVEX: return CONCAVE;
                case CONCAVE: return CONVEX;
                default: return null;
            }
        }
    };
    private String code;
    private Piece parentPiece;
    private Shape shape;

    public Edge(Shape shape, String code) {
        this.shape = shape;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /* Check if this edge fits into the other one. */
    public boolean fitsWith(Edge edge) {
        return edge.getCode().equals(getCode());
    }

    public void setParentPiece(Piece parentPiece) {
        this.parentPiece = parentPiece;
    }

    public Piece getParentPiece() {
        return parentPiece;
    }

    public Shape getShape() {
        return shape;
    }

    public String toString() {
        return code;
    }
}


