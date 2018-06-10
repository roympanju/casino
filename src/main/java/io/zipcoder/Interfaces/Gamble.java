package io.zipcoder.Interfaces;
import io.zipcoder.casino.Player;

public interface Gamble {
    public void bet(int bet, Player player);
    public void payOut(Player player);

}
