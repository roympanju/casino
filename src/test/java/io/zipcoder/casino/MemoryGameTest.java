package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;
public class MemoryGameTest {

    @Test
    public void testTable(){
        String[] expected = {"spade", "3"};
        MemoryGame memoryGame = new MemoryGame();
        String[] actual = memoryGame.table();
        UnitTestingUtils.assertArrayEquality(expected, actual);


    }
    @Test
    public void testGuess(){
        boolean expected = true;
        MemoryGame memoryGame = new MemoryGame();
        String[] actual = memoryGame.guess();

        assertEquals( expected,  actual);

    }

}
