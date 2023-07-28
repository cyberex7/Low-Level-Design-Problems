After reading the all the details about playing card here are few things which are clear:
Cards are the foundational unit.
A Deck is formed with 52 distinct cards.
When cards have been dealt to a player, the cards which belong to that player form a Hand for him/her.
A deck of cards actually does not have much value if we do not know what card game we are going to play with our deck of cards, because the value of the cards depends on the type of card game. This makes our Card class an abstract class since we won't be having a concrete implementation of the value() . value() should be defined in the class that extends the Card class for a specific card game.

For any card we need to the following states:
what Suit the card belongs to ?
Is the card of type Club, Diamond, Heart or Spade ?
what is the face value of the card ?
what is the value of the card in the game that we are playing ?
has the card already been dealt ?


From above discussion we design the Card class. 

We should be able to do the following operations on our Deck of cards:
shuffle the deck.
deal the card that is on top of the deck.
To shuffle the deck we would be using Fisher-Yates Shuffle algorithm, which is discussed in details in our Algorithms course.

While designing the Deck class we need to keep in mind that a deck can be a deck of any kinds of playing cards, like, Poker cards, BlackJack cards. We will implement by using generics.

While designing the Hand we need to keep in mind that Hand could be made up of any kind of playing card. We will implement it using generics. We should also be able to add a card to the hand when a card is dealt to the player.

To design for BlackJack game, we will be extending the object oriented design we got for generic card earlier in this chapter.

While To design BlackJack card class we need to keep in mind that Ace can have a value of either 1 or 11 and it depends on the player what value he/she wants his/her Ace card to have. Remember that the player would want the score of his/her hand to be as cloase as 21 but not more than 21. So he/she would assign either 1 or 11 as the value of Ace depending on which one would help him/her to reach more favorable score for him/her.




If we have a BlackJack Hand we should be able to determine if the hand is a Bust, or a BlackJack or a Twenty-One or we have some other situations.

We should also be able to compute what score we are getting from the hand.

