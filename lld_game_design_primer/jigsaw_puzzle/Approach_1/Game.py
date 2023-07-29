import random


class Game:
    @staticmethod
    def initializePuzzle(size):
        puzzle = [[] for _ in range(size)]
        for row in range(0, size):
            for column in range(0, size):
                edges = Game._createEdges(puzzle, column, row)
                puzzle[row][column] = Piece(edges)
        pieces = []
        for row in range(0, size):
            for column in range(0, size):
                piece = puzzle[row][column]
                rotations = random.randint(4)
                directions = [Direction.LEFT, Direction.TOP, Direction.RIGHT, Direction.BOTTOM]
                piece.setEdgeAndRotate(piece.getEdgeOnDirection(Direction.LEFT), directions[rotations])
                index = 0 if len(pieces) == 0 else random.randint(len(pieces))
                pieces.insert(index, piece)
        return pieces

    @staticmethod
    def _createEdges(puzzle, column, row):
        key = str(column) + ":" + str(row) + ":"
        left = Edge(Shape.FLAT, key + "left") if column == 0 else Game._createMatchingEdge(
            puzzle[row][column - 1].getEdgeOnDirection(Direction.RIGHT))
        top = Edge(Shape.FLAT, key + "top") if row == 0 else Game._createMatchingEdge(
            puzzle[row - 1][column].getEdgeOnDirection(Direction.BOTTOM))
        right = Edge(Shape.FLAT, key + "right") if column == len(puzzle[row]) - 1 else Game._createRandomEdge(
            key + "right")
        bottom = Edge(Shape.FLAT, key + "bottom") if row == len(puzzle) - 1 else Game._createRandomEdge(key + "bottom")
        edges = [left, top, right, bottom]
        return edges

    @staticmethod
    def _createRandomEdge(code):
        type = Shape.CONCAVE
        if random.choice([True,False]):
            type = Shape.CONVEX
        return Edge(type, code)

    @staticmethod
    def _createMatchingEdge(edge):
        if edge.getShape() == Shape.FLAT:
            return None
        return Edge(edge.getShape().getOpposite(), edge.getCode())

    @staticmethod
    def isValidSolution(solution):
        if solution is None:
            return False
        r = 0
        while r < len(solution):
            c = 0
            while c < len(solution[r]):
                piece = solution[r][c]
                if piece is None:
                    return False
                if c > 0:
                    left = solution[r][c - 1]
                    if not left.getEdgeOnDirection(Direction.RIGHT).fitsWith(piece.getEdgeOnDirection(Direction.LEFT)):
                        return False
                if c < len(solution[r]) - 1:
                    right = solution[r][c + 1]
                    if not right.getEdgeOnDirection(Direction.LEFT).fitsWith(piece.getEdgeOnDirection(Direction.RIGHT)):
                        return False
                if r > 0:
                    top = solution[r - 1][c]
                    if not top.getEdgeOnDirection(Direction.BOTTOM).fitsWith(piece.getEdgeOnDirection(Direction.TOP)):
                        return False
                if r < len(solution) - 1:
                    bottom = solution[r + 1][c]
                    if not bottom.getEdgeOnDirection(Direction.TOP).fitsWith(
                            piece.getEdgeOnDirection(Direction.BOTTOM)):
                        return False
                c += 1
            r += 1
        return True

    