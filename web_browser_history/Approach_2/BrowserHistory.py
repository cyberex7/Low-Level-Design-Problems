
import collections


class BrowserHistory:
    def __init__(self, homepage):

        self.backHistory = None
        self.forwardHistory = None

        self.backHistory = collections.deque([])
        self.forwardHistory = collections.deque([])
        self.backHistory.insert(len(self.backHistory), homepage)

    def visit(self, url):
        self.backHistory.insert(len(self.backHistory), url)
        self.forwardHistory.clear()

    def back(self, steps):
        while (not len(self.backHistory) == 0) and steps > 0:
            self.forwardHistory.insert(len(self.backHistory), self.backHistory.pop())
            steps -= 1
        if len(self.backHistory) == 0:
            self.backHistory.insert(len(self.backHistory), self.forwardHistory.pop())
        return self.backHistory.index(0)

    def forward(self, steps):
        while (not len(self.forwardHistory) == 0) and steps > 0:
            self.backHistory.insert(len(self.backHistory), self.forwardHistory.pop())
            steps -= 1
        return self.backHistory.index(0)
