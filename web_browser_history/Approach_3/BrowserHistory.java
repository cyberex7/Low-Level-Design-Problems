package web_browser_history.Approach_3;

import java.util.ArrayList;
import java.util.List;

class BrowserHistory {
    
    List<String> visitedWebpages;
    int currentIndex;

    public BrowserHistory(String homepage) {
        visitedWebpages = new ArrayList<String>();
        visitedWebpages.add(homepage);
        currentIndex = 0;
    }
    
    public void visit(String url) { // O(n)
       currentIndex++;
        // do not do visitedWebpages.add(currentIndex, url), instead do as shown below: it optimizes complexity from O(n) to O(1)
        if (currentIndex <= visitedWebpages.size() - 1) {
            // update
            visitedWebpages.set(currentIndex, url); // replace. This optimizes time complexity rather than doing add(index, value) operation since add(index, value) is O(n) but set() is O(1)
        }
        else {
           // add at the end of the list
           visitedWebpages.add(url); // O(1)
        }
       visitedWebpages.subList(currentIndex + 1, visitedWebpages.size()).clear(); // clear all forward history // clear() is O(n) operation
    }
    
    public String back(int steps) { // O(1)
        currentIndex = Math.max(0, currentIndex - steps);
        return visitedWebpages.get(currentIndex); // O(1)
    }
    
    public String forward(int steps) { // O(1)
        currentIndex = Math.min(visitedWebpages.size() - 1, currentIndex + steps);
        return visitedWebpages.get(currentIndex); // O(1)
    }
}

//The above implementation is not O(1) because in visitedWebpages.subList(currentIndex + 1, visitedWebpages.size()).clear(); // clear all forward history code clear() is O(n).

