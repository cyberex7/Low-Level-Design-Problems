package lld_game_design_primer.blackjack_deck_of_cards.Approach_1;

import java.util.ArrayList;

public class Hand <T extends Card> {
    protected ArrayList<T> cards = new ArrayList<T>();

    public void addCard(T card) {
        cards.add(card);
    }
}
