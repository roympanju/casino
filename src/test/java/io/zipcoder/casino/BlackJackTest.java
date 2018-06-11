package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class BlackJackTest {
    @Test
    public void winConditionTest() {
        // lol
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        boolean expected = true;
        boolean actual = test.winCondition();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testPayOut() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.setPot(20);
        test.payOut(players[0]);
        int expected = 120;
        int actual = players[0].getCash();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testBlackJack() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        int isBlackJack = 21;
        boolean expected = true;
        boolean actual = test.blackJack(isBlackJack);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testHit() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.hit(players[0]);
        int expectedHandLength = 1;
        int actualHandLength = players[0].getHandSize();
        Assert.assertEquals(expectedHandLength, actualHandLength);
    }
    @Test
    public void testHouseConstruction() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        Player actual = test.house;
        Assert.assertNotNull(actual);
    }
    @Test
    public void testRoundEndHandClear() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.hit(players[0]);
        test.roundEnd();
        int expectedHandLength = 0;
        int actualHandLength = players[0].getHandSize();
        Assert.assertEquals(expectedHandLength, actualHandLength);
    }
    @Test
    public void testRoundEndIncrement() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.roundEnd();
        int expected = 2;
        int actual = test.roundCount;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testExit() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.exit();
        boolean expected = false;
        boolean actual = test.round;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testBetPlayerBalance() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.bet(20, players[0]);
        int expected = 80;
        int actual = players[0].getCash();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testBetPotBalance() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.bet(20, players[0]);
        int expected = 20;
        int actual = test.getPot();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testPlayForBrokePlayer() {
        Player[] players = new Player[] { new Player("", 0) };
        BlackJack test = new BlackJack(players);
        test.play();
        boolean expected = true;
        boolean actual = test.broke;
        Assert.assertEquals(expected, actual);
    }
}
