
Start the design discussion by asking yourself two questions: what are the fundamental building blocks that build the core of this game ? Make this a habit to ask yourself this question whenever you are working on a low level design, because this would help you to identify the classes that you would need to have.

The fundamental components of this game are : (1) the board, and (2) the Reversi pieces.

What do we know about the Reversi Pieces ? We know that they have two different colors on two sides. But the color on the top side is the primary color at any point of time. We can flip the piece if we want to toggle the color of its top side.


Now that we are done with designing Pieces, we need to think about how Pieces are used in Board to actually play the game.

We should be able to do the following operations on the Board:
Initialize the game by placing two black and two white pieces in the center. The black pieces are placed at the upper left hand and lower right hand corners.



Make a move and flip pieces as necessary.
We should be able to get the score of each player using the color that repesents them by counting the number of pieces present on the board of that color.

From the discussion above we get the below Board class:

public class Board {

    public void initialize() {  }

    public boolean makeMove(int row, int column, Color color) {   }

    public int getScoreForColor(Color c) {    }

}


Now that we have designed the core components of the game, we need to design and implement the controller class to take inputs from players and actually enable the players to play the game. A key thing to decide is whether you want only one instance of the game to be there in the system, which is true for most desktop games, mobile games, video games and games at the gaming kiosks. If you are in an interview, this is something to discuss with your interviewer.

For this type of game it makes sence to have no more than one instance of the game in the system, which means we need to implement the controller class as a singleton class.


Remember that in many problems, what you did is less important than why you did it. Your interviewer probably doesn't care much whether you chose to implement Game as a singleton or not, but he/she does care about the fact that you took the time to think about it and discussed the trade-offs.
