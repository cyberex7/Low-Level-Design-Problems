from BlackJackCard import Card


class BlackJackCard(Card):
    def __init__(self, c, s):
        super().__init__(c, s)

    def value(self):
        if self.isAce():
            return 1
        elif self.isFaceCard():
            return 10
        else:
            return self.faceValue

    def minValue(self):
        return self.value()

    def maxValue(self):
        if self.isAce():
            return 11
        else:
            return self.value()

    def isAce(self):
        return self.faceValue == 1

    def isFaceCard(self):
        return self.faceValue >= 11 and self.faceValue <= 13