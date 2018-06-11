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
        Player actual = test.getHouse();
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
        int actual = test.getRoundCount();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testExit() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.exit();
        boolean expected = false;
        boolean actual = test.getRound();
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
        boolean actual = test.checkBroke();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testHouseIsWinner() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        test.houseHit(test.getHouse());
        boolean expected = true;
        boolean actual = test.houseIsWinner(0);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testFindWinningHand() {
        Player[] players = new Player[] { new Player("", 100), new Player("", 100 )};
        BlackJack test = new BlackJack(players);
        test.hit(players[0]);
        int expected = 0;
        int actual = test.findWinningHand();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testAllIn() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        int expected = 100;
        int actual = test.allIn(players[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testHouseLogicBust() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        Card jack = new Card("HEARTS", "JACK");
        Card queen = new Card("HEARTS", "QUEEN");
        Card king = new Card("HEARTS", "KING");
        test.getHouse().addCardToHand(jack);
        test.getHouse().addCardToHand(queen);
        test.getHouse().addCardToHand(king);

        boolean expected = false;
        boolean actual = test.houseLogic();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testHouseLogicHit() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        Card jack = new Card("HEARTS", "JACK");
        Card three = new Card("HEARTS", "THREE");
        test.getHouse().addCardToHand(jack);
        test.getHouse().addCardToHand(three);

        boolean expected = true;
        boolean actual = test.houseLogic();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testHouseLogicStay() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        Card jack = new Card("HEARTS", "JACK");
        Card queen = new Card("HEARTS", "QUEEN");
        test.getHouse().addCardToHand(jack);
        test.getHouse().addCardToHand(queen);

        boolean expected = false;
        boolean actual = test.houseLogic();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testHouseWinEliminatesPlayers() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        Card jack = new Card("HEARTS", "JACK");
        Card ace = new Card("HEARTS", "ACE");
        test.getHouse().addCardToHand(jack);
        test.getHouse().addCardToHand(ace);
        test.houseLogic();

        boolean expected = true;
        boolean actual = players[0].isEliminated();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testPlayerExits() {
        Player[] players = new Player[] { new Player("", 100) };
        BlackJack test = new BlackJack(players);
        boolean expected = true;
        boolean actual = test.playerExits("exit");
        Assert.assertEquals(expected, actual);
    }

}
