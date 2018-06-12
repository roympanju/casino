package io.zipcoder.casino;

import io.zipcoder.AbstractClasses.Game;
import io.zipcoder.Interfaces.Gamble;
import io.zipcoder.Console.KnockOutConsole;
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
    private int[] plNumber;
    private boolean toRoll = false;
    boolean isPlay = true;
    private Scanner kb;
    KnockOutConsole console = new KnockOutConsole();
    
    public KnockOut(Player[] players) {
        super(players);
        this.players = players;
        rollValues = new int[players.length];
        house = new Player("House", 1000000);
        houseNumber = (int)((Math.random()*6)+7);
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
        plNumber = new int[playersList.size()];
        bettingPhase();
        if (playersList.size() == 1){
            addHouseAsPlayer(playersList);
            while (playersList.size() == 2) {
                onePlayer();
            }
        }
        else {
            while (playersList.size() > 1) {
                multiplePlayers();
            }
        }
    }

    public ArrayList<Player> addHouseAsPlayer(ArrayList<Player> player){
        player.add(house);
        plNumber = new int[playersList.size()];
        rollValues = new int[playersList.size()];
        return player;
    }

    public int onePlayer(){
        if (plNumber[0] == 0) {
            plNumber[0] = getPlayerNumber(playersList.get(0).getName());
            plNumber[1] = houseNumber;
        }
        console.rollPrompt(playersList.get(0).getName());
        toRoll = kb.nextLine().equals("\n");
        rollValues[0] = getPlayerRollValue();
        rollValues[1] = getHouseRollValue();

        if (isEqualTo(rollValues[0], plNumber[0])) {
            console.displayRolledNumber(playersList.get(0).getName(), rollValues[0]);
            console.loseMessageDisplay(playersList.get(0).getName() );
            playersList.remove(0);

            if (isEqualTo(playersList.size(), 1)) {
                console.winnerMessage(getPot()*2, playersList.get(0).getName());
            }
        }
        return getPot();
    }

    public int multiplePlayers(){
        for (int i = 0; i < playersList.size(); i++) {
            if (plNumber[i] == 0) {
                plNumber[i] = getPlayerNumber(playersList.get(i).getName());
            }
            console.rollPrompt(playersList.get(i).getName());
            toRoll = kb.nextLine().equals("\n");
            rollValues[i] = getPlayerRollValue();

            console.displayRolledNumber(playersList.get(i).getName(), rollValues[i]);
            if (!(isEqualTo(rollValues[i], plNumber[i]))) {
                console.displayCheekyMessage();
            }

            else{
                console.loseMessageDisplay(playersList.get(i).getName());
                playersList.remove(i);

                if (isEqualTo(playersList.size(), 1)) {
                    console.winnerMessage(getPot(), playersList.get(0).getName());
                }
            }
        }
        return getPot();
    }

    public int getHouseRollValue(){
        houseRollValue = getPlayerRollValue();
        console.displayRolledNumber(house.getName(), houseRollValue);
        console.displayHouseSetNumber(houseNumber);
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

    public boolean isEqualTo(int a, int b){
        if(a == b){
            return true;
        }
        return false;
    }

    public void exit() {

        isPlay = false;
    }

    public boolean winCondition() {
        return true;
    }

    public void bet(int bet, Player player){
        if(bet > player.getCash()) console.promptOverBetting();
        else if (bet == player.getCash()) {
            console.promptBettingAll();
            player.setCash(0);
        }
        else {
            console.displayBet(bet);
            player.setCash(player.getCash() - bet);
        }
    }

    public void bettingPhase(){
        for (Player player : players) {
            console.promptPlaceBet(player.getName());
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

