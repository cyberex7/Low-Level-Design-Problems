package Approach_1;

import java.util.*;

class
Logger {
  HashMap < String,Integer> map;
  /** Initialize your data structure here. */
  public Logger() {
    map = new HashMap < >();
  }
  /** Returns true if the message should be printed in the given timestamp, otherwise returns fals The timestamp is in seconds granularity. */
  public boolean shouldPrintMessage( int timestamp, String message) {
    // update timestamp of the message if the message is coming in for the
    // first time, or the last logging time is earlier than 10 seconds from now
    if (!map.containsKey(message) || timestamp - map.get(message) >= 10) {
      map. put( message, timestamp);
      return true;
    }
    return false;
  }
}