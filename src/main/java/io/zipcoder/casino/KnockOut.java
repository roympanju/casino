package io.zipcoder.casino;

import java.util.Scanner;

public class KnockOut implements Game{
    Dice[] dice = new Dice[2];
    Player[] players;
    Player house;
    int houseRollValue;
    int[] rollValues;
    boolean toRoll = false;
    Scanner kb;

    public KnockOut(Player[] players) {
        this.players = players;
        rollValues = new int[players.length];
        house = new Player("House", 1000000);
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Dice();
        }
    }


    public int getPlayerRollValue() {
        int rollVal = 0;
        for (Dice die : dice) {
            rollVal += die.roll();
        }
        return rollVal;

    }

    public void play() {
        kb = new Scanner(System.in);

        for (int i = 0; i < players.length; i++) {
            //if(!players[i].isEliminated()) {
                System.out.println(players[i].getName() + " please press enter to roll");
                toRoll = kb.nextLine().equals("\n");
                if (toRoll) rollValues[i] = getPlayerRollValue();
                //else players[i].setEliminated();
                System.out.println(players[i].getName() + " rolled " + rollValues[i]);
            //}
            houseRollValue = getPlayerRollValue();
            System.out.println(house.getName() + " rolled " + houseRollValue);

        }


    }

    public  void exit(){


    }

    public Boolean winCondition(){
        return false;
    }




//    public boolean compareRoll(){
//        playerRoll = getPlayerRollValues();
//        houseRoll = getPlayerRollValues();
//        if (playerRoll == playerDeclaredNum && ){
//
//        }
//
//        return false;

}
