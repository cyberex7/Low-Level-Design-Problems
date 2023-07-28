Detailed Low Level Design:
Let's take a closer look on what happens if the snake makes a move: Here we have two scenarios:
Snake eats food : In this case everything remains same, just that the food cell gets prepended to the existing body of the snake and becomes the new head.
Snake eats no food: In this case the snake's head moves to the new cell, resulting in the tail (the last cell in the snake's body) to move one cell forward as well. So the new cell to which the snake made the move becomes the snake's new head and the tail moves to the second last cell of snake's body.
If we combine the above two scenarios we see that either the head (if the cell to which the snake moved has food) or both head and tail (if the cell to which the snake moved does not have food) get updated and the coordinates of the middle part of the snake's body remains the same.

So to represent the snake's body we would need a data structure which has below characteristics:
the data structure can dynamically grow (since whenever the snake eats food its length increments by 1),
the data structure is ordered (since whenever the tail is updated it moves one index forward),
updating the first element (head of the snake) and the last element can be done efficiently.
#1 eliminates use of array as an efficient data structure for this use case since array does not grow dynamically, even though array is ordered data structure. Also updating tail might require shifting the rest of the elements causes which is an O(N) operation.
#3 eliminates use of array list as an efficient data structure for this use case even though array list grows dynamically, since updating the tail might not be very efficient in array list since to remove by index, ArrayList find that index using random access in O(1) complexity, but after removing the element, shifting the rest of the elements causes overall O(N) time complexity.

One data structure that really fits well for our use case is Doubly LinkedList. It grows dynamically, is ordered and updating head and tail is O(1) operation. So we would use LinkedList to represent the snake's current position. Notice that we need the previous pointer of the Doubly LinkedList Nodes as well to remove and update the tail. Singly LinkedList won't serve our purpose.

Now let's look at the conditions when the game gets over:
The snake bites itself, i.e, if its head occupies a space that its body occupies after moving,
The snake hits a wall, i.e, goes out of the boundary of the given grid.
Determining whether the move that the snake made led it to hit the wall or not is very easy as we will see in the code below. The more crucial part is to efficiently determine if the snake bite itself. Iterating over the Doubly LinkedList to determine if a cell happens to be located on the body of the snake would be an O(N) operation. Can we do better ? Is there a way we can do the lookup in constant time ? We know any data structure based on hashing, for example hashset is really good at it. So if we are ready to do a trade-off on space then we would also store the coordinates of the snake body in a hashset to do an efficient lookup.

We are now ready to implement the game.

Time Complexity:
The time complexity of the move function is O(1).


Space Complexity:
Let W represent the width of the grid and H represent the height of the grid. Also, let N represent the number of food items in the list.

O(N) space is used by the food data structure.

O(W×H) space is used by the snakeConfiguration and the lookup data structures. At most, we can have snake that occupies all the cells of the grid as explained in the beginning of the article.

So overall space complexity = O((W * H) + N)
