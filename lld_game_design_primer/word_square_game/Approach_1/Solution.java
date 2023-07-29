package lld_game_design_primer.word_square_game.Approach_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<List<String>> wordSquares(String[] words) {
        int N = words[0].length();
        List<List<String>> results = new ArrayList<List<String>>();
        Trie trie = new Trie(words); // build the Trie with all the words

        // We should make all the words the top-most word in the word square
        // and see if it leads to a complete word square.
        // We are doing this because we should try all combinations
        // since the problem statement
        // asks us to build all the possible word squares.
        for (String word : words) {
            // We initialize a partial solution with the current word as top-most word
            // and try to build a word square from that.
            List<String> partialSolution = new ArrayList();
            partialSolution.add(word);

            backtrack(1, partialSolution, results, N, words, trie);
        }

        return results;
    }

    private void backtrack(int row, List<String> partialSolution, List<List<String>> completeSolution, int N, String[] words, Trie trie) {
        if (row == N) {
            completeSolution.add(new ArrayList<String>(partialSolution));
            return;
        }

        // ****Candidate Construction begins:
        // if we are currently at row R in the word square (looking for suitable words for row R),
        // we can put only those words in row R which begins (i.e, prefix) with
        // all the characters we already have in the column R so far.
        StringBuilder prefix = new StringBuilder();
        for (String word : partialSolution) {
            prefix.append(word.charAt(row));
        }
        List<Integer> suitableCandidates = trie.getWordsWithPrefix(prefix.toString()); // construct suitable candidates
        // Candidate Construction Ends****

        for (Integer wordIndex : suitableCandidates) {
            partialSolution.add(words[wordIndex]); // makeMove
            backtrack(row + 1, partialSolution, completeSolution, N, words, trie); // recursively call backtrack( ... )
            partialSolution.remove(partialSolution.size() - 1); // undoMove
        }
    }
}

class TrieNode {
    /*
    For this problem, we do not need to keep a boolean flag terminate, since
    there is no need for looking up if a word exists in the trie. We are only interested in
    looking up all the words starting with a specific prefix.
    */
    char ch;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    List<Integer> wordList = new ArrayList<Integer>();

    public TrieNode() {}
    public TrieNode(char ch) {
        this.ch = ch;
    }

    public void addWord(String word, int wordIndex) {
        char firstCharacter = word.charAt(0);
        if (!this.children.containsKey(firstCharacter)) {
            this.children.put(firstCharacter, new TrieNode(firstCharacter));
        }
        this.wordList.add(wordIndex);
        if (word.length() > 1) {
            TrieNode nextNode = this.children.get(firstCharacter);
            nextNode.addWord(word.substring(1), wordIndex);
        }
    }

}

class Trie{
    TrieNode root = new TrieNode();
    public Trie(String[] words) {
        for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            root.addWord(words[wordIndex], wordIndex);
        }
    }

    public List<Integer> getWordsWithPrefix(String prefix) {
        // go to the trie node that contains the last character in the prefix
        TrieNode node = root;
        for (Character letter : prefix.toCharArray()) {
            if (node.children.containsKey(letter)) {
                node = node.children.get(letter);
            } else {
                // return an empty list.
                return new ArrayList<Integer>();
            }
        }
        // return the wordList stored in the node
        return node.wordList;
    }
}

