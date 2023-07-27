
If you are in an interview, make sure you have a detailed discussion with your interviewer to clarify what your interviewer's expectations are and what he/she wants you to implement.

In this chapter, we will be focusing on implementing SnakeGame class with following functionalities:

SnakeGame(int width, int height, int[][] food) initializes Snake Game with a screen of size (height x width) and the positions of the food.

int move(String direction) returns the score of the game after applying one direction move by the snake. If the game is over, return -1.

Below is how our implementation would work:

SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);

snakeGame.move("R"); // return 0

snakeGame.move("D"); // return 0

snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).

snakeGame.move("U"); // return 1

snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.

snakeGame.move("U"); // return -1, game over because snake collides with border.