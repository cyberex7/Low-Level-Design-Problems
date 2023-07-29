import math
from enum import Enum

class Direction(Enum):
    LEFT = 0
    RIGHT = 1
    TOP = 2
    BOTTOM = 3

    def getOpposite(self):
        if self == Direction.LEFT:
            return Direction.RIGHT
        if self == Direction.RIGHT:
            return Direction.LEFT
        if self == Direction.TOP:
            return Direction.BOTTOM
        if self == Direction.BOTTOM:
            return Direction.TOP

