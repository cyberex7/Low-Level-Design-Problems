class Edge:
    def __init__(self, shape, code):
        self._code = None
        self._parentPiece = None
        self._shape = 0

        self._shape = shape
        self._code = code

    def getCode(self):
        return self._code

    def fitsWith(self, edge):
        return edge.getCode() == self.getCode()

    def setParentPiece(self, parentPiece):
        self._parentPiece = parentPiece

    def getParentPiece(self):
        return self._parentPiece

    def getShape(self):
        return self._shape

    def toString(self):
        return self._code
