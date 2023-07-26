package web_browser_history.Approach_2;

// Two Stacks Approach

import java.util.ArrayDeque;
import java.util.Deque;

class BrowserHistory {
    
    Deque<String> backHistory; // stack to contain current webpage and all its back pages, which means webpage on top of this stack is the current webpage we are at
    Deque<String> forwardHistory; // stack to store all forward webpages of the current webpage we are at
    

    public BrowserHistory(String homepage) {
        backHistory = new ArrayDeque<>();
        forwardHistory = new ArrayDeque<>();
        backHistory.push(homepage);
    }
    
    public void visit(String url) {
        backHistory.push(url);  
        forwardHistory.clear(); // clear all forward history
    }
    
    public String back(int steps) {
        while (!backHistory.isEmpty() && steps > 0) {
            forwardHistory.push(backHistory.pop());
            steps--;
        }
        if (backHistory.isEmpty()) {
            backHistory.push(forwardHistory.pop());
        }
        return backHistory.peek();
    }
    
    public String forward(int steps) {
        while (!forwardHistory.isEmpty() && steps > 0) {
            backHistory.push(forwardHistory.pop());
            steps--;
        }
        return backHistory.peek();
    }
}

