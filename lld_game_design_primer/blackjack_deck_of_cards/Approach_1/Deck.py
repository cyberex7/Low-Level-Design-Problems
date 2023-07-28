import random
import Card


class Deck(Card):
    def __init__(self, deckOfCards):
        self._dealtIndex = 0

        self._cards = deckOfCards

    def shuffle(self):
        i = 0
        while i < len(self._cards):
            j = self._rand(i, len(self._cards) - 1)
            card1 = self._cards[i]
            card2 = self._cards[j]
            self._cards[i] = card2
            self._cards[j] = card1
            i += 1

    @staticmethod
    def _rand(lower, higher):
        return lower + int((random.random() * (higher - lower + 1)))

    def dealCard(self):
        if len(self._cards) - self._dealtIndex == 0:
            return None
        card = self._cards[self._dealtIndex]
        card.deal()
        self._dealtIndex += 1
        return card
