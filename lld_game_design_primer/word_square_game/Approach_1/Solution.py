class Solution:
    def wordSquares(self, words):
        N = len(words[0])
        results = []
        trie = Trie(words)
        for word in words:
            partialSolution = []
            partialSolution.append(word)
            self._backtrack(1, partialSolution, results, N, words, trie)
        return results

    def _backtrack(self, row, partialSolution, completeSolution, N, words, trie):
        if row == N:
            completeSolution.append(list(partialSolution))
            return
        prefix = ""
        for word in partialSolution:
            prefix += word[row]
        suitableCandidates = trie.getWordsWithPrefix(str(prefix))
        for wordIndex in suitableCandidates:
            partialSolution.append(words[wordIndex])
            self._backtrack(row + 1, partialSolution, completeSolution, N, words, trie)
            partialSolution.pop(len(partialSolution) - 1)


class TrieNode:

    def _initialize_instance_fields(self):
        self.ch = '\0'
        self.children = {}
        self.wordList = []

    def __init__(self, ch=""):
        self._initialize_instance_fields()

        self.ch = ch

    def addWord(self, word, wordIndex):
        firstCharacter = word[0]
        if firstCharacter not in self.children.keys():
            self.children[firstCharacter] = TrieNode(firstCharacter)
        self.wordList.append(wordIndex)
        if len(word) > 1:
            nextNode = self.children[firstCharacter]
            nextNode.addWord(word[1:], wordIndex)


class Trie:
    def __init__(self, words):
        self.root = TrieNode()

        wordIndex = 0
        while wordIndex < len(words):
            self.root.addWord(words[wordIndex], wordIndex)
            wordIndex += 1

    def getWordsWithPrefix(self, prefix):
        node = self.root
        for letter in prefix.toCharArray():
            if letter in node.children:
                node = node.children.get(letter)
            else:
                return []
        return node.wordList

    