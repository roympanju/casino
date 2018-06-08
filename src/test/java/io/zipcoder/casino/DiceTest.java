package io.zipcoder.casino;

import org.junit.Test;

public class DiceTest {

    @Test
    public void testroll()
    {
        Dice die = new Dice();
        int actual = die.roll();
        assertTrue(rand.nextInt(numOfDice* 6) + 1);


    }


}*/
