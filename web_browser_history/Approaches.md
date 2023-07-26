
#1. Approach 1 implementation:
O(n) Doubly LinkedList based O(n) Approach and its drawbacks:

This solution is based on a very simple thought process: take a linkedlist to store the visited webpages in sequence (in the order they are visited). We have the older visited webpages towards the tail, and the more recently visited webpages towards the head. Since we have both forward() and back() methods, that means that we need to move on both sides of the linkedlist, this means that we would need a doubly linkedlist. This approach would automatically come to your mind if you have gone through LRU Cache and LFU Cache.



Drawback:
This doubly linkedlist based approach is not a good approach to be implemented in a language which does not have support in-built garbage collection due to the risk of data leak that would arise when we are clearing the forward history and re-assigning the head. The webpages which are cleared from the forward history should be handled by the developer to make sure there is no data leak if the above approach is implemented in a language like C++ where there is no garbage collection.


Time Complexity: O(n)
Space Complexity: O(n)

#2. Approach 2 implementation:
O(n) Approach using Two Stacks:

This approach is one of the simplest and intuitive and a perfect use of stack data structure. You keep two stacks:
Stack for storing back history: Lower the position of a webpage in the stack, the older it is in history.
Stack to store forward history: Lower the position of a webpage in the stack, the more forward in the history it is.
Now what about the current webpage ? You can keep the current webpage on top of either one of the two stacks. For the solution below, I choose to keep it on top of back history stack.


Time Complexity: O(n)
Space Complexity: O(n)

#3.  Approach 3 implementation:

O(n) solution using One List :
Instead of keeping two data structures, can we just use one ? Yes we can, we just need to keep track of the index of the current webpage at all time.

Time Complexity: O(n)
Space Complexity: O(n)

O(1) time complexity solution with O(n) space trade-off:
Look how the clear() method in visit() method is the root cause of the time complexity to be O(n) and not O(1). One of the requirements is when we call visit() it should erase all forward history. If we want to remove the forward history from our data structure it would be O(n) no matter how much optimization we do. But it saves space since we are deleting the forward history from the data structure altogether.
So if we really want to optimize space we would have to do a trade-off on space. What if we do not delete the forward history, instead keep where the current foward history ends ? This optimizes the space complexity to O(1) but at a cost of huge space requirements. No wonder, why some web browsers are memory hog!!

The below code implements this O(1) algorithm: Approach_4
Time Complexity: O(1)
Space Complexity: O(n)
