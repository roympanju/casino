package io.zipcoder.casino;


import java.util.Scanner;

public class Casino {
    private Player [] players;
    //Game gameBeingPlayed;
    private Scanner scanner;

    public void main() {

        System.out.println("Hello!");
        players = new Player[getNumberOfPlayers()];
        for (int i = 0; i < players.length; i++) {
            players[i] = createPlayer();
        }
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
        return new Player(name,cash);

    }
    private Game selectGame() {
        scanner = new Scanner(System.in);
        System.out.println("What game would you like to play?");
        String input = scanner.nextLine();
        Game game = null;
        switch (input) {
            case "black jack":  game = new BlackJackGame();
                break;
            case "memory" :  game = new MemoryGame();
                break;
            case "knock out" :  game = new KnockOutGame();
                break;
            default: System.out.println("Enter black jack, memory, or knock out.");

        }
        return game;




    }


}
