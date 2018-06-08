package io.zipcoder.casino;
import java.util.ArrayList;

public class Player {
    private int cash;
    private String name;
    ArrayList<Card> hand;


    public Player(String name,int cash) {
           this.name = name;
           this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void addCardToHand(Card cardToAdd){
        hand.add(cardToAdd);
    }

    public Card getPlayersCard(int index){
        return hand.get(index);
    }
}

