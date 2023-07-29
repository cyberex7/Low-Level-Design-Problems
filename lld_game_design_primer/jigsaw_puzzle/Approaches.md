With all the information we have gathered so far, we are ready to design the Piece class.


So far we have figured out following properties of Edge:
An edge with have a type: Flat, Inner or Outer.
An edge will have a code associated with with.
An edge will have an associated piece to which it belongs.
Give two edges we should be able to tell if they match.
Based on our findings so far, we can easily design the Edge class.

We also need to have a Puzzle class that will be responsible to solve the puzzle. I have put detailed algorithmic discussion in the inline comments.

Now that we have gotten all the core pieces (Piece, Edge, Puzzle) and functionalities put together, all we need is a controller class that would be responsible for initializing the game and validating if a given solution is a valid solution. The code below is quite self-explanatory and has inline comments wherever necessary.

