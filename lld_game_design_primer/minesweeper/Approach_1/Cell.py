class Cell:
    def __init__(self, r, c):
        self._isBomb = False
        self._number = 0
        self._isExposed = False
        self._isFlagged = False

        self._row = r
        self._column = c

    def setRowAndColumn(self, r, c):
        self._row = r
        self._column = c

    def setBomb(self):
        self._isBomb = True
        self._number = -1

    def flag(self):
        self._isFlagged = True

    def unflag(self):
        self._isFlagged = False

    def isBlank(self):
        return self._number == 0

    def isNumber(self):
        return self._number > 0

    def isExposed(self):
        return self._isExposed

    def expose(self):
        self._isExposed = True

    def getNumber(self):
        return self._number

    def setNumber(self, number):
        self._number = number

    def getRow(self):
        return self._row

    def getColumn(self):
        return self._column

    def isBomb(self):
        return self._isBomb

    