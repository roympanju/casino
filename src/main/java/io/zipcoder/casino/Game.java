package io.zipcoder.casino;

public abstract class Game implements Playable{
    private Player[] players;
    private int winnings;

    public int getWinnings() {
        return winnings;
    }

    public void setWinnings(int winnings) {
        this.winnings = winnings;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
