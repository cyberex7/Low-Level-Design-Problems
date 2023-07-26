package game_leaderboard.Approach_2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Leaderboard {
    
    private Map<Integer, Integer> scores;
    public Leaderboard() {
        scores = new HashMap<>();
    }
    
    public void addScore(int playerId, int score) { 
        if (!scores.containsKey(playerId)) { 
            scores.put(playerId, 0);
        }
        scores.put(playerId, scores.get(playerId) + score); 
    }
    
    public int top(int K) {  
        // We are taking a min-heap containing values of the hash map. 
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        
        for (int score : scores.values()) { // O(N)
            minHeap.offer(score);
            if (minHeap.size() > K) {
                minHeap.poll(); // extract min because we are interested in Top elements
                                // O(logK) 
            }
        } // Overall time complexity to execute this foreach loop: O(NlogK)
        
        int sum = 0;
        for (int a : minHeap) { // O(K)
            sum += a;
        }
        return sum;

    // Time complexity: O(K) + O(NlogK) = O(NlogK)
    }
        
    
    public void reset(int playerId) { // Time Complexity: O(1)
        // update score of given player to zero
        scores.put(playerId, 0); // O(1)
    }
    
    /*
    Space Complexity: O(N + K), where N = total number  of players.
    We are keeping scores of all the players in the map.
    O(K) is used by the heap in top(K) method.
    */
}

