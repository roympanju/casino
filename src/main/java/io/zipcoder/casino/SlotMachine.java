package io.zipcoder.casino;

import io.zipcoder.AbstractClasses.Game;
import io.zipcoder.Interfaces.Gamble;

import java.util.Arrays;
import java.util.Scanner;


//public class SlotMachine extends Game implements Gamble {
//
//    private String[] reelArray = new String[]{"CHERRY", "CHERRY", "CHERRY", "BAR", "BAR", "SEVEN", "SEVEN", "BONUS", "BONUS", "WILD", "JACKPOT"};
//    private String[] displayReel = new String[3];
//    private int betPlaced = 0;
//    private int jackpot = 500;
//    private int[] resultCounter = new int[6];
//    private int spinsRemainingOnMultiplier = 0;
//
//    public SlotMachine(Player[] players) {
//        super(players);
//    }
//
//    public void bet(int betAmount, Player player) {
//            switch (betAmount) {
//                case 1:
//                    if (betAmountIsOneAndNotEnoughCash(player)) {
//                        exit();
//                    } else {
//                        player.setCash(player.getCash() - betAmount);
//                        betPlaced = 1;
//                    }
//                    break;
//                case 3:
//                    if (notEnoughCash(player)) {
//                        play();
//                    } else {
//                        player.setCash(player.getCash() - betAmount);
//                        betPlaced = 3;
//                    }
//                    break;
//                case 5:
//                    if (notEnoughCash(player)) {
//                        play();
//                    } else {
//                        player.setCash(player.getCash() - betAmount);
//                        betPlaced = 5;
//                    }
//                    break;
//                default:
//                    System.out.println("Not a valid bet. Please try again.");
//            }
//        }
//
//
//    public boolean betAmountIsOneAndNotEnoughCash(Player player) {
//        boolean conditionMet = false;
//        if (player.getCash() < 1) {
//            System.out.println("Sorry, it looks like you are out of money. Goodbye.");
//            conditionMet = true;
//        }
//        return conditionMet;
//    }
//
//    public boolean notEnoughCash(Player player) {
//        boolean conditionMet = false;
//        if (player.getCash() < betPlaced) {
//            System.out.println("Sorry, you do not have enough to place this bet.");
//            conditionMet = true;
//        }
//        return conditionMet;
//    }
//
//    public void setReel() {
//        for (int i = 0; i < displayReel.length; i++) {
//            displayReel[i] = reelArray[(int) (Math.random() * reelArray.length)];
//            System.out.println("** " + displayReel[i] + " **   ");
//        }
//        jackpot += betPlaced;
//    }
//
//    public void tallyReelResults(String[] displayReel) {
//        Arrays.fill(resultCounter, 0);
//        for (String element : displayReel) {
//            if (element.equals("CHERRY")) {
//                resultCounter[0]++;
//            } else if (element.equals("BAR")) {
//                resultCounter[1]++;
//            } else if (element.equals("SEVEN")) {
//                resultCounter[2]++;
//            } else if (element.equals("BONUS")) {
//                resultCounter[3]++;
//            } else if (element.equals("WILD")) {
//                resultCounter[4]++;
//            } else if (element.equals("JACKPOT")) {
//                resultCounter[5]++;
//            }
//        }
//    }
//
//    public void payOut(Player player) {
//        if (resultCounter[5] == 3) { //3 jackpots
//            jackpot = 500;
//            player.setCash(player.getCash() + jackpot);
//            System.out.println("Jackpot! +" + jackpot + " credits!");
//        } else if (resultCounter[3] == 3) { //3 bonuses
//            System.out.println("3x Multiplier for 5 spins!");
//            spinsRemainingOnMultiplier += 5;
//        } else if (resultCounter[4] == 3) {//3 wilds
//            player.setCash(player.getCash() + (30 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 30 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[2] == 3) { //3 sevens
//            player.setCash(player.getCash() + (25 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 25 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[2] == 2 && resultCounter[4] == 1) { //2 sevens + wild
//            player.setCash(player.getCash() + (18 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 18 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[1] == 3) { //3 bars
//            player.setCash(player.getCash() + (14 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 14 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[1] == 2 && resultCounter[4] == 1) { //2 bars + wild
//            player.setCash(player.getCash() + (10 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 10 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[1] == 1 && resultCounter[4] == 2) { //bar + 2 wilds
//            player.setCash(player.getCash() + (7 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 7 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[0] == 3) {//3 cherries
//            player.setCash(player.getCash() + (4 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 4 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[0] == 2 && resultCounter[4] == 1) { //2 cherries + wild
//            player.setCash(player.getCash() + (2 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 2 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        } else if (resultCounter[0] == 1 && resultCounter[4] == 2) {//cherry + 2 wilds
//            player.setCash(player.getCash() + (1 * bonusIfMultiplierActive() * betPlaced));
//            System.out.println("+" + 1 * bonusIfMultiplierActive() * betPlaced + " credits!");
//        }
//        spinsRemainingOnMultiplier--;
//        Arrays.fill(resultCounter,0);
//    }
//
//    public int bonusIfMultiplierActive() {
//        int bonus = 1;
//        if (isMultiplierActive()) {
//            bonus = 3;
//        }
//        return bonus;
//    }
//
//    public boolean isMultiplierActive() {
//        boolean multiplierIsActive = false;
//        if (spinsRemainingOnMultiplier > 0)
//            multiplierIsActive = true;
//        return multiplierIsActive;
//    }
//
//    public boolean winCondition(boolean isWin) {
//        return true;
//    }
//
//    public boolean exit() {
//        return true;
//    }
//
//    public void play() {
//        do {
//            for (Player player : players) {
//                if (isMultiplierActive()) {
//                    System.out.println("Bonus multiplier: " + spinsRemainingOnMultiplier + " spins remaining");
//                }
//                System.out.println("Current jackpot: " + jackpot + ".\nCredits Remaining: " + player.getCash() + "\nPlace your bet: 1, 3, or 5.");
//                bet(getIntInput(), player);
//                spin(player);
//            }
//        } while (continuePlaying());
//        exit();
//    }
//
//    public void spin(Player player) {
//        setReel();
//        tallyReelResults(displayReel);
//        payOut(player);
//    }
//
//    public boolean continuePlaying() {
//        boolean continueToPlay = false;
//        boolean userInputNeeded = true;
//        while (userInputNeeded) {
//            System.out.println("Would you like to play again? (y/n)");
//            String userInput = getStringInput();
//            if (userInput.equalsIgnoreCase("y")) {
//                continueToPlay = true;
//                userInputNeeded = false;
//            } else if (userInput.equalsIgnoreCase("n")) {
//                continueToPlay = false;
//                userInputNeeded = false;
//            } else {
//                errorMessage();
//            }
//        }
//        return continueToPlay;
//    }
//
//    public String getStringInput() {
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();
//    }
//
//    public int getIntInput() {
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextInt();
//    }
//
//    public void errorMessage() {
//        System.out.println("Huh, what was that?");
//    }
//}
//
//
//
