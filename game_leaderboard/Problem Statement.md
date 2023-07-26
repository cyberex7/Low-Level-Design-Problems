Problem Statement:
Design a Leaderboard class, which has the following features:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.

Initially, the leaderboard is empty.

Example:

Explanation:
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73); // leaderboard = [[1,73]];
leaderboard.addScore(2,56); // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39); // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51); // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4); // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1); // returns 73;
leaderboard.reset(1); // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2); // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51); // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3); // returns 141 = 51 + 51 + 39;

Solution:
This is very good real-world problem to ask in an interview to assess someone's deep knowledge and understanding of data structures.

As with all other problems in our Low Level Design course, for this one too we come up with whatever solution comes to our mind first, and then show you how we logically go on optimizing our solution and land our final most efficient design and implementation.

If you are a beginner or an intermediate, after reading the problem statement it may seem like there is a lot to handle, and you might start think that it is so overwhelming to think about all the data structures needed to accomplish everything given in the requirements.

But as with every other problems, we would see here how breaking down the requirements into small pieces would help us conquer the problem. What we need here is systematic and logical thinking.

So let's analyze the requirements first. Think as if you are the Lead Software Engineer in a leading Games Development company and your task is to design and build the internals of generic Leadership Board that could be reused in various different games. How would you go about it ? You would talk to the business and gather the requirements. In our case we already have the requirements in the form of the given problem statement. Analyzing the requirements would be very next logical step and that is exactly what we are going to do now:
We need keep a list of all the players and their scores.
We need to (efficiently) keep track of top K scores.
We need the capability to reset score of any given player.
This also means that we should be able to efficiently access score of any given player.


The above discussion is a very good start. Now let's start with a simple solution, and as always we will go on optimizing it. If you are preparing for interviews, feel free to directly jump on the most optimized solution that you can think of since you would have limited time, but quickly mentioning other solutions and discussing their trade-offs, pros and cons (given you have time) is never a bad idea.

