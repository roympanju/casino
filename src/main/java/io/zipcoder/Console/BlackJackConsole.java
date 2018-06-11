package io.zipcoder.Console;

import java.util.Scanner;
import io.zipcoder.casino.Player;

public class BlackJackConsole {
    private Scanner scanner;
    private String wait;

    public BlackJackConsole() {
        scanner = new Scanner(System.in);
    }

    public void waitForPlayers() {
        System.out.println("Press any key to continue");
        wait = scanner.next();
    }

    public void welcome() {
        System.out.println("Welcome to BlackJack!");
    }

    public String mainMenu() {
        System.out.println("Type exit to return to Casino, or any key to begin round.");
        return scanner.nextLine();
    }

    public void brokePlayer(Player player) {
        System.out.println(player.getName() + " too broke to play, good-bye.");
    }

    public void roundStart(int roundCount) {
        System.out.println("Round " + roundCount);
    }

    public void startBetting() {
        System.out.println("Reminder, no change allowed whilst betting!");
    }

    public int betting (Player player) {
        System.out.println(player.getName() + " please place your bet.");
        return scanner.nextInt();
    }

    public void betPlaced(int bet) {
        System.out.println("$" + bet + " bet placed.");
    }

    public void allIn() {
        System.out.println("All In!!!");
    }

    public void playerHands(String hands) {
        System.out.println("current hands:");
        System.out.println(hands);
    }

    public void houseHand(String hand) {
        System.out.println("house hand:");
        System.out.println(hand);

    }


}
