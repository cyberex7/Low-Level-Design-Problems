import random
import math


class SnakeAndLadder:
    def __init__(self, squareMatrix):
        self.board = [[]]
        self.dicePosition = 0
        self.dimension = 0
        self.finishValue = 0

        self.board = squareMatrix
        self.dicePosition = 1
        self.dimension = len(squareMatrix)
        self.finishValue = self.dimension * self.dimension

    def isgameOver(self):
        return self.dicePosition == (self.finishValue)

    def rollDice(self):
        random = self._rand(1, 6)
        nextPosition = self.dicePosition + random
        if nextPosition > self.finishValue:
            return self.dicePosition
        coordinateOfNewPosition = self._getCoordinate(self.dicePosition)
        return self.board[coordinateOfNewPosition[0]][coordinateOfNewPosition[1]]

    def _rand(self, lower, higher):
        return lower + int((random.random() * (higher - lower + 1)))

    def _getCoordinate(self, num):
        rightToLeftRow = math.fmod((math.trunc((num - 1) / float(self.dimension))), 2) != 0
        row = (self.dimension - 1) - (math.trunc((num - 1) / float(self.dimension)))
        col = (self.dimension - 1) if (math.fmod(num, self.dimension)) - 1 == -1 else (math.fmod(num,
                                                                                                 self.dimension)) - 1
        if rightToLeftRow:
            col = self.dimension - 1 - col
        return [row, col]

    def __init__(self):
        self.diceRollSides = 6

    def getLeastNumberOfMovesRequiredToReachFinishSquare(self, board):
        board_size = len(board)
        if (math.fmod(board_size, 2) == 0 and board[0][0] != board_size * board_size) or (
                math.fmod(board_size, 2) != 0 and board[0][board_size - 1] != board_size * board_size):
            return -1
        source = 1
        destination = board_size * board_size
        bfsQueue = []
        processed = set()
        bfsQueue.append(source)
        numberOfEdgesFromSource = [0 for _ in range(board_size * board_size)]
        while not len(bfsQueue) == 0:
            parent = bfsQueue.pop()
            processed.add(parent)
            i = 1
            while i <= self.diceRollSides:
                coordinate = self._getCoordinate(parent + i, board_size)
                cellValue = board[coordinate[0]][coordinate[1]]
                if not cellValue in processed:
                    if numberOfEdgesFromSource[cellValue - 1] == 0:
                        numberOfEdgesFromSource[cellValue - 1] = numberOfEdgesFromSource[parent - 1] + 1
                    if cellValue == destination:
                        return numberOfEdgesFromSource[board_size * board_size - 1]
                    bfsQueue.append(cellValue)
                i += 1
        return -1

    @staticmethod
    def _getCoordinate(num, dimension):
        rightToLeftRow = math.fmod((math.trunc((num - 1) / float(dimension))), 2) != 0
        row = (dimension - 1) - (math.trunc((num - 1) / float(dimension)))
        col = (dimension - 1) if (math.fmod(num, dimension)) - 1 == -1 else (math.fmod(num, dimension)) - 1
        if rightToLeftRow:
            col = dimension - 1 - col
        return [row, col]