package io.zipcoder.casino;
import io.zipcoder.AbstractClasses.CardGame;
import io.zipcoder.Console.BlackJackConsole;

public class BlackJack extends CardGame{
    private Player house;
    private int roundCount = 1;
    private boolean playing = true;
    private boolean round = true;
    private BlackJackConsole display;

    public BlackJack(Player[] players) {
        super(players, 1);
        house = new Player("House", 100000);
        display = new BlackJackConsole();
    }

    public void play() {
        display.welcome();
        while (round) {
            if (checkBroke()) exit();
            else {
                constructDeck();
                if (playerExits(display.mainMenu())) exit();
                else playBlackJack();
            }
        }
    }

    private boolean checkBroke() {
        for (Player player : players) {
            if (player.getCash() == 0) {
                display.brokePlayer(player);
                return true;
            }
        }
        return false;
    }

    private boolean playerExits(String input) {
        return input.equalsIgnoreCase("exit");
    }

    private void playBlackJack() {
        display.roundStart(roundCount);

        bettingPhase();
        display.potValue(getPot());
        display.waitForPlayers();

        deal(2, house);
        display.playerHands(formatHands());
        display.waitForPlayers();

        display.beginPlayerTurns();
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
            display.natural();
        }
    }

    private boolean playerLogic(Player player) {
        String input = display.collectPlayerInput(player);
        if (input.equalsIgnoreCase("hit")) {
            hit(player);
            if (getHandValue(player.getHand()) > 21) {
                display.bust();
                player.setEliminated(true);
                return false;
            } else if (blackJack(getHandValue(player.getHand()))) {
                display.blackJackAchieved(player.getName());
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
        display.houseHand(formatHands(house));
        display.waitForPlayers();
        while(playing) {
            playing = houseLogic();
            display.waitForPlayers();
        }
    }

    private boolean houseLogic() {
        if (blackJack(getHandValue(house.getHand()))) {
            for (Player player : players) {
                player.setEliminated(true);
            }
            display.blackJackAchieved(house.getName());
            return false;
        } else if (getHandValue(house.getHand()) < 17) {
            houseHit(house);
            display.houseHits();
            display.houseHand(formatHands(house));
            return true;
        } else if (getHandValue(house.getHand()) < 21) {
            display.houseStays();
            return false;
        } else {
            display.bust();
            house.setEliminated(true);
            return false;
        }
    }

    private void bettingPhase() {
        display.startBetting();
        for (Player player : players) {
            bet(display.betting(player), player);
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
        display.betPlaced(bet);
        setPot(bet);
    }

    private int allIn(Player player) {
        int x;
        display.allIn();
        x = player.getCash();
        player.setCash(0);
        return x;
    }

    private void houseHit(Player player) {
        player.addCardToHand(deck[0].draw());
        display.houseHand(formatHands(house));

    }

    private void hit(Player player) {
        player.addCardToHand(deck[0].draw());
        display.playerHands(formatHands());
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
            display.winner(players[winner].getName(), getPot());
            payOut(players[winner]);
        }
        else if (houseIsWinner(winner)) display.houseWins();
        else display.houseWins();
        roundEnd();
        return true;
    }

    public boolean houseIsWinner(int winner) {
        if (!house.isEliminated())
        return getHandValue(house.getHand()) > getHandValue(players[winner].getHand());
        else return false;
    }

    public void payOut(Player player){
        player.setCash(player.getCash() + getPot());
    }

    public void exit(){ round = false; }

}
