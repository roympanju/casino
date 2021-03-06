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
       hand.clear();
    }

    public int getHandSize() { return hand.size(); }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean elim) {
        eliminated = elim;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}

