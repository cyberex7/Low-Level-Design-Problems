package lld_game_design_primer.jigsaw_puzzle.Approach_1;


import java.util.HashMap;
import java.util.Map.Entry;



public class Piece {

    public enum Direction {
        LEFT, TOP, RIGHT, BOTTOM;

        public Direction getOpposite() {
            switch (this) {
                case LEFT: return RIGHT;
                case RIGHT: return LEFT;
                case TOP: return BOTTOM;
                case BOTTOM: return TOP;
                default: return null;
            }
        }
    }

    private final static int NUMBER_OF_EDGES = 4;
    private HashMap<Direction, Edge> edges = new HashMap<>(); // map to keep track of which edge is in which direction of the piece

    public Piece(Edge[] edgeList) { // edge[0] = edge that is supposed to go to the LEFT side of the piece,
                                    // similarly edge[1] is for TOP edge of the piece,
                                    // edge[2] is for RIGHT and edge[3] for BOTTOM
        Direction[] directions = Direction.values(); // we get Direction values in this order: LEFT, TOP, RIGHT, BOTTOM
        for (int i = 0; i < edgeList.length; i++) {
            Edge edge = edgeList[i]; // get the edge
            edge.setParentPiece(this); // set this piece as the parent piece of the edge
            edges.put(directions[i], edge); // put this edge in the next direction in the piece
        }
    }

    /* Re-orient the edges of this pieces in a way that puts the given edge in the given direction
     * This method is necessary since while inserting a piece in our solution
     *  we might have to rotate the piece to match to its surrounding pieces. */
    public void setEdgeAndRotate(Edge edge, Direction newDirection) {
        Direction currentDirection = getDirection(edge);
        edges = rotateEdgesClockwiseBy(newDirection.ordinal() - currentDirection.ordinal()); // say, the edge was previously on right side
                            // and we want to rotate it and put in the bottom. Then we need to rotate all edgres by 1 position clockwise
    }

    private HashMap<Direction, Edge> rotateEdgesClockwiseBy(int rotationSteps) {
        Direction[] directions = Direction.values();
        HashMap<Direction, Edge> rotated = new HashMap<>();

        if (rotationSteps >=  NUMBER_OF_EDGES) {
            rotationSteps %= NUMBER_OF_EDGES; // 1 <= rotationSteps <= NUMBER_OF_EDGES - 1
            // note if numberRotations == NUMBER_OF_EDGES, there is no change in the positions of the edges
        }
        if (rotationSteps < 0) {
            rotationSteps += NUMBER_OF_EDGES; // if we need to rotate to place RIGHT edge in the top position
            // we would get rotationSteps = -1 but in reality we would have to rotate clockwise by 3.
            // 3 = 4 - 1 = 4 + (-1) = NUMBER_OF_EDGES + rotationSteps
        }

        for (int i = 0; i < directions.length; i++) {
            Edge edge = edges.get(directions[i]);
            Direction newDirection = directions[(i + rotationSteps) % NUMBER_OF_EDGES];
            rotated.put(newDirection, edge);
        }
        return rotated;
    }

    // Return the direction on which this edge is located in this piece.
    private Direction getDirection(Edge edge) {
        for (Entry<Direction, Edge> entry : edges.entrySet()) {
            if (entry.getValue() == edge) {
                return entry.getKey();
            }
        }
        return null;
    }

    /* Check if this piece is a corner piece. */
    public boolean isCorner() {
        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++) {
            // any two adjacent edges need to be of type FLAT for a piece to be a corner piece
            Edge.Shape current = edges.get(directions[i]).getShape();
            Edge.Shape next = edges.get(directions[(i + 1) % NUMBER_OF_EDGES]).getShape();

            if (current == Edge.Shape.FLAT && next == Edge.Shape.FLAT) {
                return true;
            }
        }
        return false;
    }

    /* Check if this piece has a border edge. */
    public boolean isBorder() {
        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++) {
            if (edges.get(directions[i]).getShape() == Edge.Shape.FLAT) {
                return true;
            }
        }
        return false;
    }

    /* Get edge at a given direction. */
    public Edge getEdgeOnDirection(Direction direction) {
        return edges.get(direction);
    }

    /* Given an edge, return the edge that belongs to this piece and is a matching edge for the given edge.
       Returns null if there is no match. */
    public Edge getMatchingEdge(Edge edge) {
        for (Edge e : edges.values()) {
            if (edge.fitsWith(e)) {
                return e;
            }
        }
        return null;
    }
}