class SnakeGame:
    def __init__(self, width, height, food):
        self.snakeConfiguration = []
        self.lookup = []
        self.foodIndex = 0
        self.width = width
        self.height = height
        self.food = food
        self.snakeConfiguration.append([0, 0])
        self.lookup.append(self._convertToString(0, 0))

    def move(self, direction):
        head = self.snakeConfiguration[0]
        headRow = head[0]
        headCol = head[1]
        if direction == "U":
            headRow -= 1
        elif direction == "D":
            headRow += 1
        elif direction == "L":
            headCol -= 1
        elif direction == "R":
            headCol += 1
        oldTail = self.snakeConfiguration[-1]
        if (self._convertToString(headRow, headCol) in self.lookup and not (
                headRow == oldTail[0] and headCol == oldTail[
            1])) or headRow < 0 or headRow >= self.height or headCol < 0 or headCol >= self.width:
            return -1
        self.snakeConfiguration.insert(0,[headRow, headCol])
        self.lookup.append(self._convertToString(headRow, headCol))
        if self.foodIndex < len(self.food) and headRow == self.food[self.foodIndex][0] and headCol == \
                self.food[self.foodIndex][1]:
            self.foodIndex += 1
        else:
            del self.snakeConfiguration[-1]
            if not (headRow == oldTail[0] and headCol == oldTail[1]):
                self.lookup.remove(self._convertToString(oldTail[0], oldTail[1]))
        return len(self.snakeConfiguration) - 1

    @staticmethod
    def _convertToString(x, y):
        sb = ""
        sb += str(x)
        sb += ", "
        sb += str(y)
        return sb
