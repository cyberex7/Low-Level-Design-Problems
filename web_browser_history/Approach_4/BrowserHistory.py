class BrowserHistory:
    def __init__(self, homepage):
        self.visitedWebpages = []
        self.visitedWebpages.append(homepage)
        self.currentIndex = 0
        self.currentHistorySize = 1


    def visit(self, url):
        self.currentIndex += 1
        if self.currentIndex <= len(self.visitedWebpages) - 1:
            self.visitedWebpages[self.currentIndex] = url
        else:
            self.visitedWebpages.insert(self.currentIndex, url)
        self.currentHistorySize = self.currentIndex + 1

    def back(self, steps):
        self.currentIndex = max(0, self.currentIndex - steps)
        return self.visitedWebpages[self.currentIndex]

    def forward(self, steps):
        self.currentIndex = min(self.currentHistorySize - 1, self.currentIndex + steps)
        return self.visitedWebpages[self.currentIndex]
