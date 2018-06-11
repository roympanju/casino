package io.zipcoder.casino;
import io.zipcoder.AbstractClasses.CardGame;
import java.util.*;

public class BlackJack extends CardGame{
    private Scanner scanner;
    private String wait;
    private Player house;
    private int roundCount = 1;
    private boolean playing = true;
    private boolean round = true;

    public BlackJack(Player[] players) {
        super(players, 1);
        house = new Player("House", 100000);
    }

    public void play() {
        System.out.println("Welcome to BlackJack!");
        while (round) {
            if (checkBroke()) exit();
            else {
                constructDeck();

                System.out.println("Type exit to return to Casino, or any key to begin round.");
                scanner = new Scanner(System.in);
                if (playerExits()) exit();
                else playBlackJack();
            }
        }
    }

    private boolean checkBroke() {
        for (Player player : players) {
            if (player.getCash() == 0) {
                System.out.println(player.getName() + " too broke to play, good-bye.");
                return true;
            }
        }
        return false;
    }

    private boolean playerExits() {
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("exit");
    }

    private void playBlackJack() {
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
        playerTurns();
        houseTurn();
        winCondition();
    }

    private void playerTurns() {
        for (Player player : players) {
            player.setEliminated(false);
            if (!blackJack(getHandValue(player.getHand()))) {
                turn(player);
            }
        }
    }

    private void turn(Player player) {
        playing = true;
        if (!blackJack(getHandValue(player.getHand()))) {
            while (playing) {
                playing = playerLogic(player);
            }
        } else {
            System.out.println("BlackJack!");
        }
    }

    private boolean playerLogic(Player player) {
        System.out.println(player.getName() + ": hit, fold, or stay?");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("hit")) {
            hit(player);
            if (getHandValue(player.getHand()) > 21) {
                System.out.println("Bust!");
                player.setEliminated(true);
                return false;
            } else if (blackJack(getHandValue(player.getHand()))) {
                System.out.println("Congratulations " + player.getName() + " BlackJack!!!");
                return false;
            }

        } else if (input.equalsIgnoreCase("fold")) {
            player.setEliminated(true);
            return false;
        } else if (input.equalsIgnoreCase("stay")) return false;

        return true;
    }

    private void houseTurn() {
        playing = true;

        System.out.println("House turn:");
        System.out.println(displayHands(house));
        waitForPlayers();
        while(playing) {
            playing = houseLogic();
            waitForPlayers();
        }
    }

    private boolean houseLogic() {
        if (blackJack(getHandValue(house.getHand()))) {
            for (Player player : players) {
                player.setEliminated(true);
            }
            System.out.println("House BlackJack!");
            return false;
        } else if (getHandValue(house.getHand()) < 17) {
            System.out.println("House Hits");
            houseHit(house);
            return true;
        } else if (getHandValue(house.getHand()) < 21) {
            System.out.println("House Stays.");
            return false;
        } else {
            System.out.println("Bust!");
            house.setEliminated(true);
            return false;
        }
    }

    private void waitForPlayers() {
        System.out.println("Press any key to continue");
        wait = scanner.next();
    }

    private void bettingPhase(){
        System.out.println("Reminder, no change allowed whilst betting!");
        for (Player player : players) {
            System.out.println(player.getName() + " please place your bet.");
            bet(scanner.nextInt(), player);
        }
    }
    public void bet(int bet, Player player) {
        if (player.getCash() <= bet) {
            bet = allIn(player);
        } else if (bet == 0 || bet == 1) {
            System.out.println("That's cute...");
            bet = allIn(player);
        } else {
            player.setCash(player.getCash() - bet);
        }
        System.out.println("$" + bet + " bet placed.");
        setPot(bet);
    }

    private int allIn(Player player) {
        int x;
        System.out.println("All In!!!");
        x = player.getCash();
        player.setCash(0);
        return x;
    }

    private void houseHit(Player player) {
        player.addCardToHand(deck[0].draw());
        System.out.println("house hand:");
        System.out.println(displayHands(house));

    }

    private void hit(Player player) {
        player.addCardToHand(deck[0].draw());
        System.out.println("current hands:");
        System.out.println(displayHands());
    }

    private void roundEnd() {
        for (Player player : players) {
            discardPlayerHand(player);
        }
        discardPlayerHand(house);
        dumpPot();
        roundCount++;
    }

    private boolean blackJack(int handVal){
        return handVal == 21;
    }


    private int findWinningHand() {
        int max = 0;
        for (int i = 0; i < players.length; i++) {
            if (!players[i].isEliminated()) {
                if (getHandValue(players[i].getHand()) > max) {
                    max = i;
                }
            }
        }
        return max;
    }

    public boolean winCondition() {
        int winner = findWinningHand();
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

    public void exit(){ round = false; }

}
