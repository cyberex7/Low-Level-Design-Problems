The solution of this problem has two major parts: (1) Backtracking, (2) Trie.

This problem at the beginning right after looking at the problem statement might seem like a pretty difficult problem. But with systematic and organized step-by-step thinking and with sound knowledge of data structures (like Trie) we can easily conquer it by designing a simple algorithm.

It is very easy o understand that it an exhaustive search problem. Next comes the systematic thinking. How would we actually build word squares from a given list of words ? We would try to build word squares by trying to make each and every word the top-most word in a word square. For each of these partial configuration we go on making progress to see if they extend to become a complete word square. We discard a partial configuration when we see that none of the remaining words would help us extend an existing partial solution solution to become a complete solution.

How do we know if a word is a good fit to extend a partial Solution to make some progress ? If we have already built the first (R - 1) rows of a word square, for the row R we can consider only those words which has the prefix equal to what substring we have at column R right now.

For example: if we have given list of words = ["ball","area","lead","lady"] and we have already built partial solution as follows:
    ball
    area


Now we are looking what word to put in 3rd row. It needs to start with "le" since the 3rd column has "le". So only those words are good candidate for 3rd row of the word square which has "le" has prefix.

So, the performance of our solution depends on how efficiently we can find out all the words with a specific prefix from the given list of words. What data structure is specifically good for searching strings with a given prefix ? TRIE.

So we would need a Trie.

Once we have figured till here, we should be able to figure everything else out if we have a good understanding of how Backtracking works and how to implement a Backtracking solution. Fortunately we have the backtracking template that makes writing Backtracking code much easier.

Let's look at the code below and everything would become crystal clear. I have put all the implementaion details and in-depth algorithmic discussion in the inline comments in the code.
