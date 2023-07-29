This is one of my personal favorites. Solving this problem shows the power and advantage of knowing the basic algorithms really good.

If someone is asked to design an algorithm with working code in a 40 mins coding interview, it might seem daunting. But here we would see how if you understand how backtracking works you would be able to come up with a very simple yet elegant algorithm with working code in under 30 minutes.

The concept is simple: we do an exhaustive search. For every cell that does not contain '0' we compute what are the values that cell can contain, and for each and every valid candidate we recursively call backtrack(...) to see if we can get a valid solution. Once we get the first valid solution the process stops.

We will be using our backtracking template discussed in Backtracking Fundamentals to write our code.
