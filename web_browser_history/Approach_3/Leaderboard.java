package game_leaderboard.Approach_3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Solution using Balanced Binary Search Tree
// Time Complexity:
// addScore, reset: O(logN)
// top(K): O(K)
// Space Complexityt: O(N)
class Leaderboard {
    
    TreeMap<Integer, Integer> sortedScores; // type needs to be TreeMap and not just Map interface, since we 
                                  // cannot just use any implementation of Map and need to use TreeMap 
                                  // only as we are looking for Balanced BST (balanced binary search tree)
                                  //
                                  // this map basically keeps all the scores in descending order along
                                  // with the number of players that currently have that score
                                  //
                                  // we have this data structure entirely to optimize top(K) method, it has no other purpose.
    Map<Integer, Integer> leaderboard; // map to keep playerId and their score, i.e, playerId -> score
    
    public Leaderboard() {
        sortedScores = new TreeMap<>(Collections.reverseOrder()); // sort keys in descending order
        leaderboard = new HashMap<Integer, Integer>();
    }
    
    public void addScore(int playerId, int score) { 
        // two scenarios we need to handle:
        // scenario #1: this player is not present in our leaderboard yet:
        // in this case (1) add the player in the leaderboard and (2) increment 
        // the value associated with the score by 1 in the sortedScores map
        //
        // scenario #2: the player is already present in the leaderboard:
        // (1) update the score in the leaderboard
        // and (2) decrement the value associated with old score by 1
        // and increment the value associated with updated score by 1 in the sortedScores map
        //
        // from the above discussion a good and experienced coder would come up with
        // below concise and clean code.
        
        int newScore = leaderboard.getOrDefault(playerId, 0) + score; //O(1) amortized
        
        leaderboard.put(playerId, newScore);  // applies to both scenario #1 and #2
                                              // O(1) amortized
        sortedScores.put(newScore, sortedScores.getOrDefault(newScore, 0) + 1); // applies to both scenario #1 and #2
                                                                                // O(logN)
        
        if (newScore != score) { // updatedScore == score only when the player's previous score 
            // was 0 i.e, NOT in leaderboard. So updatedScore != score means 
            // this player was already present in the leaderboard
            int oldScore = newScore - score;
            sortedScores.put(oldScore, sortedScores.get(oldScore) - 1); // O(logN)
        } 
    }   //Overall time complexity for addScore method = O(logN), where N = total number of players
    
    public int top(int K) {
        // Recall: inorder traversal of a BST gives us the elements in the order.
        // If a BST has [5,2,1,3,4] then the inorder traversal would
        // give 1 -> 2 -> 3 -> 4 -> 5 if we go by natural ordering.
        // In our case since we are interested in getting elements in 
        // descending order (since we are interested in top K scores)
        // we do not consider natural ordering, rather we consider Collections.reverseOrder()
        //
        // So in our case we do inorder traversal of the balanced BST and
        // sum up the scores of the first K players. The
        // BST implementation we have here sorts the player in the descending order
        // of their score, since score is the key of the BST nodes.
        //
        // In TreeMap we would achieve this just by iterating over it from the first 
        // entry making sure we do not sum up scores of more than K players
        // as we encounter them.

        int remaining = K;
        int sum = 0;
        
        // In-order traversal over the scores in the TreeMap
        for (Map.Entry<Integer, Integer> entry: sortedScores.entrySet()) { // amortized complexity of getting next node in an inorder traversal
                                // in red-black tree is O(1). TreeMap is implemented using Red-Black tree which is a balanced binary search tree. 
            int score = entry.getKey(); // getting key from entry is O(1)
            int numberOfPlayers = entry.getValue(); // getting value from an entry is O(1)
            if (remaining >= numberOfPlayers) {
                sum += score * numberOfPlayers;
            } else {
                sum += score * remaining;
            }
            remaining -= numberOfPlayers;
            if (remaining <= 0) {
                break;
            }
        } // Overall time complexity of this loop: O(K)

        return sum;

    } // Overall time complexity of top(K) method is O(K)
    
    public void reset(int playerId) {
        int score = leaderboard.get(playerId); // according to problem statement playerId is guaranteed to be present in the leaderboard if reset operation is being done for playerId
        
        // remove the player from leaderboard
        leaderboard.remove(playerId); // O(1) amortized
        
        // now that we have reset this player
        // remove this player from the sorted scores map by 
        // decrementing the number of players with the score same as this player's.
        //
        // if numberOfPlayer for this score goes down to zero, remove the score from the balanced BST altogether
        // as after this reset() operation there is no more player with this same score.
        int numberOfPlayersWithThisCore = sortedScores.get(score); // O(logN)
        if (numberOfPlayersWithThisCore == 1) { // this player is the only player with the score he/she has
            sortedScores.remove(score); // O(logN)
        } else {
            sortedScores.put(score, sortedScores.get(score) - 1); // O(logN)
        }
    } // Overall time complexity of reset operation = O(logN), where N = total number of players
}
