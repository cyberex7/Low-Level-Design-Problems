package game_leaderboard.Approach_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Leaderboard {
    private Map<Integer, Integer> scores;
    public Leaderboard() {
        scores = new HashMap<>();
    }
    
    public void addScore(int playerId, int score) { // Time Complexity: O(1)
        // if the player is not already present, initialize
        // the player's score to 0
        if (!scores.containsKey(playerId)) { // O(1)
            scores.put(playerId, 0); // O(1)
        }
        // add the given score to the current score of the player
        scores.put(playerId, scores.get(playerId) + score); // O(1)
    }
    
    public int top(int K) { // Time Complexity: O(NlogN) + O(K) = O(NlogN) where N = total number of players
        // we have all the scores in our hash map.  how do we get the top K scores ?
        // Simple. sort all the values contained in the map and return the 
        // max K elements from the sorted scores.
        // We would sort in descending order and grab the first K entries
        List<Integer> values = new ArrayList<Integer>(scores.values());
        Collections.sort(values, Collections.reverseOrder()); // O(NlogN)
        
        int sum = 0;
        for (int i = 0; i < K; i++) { // O(K)
            sum += values.get(i);            
        }
        return sum;
    }
    
    public void reset(int playerId) { // Time Complexity: O(1)
        // update score of given player to zero
        scores.put(playerId, 0); // O(1)
    }
    
    // Space Complexity: O(N), where N = total number  of players. We are keeping scores of all the players in the map.
}
