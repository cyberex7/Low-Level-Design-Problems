class Hand(Card):
    def __init__(self, c, s):
        super().__init__(c, s)
        self.cards = []

    def addCard(self,card):
        self.cards.append(card)