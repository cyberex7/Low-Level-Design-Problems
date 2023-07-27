Finding the least number of moves required to reach the finish square:

What we are looking for here is the shortest path from start square (which is 1) to finish square. Every time roll dice we move from one square to another (except of course when the the dice roll tries to lead us to a square greater than the finish square). So these squares could be thought of as nodes in a directed graph. If we are at square i then the child nodes of square i would be (i + 1), (i + 2), (i + 3), (i + 4), (i + 5) and (i + 6).
Weights of all the edges are 1. From the knowledge we have gained from our Algorithms course in the BFS chapter that BFS is an efficient way to find shortest path between source and destination in a graph with all edges with edge-weight equal to one. We have implemented this algorithm in below code.