package io.zipcoder.casino;

import io.zipcoder.AbstractClasses.Game;

import java.util.Scanner;

public class KnockOut extends Game {
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
                System.out.println(players[i].getName() + " press any key to roll");
                toRoll = kb.nextLine().equals("\n");
                rollValues[i] = getPlayerRollValue();
                //else players[i].setEliminated();
                System.out.println(players[i].getName() + " rolled " + rollValues[i]);
            //}

        }
        houseRollValue = getPlayerRollValue();
        System.out.println(house.getName() + " rolled " + houseRollValue);
    }
    public void exit() {


    }
    public boolean winCondition() {
        return false;
    }
}
