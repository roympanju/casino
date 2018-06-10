package io.zipcoder.casino;
import java.util.ArrayList;

public class Player {
    private boolean eliminated = false;
    private int cash;
    private String name;
    ArrayList<Card> hand = new ArrayList<Card>();


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

    public Card getCard(int index){
        return hand.get(index);
    }

    public void discardHand() {
       for (int i = 0; i < getHandSize(); i++)  {
           hand.remove
       }
    }
    public int getHandSize() { return hand.size(); }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated() {
        eliminated = true;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}

