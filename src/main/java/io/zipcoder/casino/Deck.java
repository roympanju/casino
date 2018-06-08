package io.zipcoder.casino;
import java.util.*;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();

    private enum CardSuits {SPADES, HEARTS, DIAMONDS, CLUBS}
    private enum CardValues {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, KING, QUEEN}

    public Deck(int numberOfMultipleDecks) {
        for (int i = 0; i <= numberOfMultipleDecks; i++) {
            for (CardSuits suit : CardSuits.values()) {
                for (CardValues value : CardValues.values()) {
                    deck.add(new Card(suit.toString(), value.toString()));
                }
            }
        }
    }

    public Card draw() {
        return deck.remove(0);
    }
}
