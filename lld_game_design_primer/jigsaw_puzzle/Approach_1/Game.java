package lld_game_design_primer.jigsaw_puzzle.Approach_1;

import java.util.LinkedList;
import java.util.Random;

import lld_game_design_primer.jigsaw_puzzle.Approach_1.Edge.Shape;
import lld_game_design_primer.jigsaw_puzzle.Approach_1.Piece.Direction;

public class Game {

    public static LinkedList<Piece> initializePuzzle(int size) {
		// Step 1. Create completed puzzle
        Piece[][] puzzle = new Piece[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Edge[] edges = createEdges(puzzle, column, row);
                puzzle[row][column] = new Piece(edges);
            }
        }

		// Step 2. Now Shuffle and rotate pieces
        LinkedList<Piece> pieces = new LinkedList<>();
        Random random = new Random();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Piece piece = puzzle[row][column];
                int rotations = random.nextInt(4); // 0 <= rotations < 4
                Direction[] directions = { Direction.LEFT, Direction.TOP, Direction.RIGHT, Direction.BOTTOM};
                piece.setEdgeAndRotate(piece.getEdgeOnDirection(Direction.LEFT), directions[rotations]); // rotate
                int index = pieces.size() == 0 ? 0 : random.nextInt(pieces.size()); // shuffle
                pieces.add(index, piece);
            }
        }

        return pieces;
    }

    private static Edge[] createEdges(Piece[][] puzzle, int column, int row) {
        String key = column + ":" + row + ":";
		/* Get left edge */
        Edge left =
                column == 0
                ? new Edge(Shape.FLAT, key + "left") // left side of top-left and bottom-left corner pieces are alwaysFLAT
                : createMatchingEdge(puzzle[row][column - 1].getEdgeOnDirection(Piece.Direction.RIGHT)); // left edge should match with right edge of the piece located on the left to this piece

		/* Get top edge */
        Edge top =
                row == 0
                ? new Edge(Shape.FLAT, key + "top") // top side of top-left and top-right corner pieces are always FLAT
                : createMatchingEdge(puzzle[row - 1][column].getEdgeOnDirection(Direction.BOTTOM)); // top edge should match with bpttom edge of above piece

		/* Get right edge */
        Edge right =
                column == puzzle[row].length - 1
                ? new Edge(Shape.FLAT, key + "right") // right side of top-right and bottom-right corner pieces are always FLAT
                : createRandomEdge(key + "right"); // create right side edge. since we are processing from left to right we only match for left side edge, not for right side edges

		/* Get bottom edge */
        Edge bottom =
                row == puzzle.length - 1
                ? new Edge(Shape.FLAT, key + "bottom") // bottom side of bottom-left and bottom-right corner pieces are always FLAT
                : createRandomEdge(key + "bottom");// create bottom edge of the piece. since we are processing from left to right and top to bottom, we only match for top side edge, not for bottom side edges


        Edge[] edges = { left, top, right, bottom };

        return edges;
    }

    private static Edge createRandomEdge(String code) {
        Random random = new Random();
        Edge.Shape type = Shape.CONCAVE;
        if (random.nextBoolean()) {
            type = Shape.CONVEX;
        }
        return new Edge(type, code);
    }

    // Given an edge, return its matching edge
    private static Edge createMatchingEdge(Edge edge) {
        if (edge.getShape() == Shape.FLAT) {
            return null;
        }
        return new Edge(edge.getShape().getOpposite(), edge.getCode());
    }

    // Given a solution, check if it is a valid solution.
    public static boolean isValidSolution(Piece[][] solution) {
        if (solution == null) {
            return false;
        }
        for (int r = 0; r < solution.length; r++) {
            for (int c = 0; c < solution[r].length; c++) {

                Piece piece = solution[r][c];
                if (piece == null) {
                    return false;
                }
                if (c > 0) {
                    // match left
                    Piece left = solution[r][c-1];
                    if (!left.getEdgeOnDirection(Direction.RIGHT).fitsWith(piece.getEdgeOnDirection(Direction.LEFT))) {
                        return false;
                    }
                }
                if (c < solution[r].length - 1) {
                    // match right
                    Piece right = solution[r][c+1];
                    if (!right.getEdgeOnDirection(Direction.LEFT).fitsWith(piece.getEdgeOnDirection(Direction.RIGHT))) {
                        return false;
                    }
                }
                if (r > 0) {
                    // match top
                    Piece top = solution[r-1][c];
                    if (!top.getEdgeOnDirection(Direction.BOTTOM).fitsWith(piece.getEdgeOnDirection(Direction.TOP))) {
                        return false;
                    }
                }
                if (r < solution.length - 1) {
                    // match bottom
                    Piece bottom = solution[r+1][c];
                    if (!bottom.getEdgeOnDirection(Direction.TOP).fitsWith(piece.getEdgeOnDirection(Direction.BOTTOM))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

