from enum import Enum


class Color(Enum):
    WHITE = 0
    BLACK = 1

class Piece:

    def __init__(self, c):
        self._color = c

    def flip(self):
        if self._color == Color.BLACK:
            self._color = Color.WHITE
        else:
            self._color = Color.BLACK

    def getColor(self):
        return self._color



from enum import Enum
import math

class Direction(Enum):
    LEFT = 0
    RIGHT = 1
    UP = 2
    DOWN = 3
    LEFT_UP = 4
    LEFT_DOWN = 5
    RIGHT_UP = 6
    RIGHT_DOWN = 7


class Board:
    def __init__(self, rows, columns):
        self._blackCount = 0
        self._whiteCount = 0
        self._board = [[] for _ in range(rows)]

    def initialize(self):
        middleRow = math.trunc(len(self._board) / float(2))
        middleColumn = math.trunc(len(self._board[middleRow]) / float(2))
        self._board[middleRow][middleColumn] = Piece(Color.White)
        self._board[middleRow + 1][middleColumn] = Piece(Color.Black)
        self._board[middleRow + 1][middleColumn + 1] = Piece(Color.White)
        self._board[middleRow][middleColumn + 1] = Piece(Color.Black)
        self._blackCount = 2
        self._whiteCount = 2

    def makeMove(self, row, column, color):
        if self._board[row][column] is not None:
            return False
        results = [0 for _ in range(8)]
        results[0] = self._flipSection(row - 1, column, color, Direction.UP)
        results[1] = self._flipSection(row + 1, column, color, Direction.DOWN)
        results[2] = self._flipSection(row, column + 1, color, Direction.RIGHT)
        results[3] = self._flipSection(row, column - 1, color, Direction.LEFT)
        results[0] = self._flipSection(row - 1, column - 1, color, Direction.LEFT_UP)
        results[1] = self._flipSection(row + 1, column + 1, color, Direction.RIGHT_DOWN)
        results[2] = self._flipSection(row - 1, column + 1, color, Direction.RIGHT_UP)
        results[3] = self._flipSection(row + 1, column - 1, color, Direction.LEFT_DOWN)
        flipped = 0
        for result in results:
            if result > 0:
                flipped += result

        if flipped < 0:
            return False
        self._board[row][column] = Piece(color)
        self._updateScore(color, flipped + 1)
        return True

    def _flipSection(self, row, column, color, d):
        rowMovement = 0
        columnMovement = 0
        if d == Board.Direction.UP:
            rowMovement = -1
        elif d == Board.Direction.DOWN:
            rowMovement = 1
        elif d == Board.Direction.LEFT:
            columnMovement = -1
        elif d == Board.Direction.RIGHT:
            columnMovement = 1
        elif d == Board.Direction.LEFT_UP:
            rowMovement = -1
            columnMovement = -1
        elif d == Board.Direction.RIGHT_DOWN:
            rowMovement = 1
            columnMovement = 1
        elif d == Board.Direction.RIGHT_UP:
            rowMovement = -1
            columnMovement = 1
        elif d == Board.Direction.LEFT_DOWN:
            rowMovement = 1
            columnMovement = -1
        if row < 0 or row >= len(self._board) or column < 0 or column >= len(self._board[row]) or self._board[row][
            column] is None:
            return -1
        if self._board[row][column].getColor() is color:
            return 0
        numberOfOtherCellsFlippedInTheDirection = self._flipSection(row + rowMovement, column + columnMovement, color,
                                                                    d)
        if numberOfOtherCellsFlippedInTheDirection == -1:
            return -1
        self._board[row][column].flip()
        return numberOfOtherCellsFlippedInTheDirection + 1

    def _updateScore(self, newColor, newPieces):
        if newColor is Color.Black:
            self._whiteCount -= newPieces - 1
            self._blackCount += newPieces
        else:
            self._blackCount -= newPieces - 1
            self._whiteCount += newPieces

    def getScoreForColor(self, c):
        if c is Color.Black:
            return self._blackCount
        else:
            return self._whiteCount




class Reversi:
    _instance = None

    def __init__(self):
        self._board = None
        self._ROWS = 10
        self._COLUMNS = 10

        self._board = Board(self._ROWS, self._COLUMNS)

    @staticmethod
    def getInstance():
        if Reversi._instance is None:
            Reversi._instance = Reversi()
        return Reversi._instance

    def getScore(self, player):
        return self._board.getScoreForColor(player)

    def play(self, row, column, player):
        return self._board.makeMove(row, column, player)