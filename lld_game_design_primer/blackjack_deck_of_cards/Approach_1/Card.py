from enum import Enum


class Card:
    class SUIT(Enum):
        CLUB = 0
        DIAMOND = 1
        HEART = 2
        SPADE = 3

    def __init__(self, c, s):
        self._isDealt = False
        self.faceValue = c
        self.suit = s

    def value(self):
        pass

    def getSuit(self):
        return self.suit

    def isDealt(self):
        return self._isDealt

    def deal(self):
        if not self._isDealt:
            self._isDealt = True
            return True
        return False
