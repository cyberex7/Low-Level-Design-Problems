
class Leaderboard:
    def __init__(self):
        self.sortedScores = {}
        self.leaderboard = {}

    def addScore(self, playerId, score):
        newScore = 0
        if playerId in self.leaderboard:
            newScore = self.leaderboard[playerId]
        newScore = newScore + score
        self.leaderboard[playerId] = newScore
        value = 0
        if newScore in self.sortedScores:
            value = self.sortedScores[newScore]
        self.sortedScores[newScore] = value + 1
        if newScore != score:
            oldScore = newScore - score
            self.sortedScores[oldScore] = self.sortedScores[oldScore] - 1

    def top(self, K):
        remaining = K
        sum = 0
        for entry in self.sortedScores:
            score = self.sortedScores[entry]
            numberOfPlayers = score
            if remaining >= numberOfPlayers:
                sum += score * numberOfPlayers
            else:
                sum += score * remaining
            remaining -= numberOfPlayers
            if remaining <= 0:
                break
        return sum

    def reset(self, playerId):
        score = self.leaderboard[playerId]
        self.leaderboard.pop(playerId)
        numberOfPlayersWithThisCore = self.sortedScores[score]
        if numberOfPlayersWithThisCore == 1:
            self.sortedScores.pop(score)
        else:
            self.sortedScores[score] = self.sortedScores[score] - 1

    