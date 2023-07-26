#1. Our first solution:
For a very simple brute force solution the first thing that comes to mind is using a hash map. We would be able to accomplish everything mentioned in our requirements, even though it might not be the most efficient solution. I have discussed the algorithm (along with complexity analysis) in the inline comments.

#2. Optimize top(int K) method implementation:
Let's revisit our requirements to see if we could find a way to optimize the above solution:

We need keep a list of all the players and their scores.
We need to (efficiently) keep track of top K scores.
We need the capability to reset score of any given player.
This also means that we should be able to efficiently access score of any given player.

Let's double click on the second requirement: keeping track of top K scores. Min heap automatically comes to mind as a very efficient data structure whenever something like "finding top K values" is what we are trying to accomplish. This is true for this problem as well.

We already have O(1) addScore(..) and reset(..) implementation and that is the best we can get. So let's concentrate on improving the performance of top(int K) method implementation.

We would optimize our solution above by using our knowledge that Min heap is a very efficient data structure whenever something like "finding top K values" is what we are trying to accomplish. Using heap or priority queue almost always beats the performance we get from using sorting.

Using the knowledge above let's see what we can do: in the top(int K) method we can use heap or priority queue instead of sorting.

So we were able able to optimize the performance of top(K) operation from O(NlogN) to O(NlogK), which is definitely a big improvement in performnace.
Let's see if we can do even better.


#3. Balanced Binary Search Tree based efficient solution that is used in real-world game leaderboard implementation on Production:

This solution will show your deep understanding of basic data structures like Binary Search Tree. We know that Binary Search Tree is a great fit to store elements as per their rank since for every node A all nodes with key less than or equal to the key of node A are on in its left subtree and all nodes with key greater than key of node A are in the right subtree of node A. These are discussed in great details in the Binary Search Tree chapter which is part of our Algorithms course. Since Leaderboard is all about displaying the players according to their ranks, balanced binary search tree is a great fit for building solution for this problem, specifically for finding top K players according to their rank (higher the score, better the rank).

The main objective we are trying to achieve is to improve the performance of the top(K) operation. If we can keep the scores of the players in descending order then it would be very easy for us to get top K scores in a very efficient way. To achieve that what we will do is store all scores in a balanced binary search tree in the reverse order of natural order (so that the BST stores scores in descending order). We would also store how many players have that score in the BST nodes. So in every BST node we would store a (key, value) pair, where score will be the key and the number of players who have gotten that score will be the corresponding value. This balanced BST would be used particularly to optimize top(K) operation.

In addition, we will have a map to store the leaderboard. The map will contain player ids and their corresponding scores.

In a balanced binary search tree like Red Black tree, amortized time complexity for getting the first k elements in inorder traversal would be O(K). This is discussed in more details in the inline comments in the top(K) implementation in the code below. This is a huge improvement in performance for top(K) operation. However, we are making a trade-off of degrading the performance of addScore(..) and reset(..) from O(1) to O(logN) since insertion and deletion operation on a balanced binary search tree are both O(logN) operations, as we will see in the code below.

In cae you are wondering why we are interested only in using Balanced BST and not just any BST, the answer to that is the worst case time complexity for BST operations is O(N) but for BALANCED BST the worst case time complexity of the BST operations are O(logN).