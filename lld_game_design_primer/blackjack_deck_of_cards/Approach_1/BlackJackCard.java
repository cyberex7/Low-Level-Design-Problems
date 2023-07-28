package lld_game_design_primer.blackjack_deck_of_cards.Approach_1;

public class BlackJackCard extends Card {
    public BlackJackCard(int c, SUIT s) {
        super(c, s);
    }

    public int value() {
        if (isAce()) { // Ace
            return 1;
        } else if (isFaceCard()) { // Face card
            return 10;
        } else { // Number card
            return faceValue;
        }
    }

    public int minValue() {
        return value();
    }

    public int maxValue() {
        if (isAce()) { // Ace
            return 11;
        } else {
            return value();
        }
    }

    public boolean isAce() {
        return faceValue == 1;
    }

    public boolean isFaceCard() {
        return faceValue >= 11 && faceValue <= 13;
    }
}