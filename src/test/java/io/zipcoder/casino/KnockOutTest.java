package io.zipcoder.casino;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class KnockOutTest {

    ArrayList<Player> players = new ArrayList<Player>();

    Player player1 = new Player("Roy",1000);
    Player player2 = new Player("Froilan",1000);
    Player[] players1 = {player1, player2};
    KnockOut knockOut = new KnockOut(players1);
    //ArrayList<Player> playersList ;


    @Test
    public void isEqualToTest(){
        boolean actual = false;
        boolean expected =knockOut.isEqualTo(10, 20);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addPlayerToArrayListTest(){
        players.add(player1);
        //players = knockOut.addHouseAsPlayer(players);
        int actual = 1;
        int expected  = players.size();
        Assert.assertEquals(expected, actual);

    }

}



