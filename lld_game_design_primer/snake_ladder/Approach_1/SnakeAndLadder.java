package lld_game_design_primer.snake_ladder.Approach_1;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class SnakeAndLadder {
    int[][] board;
    int dicePosition;
    int dimension;
    int finishValue;
    int diceRollSides = 6; // number of sides the dice has
    
    public SnakeAndLadder(int[][] squareMatrix) {
        board = squareMatrix;
        dicePosition = 1;
        dimension = squareMatrix.length;
        finishValue = dimension * dimension;
    }
    
    public boolean isgameOver() {
        return dicePosition == (finishValue);
    }
    
    // return position of dice after making next move
    public int rollDice() {
       int random = rand(1, 6);
        int nextPosition = dicePosition + random;
        if (nextPosition > finishValue) {
            return dicePosition;
        }
        int[] coordinateOfNewPosition = getCoordinate(dicePosition);
        return board[coordinateOfNewPosition[0]][coordinateOfNewPosition[1]]; // instead of returning
        // new dice position directly we are getting the value from board because
        // the corresponding value in board might be different of the new dice position
        // has snake mouth or ladder bottom-end
    }
    
    // Random number between lower and higher, both inclusive
    // [lower, higher]
    private int rand(int lower, int higher) {
        return lower + (int)(Math.random() * (higher - lower + 1)); // Range of Math.random() = [0.0, 1.0)
    }

    // method to find coordinate of squares
    // in the snake and ladder game board in which squares are labeled from 1 to (n * n)
    // in a Boustrophedon style starting from the bottom left of the board and
    // alternating direction each row.
    private int[] getCoordinate(int num) {
        boolean rightToLeftRow = ((num - 1) / dimension) % 2 != 0;
        int row = (dimension - 1) - ((num - 1) / dimension);
        int col = (num % dimension) - 1 == -1 ? (dimension - 1) : (num % dimension) - 1;
        if (rightToLeftRow) {
            col = dimension - 1 - col;
        }
        return new int[] {row, col};
    }

    public int getLeastNumberOfMovesRequiredToReachFinishSquare(int[][] board) {
        int len = board.length;

        // the only time that there would be no solution is if the square (n ^ 2)
        // has a snake mouth, since every time you reach here you go down to some other square
        // so there is no way you can stay at square (n ^ 2)
        if ((len % 2 == 0 && board[0][0] != len * len) 
                    || (len % 2 != 0 && board[0][len - 1] != len * len)) {
            return -1;
        }
        
        int source = 1;
        int destination = len * len;
        
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        Set<Integer> processed = new HashSet<>(); // hashset offers efficient search
        bfsQueue.add(source);
        
        int[] numberOfEdgesFromSource = new int[len * len];
        
        // BFS
        while (!bfsQueue.isEmpty()) {
            int parent = bfsQueue.poll();
            processed.add(parent);
            for (int i = 1; i <= diceRollSides; i++) {
                int[] coordinate = getCoordinate(parent + i, len);
                int cellValue = board[coordinate[0]][coordinate[1]];
                if (!processed.contains(cellValue)) { // do not revisit a square which has already been processed before
                    if (numberOfEdgesFromSource[cellValue - 1] == 0) { // if we have already found a path from source to this node we do not need to update the value since we are interested in the shortest path with all edges with weight 1 
                        numberOfEdgesFromSource[cellValue - 1] = numberOfEdgesFromSource[parent - 1] + 1;
                    }
                    if (cellValue == destination) {
                        return numberOfEdgesFromSource[len * len - 1];
                    }
                    bfsQueue.add(cellValue);
                }
            }
        }
        
        return -1;
    }

    // method to find coordinate of squares
    // in the snake and ladder game board in which squares are labeled from 1 to (n * n) 
    // in a Boustrophedon style starting from the bottom left of the board and 
    // alternating direction each row.
    private int[] getCoordinate(int num, int dimension) {
        boolean rightToLeftRow = ((num - 1) / dimension) % 2 != 0;
        int row = (dimension - 1) - ((num - 1) / dimension);
        int col = (num % dimension) - 1 == -1 ? (dimension - 1) : (num % dimension) - 1;
        if (rightToLeftRow) {
            col = dimension - 1 - col;
        }
        return new int[] {row, col};
    }
}

