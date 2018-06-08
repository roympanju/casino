package io.zipcoder.casino;

public class Dice {

    int numOfDice;



    public int roll(){
        return (int) ((Math.random() * 6 ) + 1);

    }
}
