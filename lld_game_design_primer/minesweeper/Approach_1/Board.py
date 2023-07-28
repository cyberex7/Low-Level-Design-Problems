from enum import Enum

from  Cell import Cell


class GameState(Enum):
    INPROGRESS = 0
    WON = 1
    LOST = 2


class Board:
    def __init__(self, rows, columns, numberOfBombsToBePlanted):
        self._cells = [[]]
        self._unexposedCellsRemaining = 0

        self._rows = rows
        self._columns = columns
        bombs = [None for _ in range(numberOfBombsToBePlanted)]
        self._initializeBoard()
        self._plantBombsAtRandomPositions(numberOfBombsToBePlanted, bombs)
        self._setNumberedCells(bombs)
        self._unexposedCellsRemaining = rows * columns - numberOfBombsToBePlanted

    def _initializeBoard(self):
        self._cells = [[] for _ in range(self._rows)]
        r = 0
        while r < self._rows:
            c = 0
            while c < self._columns:
                self._cells[r][c] = Cell(r, c)
                c += 1
            r += 1

    def _plantBombsRandomly(self, numberOfBombsToBePlanted, bombs):
        self._plantBombsSequentially(numberOfBombsToBePlanted, bombs)
        self._shuffleBombs()

    def _shuffleBombs(self):
        nCells = self._rows * self._columns
        for index1 in range(0, nCells):
            index2 = index1 + random.randint(nCells - index1)
            if index1 != index2:
                
                row1 = index1 / self._columns
                column1 = int(math.fmod((index1 - row1 * self._columns), self._columns))
                cell1 = self._cells[row1][column1]
                
                row2 = index2 / self._columns
                column2 = int(math.fmod((index2 - row2 * self._columns), self._columns))
                cell2 = self._cells[row2][column2]
                self._cells[row1][column1] = cell2
                cell2.setRowAndColumn(row1, column1)
                self._cells[row2][column2] = cell1
                cell1.setRowAndColumn(row2, column2)

    def _plantBombsSequentially(self, numberOfBombs, bombs):
        for i in range(0, numberOfBombs):
            
            r = i / self._columns
            c = int(math.fmod((i - r * self._columns), self._columns))
            self._cells[r][c].setBomb()
            bombs[i] = self._cells[r][c]

    def _setNumberedCells(self, bombs):
        directions = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]
        for bomb in bombs:
            row = bomb.getRow()
            col = bomb.getColumn()
            for direction in directions:
                r = row + direction[0]
                c = col + direction[1]
                if 0 <= r < self._rows and 0 <= c < self._columns:
                    self._cells[r][c].setNumber(self._cells[r][c].getNumber() + 1)

    def exposeCell(self, row, col):
        cell = self._cells[row][col]
        if not cell.isExposed():
            cell.expose()
            if cell.isBlank():
                self._expandBlank()
            self._unexposedCellsRemaining -= 1
            if self._unexposedCellsRemaining == 0:
                return GameState.WON
            if cell.isBomb():
                return GameState.LOST
        return GameState.INPROGRESS

    def _expandBlank(self, cell):
        directions = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]
        toExplore = []
        toExplore.add(cell)
        while not len(toExplore) == 0:
            current = toExplore.remove(0)
            for direction in directions:
                r = current.getRow() + direction[0]
                c = current.getColumn() + direction[1]
                if 0 <= r < self._rows and 0 <= c < self._columns:
                    neighbor = self._cells[r][c]
                    neighbor.expose()
                    if neighbor.isBlank():
                        toExplore.add(neighbor)

    def printBoard(self):

        pass

    