package io.zipcoder.casino;

import org.junit.Test;
import org.junit.Assert;

public class DeckTest {

    @Test
    public void getDeckSize(){
        Deck deck = new Deck(1);
        Assert.assertTrue(deck.getDeckSize()==52);
    }

    @Test
    public void drawTest() {
        Deck deck = new Deck(1);
        deck.draw();
        Assert.assertTrue(deck.getDeckSize() == 51);
    }

    @Test
    public void preShuffleTest(){
        Deck deck = new Deck(1);
        Card card1 = deck.draw();
        Deck deck2 = new Deck(1);
        Card card2 = deck2.draw();
        Assert.assertEquals(card1.getValue(),card2.getValue());
    }

    @Test
    public void shuffleTest(){
        Deck deck = new Deck(1);
        Card card1 = deck.draw();
        deck.shuffle();
        Card card2 = deck.draw();
        Assert.assertNotEquals(card1.getValue(),card2.getValue());
    }
}
