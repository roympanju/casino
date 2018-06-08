package io.zipcoder.AbstractClasses;


import io.zipcoder.Interfaces.Playable;
import io.zipcoder.casino.Player;

public abstract class Game implements Playable {
    protected Player[] players;

    private int winnings;

    public Game(Player[] players){
        this.players = players;
    }
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
