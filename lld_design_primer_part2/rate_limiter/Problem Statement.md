Design and implement a logger system that receives a stream of messages along with their timestamps. Each unique messageshould only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messagesfrom being printed until timestamp t + 10).
All messages will come in chronological order. Several messages may arrive at the same timestamp.
Implement the Logger class:
Logger()
Initializes the logger object.
bool shouldPrintMessage(int timestamp, string message)
Returns true if the message should be printed in the giventimestamp, otherwise returns false.
Example:
Logger logger = new Logger();
logger.shouldPrintMessage(1, "foo"); // return true, next allowed timestamp for "foo" is 1 + 10 = 11
logger.shouldPrintMessage(2, "bar"); // return true, next allowed timestamp for "bar" is 2 + 10 = 12
logger.shouldPrintMessage(3, "foo"); // 3 < 11, return false
logger.shouldPrintMessage(8, "bar"); // 8 < 12, return false
logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21