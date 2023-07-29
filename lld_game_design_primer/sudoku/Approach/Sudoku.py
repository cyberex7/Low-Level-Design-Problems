import math


class Sudoku:
    _instance = None

    def __init__(self):
        self._finished = False
        self._board = [[] for _ in range(9)]

    @staticmethod
    def getInstance():
        if Sudoku._instance is None:
            Sudoku._instance = Sudoku()
        return Sudoku._instance

    def setBoard(self, board):
        self._board = board

    def solve(self):
        self.backtrack(self._board, 0, -1)

    def backtrack(self, board, row, col):
        if row == 8 and col == 8 and board[row][col] == '0':
            self._finished = True
            return
        currentRow = row + 1 if col == 8 else row
        currentCol = 0 if col == 8 else col + 1
        if currentRow == 9:
            self._finished = True
            return
        if board[currentRow][currentCol] == '0':
            suitableCandidatesForCurrentCell = self._constructCandidates(board, currentRow, currentCol)
            for k in range(1, 10):
                if suitableCandidatesForCurrentCell[k - 1]:
                    self._fillBoard(currentRow, currentCol, chr((k + '0')))
                    self.backtrack(board, currentRow, currentCol)
                    if self._finished:
                        return
                    self._undoFillBoard(currentRow, currentCol)
        else:
            self.backtrack(board, currentRow, currentCol)

    def _constructCandidates(self, board, row, col):
        candidates = [False for _ in range(9)]
        for i in range(len(candidates)):
            candidates[i] = True
        for i in range(0, 9):
            if board[i][col] != '0':
                candidates[board[i][col] - '0' - 1] = False

        for j in range(0, 9):
            if board[row][j] != '0':
                candidates[board[row][j] - '0' - 1] = False
        topLeftRow = (math.trunc(row / float(3))) * 3
        topLeftCol = (math.trunc(col / float(3))) * 3
        i = topLeftRow
        while i < topLeftRow + 3:
            j = topLeftCol
            while j < topLeftCol + 3:
                if board[i][j] != '0':
                    candidates[board[i][j] - 1 - '0'] = False
                j += 1
            i += 1
        return candidates

    def _printBoard(self):
        for i in range(0, 9):
            for j in range(0, 9):
                print(self._board[i][j] + " ", end='')
            print()

    def _fillBoard(self, x, y, value):
        self._board[x][y] = value

    def _undoFillBoard(self, x, y):
        self._board[x][y] = '0'

    @staticmethod
    def main(args):
        sudoku = Sudoku.getInstance()
        board = [['3', '0', '6', '5', '0', '8', '4', '0', '0'], ['5', '2', '0', '0', '0', '0', '0', '0', '0'],
                 ['0', '8', '7', '0', '0', '0', '0', '3', '1'], ['0', '0', '3', '0', '1', '0', '0', '8', '0'],
                 ['9', '0', '0', '8', '6', '3', '0', '0', '5'], ['0', '5', '0', '0', '9', '0', '6', '0', '0'],
                 ['1', '3', '0', '0', '0', '0', '2', '5', '0'], ['0', '0', '0', '0', '0', '0', '0', '7', '4'],
                 ['0', '0', '5', '2', '0', '6', '3', '0', '0']]
        sudoku.setBoard(board)
        sudoku.solve()
        sudoku._printBoard()

    