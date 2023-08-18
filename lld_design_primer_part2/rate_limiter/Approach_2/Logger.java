package Approach_2;

import java.util.HashMap;

class Logger {
    HashMap<String, Integer> nextEligibleTime;

    /** Initialize your data structure here. */
    public Logger() {
        nextEligibleTime = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timestamp < nextEligibleTime.getOrDefault(message, 0)) {
            return false;
        }
        nextEligibleTime.put(message, timestamp + 10);
        return true;
    }
}
