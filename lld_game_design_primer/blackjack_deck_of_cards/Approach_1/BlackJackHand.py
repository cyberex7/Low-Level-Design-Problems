import sys

from BlackJackHand import Hand


class BlackJackHand(Hand):
    def __init__(self):
        pass

    def score(self):
        scores = self._possibleScores()
        maxUnder = float('inf')
        minOver = float('-inf')
        for score in scores:
            if 21 < score < minOver:
                minOver = score
            elif 21 >= score > maxUnder:
                maxUnder = score
        return minOver if maxUnder == float('-inf') else maxUnder

    def _possibleScores(self):
        scores = []
        if len(self.cards) == 0:
            return scores
        for card in self.cards:
            self._addCardToScoreList(card, scores)
        return scores

    @staticmethod
    def _addCardToScoreList(card, scores):
        if len(scores) == 0:
            scores.append(0)
        length = len(scores)
        for i in range(0, length):
            score = scores[i]
            scores[i] = score + card.minValue()
            if card.minValue() != card.maxValue():
                scores.append(score + card.maxValue())

    def busted(self):
        return self.score() > 21

    def is21(self):
        return self.score() == 21

    def isBlackJack(self):
        if len(self.cards) != 2:
            return False
        first = self.cards[0]
        second = self.cards[1]
        return (first.isAce() and second.isFaceCard()) or (second.isAce() and first.isFaceCard())
