package io.zipcoder.Console;

import java.util.Scanner;
import io.zipcoder.casino.Player;

public class BlackJackConsole {
    private Scanner scanner;
    private String wait;

    public BlackJackConsole() {
        scanner = new Scanner(System.in);
    }

    private void print(String message) {
        System.out.println(message);
    }

    public void waitForPlayers() {
        print("Press any key to continue");
        wait = scanner.nextLine();
    }

    public void welcome() {
        print("Welcome to BlackJack!");
    }

    public String mainMenu() {
        print("Type exit to return to Casino, or any key to begin round.");
        return scanner.nextLine();
    }

    public void brokePlayer(Player player) {
        print(player.getName() + " too broke to play, good-bye.");
    }

    public void roundStart(int roundCount) {
        print("Round " + roundCount);
    }

    public void beginPlayerTurns() {
        print("Begin player turns.");
    }

    public void potValue(int pot) {
        print("Current pot value: $" + pot);
    }

    public void startBetting() {
        print("Reminder, no change allowed whilst betting!");
    }

    public int betting (Player player) {
        print(player.getName() + " please place your bet.");
        return scanner.nextInt();
    }

    public void betPlaced(int bet) {
        print("$" + bet + " bet placed.");
    }

    public void allIn() {
        print("All In!!!");
    }

    public void playerHands(String hands) {
        print("Current hands:");
        print(hands);
    }

    public void houseHand(String hand) {
        print("House hand:");
        print(hand);

    }

    public String collectPlayerInput(Player player) {
        print(player.getName() + ": hit, fold, or stay?");
        return scanner.nextLine();
    }

    public void bust() {
        print("Bust!");
    }

    public void blackJackAchieved(String name) {
        print("Congratulations " + name + " BlackJack!!!");
    }

    public void houseHits() {
        print("House Hits \n");
    }

    public void houseStays() {
        print("House Stays.");
    }

    public void winner(String name, int money) {
        print(name + " wins $" + money);
    }

    public void houseWins() {
        print("House Wins!");
    }

    public void natural() {
        print("BlackJack!");
    }

}
