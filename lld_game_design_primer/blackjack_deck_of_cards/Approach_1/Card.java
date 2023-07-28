package lld_game_design_primer.blackjack_deck_of_cards.Approach_1;

public abstract class Card {
    public enum SUIT { CLUB, DIAMOND, HEART, SPADE}

    private boolean isDealt = false; // has the card already been dealt ?

    protected SUIT suit;

    /* number or face that's on card :
     * 1 for Ace , or
     * a number 2 through 10, or
     * 11 for Jack, or
     * 12 for Queen, or
     * 13 for King
     */
    protected int faceValue;


    public Card(int c, SUIT s) {
        faceValue = c;
        suit = s;
    }

    public abstract int value(); // value of a card depends on the type of card game to be played

    public SUIT getSuit() {
        return suit;
    }

    public boolean isDealt() {
        return isDealt;
    }

    public boolean deal() {
        if (!isDealt) {
            isDealt = true;
            return true;
        }
        return false; // cannot deal an already dealt card
    }
}


