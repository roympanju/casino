package io.zipcoder.AbstractClasses;


import io.zipcoder.Interfaces.Playable;
import io.zipcoder.casino.Player;

import java.util.ArrayList;

public abstract class Game implements Playable {
    public Player[] players;

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
