
class Leaderboard:
    def __init__(self):
        self._scores = {}

    def addScore(self, playerId, score):
        if playerId not in self._scores.keys():
            self._scores[playerId] = 0

        self._scores[playerId] = self._scores[playerId] + score

    def top(self, K):
        values = list(self._scores.values())
        sorted(values, reverse=True)
        sumi = 0
        for i in range(0, K):
            sumi += values[i]
        return sumi

    def reset(self, playerId):
        self._scores[playerId] = 0