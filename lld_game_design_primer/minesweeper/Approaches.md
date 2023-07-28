Detailed Low Level Design:

The board represents the Minesweeper game and the board consists of Cell. So at the very least we would need Board and Cell classes. Now we would also need a Controller that would actually construct the board when a new instance of the game is created, keeps track of state of the game throughout the lofecycle of the game, as well as handle user input. So below are the classes we would need to design:
Cell
Board
Game (This is the controller class.)
We will now dive deeper into each of the class design.

Cell
Let's jot down all the information we have about the minesweeper board cells:
A Cell should always have an assigned coordinate i.e, row index and column index in the board.

A cell can contain either a Bomb, or a Number, or could be Blank.

We can design for this in two ways:
We could have an enumerator as follows:

public enum TYPE {
    BOMB,
    NUMBER,
    BLANK
}

We can represent what kind of a cell one is just by using integers:
If a cell is a Number then the number would be greater than equal to one. So if value of a cell is greater than zero we would know the cell is of type Number.
We can represent a Blank cell using the interger value 0.
We can use -1 to represent a cell with Bomb.
In an interview, you should speak out loud all the alternative ways you can achieve something. Just make sure you are not spending too much time doing that.

A Cell could be in either of these two states:
Exposed
Unexposed


Board
Board is a 2 dimensional square matrix of Cell objects. Board has the below reponsibilities:
Being able to initialize the board and randomly place the blank cells, numbered cells and bombs.
Being able to expose a cell when the player clicks on the cell.
Being able to flag a cell as per player's instruction.
Being able to keep of track of number of unexposed non-bomb cells at all time. This is important to determine when a player wins. A player wins when this number drops to zero.
Being able to carry out below operation:
If player clicks on a blank cell, all adjacent blank cells (up to and including the surrounding numeric cells) would be exposed.

Game
The Game class takes instructions from the user (i.e, player) and carries them out. Game class should have the capability to initialize a board, start the game and determine when a player wins or loses.

Low Level Implementation:

    This is where your interviewer would ask you to
    go deeper in some of the features and do a low level implementation.
    We would focus on desiging the algorithms and coming up with low level design of

expandBlank(cell),

setNumberedCells(Cell[] bombs),


shuffleBombs().


Placing the bombs randomly across the board:

    We randomize the positions of bombs across the board by using a very sophisticated
    shuffle algorithm called Fisher-Yates Shuffling
    which is discussed in our Algorithms module.
We would place the K bombs in the first K cells and then shuffle all the cells around.


Set the numbered cells:

    Now that all the bombs are planted in random cells
    throughout the board, we go to each bomb and update the surrounding
    non-bomb cells with appropriate number.

Expand a Blank Region:

    If you think profoundly, you would figire out that
    each blank cell is surrounded either by blank cells or numbered
    cells, and never a bomb. The fact that there is no bomb cell surounding a blank cell is the reason
    why a blank cell never gets number in the first place. A blank cell can be imagined as a cell with number value = 0, which means
    0 bombs surrounding it.

    All surrounding cells, blank and numbered, need to be exposed.
    But, if you're exposing a blank cell, you also need to add the blank
    cells to a queue of cells that need to be further expanded. We do not expand on a number cell, we only
    expose them. But for a neighbor cell
    which is a blank
    cell, we expose them and then keep expanding on them.

    We would implement our search algorithm using Breadth First Search.




