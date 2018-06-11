package io.zipcoder.casino;
import io.zipcoder.AbstractClasses.CardGame;
import java.util.*;

public class BlackJack extends CardGame{
    Scanner scanner;
    String wait;
    Player house;
    int roundCount = 1;
    boolean playing = true;
    boolean round = true;
    boolean broke = false;

    public BlackJack(Player[] players) {
        super(players, 1);
        house = new Player("House", 100000);
    }

    public void play(){
        System.out.println("Welcome to BlackJack!");
        while (round) {
            for (Player player : players) {
                if (player.getCash() == 0) {
                    System.out.println(player.getName() + " too broke to play, good-bye.");
                    exit();
                    broke = true;
                }
            }

            if (!broke) {
                constructDeck();

                System.out.println("Type exit to return to Casino, or any key to begin round.");
                scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) exit();
                else playBlackJack();
            }
        }
    }

    public void playBlackJack() {
        System.out.println("Round " + roundCount);

        bettingPhase();
        System.out.println("Current pot value: $" + getPot());
        waitForPlayers();

        deal(2, house);
        System.out.println("Current hands:");
        System.out.println(displayHands());
        waitForPlayers();
        wait = scanner.nextLine();

        System.out.println("Begin player turns.");
        for (Player player : players) {
            player.setEliminated(false);
            if (!blackJack(getHandValue(player.getHand()))) {
                turn(player);
            }
        }
        houseTurn();
        winCondition();
    }

    public void turn(Player player) {
        String input;
        playing = true;
        if (!blackJack(getHandValue(player.getHand()))) {

            while (playing) {
                System.out.println(player.getName() + ": hit, fold, or stay?");
                input = scanner.nextLine();
//Need to break this into smaller methods, just looks like shit for now. Clean Code v2 amirite???!?!?!?!!
                if (input.equalsIgnoreCase("hit")) {
                    hit(player);
                    if (getHandValue(player.getHand()) > 21) {
                        System.out.println("Bust!");
                        playing = false;
                        player.setEliminated(true);
                    } else if (blackJack(getHandValue(player.getHand()))) {
                        System.out.println("Congratulations " + player.getName() + " BlackJack!!!");
                        playing = false;
                    }
                } else if (input.equalsIgnoreCase("fold")) {
                    playing = false;
                    player.setEliminated(true);
                } else if (input.equalsIgnoreCase("stay")) playing = false;
            }
        } else {
            System.out.println("BlackJack!");
        }
    }

    public void houseTurn() {
        String input;
        playing = true;

        System.out.println("house turn:");
        System.out.println(displayHands(house));
        waitForPlayers();
        while(playing) {
            if (blackJack(getHandValue(house.getHand()))) {
                for (Player player : players) {
                    player.setEliminated(true);
                }
                System.out.println("House BlackJack!");
                playing = false;
            } else if (getHandValue(house.getHand()) < 17) {
                System.out.println("House Hits");
                houseHit(house);
                waitForPlayers();
            } else if (getHandValue(house.getHand()) < 21) {
                System.out.println("House Stays.");
                playing = false;
                waitForPlayers();
            } else {
                System.out.println("Bust!");
                house.setEliminated(true);
                playing = false;
                waitForPlayers();
            }
        }
    }

    public void waitForPlayers() {
        System.out.println("Press any key to continue");
        wait = scanner.next();
    }

    public void bettingPhase(){
        System.out.println("Reminder, no change allowed whilst betting!");
        for (Player player : players) {
            System.out.println(player.getName() + " please place your bet.");
            bet(scanner.nextInt(), player);
        }
    }

    public void houseHit(Player player) {
        player.addCardToHand(deck[0].draw());
        System.out.println("house hand:");
        System.out.println(displayHands(house));

    }

    public void hit(Player player) {
        player.addCardToHand(deck[0].draw());
        System.out.println("current hands:");
        System.out.println(displayHands());
    }

    public void roundEnd() {
        for (Player player : players) {
            discardPlayerHand(player);
        }
        discardPlayerHand(house);
        dumpPot();
        roundCount++;
    }

    public void exit(){ round = false; }

    public void bet(int bet, Player player) {
        if (player.getCash() <= bet) {
            System.out.println("All In!!!");
            bet = player.getCash();
            player.setCash(0);
        } else if (bet == 0 || bet == 1) {
            System.out.println("That's cute... All In!!!");
            bet = player.getCash();
            player.setCash(0);
        } else {
            player.setCash(player.getCash() - bet);
        }
        System.out.println("$" + bet + " bet placed.");
        setPot(bet);
    }

    public boolean blackJack(int handVal){
        if (handVal == 21) return true;
        return false;
    }

    public boolean winCondition() {
        int winner = 0;
        for (int i = 0; i < players.length; i++) {
            if (!players[i].isEliminated()) {
                if (getHandValue(players[i].getHand()) > winner) {
                    winner = i;
                }
            }
        }
        if (!players[winner].isEliminated()) {
            System.out.println(players[winner].getName() + " wins $" + getPot());
            payOut(players[winner]);
        }
        else System.out.println("House Wins!");
        roundEnd();
        return true;
    }

    public void payOut(Player player){
        player.setCash(player.getCash() + getPot());
    }

}
