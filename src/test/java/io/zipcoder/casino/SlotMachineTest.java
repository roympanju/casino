package io.zipcoder.casino;

import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;


public class SlotMachineTest {
    Player player = new Player("Test",500);
    Player[] players = new Player[]{player};
    SlotMachine slots = new SlotMachine(players);


    @Test
    public void isMultiplierActiveTest(){
        slots.setSpinsRemainingOnMultiplier(5);
        Assert.assertTrue(slots.isMultiplierActive());
    }

    @Test
    public void bonusIfMultiplierActiveTest(){
        Assert.assertEquals(1,slots.bonusIfMultiplierActive());
    }

    @Test
    public void bonusIfMultiplierActive2Test(){
        slots.setSpinsRemainingOnMultiplier(2);
        Assert.assertEquals(3,slots.bonusIfMultiplierActive());
    }

    @Test
    public void proceedToSpinCheckTest(){
        Assert.assertTrue(slots.proceedToSpinCheck(3,player));
    }

    @Test
    public void proceedToSpinCheckTest2(){
        slots.setBetPlaced(5);
        player.setCash(3);
        Assert.assertFalse(slots.proceedToSpinCheck(5,player));
    }

    @Test
    public void updatePlayerCashAfterBettingTest(){
        slots.updatePlayerCashAfterBetting(player,5);
        Assert.assertEquals(495, player.getCash());
    }

    @Test
    public void notEnoughCashTest(){
        slots.setBetPlaced(5);
        Assert.assertFalse(slots.notEnoughCash(player));
    }

    @Test
    public void notEnoughCashTest2(){
        slots.setBetPlaced(5);
        player.setCash(3);
        Assert.assertTrue(slots.notEnoughCash(player));
    }

    @Test
    public void playerIsBrokeTest(){
        player.setCash(0);
        Assert.assertTrue(slots.playerIsBroke(player));
    }

    @Test
    public void playerIsBrokeTest2(){
        Assert.assertFalse(slots.playerIsBroke(player));
    }

    @Test
    public void isValidBetTest(){
        slots.setBetPlaced(5);
        player.setCash(3);
        Assert.assertFalse(slots.isValidBet(player,5));
    }

    @Test
    public void isValidBetTest2(){
        player.setCash(0);
        Assert.assertFalse(slots.isValidBet(player,5));
    }

    @Test
    public void isValidBetTest3(){
        slots.isValidBet(player, 5);
        Assert.assertEquals(495, player.getCash());
    }

    @Test
    public void isValidBetTest4(){
        Assert.assertTrue(slots.isValidBet(player, 5));
    }

    @Test
    public void setReelTest(){
        Assert.assertNotNull(slots.setReel());
    }

    @Test
    public void tallyReelResultsTest(){
        String[] displayReel = new String[]{"CHERRY","BAR","SEVEN"};
        int[] actual = slots.tallyReelResults(displayReel);
        Assert.assertEquals("[1, 1, 1, 0, 0, 0]",Arrays.toString(actual));
    }
}
