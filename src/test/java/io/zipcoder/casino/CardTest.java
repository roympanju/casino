package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void getSuitTest(){
        Card card = new Card("HEARTS","FIVE");
        String actual = card.getSuit();
        Assert.assertEquals("HEARTS",actual);
    }

    @Test
    public void getValueTest(){
        Card card = new Card("HEARTS","FIVE");
        String actual = card.getValue();
        Assert.assertEquals("FIVE",actual);
    }

    @Test
    public void getIntValueTest(){
        Card card = new Card("HEARTS","FIVE");
        Assert.assertEquals(5,card.getIntValue());
    }
}
