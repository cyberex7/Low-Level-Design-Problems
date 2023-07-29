import math


class Puzzle:
    def __init__(self, size, pieces):
        self._solution = [[]]
        self._size = 0

        self._pieces = pieces
        self._size = size

    def getSolution(self):
        return self._solution

    def solvePuzzle(self):
        cornerPieces = []
        borderPieces = []
        insidePieces = []
        self._groupPieces(cornerPieces, borderPieces, insidePieces)
        self._solution = [[] for _ in range(self._size)]
        row = 0
        while row < self._size:
            column = 0
            while column < self._size:
                piecesToSearch = self._getPieceListToSearch(cornerPieces, borderPieces, insidePieces, row, column)
                if not self._insertAppropriatePiece(row, column, piecesToSearch):
                    return False
                column += 1
            row += 1
        return True

    def _groupPieces(self, cornerPieces, borderPieces, insidePieces):
        for p in self._pieces:
            if p.isCorner():
                cornerPieces.append(p)
            elif p.isBorder():
                borderPieces.append(p)
            else:
                insidePieces.append(p)

    def _getPieceListToSearch(self, cornerPieces, borderPieces, insidePieces, row, column):
        if self._isBorderIndex(row) and self._isBorderIndex(column):
            return cornerPieces
        elif self._isBorderIndex(row) or self._isBorderIndex(column):
            return borderPieces
        else:
            return insidePieces

    def _isBorderIndex(self, location):
        return location == 0 or location == self._size - 1

    def _insertAppropriatePiece(self, row, column, piecesToSearch):
        if row == 0 and column == 0:
            p = piecesToSearch.remove()
            self._insertAtTopLeftCorner(p)
            self._solution[0][0] = p
        else:
            pieceToMatch = self._solution[row - 1][0] if column == 0 else self._solution[row][column - 1]
            directionToMatch = Direction.BOTTOM if column == 0 else Piece.Direction.RIGHT
            edgeToMatch = pieceToMatch.getEdgeOnDirection(directionToMatch)
            edge = self._getMatchingEdge(edgeToMatch, piecesToSearch)
            if edge is None:
                return False
            directionThatNeedsToBeMatched = directionToMatch.getOpposite()
            self._insertIntoSolution(piecesToSearch, edge, row, column, directionThatNeedsToBeMatched)
        return True

    def _insertAtTopLeftCorner(self, piece):
        if not piece.isCorner():
            return
        directions = Piece.Direction.values()
        i = 0
        while i < len(directions):
            current = piece.getEdgeOnDirection(directions[i])
            clockwiseNext = piece.getEdgeOnDirection(directions[math.fmod((i + 1), len(directions))])
            if current.getShape() == Edge.Shape.FLAT and clockwiseNext.getShape() == Edge.Shape.FLAT:
                piece.setEdgeAndRotate(current, Piece.Direction.LEFT)
                return
            i += 1

    @staticmethod
    def _getMatchingEdge(edge, pieces):
        for piece in pieces:
            matchingEdge = piece.getMatchingEdge(edge)
            if matchingEdge is not None:
                return matchingEdge
        return None

    def _insertIntoSolution(self, pieces, edge, row, column, orientation):
        piece = edge.getParentPiece()
        piece.setEdgeAndRotate(edge, orientation)
        pieces.remove(piece)
        self._solution[row][column] = piece


