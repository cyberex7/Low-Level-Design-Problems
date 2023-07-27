
Problem Statement:

Design Snake and Ladder game.
Cells in the snake and ladder game board are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board and alternating direction each row.
Rules of Snake and Ladder game:
Start from cell 1.
Throw the dice and whatever number you get, move on the number of cells on the board.
If you reach a cell which is base of a ladder, then you have to climb up that ladder without a dice throw.
If you reach a cell is mouth of the snake, has to go down to the tail of snake without a dice throw.
Follow-up question:
Implement a method that returns the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.

For the below board the least number of moves required to reach the square with value 36 is 4 by taking below moves:
1 --1st move--> (6 --ladder--> 18) --2nd move-> (15 --ladder-> 22) --3rd move--> (23 --ladder--> 35) --4th move-> 36
