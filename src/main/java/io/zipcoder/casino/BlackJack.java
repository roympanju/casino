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

    public BlackJack(Player[] players) {
        super(players, 1);
        house = new Player("House", 100000);
    }

    public void play(){
        System.out.println("Welcome to BlackJack!");
        while (round) {
            for(Deck e : deck) {
                e.shuffle();
            }
            System.out.println("Type exit to return to Casino, or any key to begin round.");
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) exit();
            else playBlackJack();
        }
    }

    public void playBlackJack() {
        System.out.println("Round " + roundCount);

        bettingPhase();
        System.out.println("Current pot value: $" + getPot());
        waitForPlayers();

        deal(2, house);
        System.out.println("Current hands: ");
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
        winCondition();
    }

    public void turn(Player player) {
        String input;
        playing = true;
        if (!blackJack(getHandValue(player.getHand()))) {

            while (playing) {
                System.out.println(player.getName() + ": hit, fold, or stay?");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("hit")) {
                    hit(player);
                    if (getHandValue(player.getHand()) > 21) {
                        System.out.println("Bust!");
                        playing = false;
                        player.setEliminated(true);
                    } else if (blackJack(getHandValue(player.getHand()))) {
                        System.out.println("Congratulations " + player.getName() + " BlackJack!!!");
                        payOut(player);
                        playing = false;
                        roundEnd();
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

    public void hit(Player player) {
        player.addCardToHand(deck[0].draw());
        System.out.println("current hands:");
        System.out.println(displayHands());
    }

    public void roundEnd() {
        for (Player player : players) {
            discardHand(player);
        }
        discardHand(house);
        dumpPot();
        roundCount++;
    }

    public void exit(){ round = false; }

    public void bet(int bet, Player player) {
        if (player.getCash() <= bet) {
            System.out.println("All In!!!");
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
        int[] results;
        int winner;
        for (int i = 0; i < players.length; i++) {
            if (!players[i].isEliminated()) {
                if (getHandValue(players[i].getHand()) > winner) {
                    winner = i;
                }
            }
        }
        System.out.println(players[winner].getName() + " wins $" + getPot());
        if (!players[winner].isEliminated()) payOut(players[winner]);
        else System.out.println("all players eliminated, house takes the pot.");
        roundEnd();
        return true;
    }

    public void payOut(Player player){
        player.setCash(player.getCash() + getPot());
    }

}
