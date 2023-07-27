
import math


class SnakeAndLadder:

    def __init__(self):
        self.diceRollSides = 6

    def snakesAndLadders(self, board):
        board_size = len(board)
        j = 1
        while j <= board_size * board_size:
            coord = self._getCoordinate(j, board_size)
            if board[coord[0]][coord[1]] == -1:
                board[coord[0]][coord[1]] = j
            j += 1
        if (math.fmod(board_size, 2) == 0 and board[0][0] != board_size * board_size) or (
                math.fmod(board_size, 2) != 0 and board[0][board_size - 1] != board_size * board_size):
            return -1
        source = 1
        destination = board_size * board_size
        bfsQueue = []
        visited = set()
        bfsQueue.append(source)
        numberOfEdgesFromSource = [0 for _ in range(board_size * board_size)]
        count = 0
        while not len(bfsQueue) == 0:
            count += 1
            queueSize = len(bfsQueue)
            for i in range(0, queueSize):
                curr = bfsQueue.pop()
                visited.add(curr)
                j = 1
                while j <= self.diceRollSides:
                    coordinate = self._getCoordinate(curr + j, board_size)
                    cellValue = board[coordinate[0]][coordinate[1]]
                    if cellValue == destination:
                        return count
                    if cellValue not in visited:
                        bfsQueue.append(cellValue)
                    j += 1
        return -1

    @staticmethod
    def _getCoordinate(num, dimension):
        equivalentZeroBasedIndex = num - 1
        evenRow = math.fmod((math.trunc((num - 1) / float(dimension))), 2) == 0
        row = (dimension - 1) - (math.trunc((num - 1) / float(dimension)))
        col = math.fmod((num - 1), dimension)
        if not evenRow:
            col = dimension - 1 - col
        return [row, col]
