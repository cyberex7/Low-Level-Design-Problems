package lld_game_design_primer.jigsaw_puzzle.Approach_1;

import java.util.LinkedList;
import java.util.List;

public class Puzzle {
    private List<Piece> pieces; /* Remaining pieces left to put in the solution. */
    private Piece[][] solution;
    private int size;

    public Puzzle(int size, List<Piece> pieces) {
        this.pieces = pieces;
        this.size = size;
    }

    public Piece[][] getSolution() {
        return solution;
    }

    public boolean solvePuzzle() {

        // (1) group together all corner pieces, (2) group together all border pieces, (3) group together all inside pieces 
        LinkedList<Piece> cornerPieces = new LinkedList<>();
        LinkedList<Piece> borderPieces = new LinkedList<>();
        LinkedList<Piece> insidePieces = new LinkedList<>();

        groupPieces(cornerPieces, borderPieces, insidePieces); // this is where grouping happens

        solution = new Piece[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                // determine if [row, col] will have a corner piece or inside piece or border piece,
                // depending on which we decide which group of pieces we should search in
                // for the appropriate piece that foes at [row, col]
                LinkedList<Piece> piecesToSearch = getPieceListToSearch(cornerPieces, borderPieces, insidePieces, row, column);

                if (!insertAppropriatePiece(row, column, piecesToSearch)) {
                    return false; // return false if insertion fails, i.e, there is no appropriate piece that can go at [row, col]
                }
            }
        }

        return true;
    }

    /* Group pieces into border pieces (including corners) and inside pieces. */
    private void groupPieces(List cornerPieces, List borderPieces, List insidePieces) {
        for (Piece p : pieces) {
            if (p.isCorner()) {
                cornerPieces.add(p);
            } else if (p.isBorder()) {
                borderPieces.add(p);
            } else {
                insidePieces.add(p);
            }
        }
    }

    /* Return the list where a piece with this index would be found. */
    private LinkedList<Piece> getPieceListToSearch(
            LinkedList<Piece> cornerPieces,
            LinkedList<Piece> borderPieces,
            LinkedList<Piece> insidePieces,
            int row,
            int column)
    {
        if (isBorderIndex(row) && isBorderIndex(column)) {
            return cornerPieces;
        } else if (isBorderIndex(row) || isBorderIndex(column)) {
            return borderPieces;
        } else {
            return insidePieces;
        }
    }

    /* Bounds check. Check if this index is on a border (0 or size - 1) */
    private boolean isBorderIndex(int location) {
        return location == 0 || location == size - 1;
    }

    /* Find the matching piece within piecesToSearch and insert it at row, column. */
    private boolean insertAppropriatePiece(int row, int column, LinkedList piecesToSearch) {
        if (row == 0 && column == 0) {
            // if we just started solving the puzzle then
            // just grab any one corner piece and insert that
            // piece in the left top corner
            Piece p = (Piece) piecesToSearch.remove();
            insertAtTopLeftCorner(p);
            solution[0][0] = p;
        } else {
            /*
            Algorithm:
            Now that we have the first corner piece, we can go on solving the puzzle
            row wise, starting from the first row. For every row we would start with the left-most piece and would
            solve for all the pieces in that row from left to right. Once that row is solved we would move on to the next row.
            We repeat this process until the entire puzzle is solved.
             */


            // find the next piece in a way that matches with the pieces inserted so far in our solution


            // if we are looking for a piece to be inserted in the first column, then
            // there is no piece on the left of it. So
            // the appropriate piece to be inserted must match with the piece right above it in column index = 0.
            // if column index is not zero then the piece to be inserted must match the piece which is on the left to it.
            //
            // Once we have figured out which piece (p1) we need to match with, we
            // also need to figure out which edge of the piece (p1) should match with the
            // piece to be inserted. Once we have figured out which piece goes at [row, col]
            // we need to make sure we are rotating it correctly so hat the edges match as well.
            Piece pieceToMatch =
                    column == 0
                    ? solution[row - 1][0]
                    : solution[row][column - 1];

            Piece.Direction directionToMatch =
                    column == 0
                    ? Piece.Direction.BOTTOM // match with the bottom edge of the above piece
                    : Piece.Direction.RIGHT; // match with the right edge of the piece that is on the left

            Edge edgeToMatch = pieceToMatch.getEdgeOnDirection(directionToMatch);

            Edge edge = getMatchingEdge(edgeToMatch, piecesToSearch);

            if (edge == null) {
                return false; // not solvable, there is missing pieces
            }

            Piece.Direction directionThatNeedsToBeMatched = directionToMatch.getOpposite(); 
            insertIntoSolution(piecesToSearch, edge, row, column, directionThatNeedsToBeMatched);
        }
        return true;
    }

    /* Orient this corner piece so that its flat edges are on the top and left. */
    private void insertAtTopLeftCorner(Piece piece) {
        if (!piece.isCorner()) return;

        Piece.Direction[] directions = Piece.Direction.values(); // get the directions in the order: LEFT, TOP, RIGHT, BOTTOM

        for (int i = 0; i < directions.length; i++) {

            Edge current = piece.getEdgeOnDirection(directions[i]);
            Edge clockwiseNext = piece.getEdgeOnDirection(directions[(i + 1) % directions.length]);

            if (current.getShape() == Edge.Shape.FLAT && clockwiseNext.getShape() == Edge.Shape.FLAT) {
                piece.setEdgeAndRotate(current, Piece.Direction.LEFT); // since this is top left corner piece, make sure
                            // that the left and top edges are FLAT. Set the edges accordingly and rotate if necessary.
                return;
            }
        }
    }

    /* Given a list of pieces, check if any have an edge that matches this piece. Return the edge. */
    private Edge getMatchingEdge(Edge edge, LinkedList<Piece> pieces) {
        for (Piece piece : pieces) {
            Edge matchingEdge = piece.getMatchingEdge(edge);
            if (matchingEdge != null) {
                return matchingEdge;
            }
        }
        return null;
    }

    /* Put the piece into the solution, turn it appropriately, and remove from list of unused pieces. */
    private void insertIntoSolution(List<Piece> pieces, Edge edge, int row, int column, Piece.Direction orientation) {
        Piece piece = edge.getParentPiece();
        piece.setEdgeAndRotate(edge, orientation);
        pieces.remove(piece); // remove piece from unused pieces
        solution[row][column] = piece; // insert piece in the solution
    }
}