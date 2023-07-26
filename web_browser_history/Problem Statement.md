Problem Statement:

You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory as follows:

BrowserHistory(string homepage) Initializes the object with the homepage of the browser.

void visit(string url) Visits url from the current page. It clears up all the forward history.

string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.

string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.

Example:
BrowserHistory browserHistory = new BrowserHistory("example.com");
browserHistory.visit("google.com"); // You are in "example.com". Visit "google.com"

browserHistory.visit("facebook.com"); // You are in "google.com". Visit "facebook.com"

browserHistory.visit("youtube.com"); // You are in "facebook.com". Visit "youtube.com"

browserHistory.back(1); // You are in "youtube.com", move back to "facebook.com" return "facebook.com"

browserHistory.back(1); // You are in "facebook.com", move back to "google.com" return "google.com"

browserHistory.forward(1); // You are in "google.com", move forward to "facebook.com" return "facebook.com"

browserHistory.visit("linkedin.com"); // You are in "facebook.com". Visit "linkedin.com"

browserHistory.forward(2); // You are in "linkedin.com", you cannot move forward any steps.

browserHistory.back(2); // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"

browserHistory.back(7); // You are in "google.com", you can move back only one step to "example.com". return "example.com"

Solution:
While solving this design problem I would show you how to methodically go from O(n) approach to O(1) approach using your critical thinking ability and clear understanding of data structures. Please pay special attention to how we systematically optimize our solution. This is one of the qualities we are trying to build in you in this course and would greatly help you in your interviews as well as later in your software engineering career to stand out and get promoted and progress towards becoming an architect.

