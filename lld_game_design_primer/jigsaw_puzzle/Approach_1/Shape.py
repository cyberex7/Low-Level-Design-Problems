from enum import Enum


class Shape(Enum):
    CONCAVE = 1
    CONVEX = 2
    FLAT = 3

    def getOpposite(self):
        if Shape.CONCAVE:
            return Shape.CONVEX
        elif Shape.CONVEX:
            return Shape.CONCAVE
        else:
            return None

