package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {
    @Test
    public void getNameTest(){
        Player player = new Player("Froilan",10000);
        String expected = "Froilan";
        Assert.assertEquals(expected,player.getName());
    }

    @Test
    public void getCashTest(){
        Player player = new Player("Froilan",10000);
        int expected = 10000;
        Assert.assertEquals(expected,player.getCash());
    }

    @Test
    public void setCashTest(){
        Player player = new Player("Froilan",10000);
        int expected = 15000;
        player.setCash(15000);
        Assert.assertEquals(expected,player.getCash());
    }

    @Test
    public void getHandTest(){
        Player player = new Player("Froilan",10000);
        Card card = new Card("SPADES","SIX");
        player.addCardToHand(card);
        ArrayList<Card> hand = player.getHand();
        Assert.assertEquals("SPADES",hand.get(0).getSuit());
    }

    @Test
    public void addCardToHandTest(){
        Player player = new Player("Froilan",10000);
        Card card1 = new Card("SPADES","SIX");
        Card card2 = new Card("DIAMONDS","FOUR");
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        Assert.assertEquals(2,player.getHandSize());

    }

    @Test
    public void getCardTest(){
        Player player = new Player("Froilan",10000);
        Card card = new Card("SPADES","SIX");
        player.addCardToHand(card);
        Assert.assertEquals("SIX",player.getCard(0).getValue());
    }

    @Test
    public void discardHandTest(){
        Player player = new Player("Froilan",10000);
        Card card = new Card("SPADES","SIX");
        player.addCardToHand(card);
        player.discardHand();
        Assert.assertEquals(0,player.getHandSize());
    }

    @Test
    public void getHandSize(){
        Player player = new Player("Froilan",10000);
        Card card = new Card("SPADES","SIX");
        player.addCardToHand(card);
        Assert.assertEquals(1,player.getHandSize());
    }

    @Test
    public void isSetEliminatedTest() {
        Player player = new Player("Froilan",10000);
        player.setEliminated(true);
        Assert.assertTrue(player.isEliminated());
    }

}
