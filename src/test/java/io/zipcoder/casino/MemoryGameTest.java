package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;
public class MemoryGameTest {

    @Test
    public void testIsMatch1(){
        boolean expected = false;
        Player[]  newPlayer = new Player[1];
        Card c1 = new Card("spade", "4");
        Card c2 = new Card("club", "2");
        MemoryGame mg = new MemoryGame(4,newPlayer,4);


        boolean actual = mg.isMatch(c1, c2);
        Assert.assertEquals(expected, actual);



    }
    @Test
    public void testIsCardListNull(){
        boolean expected = false;
        Player[] p1 = new Player[1];
        MemoryGame memoryGame = new MemoryGame(4, p1, 4);
        boolean actual = memoryGame.iscardListNull();
        Assert.assertEquals( expected,  actual);



    }

}
