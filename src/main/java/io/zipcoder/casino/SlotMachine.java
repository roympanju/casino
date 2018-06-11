package io.zipcoder.casino;

import io.zipcoder.AbstractClasses.Game;
import io.zipcoder.Console.SlotMachineConsole;
import io.zipcoder.Interfaces.Gamble;

import java.util.Arrays;
import java.util.Scanner;


public class SlotMachine extends Game implements Gamble {

    private String[] reelArray = new String[]{"CHERRY", "CHERRY", "CHERRY", "BAR", "BAR", "SEVEN", "SEVEN", "BONUS", "BONUS", "WILD", "JACKPOT"};
    private int betPlaced = 0;
    private int jackpot = 500;
    private int spinsRemainingOnMultiplier = 0;
    private String spinResult = "";
    SlotMachineConsole console = new SlotMachineConsole();




    public SlotMachine(Player[] players) {
        super(players);
    }

    public void setSpinsRemainingOnMultiplier(int numberOfSpins) {spinsRemainingOnMultiplier = numberOfSpins;}
    public void setBetPlaced(int betAmount) {betPlaced = betAmount;}
    public void setJackpot(int amount){this.jackpot = amount;}
    public void setSpinResult(String spinResult){this.spinResult = spinResult;}
    public int getBetPlaced(){return betPlaced;}

    public void play() {
        do {
            for (Player player : players) {
                console.numberOfMultiplierSpinsRemaining(isMultiplierActive(), spinsRemainingOnMultiplier);
                bet(jackpot,player);
                if(proceedToSpinCheck(console.getIntInput(), player)) {
                    scoreResults(player);
                    decreaseSpinsOnMultiplier();
                    payOut(player);
                    increaseJackpot();
                }else{
                    break;
                }
            }
        } while (continuePlaying());
        exit();
    }

    public boolean continuePlaying() {
        boolean continueToPlay = false;
        boolean userInputNeeded = true;
        while (userInputNeeded) {
            String userInput = console.checkIfPlayAgain();
            if (userInput.equalsIgnoreCase("y")) {
                continueToPlay = true;
                userInputNeeded = false;
            } else if (userInput.equalsIgnoreCase("n")) {
                continueToPlay = false;
                userInputNeeded = false;
            } else {
                console.errorMessage();
            }
        }
        return continueToPlay;
    }

    public void bet(int jackpot,Player player){
        console.betPrompt(jackpot, player);
    }

    public boolean isMultiplierActive() {
        boolean multiplierIsActive = false;
        if (spinsRemainingOnMultiplier > 0)
            multiplierIsActive = true;
        return multiplierIsActive;
    }

    public int bonusIfMultiplierActive() {
        int bonus = 1;
        if (isMultiplierActive()) {
            bonus = 3;
        }
        return bonus;
    }

    public boolean proceedToSpinCheck(int betAmount, Player player) {
        boolean enoughToProceed = false;
        if (betAmount == 1 || betAmount == 3 || betAmount == 5) {
            enoughToProceed = isValidBet(player, betAmount);
        } else {
            console.invalidBet();
        }
        return enoughToProceed;
    }

    public void updatePlayerCashAfterBetting(Player player, int betAmount) {
        player.setCash(player.getCash() - betAmount);
        setBetPlaced(betAmount);
    }

    public boolean notEnoughCash(Player player) {
        boolean conditionMet = false;
        if (player.getCash() < getBetPlaced()) {
            console.betIsHigherThanRemainingMoney();
            conditionMet = true;
        }
        return conditionMet;
    }

    public boolean playerIsBroke(Player player) {
        boolean conditionMet = false;
        if (player.getCash() == 0) {
            console.moneyRemainingAtZero();
            conditionMet = true;
        }
        return conditionMet;
    }

    public boolean isValidBet(Player player, int betAmount) {
        boolean enoughMoneyToProceed = true;
        if (playerIsBroke(player)) {
            enoughMoneyToProceed = false;
            exit();
        } else if (notEnoughCash(player)) {
            enoughMoneyToProceed = false;
        } else {
            updatePlayerCashAfterBetting(player, betAmount);
        }
        return enoughMoneyToProceed;
    }

    public String[] setReel() {
        String[] displayReel = new String[3];
        for (int i = 0; i < displayReel.length; i++) {
            displayReel[i] = reelArray[(int) (Math.random() * reelArray.length)];
            console.printReel(displayReel,i);
        }
        return displayReel;
    }

    public void increaseJackpot(){
        setJackpot(this.jackpot += getBetPlaced());
    }

    public int[] tallyReelResults(String[] displayReel) {
        int[] resultCounter = new int[6];
        Arrays.fill(resultCounter, 0);
        for (String element : displayReel) {
            if (element.equals("CHERRY")) {
                resultCounter[0]++;
            } else if (element.equals("BAR")) {
                resultCounter[1]++;
            } else if (element.equals("SEVEN")) {
                resultCounter[2]++;
            } else if (element.equals("BONUS")) {
                resultCounter[3]++;
            } else if (element.equals("WILD")) {
                resultCounter[4]++;
            } else if (element.equals("JACKPOT")) {
                resultCounter[5]++;
            }
        }
        return resultCounter;
    }

    public String winningCombinations(int[] resultCounter, Player player) {
        String winningCombo = "";
        if (resultCounter[5] == 3) {
            player.setCash(player.getCash() + jackpot);
            console.jackpotMessage(jackpot);
            setJackpot(500);
            } else if (resultCounter[3] == 3) {
                console.multiplierMessage();
                setSpinsRemainingOnMultiplier(5);
            } else if (resultCounter[4] == 3) {
                winningCombo = "3W";
            } else if (resultCounter[2] == 3) {
                winningCombo = "3S";
            } else if (resultCounter[2] == 2 && resultCounter[4] == 1) {
                winningCombo = "2S/W";
            } else if (resultCounter[1] == 3) {
                winningCombo = "3B";
            } else if (resultCounter[1] == 2 && resultCounter[4] == 1) {
                winningCombo = "2B/W";
            } else if (resultCounter[1] == 1 && resultCounter[4] == 2) {
                winningCombo = "B/2W";
            } else if (resultCounter[0] == 3) {
                winningCombo = "3C";
            } else if (resultCounter[0] == 2 && resultCounter[4] == 1) {
                winningCombo = "2C/W";
            } else if (resultCounter[0] == 1 && resultCounter[4] == 2) {
                winningCombo = "C/2W";
            }
        return winningCombo;
        }

    public void payOut(Player player) {
        int payOutAmount = creditsAwarded(creditsWon(spinResult));
        updatePlayerCash(player,payOutAmount);
    }

    public void updatePlayerCash(Player player, int amount){
        player.setCash(player.getCash() + amount);
    }

    public int creditsWon(String winningCombo) {
        SlotsPayOut slotsPayOut = new SlotsPayOut();
        return slotsPayOut.getValue(winningCombo);
    }

    public int creditsAwarded(int creditsWon){
        console.creditsAwarded(creditsWon);
        return creditsWon * bonusIfMultiplierActive() * getBetPlaced();
    }

    public void scoreResults(Player player) {
        setSpinResult(winningCombinations(tallyReelResults(setReel()),player));
    }

    public void decreaseSpinsOnMultiplier(){
        if(spinsRemainingOnMultiplier>0){
            spinsRemainingOnMultiplier--;
        }
    }
    //wip
    public boolean winCondition() {
        return true;
    }

    //wip
    public void exit() {

    }
}



