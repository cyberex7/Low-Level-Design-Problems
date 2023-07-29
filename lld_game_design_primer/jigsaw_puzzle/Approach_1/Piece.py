class Piece:
    _NUMBER_OF_EDGES = 4

    def __init__(self, edgeList):
        self._edges = {}

        directions = [e.name for e in Direction]
        i = 0
        while i < len(edgeList):
            edge = edgeList[i]
            edge.setParentPiece(self)
            self._edges[directions[i]] = edge
            i += 1

    def setEdgeAndRotate(self, edge, newDirection):
        currentDirection = self._getDirection(edge)
        self._edges = self._rotateEdgesClockwiseBy(newDirection.ordinal() - currentDirection.ordinal())

    def _rotateEdgesClockwiseBy(self, rotationSteps):
        directions = [e.name for e in Direction]
        rotated = {}
        if rotationSteps >= Piece._NUMBER_OF_EDGES:
            rotationSteps = math.fmod(rotationSteps, Piece._NUMBER_OF_EDGES)
        if rotationSteps < 0:
            rotationSteps += Piece._NUMBER_OF_EDGES
        i = 0
        while i <len(directions):
            edge = self._edges[directions[i]]
            newDirection = directions[int(math.fmod((i + rotationSteps), Piece._NUMBER_OF_EDGES))]
            rotated[newDirection] = edge
            i += 1
        return rotated

    def _getDirection(self, edge):
        for entry in self._edges.entrySet():
            if entry.getValue() is edge:
                return entry.getKey()
        return None

    def isCorner(self):
        directions = [e.name for e in Direction]
        i = 0
        while i < len(directions):
            current = self._edges[directions[i]].getShape()
            next = self._edges[directions[int(math.fmod((i + 1), Piece._NUMBER_OF_EDGES))]].getShape()
            if current is Shape.FLAT and next is Shape.FLAT:
                return True
            i += 1
        return False

    def isBorder(self):
        directions = [e.name for e in Direction]
        i = 0
        while i < len(directions):
            if self._edges[directions[i]].getShape() == Shape.FLAT:
                return True
            i += 1
        return False

    def getEdgeOnDirection(self, direction):
        return self._edges[direction]

    def getMatchingEdge(self, edge):
        for e in self._edges.values():
            if edge.fitsWith(e):
                return e
        return None


