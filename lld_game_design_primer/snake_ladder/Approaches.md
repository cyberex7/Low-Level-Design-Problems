Detailed Low Level Design:
While desigining the snake and ladder game board the main challenge is how to represent the snakes and ladders in the board. Let's look at how many different types of squares we can have in a snake and ladder board:
Squares that have bottom-end of a ladder in it:
Dice cannot stay in this square, because once the dice reaches this square it climbs up the ladder and reaches the square that has the top-end of the ladder.
Squares that have top-end of a ladder in it:
This does not have any special implication. If dice reaches this square it stays there. These squares are actually like the regular squares.
Squares that have snake mouth in it:
Dice cannot stay in this square, because once the dice reaches this square it goes down to the square that has the tail of the snake.
Squares that have snake tail in it:
This does not have any special implication. If dice reaches this square it stays there. These squares are actually like the regular squares.
Regular square:
Any square that does not have any snake or ladder in it.
Since dice cannot stay in the squares that have either ladder bottom-end or a snake mouth, we should replace the values in those squares with the values of the squares to which they lead.
Let's see following this above rule how we could represent the above snake and ladder board:

[
[36, 35, 22, 33, 32, 20],
[12, 26, 27, 28, 29, 30],
[24, 35, 22, 28,  5, 19],
[13, 14, 22,  2, 17, 18],
[12, 14, 10,  9,  8,  7],
[ 1,  2,  3,  4,  5, 18]
]
