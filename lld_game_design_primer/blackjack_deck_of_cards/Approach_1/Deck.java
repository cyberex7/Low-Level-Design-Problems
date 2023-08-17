package lld_game_design_primer.blackjack_deck_of_cards.Approach_1;

import java.util.ArrayList;

public class Deck <T extends Card> {
    private ArrayList<T> cards;
    private int dealtIndex = 0;

    public Deck(ArrayList<T> deckOfCards) {
        cards = deckOfCards;
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int j = rand(i, cards.size() - 1);
            T card1 = (T) cards.get(i);
            T card2 = (T) cards.get(j);

            // swap
            cards.set(i, card2);
            cards.set(j, card1);
        }
    }

    // Random number between lower and higher, both inclusive
    // [lower, higher]
    private int rand(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1)); // Range of Math.random() = [0.0, 1.0)
    }

    public T dealCard() {
        if (cards.size() - dealtIndex == 0) {
            return null;
        }

        T card = (T) cards.get(dealtIndex);
        card.deal();
        dealtIndex++;

        return card;
    }
}

