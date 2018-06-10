package io.zipcoder.casino;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class DiceTest {

    @Test
    public void rollTest() {
        Dice die = new Dice();
        int min = 1;
        int max = 6;
        int actual = die.roll();
        assertTrue(min <= actual && max >= actual);
    }

}
