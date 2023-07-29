
This problem is not a trivial problem to design for. But how we solve this problem will show you for solving Low Level Design problems all you need is systematic and logical thinking, no matter how hard a problem looks at the beginning. We will be focusing on mastering the art of breaking down a problem into small pieces and analyse every step and gather information, in order to be really good at Low Level Design.

To design for this problem, we need to think in real life how we would solve a puzzle when we are given a pile that contains all the individual pieces of the puzzle.

This is how I would go about solving the puzzle:

I would start with one corner by picking any one of the four corner pieces of the puzzle. How would we know if a piece is a corner piece ? It will have two flat sides.

Since we are talking about sides or edge of a piece, let's also discuss the different types of edges we might encounter in a piece. There could be three types of edges: flat, inner, outer. The outer edge is the one that has the middle portion sticking out or portruding. The inner edge is the opposite of the outer edge. The middle portion of the inner edges are concave.

Logically speaking, we can start with any of the 4 corner pieces and start solving the puzzle from there.

So we got one type of piece that is corner piece. What other types of pieces can we have ? We would have pieces that would have each of the 4 edges either inner edge or outer edge. This are the pieces that make up the inner portion of the puzzle. Let's call these type pieces inside pieces. And then we have border pieces, the ones that has one out of 4 edges flat. So, we got three types of pieces: corner, border, inside.

Now that we have the first corner piece, we can go on solving the puzzle row wise, starting from the first row. For every row we would start with the left-most piece and would solve for all the pieces in that row from left to right. Once that row is solved we would move on to the next row. We repeat this process until the entire puzzle is solved.

An important observation that is critical for our design:
An inside piece has 4 matching pieces, one in each of the four directions: left, top, right, bottom. So, generally speaking, a piece can have up to 4 matching pieces. However, an edge of a piece can only have one matching edge of the matching piece.
This observation is important because while solving the puzzle we would have to figure out next matching piece, and leveraging the discussion we just had, we would see that even though we are saying that we are trying to find a matching piece we are actually trying to find a matching edge.

How would we know if two edges are matching to each other ? This is a critical part of the design and would show your interviewer how you can think of simple solutions to complex problems. Even though this looks like a complex and abstract problem, you can implement this just by assigning every edge a code, and two matching edges would have the same code.
