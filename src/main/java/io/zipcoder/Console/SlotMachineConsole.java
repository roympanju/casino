package io.zipcoder.Console;

import io.zipcoder.casino.Player;

import java.util.Scanner;

public class SlotMachineConsole {
    public void numberOfMultiplierSpinsRemaining(boolean isMultiplierActive, int spinsRemainingOnMultiplier) {
        if (isMultiplierActive) {
            System.out.println("Bonus multiplier: " + spinsRemainingOnMultiplier + " spins remaining");
        }
    }

    public void betPrompt(int jackpot, Player player) {
        System.out.println("Current jackpot: " + jackpot + ".\nCredits Remaining: " + player.getCash() + "\nPlace your bet: 1, 3, or 5.");
    }

    public void invalidBet() {
        System.out.println("Not a valid bet. Please try again.");
    }

    public void betIsHigherThanRemainingMoney() {
        System.out.println("Sorry, you do not have enough to place this bet.");
    }

    public void moneyRemainingAtZero() {
        System.out.println("Sorry, it looks like you are out of money. Goodbye.");
    }

    public void printReel(String[] displayReel, int i) {
        System.out.println("** " + displayReel[i] + " **   ");
    }

    public void jackpotMessage(int jackpot) {
        System.out.println("Jackpot! +" + jackpot + " credits!");
    }

    public void multiplierMessage() {
        System.out.println("3x Multiplier for 5 spins!");
    }

    public void creditsAwarded(int amountWon){
        System.out.println("+" + amountWon+ " credits!");
    }

    public void errorMessage() {
        System.out.println("Huh, what was that?");
    }

    public String checkIfPlayAgain(){
        System.out.println("Would you like to play again? (y/n)");
        return getStringInput();
    }

    public String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
