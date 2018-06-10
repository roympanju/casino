package io.zipcoder.casino;

import io.zipcoder.AbstractClasses.Game;

import java.util.ArrayList;
import java.util.Scanner;


public class KnockOut extends Game {

    private Dice[] dice = new Dice[2];
    private Player[] players;
    private ArrayList<Player> playersList;
    private Player house;
    private int houseRollValue;
    private int houseNumber;
    private int[] rollValues;
    private boolean toRoll = false;
    private Scanner kb;

    
    public KnockOut(Player[] players) {
        super(players);
        rollValues = new int[players.length];
        house = new Player("House", 1000000);
        houseNumber = (int)((Math.random()*12)+7);
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Dice();
        }
    }

    public void playerArrayList(Player[] players){
        playersList = new ArrayList<Player>();
        for (int i=0; i<players.length; i++){
            playersList.add(i, players[i]);
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
        playerArrayList(players);
        kb = new Scanner(System.in);
        boolean isPlay = true;
        boolean[] winCon = new boolean[playersList.size()];
        int plNumber[] = new int[playersList.size()];

        while(playersList.size() > 1) for (int i = 0; i < playersList.size(); i++) {
            if(plNumber[i] == 0){
                plNumber[i] = getPlayerNumber();
            }

            System.out.println(playersList.get(i).getName() + " press any key to roll");
            toRoll = kb.nextLine().equals("\n");
            rollValues[i] = getPlayerRollValue();

            System.out.println(playersList.get(i).getName() + " rolled " + rollValues[i]);
            if (rollValues[i] != plNumber[i]) {
                winCon[i] = true;
                System.out.println(rollValues[i] + " is not equal to " + plNumber[i]);
            }
            else {
                System.out.println(playersList.get(i).getName() + " you lost try harder next time");
                playersList.remove(i);
                winCon[i] = false;
                isPlay = false;
            }

        }
        getHouseRollValue();

    }

    public void getHouseRollValue(){
        houseRollValue = getPlayerRollValue();
        System.out.println(house.getName() + " rolled " + houseRollValue);
        System.out.println("The house set number is "+ houseNumber);
    }

    public int getPlayerNumber(){
        System.out.println("Please choose your number between 6 and 12:");
        int number = kb.nextInt();
        while(number <= 6 || number >= 12) {
            System.out.println("number should be greater than 6 and less than 12");
            number = kb.nextInt();
        }

        return number;
    }

    public boolean exit() {

        return false;
    }

    public boolean winCondition(boolean a) {
        return a;

    }

    public boolean winnerIs(boolean a,  boolean b){
        if ((a && !b)) return a;
        else if (!a && b) return b;
        return false;
    }


}

