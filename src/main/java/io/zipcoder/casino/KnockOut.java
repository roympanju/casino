package io.zipcoder.casino;

import io.zipcoder.AbstractClasses.Game;
import io.zipcoder.Interfaces.Gamble;

import java.util.ArrayList;
import java.util.Scanner;


public class KnockOut extends Game implements Gamble {

    private Dice[] dice = new Dice[2];
    private Player[] players;
    private ArrayList<Player> playersList;
    private Player house;
    private int houseRollValue;
    private int houseNumber;
    private int pot = 0;
    private int bettings;
    private int[] rollValues;
    private boolean toRoll = false;
    boolean isPlay = true;
    private Scanner kb;

    
    public KnockOut(Player[] players) {
        super(players);
        this.players = players;
        rollValues = new int[players.length];
        house = new Player("House", 1000000);
        houseNumber = (int)((Math.random()*12)+7);
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Dice();
        }
    }

    public void playerArrayList(){
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
        playerArrayList();
        kb = new Scanner(System.in);
        boolean[] winCon = new boolean[playersList.size()];
        int plNumber[] = new int[playersList.size()];
        bettingPhase();
        System.out.println(getPot());
        if (playersList.size() == 1){
            playersList.add(house);
            plNumber = new int[playersList.size()];
            rollValues = new int[playersList.size()];

            while (playersList.size() == 2) {
                //for (int i = 0; i < 2; i++) {
                    if (plNumber[0] == 0) {
                        plNumber[0] = getPlayerNumber(playersList.get(0).getName());
                        plNumber[1] = houseNumber;
                    }
                    System.out.println(playersList.get(0).getName() + " press any key to roll");
                    toRoll = kb.nextLine().equals("\n");
                    rollValues[0] = getPlayerRollValue();
                    rollValues[1] = getHouseRollValue();

                    if (rollValues[0] == plNumber[0]) {
                        System.out.println(playersList.get(0).getName() + " you lost try harder next time");
                        playersList.remove(0);
                        System.out.println(playersList.size());


                        if (playersList.size() == 1) {

                            System.out.println("Congratulations " + playersList.get(0).getName() + " you have won " +
                                    "you have won $" + getPot() * 2);

                        }
                    }
               // }
            }
        }
        else {
            while (playersList.size() > 1) for (int i = 0; i < playersList.size(); i++) {
                if (plNumber[i] == 0) {
                    plNumber[i] = getPlayerNumber(playersList.get(i).getName());
                }

                System.out.println(playersList.get(i).getName() + " press any key to roll");
                toRoll = kb.nextLine().equals("\n");
                rollValues[i] = getPlayerRollValue();

                System.out.println(playersList.get(i).getName() + " rolled " + rollValues[i]);
                if (rollValues[i] != plNumber[i]) {
                    System.out.println(rollValues[i] + " is not equal to " + plNumber[i]);
                }

                if (rollValues[i] == plNumber[i]) {
                    System.out.println(playersList.get(i).getName() + " you lost try harder next time");
                    playersList.remove(i);
                    System.out.println(playersList.size());


                    if (playersList.size() == 1) {

                        System.out.println("Congratulations " + playersList.get(0).getName() + " you have won " +
                                "you have won $" + getPot());

                    }
                }

//            if (playersList.size() == 1){
//                System.out.println("Congratulations " + playersList.get(i).getName() + " you have won " +
//                        "you have won $" + getPot());
//            }


            }
        }


    }

    public int getHouseRollValue(){
        houseRollValue = getPlayerRollValue();
        System.out.println(house.getName() + " rolled " + houseRollValue);
        System.out.println("The house set number is "+ houseNumber);
        return houseRollValue;
    }

    public int getPlayerNumber(String playerName){
        System.out.println(playerName + " Please choose your number between 6 and 12:");
        int number = kb.nextInt();
        while(number <= 6 || number >= 12) {
            System.out.println("number should be greater than 6 and less than 12");
            number = kb.nextInt();
        }

        return number;
    }

    public void exit() {

        isPlay = false;
    }

    public boolean winCondition() {
        return true;

    }

    public boolean winnerIs(boolean a,  boolean b){
        if ((a && !b)) return a;
        else if (!a && b) return b;
        return false;
    }

    public void bet(int bet, Player player){
        if(bet > player.getCash()) System.out.println("Sorry you don't have enough money for this bet");
        else if (bet == player.getCash()) {
            System.out.println("You are all in bet carefully");
            player.setCash(0);
        }
        else {
            System.out.println("your bet is $" + bet);
            player.setCash(player.getCash() - bet);
        }


    }

    public void bettingPhase(){
        for (Player player : players) {
            System.out.println(player.getName() + " please place your bet.");
            bettings = kb.nextInt();
            setPot();
            bet(bettings, player);
        }
    }


    public void payOut(Player player){

        player.setCash(player.getCash() + getPot());


    }

    public void setPot() {
        pot += bettings;
    }
    public void dumpPot() {
        pot = 0;
    }
    public int getPot() {
        return pot;
    }


}

