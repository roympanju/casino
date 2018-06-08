package io.zipcoder.casino;


import io.zipcoder.AbstractClasses.Game;

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
        return new Player(name,cash);
    }

    private Game selectGame() {
        scanner = new Scanner(System.in);
        System.out.println("What game would you like to play?");
        String input = scanner.nextLine();
        Game game = null;
//        switch (input) {
//            //case "black jack":  game = new BlackJackGame(players);
//              //  break;
//            //case "memory" :  game = new MemoryGame(players);
//               // break;
//            case "knock out" :  game = new KnockOut(players);
//                break;
//            default: System.out.println("Enter black jack, memory, or knock out.");
//        }
        System.out.println("All games broken, please type: KnockOut");
        game = scanner.nextLine().equalsIgnoreCase("knockout") ? new KnockOut(players) : null;
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
