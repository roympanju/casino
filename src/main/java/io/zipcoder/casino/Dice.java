package io.zipcoder.casino;
 import java.util.Random;

public class Dice {
    int numOfDice;

    public Dice(int numOfDice)
    {
        numOfDice = 1;
    }

    public int roll(){


        Random rand = new Random();

        int  n = rand.nextInt(numOfDice* 6) + 1;
        return n;


    }

}
