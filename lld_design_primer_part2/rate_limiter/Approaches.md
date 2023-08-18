1. Most Basic Solution:
The below two solutions might come to your mind instantly after looking at the problem statement:
1.
You keep a map to keep track of when a message was last logged and using that information you decide whether to printan incoming message or not.
2.
Another approach would be: instead of keeping track of when a message was last printed, you keep track of when themessage becomes eligible to be printed again, and using this information you decide whether to print an incoming message or not.


Complexity Analysis:
Time Complexity:
O(1)
Space Complexity:
O(m), where m is the number of all incoming messages till now (not just in last 10 seconds).
Drawback:
The biggest drawback of the above approach is that the map never stops growing. This makes the above solution useless foractual production environment.