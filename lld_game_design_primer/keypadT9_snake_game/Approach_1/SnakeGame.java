package lld_game_design_primer.keypadT9_snake_game.Approach_1;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

class SnakeGame {

    Deque<int[]> snakeConfiguration; // doubly linkedlist using deque or double ended queue interface
    HashSet<String> lookup; // by converting position coordinates to string we are bypassing creating
                               // an extra wrapper class and implementing equals and hashcode
    int foodIndex; // tracks next food position
    int width; // used to track if the snake hits the wall
    int height; // used to track if the snake hits the wall
    int[][] food;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        snakeConfiguration = new LinkedList<>();
        lookup = new HashSet<>();
        foodIndex = 0;
        this.width = width;
        this.height = height;
        this.food = food;
        
        // the snake is initially at (0, 0) position
        // initialize the snake configuration and lookup table
        snakeConfiguration.add(new int[] {0, 0});
        lookup.add(convertToString(0, 0));
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] head = snakeConfiguration.getFirst();
        int headRow = head[0];
        int headCol = head[1];
        
        switch (direction) {
            case "U":
                headRow--;
                break;
            case "D":
                headRow++;
                break;
            case "L":
                headCol--;
                break;
            case "R":
                headCol++;
                break;
        }
        
        int[] oldTail = snakeConfiguration.getLast();
        if ((lookup.contains(convertToString(headRow, headCol)) && !(headRow == oldTail[0] && headCol == oldTail[1])) // check if snakes bites itself.
                                                                    // notice that if the new head is at the place of old tail then 
                                                                    // it does not bite itself since the new position of tail is no more at 
                                                                    // the position of old tail and has moved one cell forward 
           || headRow < 0 || headRow >= height || headCol < 0 || headCol >= width) { // checks if the snake hit the wall
            return -1;
        }
        
        // add new head
        snakeConfiguration.addFirst(new int[] {headRow, headCol});
        lookup.add(convertToString(headRow, headCol));
        
        if (foodIndex < food.length && headRow == food[foodIndex][0] && headCol == food[foodIndex][1]) { // snake ate food
            foodIndex++;
        } else {
            // remove old tail
            snakeConfiguration.removeLast();
            
            // remove old Tail from lookup table only if the new head is not at the position
            // of old tail, otherwise removing the old Tail from lookup table 
            // would also remove the new head from the lookup table, which we should avoid doing
            if (!(headRow == oldTail[0] && headCol == oldTail[1])) {
                lookup.remove(convertToString(oldTail[0], oldTail[1]));
            }
        }
        
        return snakeConfiguration.size() - 1;
    }
    
    private String convertToString(int x, int y) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(x));
        sb.append(", ");
        sb.append(Integer.toString(y));
        return sb.toString();
    }
}

