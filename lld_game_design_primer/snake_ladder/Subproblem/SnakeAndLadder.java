package lld_game_design_primer.snake_ladder.Subproblem;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class SnakeAndLadder {
    int diceRollSides = 6; // number of sides the dice has
    public int snakesAndLadders(int[][] board) {
        int len = board.length;
        for (int j = 1; j <= len * len; j++) {
            int[] coord = getCoordinate(j, len);
            if (board[coord[0]][coord[1]] == -1) {
                board[coord[0]][coord[1]] = j;
            }
        }
        
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
        Set<Integer> visited = new HashSet<>(); // hashset offers efficient search
        bfsQueue.add(source);
        
        int count = 0;
        // BFS
        while (!bfsQueue.isEmpty()) {
            count++;
            int queueSize = bfsQueue.size();
            for (int i = 0; i < queueSize; i++) {
                int curr = bfsQueue.poll();
                visited.add(curr);
                for (int j = 1; j <= diceRollSides; j++) {
                    int[] coordinate = getCoordinate(curr + j, len);
                    int cellValue = board[coordinate[0]][coordinate[1]];
                    if (cellValue == destination) {
                        return count;
                    }
                    if (!visited.contains(cellValue)) {
                        bfsQueue.add(cellValue);
                    }
                }
            }
        }
        return -1;
    }
    
    private int[] getCoordinate(int num, int dimension) {
        boolean evenRow = ((num - 1) / dimension) % 2 == 0; // zero based -> row index starts from 0 which is even
                                // all even rows have numbers in ascending order from left to right.
                                // this indexing is done brom bottom to top which 
                                // is reverse of the index order in given matrix/board.
        int row = (dimension - 1) - ((num - 1) / dimension);
        int col = (num - 1) % dimension;
        if (!evenRow) {
            col = dimension - 1 - col;
        }
        return new int[] {row, col};
    }
}