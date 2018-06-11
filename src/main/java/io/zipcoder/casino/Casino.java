package io.zipcoder.casino;


import io.zipcoder.AbstractClasses.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Casino {
    private Player[] players;
    //Game gameBeingPlayed;
    private Scanner scanner;

    public void main() {

        System.out.println("Hello!");
        int numOfPlayers = getNumberOfPlayers();
        players = new Player[numOfPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = createPlayer();
        }

        System.out.println(players.toString());

        playGame();
    }

    public int getNumberOfPlayers() {
        scanner = new Scanner(System.in);
        System.out.println("Enter number of players.");
        return scanner.nextInt();
    }

    public Player createPlayer(){
        String name;
        int cash;
        scanner = new Scanner(System.in);
        System.out.println("Name of player:");
        name = scanner.nextLine();
        System.out.println("How much money you got??");
        cash = scanner.nextInt();
        return new Player(name, cash);
    }

    private Game selectGame() {
        scanner = new Scanner(System.in);
        System.out.println("Game options: KnockOut, Memory, BlackJack");
        System.out.println("Please choose a game!");
        String input = scanner.nextLine();
        Game game;

        if (input.equalsIgnoreCase("blackjack")) game = new BlackJack(players);
        else if (input.equalsIgnoreCase("knockout")) game = new KnockOut(players);
        else if (input.equalsIgnoreCase("memory")) game = new MemoryGame(2, players, 4);
        //else if (input.equalsIgnoreCase("slots")) game = new SlotMachine(players);
        else {
            System.out.println("Game not recognized, how about some Knockout!");
            game = new KnockOut(players);
        }

        return game;
    }

    private void playGame() {
        do {
            selectGame().play();
        } while (continuePlaying());
    }

    private Boolean continuePlaying() {
        boolean continuePlaying = false;
        System.out.println("Would you like to play another game?");
        if (scanner.nextLine().equals("yes")) {
            continuePlaying = true;
        } else {
            System.out.println("Are you sure you want to leave?");
            if (scanner.nextLine().equals("yes")) {
                exitMessage();
            } else {
                continuePlaying = true;
            }
        }
        return continuePlaying;
    }

    private void exitMessage(){
        System.out.println("Thanks for playing " );
        for(Player player: players){
            System.out.println(player.getName());
        }

    }
}
